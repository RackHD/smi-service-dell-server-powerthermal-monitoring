/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.dell.isg.aps.powerthermal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.dell.isg.aps.powerthermal.common.UriConstants;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@EnableSwagger2
@ComponentScan("com.dell.isg.aps.powerthermal")
public class ServicePowerThermalMonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicePowerThermalMonitoringApplication.class, args);
    }


    @Bean
    public Docket anewsApipi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("powerthermal").apiInfo(apiInfo()).select().paths(regex("/api.*")).build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("SMI Microservice :  Power and Thermal ").description("Power and Thermal Microservice collect power monitoring and consumption on compute node using industry standard WS-Management - WSMAN Protocol").termsOfServiceUrl("http://www.dell.com/smi/powermonitoring").license("Dell SMI License Version 1.0").licenseUrl("www.dell.com/smi").version("1.0 dev").build();
    }

}
