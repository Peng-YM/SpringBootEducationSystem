package com.peng1m.education.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

public class SecurityUtils {
    public static void runAsAdmin() {
        runAs("system", "system", "ROLE_USER", "ROLE_ADMIN");
    }

    public static void runAs(String username, String password, String... roles) {
        Assert.notNull(username, "Username must not be null!");
        Assert.notNull(password, "Password must not be null!");
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(
                        username, password, AuthorityUtils.createAuthorityList(roles));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    public static void clear() {
        SecurityContextHolder.clearContext();
    }
}
