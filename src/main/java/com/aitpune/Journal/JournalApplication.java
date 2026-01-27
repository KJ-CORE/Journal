package com.aitpune.Journal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournalApplication {

	public static void main(String[] args) {

        SpringApplication.run(JournalApplication.class, args);
	}
    @Bean
    public CommandLineRunner checkConfig(
            @Value("${spring.data.mongodb.uri:NOT_FOUND}") String mongoUri
    ) {
        return args -> {
            System.out.println("=================================");
            System.out.println("MongoDB URI: " + mongoUri);
            System.out.println("=================================");
        };
    }
    @Bean
    public PlatformTransactionManager add(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
