package io.chat.filters;

import io.chat.entity.User;
import io.chat.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Optional;

public class AuthFilter extends GenericFilterBean {

    UserService userService;

    @Autowired
    public AuthFilter(UserService userService){
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        String url = (String) servletRequest.getRequestURI();

        if(url.equals("/") ||
                url.equals("/sign-up") || url.equals("/showLogin")){
            String email = (String) servletRequest.getSession().getAttribute("email");
            Optional<User> result = userService.getUserByEmail(email);
            if(result.isPresent()){
                User user = result.get();
                String encodedRedirectURL = ((HttpServletResponse) response).encodeRedirectURL(
                        servletRequest.getContextPath() + "/search-contact/" + user.getId());

                servletResponse.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
                servletResponse.setHeader("Location", encodedRedirectURL);
            }
        }

        chain.doFilter(servletRequest, servletResponse);
    }

}
