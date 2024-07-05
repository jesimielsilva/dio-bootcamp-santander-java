package com.bootcamp.santander.api.cliente.filter;

import java.io.IOException;
import java.util.Base64;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.bootcamp.santander.api.cliente.repository.ContaRepository;
import com.bootcamp.santander.api.cliente.service.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import at.favre.lib.crypto.bcrypt.BCrypt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterPasswordAuth extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    ContaRepository contaRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.equals("/contas/")) {
            try {
                var authorization = request.getHeader("Authorization");

                var authEncoded = authorization.substring("Basic".length()).trim();

                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                var authString = new String(authDecode);

                String[] credentials = authString.split(":");
                String name = credentials[0];
                String cpf = credentials[1];

                var user = this.userService.findUserByCpfAndName(cpf, name);

                if (user == null) {
                    System.out.println("Usuario não encontrado");
                    response.sendError(401);
                } else {
                    var account = contaRepository.findContaPasswordByUserId(user.getId());
                    if (account.isEmpty()) {
                        System.out.println("Este usuário não tem nenhuma conta cadastrada!");
                        response.sendError(401);
                        return;
                    } else {
                        // Acessa o corpo da requisicao
                        var bodyJson = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
                        System.out.println(bodyJson);
                        // Parsear o JSON para um objeto JsonNode usando Jackson ObjectMapper
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode jsonNode = objectMapper.readTree(bodyJson);

                        // Acessar o valor da chave "password"
                        String password = jsonNode.get("password").asText();
                        var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(),
                                account.get(0).getPassword().toCharArray());

                        if (passwordVerify.verified) {
                            // segue viagem
                            request.setAttribute("idUser", user.getId());
                            System.out.println("Passou no Password");
                            filterChain.doFilter(request, response);
                            return;
                        } else {
                            response.sendError(401);
                        }
                    }
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
