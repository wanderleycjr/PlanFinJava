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


@WebServlet("/AtualizaPagamentoServlet")
public class AtualizaPagamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int idusu = Integer.parseInt(request.getParameter("idusu"));
        String pago = request.getParameter("pago");

        // Alterna o valor de pago
        Lancamento lancamento = new Lancamento();
        lancamento.setId(idusu);
        lancamento.setId(id);
        lancamento.setPago("1".equals(pago) ? "0" : "1");

            try {
				LancamentoDAO.updatePgto(lancamento);
			} catch (UnknownHostException | SQLException e) {
				e.printStackTrace();
			}


        // Redireciona para a página após a atualização
        //response.sendRedirect("listapcmoney.jsp");
        
        response.sendRedirect("ListaLancamentos?acao=listalanc&id=" + idusu);
    }
}



