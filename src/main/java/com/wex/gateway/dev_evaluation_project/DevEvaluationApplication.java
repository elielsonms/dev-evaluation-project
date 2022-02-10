package com.wex.gateway.dev_evaluation_project;

import com.oembedler.moon.graphql.boot.SchemaDirective;
import com.wex.gateway.dev_evaluation_project.directive.constraint.ConstraintDirective;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DevEvaluationApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevEvaluationApplication.class, args);
    }

    @Bean
    public SchemaDirective constraintDirective(){
     return new SchemaDirective("constraint",new ConstraintDirective());
    }

}
