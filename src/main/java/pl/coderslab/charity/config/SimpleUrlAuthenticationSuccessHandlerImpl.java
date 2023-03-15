package pl.coderslab.charity.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class SimpleUrlAuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String targetURL;
        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            targetURL = "/admin/home";
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            targetURL = "/user/home";
        } else {
            throw new IllegalStateException("Role not found for this user");//czy ok obsługa braku roli dla użytkownika
        }

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        redirectStrategy.sendRedirect(request, response, targetURL);

//        clearAuthenticationAttributes(request); do czego to słuzy

    }
}
