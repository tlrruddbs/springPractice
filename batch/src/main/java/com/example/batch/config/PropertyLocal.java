package com.example.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile(value="local")
@PropertySource({"classpath:local/application.properties"})
class PropertyLocal {

}
