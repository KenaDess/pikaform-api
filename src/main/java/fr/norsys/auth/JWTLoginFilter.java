package fr.norsys.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.norsys.models.Hunter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        /*
        Lire le contenu

        ServletInputStream reqInputStream = req.getInputStream();
        String text = null;
        try (final Reader reader = new InputStreamReader(reqInputStream)) {
            text = CharStreams.toString(reader);
        }
        System.out.println("----> "+ text);

        */

        // Hunter creds = new ObjectMapper().readValue(req.getInputStream(), Hunter.class);
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        "admin",
                        "admin",
                        Collections.emptyList()
                )
        );
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        TokenAuthenticationService.addAuthentication(res, auth.getName());
    }
}