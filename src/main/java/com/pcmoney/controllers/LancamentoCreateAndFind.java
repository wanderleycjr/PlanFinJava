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

@WebServlet("/CreateAndFindLanc")
public class LancamentoCreateAndFind extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LancamentoCreateAndFind() {
 
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
		
		String idid = request.getParameter("idid");

		lancamento.setTplanc(request.getParameter("tplanc"));
		lancamento.setTipo(request.getParameter("tipo"));
		lancamento.setNome(request.getParameter("nome"));
		lancamento.setDescr(request.getParameter("descr"));

		//Convertendo ponto por virgula		
        String svalor = request.getParameter("valor");
		lancamento.setValor(Double.parseDouble(svalor.replace(".", "").replace(",", ".")));
		
	    lancamento.setVenc(request.getParameter("venc"));
		lancamento.setDtpgto(request.getParameter("dtpgto"));
		lancamento.setParc(Integer.parseInt(request.getParameter("parc")));
		lancamento.setQtdparc(Integer.parseInt(request.getParameter("qtdparc")));
		lancamento.setMaq(request.getParameter("maq"));
		lancamento.setUser(request.getParameter("user"));
		lancamento.setData(request.getParameter("data"));
		lancamento.setHora(request.getParameter("hora"));
		lancamento.setIdusu(Integer.parseInt(request.getParameter("idid")));
		lancamento.setObstext(request.getParameter("obstext"));
		lancamento.setPago(request.getParameter("pago"));

		try {
			LancamentoDAO.create(lancamento);
		} catch (UnknownHostException | SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("ListaLancamentos?acao=listalanc&id=" + idid);
		
	}

}

