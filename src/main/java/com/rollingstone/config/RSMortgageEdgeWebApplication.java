package com.rollingstone.config;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableJpaRepositories("com.rollingstone.persistence.dao")
@EntityScan("com.rollingstone.persistence.model")
@ComponentScan(basePackages = { "com.rollingstone" })
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients(basePackages = {"com.rollingstone.service"})
public class RSMortgageEdgeWebApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(RSMortgageEdgeWebApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);

    }
}