package com.api.Nutricorp.Model;


public class TokenModel {
    private String accessToken;
    private String expiresIn;
    private String tokenType;
    private String Scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getScope() {
        return Scope;
    }

    public void setScope(String scope) {
        Scope = scope;
    }
}
