package com.sthi.signature.config;

import com.sthi.signature.repository.AgreementTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Slf4j
@Component
@RequiredArgsConstructor
public class SeedDataRunner implements CommandLineRunner {

        private final AgreementTemplateRepository templateRepository;
        private final DataSource dataSource;

        @Override
        public void run(String... args) throws Exception {
                if (templateRepository.count() == 0) {
                        log.info("Seeding initial AgreementTemplate data...");
                        try (Connection connection = dataSource.getConnection()) {
                                ScriptUtils.executeSqlScript(
                                                connection,
                                                new ClassPathResource("rental-template-data.sql"));
                                log.info("Seeding complete.");
                        } catch (Exception e) {
                                log.error("Failed to seed initial data", e);
                        }
                }
        }
}
