package com.pcmoney.controllers;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		LocalTime localTime = LocalTime.now();

		HttpSession session = request.getSession();
		session.invalidate();
		
		System.out.println("Saída do sistema efetuada com sucesso às " + Time.valueOf(localTime) + ".");
		
		response.sendRedirect("login.jsp");
		
	}

}
