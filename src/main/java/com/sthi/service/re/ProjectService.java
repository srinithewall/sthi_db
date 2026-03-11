package com.sthi.service.re;

import com.sthi.dto.request.ApiResponse;
import com.sthi.dto.re.request.CreateProjectRequest;
import com.sthi.dto.re.request.ImageRequest;
import com.sthi.dto.re.request.UnitTypeRequest;
import com.sthi.dto.re.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class ProjectService {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Transactional
    public ApiResponse<ProjectResponse> createProject(CreateProjectRequest request) {
        try {
            // Validate price range
            if (request.getPriceMin().compareTo(request.getPriceMax()) > 0) {
                return ApiResponse.error("Minimum price cannot be greater than maximum price");
            }
            
            // Check if project with same name already exists
            String checkProjectSql = "SELECT COUNT(*) FROM re_projects WHERE project_name = ?";
            Integer projectCount = jdbcTemplate.queryForObject(checkProjectSql, Integer.class, request.getProjectName());
            if (projectCount != null && projectCount > 0) {
                return ApiResponse.error("Project with this name already exists");
            }
            
            // Check if RERA number already exists (if provided)
            if (request.getReraNumber() != null && !request.getReraNumber().trim().isEmpty()) {
                String checkReraSql = "SELECT COUNT(*) FROM re_projects WHERE rera_number = ?";
                Integer reraCount = jdbcTemplate.queryForObject(checkReraSql, Integer.class, request.getReraNumber());
                if (reraCount != null && reraCount > 0) {
                    return ApiResponse.error("RERA number already exists");
                }
            }
            
            // 1. Process Developer (find or create)
            DeveloperResponse developer = findOrCreateDeveloper(request.getDeveloperName());
            
            // 2. Process Location (find or create)
            LocationResponse location = findOrCreateLocation(
                request.getCity(),
                request.getZone(),
                request.getLocationArea(),
                request.getState(),
                request.getCountry()
            );
            
            // 3. Process Project Type (find or create)
            String propertyType = findOrCreateProjectType(request.getPropertyType());
            
            // 4. Insert Project
            Long projectId = insertProject(request, developer.getDeveloperId(), location.getLocationId(), propertyType);
            
            // 5. Insert Images (synchronous)
            if (request.getImages() != null && !request.getImages().isEmpty()) {
                insertImages(projectId, request.getImages());
            }
            
            // 6. Insert Unit Types
            if (request.getUnitTypes() != null && !request.getUnitTypes().isEmpty()) {
                insertUnitTypes(projectId, request.getUnitTypes());
            }
            
            // 7. Insert Amenities
            if (request.getAmenities() != null && !request.getAmenities().isEmpty()) {
                insertAmenities(projectId, request.getAmenities());
            }
            
            // 8. Fetch and return complete project
            ProjectResponse project = getProjectWithDetails(projectId);
            
            return ApiResponse.success("Project created successfully", project);
            
        } catch (DuplicateKeyException e) {
            return ApiResponse.error("Duplicate entry found: " + e.getMessage());
        } catch (DataAccessException e) {
            return ApiResponse.error("Database error: " + e.getMostSpecificCause().getMessage());
        } catch (Exception e) {
            return ApiResponse.error("Error creating project: " + e.getMessage());
        }
    }
    
    private DeveloperResponse findOrCreateDeveloper(String developerName) {
        String findSql = "SELECT developer_id, developer_name, description, website_url, contact_email, contact_phone " +
                        "FROM re_developers WHERE developer_name = ?";
        
        try {
            return jdbcTemplate.queryForObject(findSql, (rs, rowNum) -> 
                DeveloperResponse.builder()
                    .developerId(rs.getLong("developer_id"))
                    .developerName(rs.getString("developer_name"))
                    .description(rs.getString("description"))
                    .websiteUrl(rs.getString("website_url"))
                    .contactEmail(rs.getString("contact_email"))
                    .contactPhone(rs.getString("contact_phone"))
                    .build(), developerName);
        } catch (Exception e) {
            // Developer doesn't exist, create new one
            String insertSql = "INSERT INTO re_developers (developer_name, created_at) VALUES (?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, developerName);
                ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
                return ps;
            }, keyHolder);
            
            Long developerId = keyHolder.getKey().longValue();
            
            return DeveloperResponse.builder()
                    .developerId(developerId)
                    .developerName(developerName)
                    .build();
        }
    }
    
    private LocationResponse findOrCreateLocation(String city, String zone, String area, String state, String country) {
        String findSql = "SELECT location_id, city, zone, area, state, country " +
                        "FROM re_locations WHERE city = ? AND zone = ? AND area = ? AND state = ? AND country = ?";
        
        try {
            return jdbcTemplate.queryForObject(findSql, (rs, rowNum) -> 
                LocationResponse.builder()
                    .locationId(rs.getLong("location_id"))
                    .city(rs.getString("city"))
                    .zone(rs.getString("zone"))
                    .area(rs.getString("area"))
                    .state(rs.getString("state"))
                    .country(rs.getString("country"))
                    .build(), city, zone, area, state, country);
        } catch (Exception e) {
            // Location doesn't exist, create new one
            String insertSql = "INSERT INTO re_locations (city, zone, area, state, country) VALUES (?, ?, ?, ?, ?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, city);
                ps.setString(2, zone);
                ps.setString(3, area);
                ps.setString(4, state);
                ps.setString(5, country);
                return ps;
            }, keyHolder);
            
            Long locationId = keyHolder.getKey().longValue();
            
            return LocationResponse.builder()
                    .locationId(locationId)
                    .city(city)
                    .zone(zone)
                    .area(area)
                    .state(state)
                    .country(country)
                    .build();
        }
    }
    
    private String findOrCreateProjectType(String typeName) {
        String findSql = "SELECT type_name FROM re_project_types WHERE type_name = ?";
        
        try {
            return jdbcTemplate.queryForObject(findSql, String.class, typeName);
        } catch (Exception e) {
            // Project type doesn't exist, create new one
            String insertSql = "INSERT INTO re_project_types (type_name) VALUES (?)";
            jdbcTemplate.update(insertSql, typeName);
            return typeName;
        }
    }
    
    private Long insertProject(CreateProjectRequest request, Long developerId, Long locationId, String propertyType) {
        // First get type_id
        String getTypeIdSql = "SELECT type_id FROM re_project_types WHERE type_name = ?";
        Long typeId = jdbcTemplate.queryForObject(getTypeIdSql, Long.class, propertyType);
        
        String sql = "INSERT INTO re_projects (" +
                    "developer_id, type_id, location_id, project_name, rera_number, " +
                    "status, price_min, price_max, description, cashback_offer, " +
                    "latitude, longitude, created_at" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, developerId);
            ps.setLong(2, typeId);
            ps.setLong(3, locationId);
            ps.setString(4, request.getProjectName());
            ps.setString(5, request.getReraNumber());
            ps.setString(6, request.getStatus());
            ps.setBigDecimal(7, request.getPriceMin());
            ps.setBigDecimal(8, request.getPriceMax());
            ps.setString(9, request.getDescription());
            ps.setString(10, request.getCashbackOffer());
            ps.setBigDecimal(11, request.getLatitude());
            ps.setBigDecimal(12, request.getLongitude());
            ps.setTimestamp(13, Timestamp.valueOf(LocalDateTime.now()));
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey().longValue();
    }
    
    private void insertImages(Long projectId, List<ImageRequest> images) {
        String sql = "INSERT INTO re_project_images (project_id, image_url, is_primary) VALUES (?, ?, ?)";
        
        List<Object[]> batchArgs = new ArrayList<>();
        for (ImageRequest image : images) {
            batchArgs.add(new Object[]{projectId, image.getUrl(), image.getIsPrimary()});
        }
        
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
    
    private void insertUnitTypes(Long projectId, List<UnitTypeRequest> unitTypes) {
        String sql = "INSERT INTO re_unit_types (project_id, unit_name, size_sqft, price) VALUES (?, ?, ?, ?)";
        
        List<Object[]> batchArgs = new ArrayList<>();
        for (UnitTypeRequest unit : unitTypes) {
            batchArgs.add(new Object[]{projectId, unit.getUnitName(), unit.getSizeSqft(), unit.getPrice()});
        }
        
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
    
    private void insertAmenities(Long projectId, List<String> amenities) {
        // First, get or create amenities
        Map<String, Long> amenityMap = new HashMap<>();
        for (String amenityName : amenities) {
            Long amenityId = findOrCreateAmenity(amenityName);
            amenityMap.put(amenityName, amenityId);
        }
        
        // Insert project-amenity relationships
        String sql = "INSERT INTO re_project_amenities (project_id, amenity_id) VALUES (?, ?)";
        
        List<Object[]> batchArgs = new ArrayList<>();
        for (Long amenityId : amenityMap.values()) {
            batchArgs.add(new Object[]{projectId, amenityId});
        }
        
        jdbcTemplate.batchUpdate(sql, batchArgs);
    }
    
    private Long findOrCreateAmenity(String amenityName) {
        String findSql = "SELECT amenity_id FROM re_amenities WHERE amenity_name = ?";
        
        try {
            return jdbcTemplate.queryForObject(findSql, Long.class, amenityName);
        } catch (Exception e) {
            // Amenity doesn't exist, create new one
            String insertSql = "INSERT INTO re_amenities (amenity_name) VALUES (?)";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, amenityName);
                return ps;
            }, keyHolder);
            
            return keyHolder.getKey().longValue();
        }
    }
    
    private ProjectResponse getProjectWithDetails(Long projectId) {
        // Main project query with joins
        String sql = "SELECT " +
                    "p.project_id, p.project_name, p.rera_number, p.status, " +
                    "p.price_min, p.price_max, p.description, p.cashback_offer, " +
                    "p.latitude, p.longitude, p.created_at, " +
                    "d.developer_id, d.developer_name, d.description as dev_description, " +
                    "d.website_url, d.contact_email, d.contact_phone, " +
                    "pt.type_name, " +
                    "l.location_id, l.city, l.zone, l.area, l.state, l.country " +
                    "FROM re_projects p " +
                    "LEFT JOIN re_developers d ON p.developer_id = d.developer_id " +
                    "LEFT JOIN re_project_types pt ON p.type_id = pt.type_id " +
                    "LEFT JOIN re_locations l ON p.location_id = l.location_id " +
                    "WHERE p.project_id = ?";
        
        ProjectResponse project = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            ProjectResponse.ProjectResponseBuilder builder = ProjectResponse.builder();
            
            builder.projectId(rs.getLong("project_id"))
                   .projectName(rs.getString("project_name"))
                   .reraNumber(rs.getString("rera_number"))
                   .status(rs.getString("status"))
                   .priceMin(rs.getBigDecimal("price_min"))
                   .priceMax(rs.getBigDecimal("price_max"))
                   .description(rs.getString("description"))
                   .cashbackOffer(rs.getString("cashback_offer"))
                   .latitude(rs.getBigDecimal("latitude"))
                   .longitude(rs.getBigDecimal("longitude"))
                   .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                   .propertyType(rs.getString("type_name"));
            
            // Set developer info
            DeveloperResponse developer = DeveloperResponse.builder()
                    .developerId(rs.getLong("developer_id"))
                    .developerName(rs.getString("developer_name"))
                    .description(rs.getString("dev_description"))
                    .websiteUrl(rs.getString("website_url"))
                    .contactEmail(rs.getString("contact_email"))
                    .contactPhone(rs.getString("contact_phone"))
                    .build();
            builder.developer(developer);
            
            // Set location info
            LocationResponse location = LocationResponse.builder()
                    .locationId(rs.getLong("location_id"))
                    .city(rs.getString("city"))
                    .zone(rs.getString("zone"))
                    .area(rs.getString("area"))
                    .state(rs.getString("state"))
                    .country(rs.getString("country"))
                    .build();
            builder.location(location);
            
            return builder.build();
        }, projectId);
        
        // Fetch images
        String imageSql = "SELECT image_id, project_id, image_url, is_primary " +
                         "FROM re_project_images WHERE project_id = ?";
        List<ImageResponse> images = jdbcTemplate.query(imageSql, (rs, rowNum) -> 
            ImageResponse.builder()
                .imageId(rs.getLong("image_id"))
                .projectId(rs.getLong("project_id"))
                .imageUrl(rs.getString("image_url"))
                .isPrimary(rs.getBoolean("is_primary"))
                .build(), projectId);
        project.setImages(images);
        
        // Fetch unit types
        String unitSql = "SELECT unit_id, project_id, unit_name, size_sqft, price " +
                        "FROM re_unit_types WHERE project_id = ?";
        List<UnitTypeResponse> unitTypes = jdbcTemplate.query(unitSql, (rs, rowNum) -> 
            UnitTypeResponse.builder()
                .unitId(rs.getLong("unit_id"))
                .projectId(rs.getLong("project_id"))
                .unitName(rs.getString("unit_name"))
                .sizeSqft(rs.getBigDecimal("size_sqft"))
                .price(rs.getBigDecimal("price"))
                .build(), projectId);
        project.setUnitTypes(unitTypes);
        
        // Fetch amenities
        String amenitySql = "SELECT a.amenity_name FROM re_amenities a " +
                           "JOIN re_project_amenities pa ON a.amenity_id = pa.amenity_id " +
                           "WHERE pa.project_id = ?";
        List<String> amenities = jdbcTemplate.queryForList(amenitySql, String.class, projectId);
        project.setAmenities(amenities);
        
        return project;
    }
}