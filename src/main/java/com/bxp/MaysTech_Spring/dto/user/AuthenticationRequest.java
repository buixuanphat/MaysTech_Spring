package com.bxp.MaysTech_Spring.dto.user;

public class AuthenticationRequest {
    boolean authenticated;

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
