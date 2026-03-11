package com.sthi.re.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sthi.re.dto.response.ProjectCardResponse;
import com.sthi.re.model.Project;

public interface ProjectCardRepository extends JpaRepository<Project, Long> {

	@Query("""
			select new com.sthi.re.dto.response.ProjectCardResponse(
			    p.projectId,
			    p.projectName,
			    l.city,
			    l.area,
			    min(ut.priceMin),
			    max(ut.priceMax),
			    ut.priceUnit,
			    case when p.reraStatus = 1 then true else false end,
			    cs.label,
			    p.possessionDate,
			    d.developerName,
			    pt.name,
			    pi.imageUrl
			)
			from Project p
			left join Location l on l.projectId = p.projectId
			left join Developer d on d.developerId = p.developerId
			left join ProjectType pt on pt.projectTypeId = p.projectTypeId
			left join ConstructionStatus cs on cs.constructionStatusId = p.constructionStatusid
			left join ProjectUnitType ut on ut.projectId = p.projectId
			left join ProjectImage pi on pi.projectId = p.projectId and pi.sortOrder = 1
			where p.status = 1 AND p.isVerified = 1
			group by
			    p.projectId,
			    p.projectName,
			    l.city,
			    l.area,
			    ut.priceUnit,
			    p.reraStatus,
			    cs.label,
			    p.possessionDate,
			    d.developerName,
			    pt.name,
			    pi.imageUrl
			""")
	List<ProjectCardResponse> findProjectCards();

	@Query("""
			select new com.sthi.re.dto.response.ProjectCardResponse(
			    p.projectId,
			    p.projectName,
			    l.city,
			    l.area,
			    min(ut.priceMin),
			    max(ut.priceMax),
			    ut.priceUnit,
			    case when p.reraStatus = 1 then true else false end,
			    cs.label,
			    p.possessionDate,
			    d.developerName,
			    pt.name,
			    pi.imageUrl
			)
			from Project p
			left join Location l on l.projectId = p.projectId
			left join Developer d on d.developerId = p.developerId
			left join ProjectType pt on pt.projectTypeId = p.projectTypeId
			left join ConstructionStatus cs on cs.constructionStatusId = p.constructionStatusid
			left join ProjectUnitType ut on ut.projectId = p.projectId
			left join ProjectImage pi on pi.projectId = p.projectId and pi.sortOrder = 1
			where p.status = 1 AND p.isVerified = 0
			group by
			    p.projectId,
			    p.projectName,
			    l.city,
			    l.area,
			    ut.priceUnit,
			    p.reraStatus,
			    cs.label,
			    p.possessionDate,
			    d.developerName,
			    pt.name,
			    pi.imageUrl
			""")
	List<ProjectCardResponse> findUnverifiedProjectCards();

	@Query("""
			select new com.sthi.re.dto.response.ProjectCardResponse(
			    p.projectId,
			    p.projectName,
			    l.city,
			    l.area,
			    min(ut.priceMin),
			    max(ut.priceMax),
			    ut.priceUnit,
			    case when p.reraStatus = 1 then true else false end,
			    cs.label,
			    p.possessionDate,
			    d.developerName,
			    pt.name,
			    pi.imageUrl
			)
			from Project p
			left join Location l on l.projectId = p.projectId
			left join Developer d on d.developerId = p.developerId
			left join ProjectType pt on pt.projectTypeId = p.projectTypeId
			left join ConstructionStatus cs on cs.constructionStatusId = p.constructionStatusid
			left join ProjectUnitType ut on ut.projectId = p.projectId
			left join ProjectImage pi on pi.projectId = p.projectId and pi.sortOrder = 1
			where p.status = 1
			group by
			    p.projectId,
			    p.projectName,
			    l.city,
			    l.area,
			    ut.priceUnit,
			    p.reraStatus,
			    cs.label,
			    p.possessionDate,
			    d.developerName,
			    pt.name,
			    pi.imageUrl
			""")
	List<ProjectCardResponse> findAllProjectCards();

}
