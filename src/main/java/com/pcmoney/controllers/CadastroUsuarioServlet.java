package com.pcmoney.controllers;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcmoney.dao.UsuarioDAO;
import com.pcmoney.model.Usuario;

@WebServlet("/CadastroUsuario")
public class CadastroUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Usuario usuario = new Usuario();

		String senha = request.getParameter("senha");
		if (senha == null || senha.length() < 4) {
			request.setAttribute("mensagemErro", "A senha deve ter no mínimo 4 caracteres.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrousuario.jsp");
			dispatcher.forward(request, response);
			return;
		}

		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(senha); // senha já validada acima
		usuario.setIp(request.getParameter("ip"));
		usuario.setDataCriacao(request.getParameter("dataCriacao"));
		usuario.setDataAtualizacao(request.getParameter("dataAtualizacao"));
		usuario.setHora(request.getParameter("hora"));

		try {

			Usuario usuarioGravado = UsuarioDAO.criarUsuario(usuario);
			System.out.println("Usuario gravado " + usuarioGravado);
			request.setAttribute("mensagem", "Bem vindo ao PCMoney! Cadastro efetuado com sucesso!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {

			System.out.println("Erro ao gravar usuário: " + e.getMessage());
			request.setAttribute("mensagemErro", "Erro ao cadastrar usuário: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrousuario.jsp");
			dispatcher.forward(request, response);

		}

	}

}
