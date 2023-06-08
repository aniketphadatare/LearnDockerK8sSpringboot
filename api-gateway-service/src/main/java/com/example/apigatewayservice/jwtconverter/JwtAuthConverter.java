package com.example.apigatewayservice.jwtconverter;

import com.nimbusds.jose.shaded.gson.internal.LinkedTreeMap;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private JwtAuthenticationConverter jwtAuthenticationConverter;
    private JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;
    private final JwtAuthConverterProperties properties;

    public JwtAuthConverter(JwtAuthConverterProperties properties) {
        this.jwtAuthenticationConverter = new JwtAuthenticationConverter();
        this.jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        this.properties = properties;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = extractResourceRoles(jwt);
        return new JwtAuthenticationToken(jwt, authorities, getPrincipalClaimName(jwt));
    }

    private String getPrincipalClaimName(Jwt jwt) {
        String claimName = JwtClaimNames.SUB;
        if (properties.getPrincipalAttribute() != null) {
            claimName = properties.getPrincipalAttribute();
        }
        return jwt.getClaim(claimName);
    }

    private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt) {
        Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
        Map<String, Object> resource;

        Object client = resourceAccess.get("login-app-keycloak-client");

        LinkedTreeMap<String, List<String>> clientRoleMap = (LinkedTreeMap<String, java.util.List<String>>) client;

        Collection<String> resourceRoles = new ArrayList<>(clientRoleMap.get("roles"));

        if (resourceAccess == null || resourceRoles == null) {
            return Collections.emptySet();
        }
        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toSet());
    }
}
