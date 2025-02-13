package com.pcmoney.controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.pcmoney.model.Usuario;

@WebFilter("/*")
public class UnifiedAuthFilter implements Filter {

    private static final String FILTER_APPLIED = "_security_filter_applied";

    public void destroy() {
        AbandonedConnectionCleanupThread.checkedShutdown();
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        // Recupera a URL da página atual
        String currentURL = request.getRequestURI();

        // Lista de páginas públicas que não requerem autenticação
        boolean isPublicPage = currentURL.endsWith("index.jsp") ||   // Permite o index.jsp
                               currentURL.endsWith("login.jsp") ||
                               currentURL.endsWith("loginerro.jsp") ||
                               currentURL.endsWith("cadastrousuario.jsp") ||
                               currentURL.endsWith("emailsenha.jsp") ||
                               currentURL.endsWith("esqueciminhasenha.jsp") ||
                               currentURL.endsWith("redefinesenha.jsp") ||
                               currentURL.endsWith("EmailRedefSenha") ||
                               currentURL.endsWith("GravaNovaSenha") ||
                               currentURL.endsWith("LoginUsuario") ||
                               currentURL.endsWith("UsuarioCreate") ||
                               currentURL.endsWith("CadastroUsuario") ||
                               currentURL.contains("webapp") ||
                               currentURL.contains("img") ||
                               currentURL.contains("css") ||
                               currentURL.contains("js");
        
        
        if (!isPublicPage) {

            // Se o filtro já foi aplicado, não reaplica para evitar loops
            if (request.getAttribute(FILTER_APPLIED) == null) {
                request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

                // Verifica se o usuário está autenticado
                Usuario usuarioLogado = (Usuario) session.getAttribute("usuAutenticado");
                
                if (usuarioLogado == null || usuarioLogado.getId() == 0) {
                    response.sendRedirect("index.jsp");
                	//response.sendRedirect("login.jsp");
                    return;
                }
            }
        }

        // Passa o controle para o próximo filtro ou recurso
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }
}
