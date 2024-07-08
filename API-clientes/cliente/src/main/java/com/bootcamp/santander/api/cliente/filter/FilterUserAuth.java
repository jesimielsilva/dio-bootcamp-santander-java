package com.bootcamp.santander.api.cliente.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bootcamp.santander.api.cliente.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterUserAuth extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var servletPath = request.getServletPath();

        if (servletPath.equals("/users/") || servletPath.startsWith("/contas/")) {
            try {
                var authorization = request.getHeader("Authorization");
                var authEncoded = authorization.substring("Basic".length()).trim();
                byte[] authDecode = Base64.getDecoder().decode(authEncoded);
                var authString = new String(authDecode);
                String[] credentials = authString.split(":");
                String name = credentials[0];
                String cpf = credentials[1];

                System.out.println("Authorization");
                System.out.println(name);
                System.out.println(cpf);

                var user = this.userService.findUserByCpfAndName(cpf, name);

                if (user == null) {
                    System.out.println("Usuario não encontrado");
                    response.sendError(401);
                } else {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                }
            } catch (Exception err) {
                System.out.println("Ocorreu um erro: " + err.getMessage());
                response.sendError(501);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
