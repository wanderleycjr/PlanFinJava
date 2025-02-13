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

import com.pcmoney.dao.LancamentoDAO;
import com.pcmoney.model.Lancamento;
import com.pcmoney.model.Periodo;
import com.pcmoney.dao.PeriodoDAO;

@WebServlet("/CreateSaldoInicial")
public class LancamentoSaldoInicial extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LancamentoSaldoInicial() {
 
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pesquisa = request.getParameter("pesquisa");
        
        if(pesquisa == null) {
        	pesquisa="";
        }
        
        //List<Lancamento> lancamentos = LancamentoDao.findById(pesquisa);
        
        //request.setAttribute("lancamentos", lancamentos);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listapcmoney.jsp");        
        requestDispatcher.forward(request, response);
        
	};


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Lancamento lancamento = new Lancamento();
		Periodo periodo = new Periodo();

		String idid = request.getParameter("idid");

		lancamento.setTplanc(request.getParameter("tplanc"));
		lancamento.setTipo(request.getParameter("tipo"));
		lancamento.setNome(request.getParameter("nome"));
		lancamento.setDescr(request.getParameter("descr"));

		//Convertendo ponto por virgula		
        String svalor = request.getParameter("valor");
		lancamento.setValor(Double.parseDouble(svalor.replace(".", "").replace(",", ".")));
		
		lancamento.setParc(Integer.parseInt(request.getParameter("parc")));
		lancamento.setQtdparc(Integer.parseInt(request.getParameter("qtdparc")));
		lancamento.setMaq(request.getParameter("maq"));
		lancamento.setUser(request.getParameter("user"));
		lancamento.setData(request.getParameter("data"));
		lancamento.setHora(request.getParameter("hora"));
		lancamento.setIdusu(Integer.parseInt(request.getParameter("idid")));
		
		periodo.setDataDe(request.getParameter("dataIncial"));
		periodo.setDataAte(request.getParameter("dataFinal"));
		periodo.setIdusu(Integer.parseInt(request.getParameter("idid")));
		
		try {
			PeriodoDAO.createPeriodo(periodo);
		} catch (UnknownHostException | SQLException e) {
			e.printStackTrace();
		}
		
		try {
			
			LancamentoDAO.saldoInicial(lancamento);
			
			
			
		} catch (UnknownHostException | SQLException e) {
			e.printStackTrace();			
		}
	
		response.sendRedirect("ListaLancamentos?acao=listalanc&id=" + idid);

	}

}