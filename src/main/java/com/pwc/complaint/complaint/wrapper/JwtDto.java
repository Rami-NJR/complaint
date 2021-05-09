package com.pwc.complaint.complaint.wrapper;

import java.io.Serializable;

public class JwtDto implements Serializable {

    private String token;
    private String type = "Bearer";

    public JwtDto() {
    }

    public JwtDto(String accessToken) {
        this.token = accessToken;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}
