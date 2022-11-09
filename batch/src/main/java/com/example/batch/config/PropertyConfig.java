package com.example.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({PropertyLocal.class, PropertyDev.class, })
@Configuration
public class PropertyConfig {
}


