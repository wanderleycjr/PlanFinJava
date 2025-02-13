package com.pcmoney.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcmoney.model.Usuario;
import com.pcmoney.dao.UsuarioDAO;


@WebServlet("/GravaNovaSenha")
public class GravaNovaSenhaServlet extends HttpServlet {
		
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Captura os dados do formulário
        String novaSenha = request.getParameter("password");
        String email = request.getParameter("email");  // Certifique-se de que o e-mail foi passado corretamente no formulário
        
        // Criar um objeto Usuario e definir a nova senha e o e-mail
        Usuario usuario = new Usuario();
        usuario.setSenha(novaSenha);
        usuario.setEmail(email);  // Email para identificar o usuário no banco

        // Chama o método que grava a nova senha no banco de dados
        try {
			UsuarioDAO.redefineNovaSenha(usuario);
		} catch (SQLException e) {
			e.printStackTrace();
		}  

        // Redireciona para uma página de sucesso após a atualização
        request.setAttribute("mensagemSucesso", "Senha alterada com sucesso!");
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
