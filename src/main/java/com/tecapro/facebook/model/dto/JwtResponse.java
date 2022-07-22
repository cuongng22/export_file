package com.tecapro.facebook.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
public class JwtResponse {
    private Long id;

    private String token;

    private String type = "Bearer";

    private String username;

    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(Long id, String token, String type, String username, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.username = username;
        this.roles = roles;
    }

    public JwtResponse(Long id, String token, String username, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.username = username;
        this.roles = roles;
    }
}
