package com.example.oauthresource.config;

import com.starter.user.entity.Role;
import com.starter.user.entity.RoleSecured;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.annotation.AnnotationMetadataExtractor;
import org.springframework.security.access.annotation.SecuredAnnotationSecurityMetadataSource;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.method.MethodSecurityMetadataSource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }

    public MethodSecurityMetadataSource customMethodSecurityMetadataSource() {
        return new SecuredAnnotationSecurityMetadataSource(new RoleSecuredAnnotationMetadataExtractor());
    }

    private class RoleSecuredAnnotationMetadataExtractor implements AnnotationMetadataExtractor<RoleSecured> {

        @Override
        public Collection<? extends ConfigAttribute> extractAttributes(RoleSecured securityAnnotation) {
            return Arrays.stream(securityAnnotation.value())
                    .map(Role::getAuthority)
                    .map(SecurityConfig::new)
                    .collect(Collectors.toList());
        }
    }

}