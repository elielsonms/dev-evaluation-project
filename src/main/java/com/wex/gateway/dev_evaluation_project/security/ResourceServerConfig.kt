package com.wex.gateway.dev_evaluation_project.security

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter

@Configuration
class ResourceServerConfig : ResourceServerConfigurerAdapter(){
    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()
                ?.requestMatchers(EndpointRequest.toAnyEndpoint())
                ?.permitAll()
                ?.anyRequest()
                ?.authenticated()
    }
}