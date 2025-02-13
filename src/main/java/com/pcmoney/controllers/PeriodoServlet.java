package com.pcmoney.controllers;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.pcmoney.model.Periodo;
import com.pcmoney.dao.PeriodoDAO;

@WebServlet("/DefinirPeriodo")
public class PeriodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PeriodoServlet() {
 
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pesquisa = request.getParameter("pesquisa");
        
        if(pesquisa == null) {
        	pesquisa="";
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listapcmoney.jsp");        
        requestDispatcher.forward(request, response);
        
	};


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Periodo periodo = new Periodo();
		String id = request.getParameter("id");

		periodo.setDataDe(request.getParameter("dataInicial"));
		periodo.setDataAte(request.getParameter("dataFinal"));
		periodo.setIdusu(Integer.parseInt(request.getParameter("id")));
		
		try {
			PeriodoDAO.deletaPeriodo(periodo);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			PeriodoDAO.createPeriodo(periodo);
		} catch (UnknownHostException | SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("ListaLancamentos?acao=listalanc&id=" + id);
		
		//doGet(request,response);

	}

}

