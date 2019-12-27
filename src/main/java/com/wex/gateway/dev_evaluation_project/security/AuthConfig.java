package com.wex.gateway.dev_evaluation_project.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix="auth")
public class AuthConfig {
    private AuthServer server;
    private List<AuthClient> clients;

    public AuthServer getServer() {
        return server;
    }

    public void setServer(AuthServer server) {
        this.server = server;
    }

    public List<AuthClient> getClients() {
        return clients;
    }

    public void setClients(List<AuthClient> clients) {
        this.clients = clients;
    }
}


class AuthClient{
    private String clientId;
    private String clientSecret;
    private String scopes;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }
}

class AuthServer{
    private String scopes;
    private String grantTypes;
    private String jwtSigningKey;

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}