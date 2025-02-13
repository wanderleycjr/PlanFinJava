package com.pcmoney.controllers;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.mail.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcmoney.model.Usuario;
import com.pcmoney.dao.UsuarioDAO;
import com.pcmoney.util.EnviaEmailConfig;

@WebServlet("/EmailRedefSenha")
public class EmailRedefSenhaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Pega o e-mail digitado no formulário
		String recipient = request.getParameter("recipiente");
		System.out.println("E-mail encontrado aqui no servlet: " + recipient);
		

		// Cria um objeto Usuario com o e-mail informado
		Usuario usuario = new Usuario();
		usuario.setEmail(recipient);
		System.out.println("Usuário encontrado aqui no servlet: " + usuario);
		
		
		// Chama o método que verifica se o e-mail existe no banco de dados
		UsuarioDAO usuarioDao = new UsuarioDAO();
		
		try {
			
			Usuario usuarioEncontrado = usuarioDao.verificaEmail(usuario);

			if (usuarioEncontrado != null) {
				System.out.println("UsuárioEncontrado encontrado aqui no servlet: " + usuarioEncontrado);

				// Chamar a classe de envio de e-mail
				EnviaEmailConfig enviaEmailConfig = new EnviaEmailConfig();
				String subject = "PCmoney - Redefinição de Senha";
				
				//String body = "Olá!\n\nAqui está o link para redefinir sua senha: http://localhost:8080/pcmoney/redefinesenha.jsp?email=" + recipient + "\nCaso não tenha sido você que solicitou a redefinição da senha, por favor, desconsidere esta mensagem.\n\nAtenciosamente,\n\nPCMoney agradace.";
				String body = "Olá\n\nAqui está o link para redefinir sua senha: http://www.pcmoney.com.br/redefinesenha.jsp?email=" + recipient + "\nCaso não tenha sido você que solicitou a redefinição da senha, por favor, desconsidere esta mensagem.\n\nAtenciosamente,\n\nNós da PCMoney agradacemos.";;
				
				enviaEmailConfig.sendEmail(recipient, subject, body);
				
				request.setAttribute("mensagem", "E-mail enviado! Por favor, verifique sua caixa de mensagens. Caso não tenha recebido, verifique na caixa de spam ou reenvie o e-mail.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("esqueciminhasenha.jsp");
				dispatcher.forward(request, response);

			} else {
				
				request.setAttribute("mensagemerro", "E-mail digitado não foi encontrado em nosso sistema. Por favor, tente novamente ou efetue seu cadastro.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("esqueciminhasenha.jsp");
				dispatcher.forward(request, response);
				
				//response.sendRedirect("emailnaoencontrado.jsp");
				System.out.println("Email não encontrado aqui no servlet");
			}

		} catch (MessagingException e) {
			response.getWriter().println("Erro ao enviar o e-mail: " + e.getMessage());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
