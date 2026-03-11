-- Sthirvaa Phase 1: Data Stabilization & Scraper Safety
-- Run this script on the 'sthi' database (MySQL 8.0)

START TRANSACTION;

-- 1. Normalize price_unit values
-- Standardizing values to ('L', 'Lac', 'Cr', 'Thou')
UPDATE re_properties 
SET price_unit = TRIM(price_unit);

UPDATE re_properties 
SET price_unit = 'Lac' 
WHERE price_unit IN ('L', 'l', 'Lac', 'lac');

UPDATE re_properties 
SET price_unit = 'Cr' 
WHERE price_unit IN ('CR', 'Cr', 'cr');

UPDATE re_project_unit_types 
SET price_unit = TRIM(price_unit);

UPDATE re_project_unit_types 
SET price_unit = 'Lac' 
WHERE price_unit IN ('L', 'l', 'Lac', 'lac');

UPDATE re_project_unit_types 
SET price_unit = 'Cr' 
WHERE price_unit IN ('CR', 'Cr', 'cr');


-- 2. Fix re_project_advisors UNIQUE KEY bug
-- Current unique key is on project_id only. We need (project_id, advisor_user_id)
ALTER TABLE re_project_advisors DROP INDEX UK5dmvf6h9vmd51jg9rwls4ofnq;
ALTER TABLE re_project_advisors ADD UNIQUE INDEX uk_project_advisor (project_id, advisor_user_id);


-- 3. Prepare re_projects for Source Tracking
-- Updating source_type to be more structured and adding is_scraped flag
ALTER TABLE re_projects 
MODIFY COLUMN source_type ENUM('MANUAL', 'SCRAPER', 'API', 'IMPORT') DEFAULT 'MANUAL',
ADD COLUMN is_scraped BIT DEFAULT b'0',
ADD COLUMN scraper_source VARCHAR(255) DEFAULT NULL;

-- Backfill existing source_type if it was varchar
UPDATE re_projects SET source_type = 'MANUAL' WHERE source_type IS NULL OR source_type = '';


-- 4. Create re_projects_staging table
-- This mirrors re_projects and allows safe scraper ingestion
CREATE TABLE IF NOT EXISTS re_projects_staging LIKE re_projects;
ALTER TABLE re_projects_staging ADD COLUMN merge_status ENUM('PENDING', 'MERGED', 'SKIPPED', 'REJECTED') DEFAULT 'PENDING';


-- 5. Merge re_locations into re_project_locations
-- re_project_locations has 'location_type' which re_locations lacks.
-- First, ensure all projects in re_locations have a type (defaulting to 'PROJECT')
INSERT IGNORE INTO re_project_locations (
    address_line, area, city, created_at, latitude, location_type, longitude, project_id, updated_at, zone
)
SELECT 
    address_line, area, city, created_at, latitude, 'PROJECT', longitude, project_id, updated_at, zone
FROM re_locations;

-- After verification, re_locations can be dropped (Manual step recommended)
-- DROP TABLE re_locations;

COMMIT;
