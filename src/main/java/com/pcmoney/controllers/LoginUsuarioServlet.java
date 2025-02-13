package com.pcmoney.controllers;

import java.io.IOException; 
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.pcmoney.model.Lancamento;
import com.pcmoney.model.Usuario;
import com.pcmoney.dao.LancamentoDAO;
import com.pcmoney.dao.UsuarioDAO;

@WebServlet("/LoginUsuario")
public class LoginUsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession sessao = request.getSession(false);
		if (sessao != null) {
			sessao.invalidate();
		}
		response.sendRedirect("login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String semail = request.getParameter("email");
		String ssenha = DigestUtils.md5Hex(request.getParameter("senha"));

		Usuario usu = new Usuario();
		usu.setEmail(semail);
		usu.setSenha(ssenha);

		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usuAutenticado = null;
		try {
			usuAutenticado = usuDAO.autenticacao(usu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		if (usuAutenticado != null) {

			HttpSession sessao = request.getSession();
			sessao.setAttribute("usuAutenticado", usuAutenticado);
			
			//sessao.setMaxInactiveInterval(10 * 60); // Expira após 10 minutos de inatividade
			
			int idUsuario = 0;
			idUsuario = usuAutenticado.getId();
			
			List<Lancamento> listaLanc = null;
			try {
				listaLanc = LancamentoDAO.recuperaListaPeloUsuario(idUsuario);
			} catch (SQLException e) {
 				e.printStackTrace();
			}
			
			if (listaLanc.isEmpty()) {
				
				System.out.println("saldo inicial");
				
				//response.sendRedirect("saldoinicial.jsp?id=" + idUsuario);
				response.sendRedirect("ListaLancamentos?acao=getNewUser&id=" + idUsuario);
			} else {
				
				System.out.println("lista lançamentos");
				
				response.sendRedirect("ListaLancamentos?acao=listalanc&id=" + idUsuario);
			}

		} else {
     		response.sendRedirect("loginerro.jsp");
		}

	}

}
