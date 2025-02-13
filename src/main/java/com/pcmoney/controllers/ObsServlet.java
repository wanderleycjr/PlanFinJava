package com.pcmoney.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pcmoney.dao.*;
import com.pcmoney.model.*;


@WebServlet("/ObsServlet")
public class ObsServlet  extends HttpServlet {
	private static final long serialVersionUID = 1L;
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		String id = request.getParameter("id");

		if (acao != null && acao.equals("recObs")) {
			String idUsuObs = request.getParameter("idusu");

			// Método para buscar as Observações por Id do usuário e Id da lista criado no LancamentoDao.java
			Lancamento lancamento = null;
			try {
				lancamento = LancamentoDAO.buscarObsPorIDeIDUSU(Integer.parseInt(id), Integer.parseInt(idUsuObs));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("lancamento", lancamento);
			request.getRequestDispatcher("alteraobs.jsp").forward(request, response);
		}
	        
	}
		

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// criando objeto lancamento e atribuindo valores da tela
		Lancamento lancamento = new Lancamento();
		
		lancamento.setId(Integer.parseInt(request.getParameter("id")));
		lancamento.setIdusu(Integer.parseInt(request.getParameter("idusu")));
		lancamento.setObstext((request.getParameter("obstext")));
	
		// Salvando no banco de dados
		try {
			LancamentoDAO.updateObs(lancamento);
		} catch (UnknownHostException | SQLException e) {
			e.printStackTrace();
		}

		
		String idusu = request.getParameter("idusu");
		
		
		response.sendRedirect("ListaLancamentos?acao=listalanc&id=" + idusu);
		
	}


}
