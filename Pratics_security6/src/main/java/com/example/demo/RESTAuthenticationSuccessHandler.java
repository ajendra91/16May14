package com.example.demo;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.util.Date;

@Component
public class RESTAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

		@Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	       
	    	super.clearAuthenticationAttributes(request);
	        getRedirectStrategy().sendRedirect(request, response, "http://localhost:8080/admin");
	    }
	
	
	/*@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	String targetUrl2 = getDefaultTargetUrl();

    	System.out.println(targetUrl2);
    	
    	//UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ 864000000);

        String token= Jwts.builder()
                .setSubject("kk")
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, "926D96C90030DD58429D2751AC1BDBBC")
                .compact();
        
        

        String targetUrl = UriComponentsBuilder.fromUriString("/app")
                .queryParam("token", token)
                .build().toUriString();

        System.out.println(targetUrl2);
        
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
        
    }*/
}
