package com.artmuh.taskmanagementsystem.filter;

import com.artmuh.taskmanagementsystem.entity.User;
import com.artmuh.taskmanagementsystem.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


        if (!hasAuthorizationBearer(request)){
            filterChain.doFilter(request,response);
            return;
        }
        String token=getAccessToken(request);

        if (!jwtTokenUtil.validateAccessToken(token)){
            filterChain.doFilter(request,response);
            return;
        }

        setAuthenticationContext(token, request);
        filterChain.doFilter(request, response);
    }

    private boolean hasAuthorizationBearer(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        return StringUtils.hasText(header) && header.startsWith("Bearer");
    }

    private String getAccessToken(HttpServletRequest request){
        String header=request.getHeader("Authorization");
        String token=header.split(" ")[1].trim();
        return token;
    }

    private void setAuthenticationContext(String token ,HttpServletRequest request){

        UserDetails userDetails = getUserDetails(token);

        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,null);

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private UserDetails getUserDetails(String token){
        User userDetails=new User();

        String[] jwtSubject=jwtTokenUtil.getSubject(token).split(",");

        userDetails.setId(Long.parseLong(jwtSubject[0]));
        userDetails.setUsername(jwtSubject[1]);

        return userDetails;
    }


}
