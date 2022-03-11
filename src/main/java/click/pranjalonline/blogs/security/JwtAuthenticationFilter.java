package click.pranjalonline.blogs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    // Inject dependency
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    /**
     * This method is executes once per http requests
     * First we extract the JWT token from the request
     * Next we will validate the token
     * Then we will get the username from the token
     * load the username associated with the token
     * then will  set this information to the spring security
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // GET THE TOKEN NOW
        String token = getJWTTokenFromHeader(request);
        // VALIDATE TOKEN
        if(StringUtils.hasText(token) && tokenProvider.validateJWTToken(token)){
            // RETRIEVE USERNAME FROM THE TOKEN
            String username = tokenProvider.getUsernameFromJWT(token);
            // LOAD THE USER TO SPRING SECURITY ASSOCIATED WITH THE USERNAME
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,null,userDetails.getAuthorities()
            );
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // SET SPRING SECURITY
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);

    }

    /**
     * To extract token from the Bearer header
     * @param request
     * @return
     */
    private String getJWTTokenFromHeader(HttpServletRequest request){
        String bearerToken= request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }
}
