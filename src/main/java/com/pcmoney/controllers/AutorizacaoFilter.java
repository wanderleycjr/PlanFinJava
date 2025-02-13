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

@WebFilter("/????????????????")
public class AutorizacaoFilter implements Filter {

	public AutorizacaoFilter() {
	}

	public void destroy() {
		
		AbandonedConnectionCleanupThread.checkedShutdown();

	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession sessao = request.getSession();
		
        if (sessao.getAttribute("usuAutenticado") != null 
				|| request.getRequestURI().endsWith("index.jsp")
				|| request.getRequestURI().endsWith("login.jsp")
				|| request.getRequestURI().endsWith("loginerro.jsp") 
				|| request.getRequestURI().endsWith("cadastrousuario.jsp")
				|| request.getRequestURI().endsWith("emailsenha.jsp")
				|| request.getRequestURI().endsWith("esqueciminhasenha.jsp")
				|| request.getRequestURI().endsWith("EmailRedefSenha")
				|| request.getRequestURI().endsWith("GravaNovaSenha")
				|| request.getRequestURI().endsWith("LoginUsuario") 
				|| request.getRequestURI().endsWith("UsuarioCreate")
				|| request.getRequestURI().endsWith("CadastroUsuario")
				|| request.getRequestURI().endsWith("EmailRedefSenha")
				|| request.getRequestURI().contains("webapp") 
				|| request.getRequestURI().contains("img")
				|| request.getRequestURI().contains("css") 
				|| request.getRequestURI().contains("js")
				|| request.getRequestURI().contains("controllers")
				) {
			
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("login.jsp");
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
