package com.bxp.MaysTech_Spring.dto.request;

public class AuthenticationRequest {
    boolean authenticated;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
