package com.pwc.complaint.complaint.wrapper;

import java.io.Serializable;
import java.util.Set;

public class SignUpDto implements Serializable {

    private String name;

    private String email;

    private String username;

    private String password;

    private Set<String> userRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<String> userRole) {
        this.userRole = userRole;
    }
}
