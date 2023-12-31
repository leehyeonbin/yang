package dev.yangsijun.gauth.authentication;

import gauth.GAuthToken;
import dev.yangsijun.gauth.core.GAuthSpringSecurityPluginVersion;
import dev.yangsijun.gauth.core.user.GAuthUser;
import dev.yangsijun.gauth.registration.GAuthRegistration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GAuthAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = GAuthSpringSecurityPluginVersion.SERIAL_VERSION_UID;

    private String code;
    private GAuthRegistration gauthRegistration;
    private GAuthUser principal;
    private Map<String, Object> additionalParameters = new HashMap<>();

    private GAuthToken token;

    public GAuthAuthenticationToken(Collection<? extends GrantedAuthority> authorities, String code, GAuthRegistration gauthRegistration, GAuthUser principal, GAuthToken token) {
        super(authorities);
        this.code = code;
        this.gauthRegistration = gauthRegistration;
        this.principal = principal;
        this.token = token;
        this.setAuthenticated(true);
    }

    public GAuthAuthenticationToken(String code, GAuthRegistration gauthRegistration, Map<String, Object> additionalParameters) {
        super(Collections.emptyList());
        this.code = code;
        this.gauthRegistration = gauthRegistration;
        this.additionalParameters = additionalParameters;
        this.setAuthenticated(false);
    }

    @Override
    public GAuthUser getPrincipal() {
        return this.principal;
    }

    @Override
    public Object getCredentials() {
        // Credentials are never exposed (by the Provider) for an GAuth User
        return "";
    }

    public String getCode() {
        return code;
    }

    public GAuthToken getToken() {
        return token;
    }

    public GAuthRegistration getgauthRegistration() {
        return gauthRegistration;
    }

    public Map<String, Object> getAdditionalParameters() {
        return additionalParameters;
    }
}
