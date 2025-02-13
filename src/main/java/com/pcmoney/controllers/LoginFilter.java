package com.pcmoney.controllers;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pcmoney.model.Usuario;


@WebFilter("/?????????????")
public class LoginFilter extends HttpFilter implements Filter {
  
	private static final long serialVersionUID = 1L;
	
	//Constante de segurança
    private final static String FILTER_APPLIED = "_security_filter_applied";
	
    public LoginFilter() {
        super();

    }

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
		//Conversão dos parametros do metodo para Http
		HttpServletRequest hRequest = (HttpServletRequest)request;
		HttpServletResponse hResponse = (HttpServletResponse)response;
		
		//recupera na variável do tipo HttpSession a aessão do request
		HttpSession session = hRequest.getSession();
		
		//Recupera na variável paginaAtual a pagina URL que será identificada para passar ou não		
		String paginaAtual = new String(hRequest.getRequestURL());
		
		if ((request.getAttribute(FILTER_APPLIED)) == null 
				&& (!paginaAtual.endsWith("index.jsp"))
				&& (paginaAtual.endsWith(".jsp")) 
				&& (!paginaAtual.endsWith("cadastro.jsp"))
				&& (!paginaAtual.endsWith("loginerro.jsp")) 
				&& (!paginaAtual.endsWith("saldoinicial.jsp"))
				&& (!paginaAtual.endsWith("esqueciminhasenha.jsp")) 
				&& (!paginaAtual.endsWith("redefinesenha.jsp"))
				&& (!paginaAtual.endsWith("cadastrousuario.jsp"))
				&& (!paginaAtual.endsWith("login.jsp"))
			){
			
			request.setAttribute(FILTER_APPLIED, Boolean.TRUE);
			Usuario usuarioLogado = null;
			if (((Usuario)session.getAttribute("usuarioLogado")) != null) {
				usuarioLogado = (Usuario)session.getAttribute("usuarioLogado");
			}
			
			if ((usuarioLogado == null) || (usuarioLogado.getId() == 0)) {
			   hResponse.sendRedirect("login.jsp");
			}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
