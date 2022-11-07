package com.cognixia.jump.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final org.jboss.logging.Logger LOG
      = LoggerFactory.logger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(
      HttpServletRequest request,
      HttpServletResponse response, 
      AccessDeniedException exc) throws IOException, ServletException {
        
        Authentication auth 
          = SecurityContextHolder.getContext().getAuthentication();
        System.out.print(" USER NAME...????" +  auth.getName());
        if (auth != null) {
            LOG.info("User: " + auth.getName() 
              + " attempted to access the protected URL: "
              + request.getRequestURI());
        }

     
        response.sendRedirect(request.getContextPath() + "/accessDenied");
    }
}
