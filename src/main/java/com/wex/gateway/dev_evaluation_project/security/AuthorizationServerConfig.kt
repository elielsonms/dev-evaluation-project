package com.wex.gateway.dev_evaluation_project.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.token.*
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter



@Configuration
class AuthorizationServerConfig (private val config: AuthConfig,
                                 private val authenticationManager:AuthenticationManager): AuthorizationServerConfigurerAdapter() {

    private val converter by lazy {
        JwtAccessTokenConverter().apply {
            setSigningKey(config.server.jwtSigningKey)
        }
    }

    override fun configure(clients:ClientDetailsServiceConfigurer?) {
        clients?.inMemory()?.apply{
            config.clients?.forEach {
                withClient(it.clientId)
                    .secret(it.clientSecret)
                    .scopes(*it.scopes?.split(",").orEmpty().toTypedArray())
                    .authorizedGrantTypes(*config.server.grantTypes?.split(",").orEmpty().toTypedArray())
            }
        }
    }

    override fun configure(endpoints:AuthorizationServerEndpointsConfigurer?) {
        val tokenConverter = accessTokenConverter()
        endpoints?.authenticationManager(authenticationManager)
                 ?.accessTokenConverter(tokenConverter)
                 ?.tokenStore(tokenStore(tokenConverter))
                 ?: throw Exception("Failed to configuration authentication manager")
    }

    @Configuration
    protected class AuthenticationManagerConfiguration : GlobalAuthenticationConfigurerAdapter() {

        override fun init(auth: AuthenticationManagerBuilder) {
            auth.inMemoryAuthentication().
                    withUser("contactUser").password("{bcrypt}\$2a\$04\$.g48RTGdUihKrhA9JPgvhuysWgwkkrUTPwwd.Ko3eSh.1.aayyoKu").roles("USER").
                and().
                    withUser("masterUser").password("{bcrypt}\$2a\$04\$rN8jbbz.GN9EsQLLAZC3yOUCU16/yQkfwNBIXkc75oavoC.6oDITW").roles("USER")
        }

    }

    @Bean
    fun accessTokenConverter():JwtAccessTokenConverter = converter

    @Bean
    fun tokenStore(accessTokenConverter:JwtAccessTokenConverter):TokenStore = JwtTokenStore(accessTokenConverter)

    @Bean
    fun passwordEncoder():PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

}