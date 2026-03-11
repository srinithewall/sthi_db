// Triggering re-index for STS
package com.sthi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableCaching
@SpringBootApplication
public class SthiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SthiApplication.class, args);

	}

}
