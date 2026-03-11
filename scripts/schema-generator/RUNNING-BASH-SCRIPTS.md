RUNNING BASH SCRIPTS (Schema Generator)

Environment: Windows
Shell: Git Bash
Project Type: Spring Boot (Java)
Use Case: Running internal schema generator scripts

1. Purpose of This Document

This document explains how to safely run Bash (.sh) scripts in this project, specifically the Schema Generator scripts used to generate:

JPA entity classes

DTO classes

# Go to project root
cd /c/SpringBoot/WorkSpace/Sthi

# One-time: make script executable
chmod +x scripts/schema-generator/create-re-entities.sh

# Run schema generator
./scripts/schema-generator/create-re-entities.sh


Expected Console Output (Example)
🚀 Starting RE Schema Generator...
✅ Generated: Developer (Entity + DTO)
✅ Generated: ProjectType (Entity + DTO)
✅ Generated: Amenity (Entity + DTO)
✅ Generated: UnitType (Entity + DTO)
✅ Generated: Project (Entity + DTO)
🎉 RE Schema Generation Completed Successfully


(Optional) Start Spring Boot
mvn clean spring-boot:run
