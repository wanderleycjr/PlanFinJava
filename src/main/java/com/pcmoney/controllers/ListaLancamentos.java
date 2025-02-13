package com.pcmoney.controllers; 

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.pcmoney.dao.LancamentoDAO;
import com.pcmoney.dao.UsuarioDAO;
import com.pcmoney.dao.SaldoAnteriorDAO;
import com.pcmoney.model.*;

@WebServlet("/ListaLancamentos")
public class ListaLancamentos extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Lancamento lanc = new Lancamento();
		String acao = request.getParameter("acao");
		//LancamentoDAO lancDAO = new LancamentoDAO();
		//SaldoAnteriorDAO saldoAnteriorDAO = new SaldoAnteriorDAO();
		String id = request.getParameter("id");
		//UsuarioDAO usuDAO = new UsuarioDAO();
		//Usuario usu = new Usuario();
		//Periodo periodo = new Periodo();

		if (acao != null && acao.equals("listalanc")) {

			List<Lancamento> listaLanc = null;
			try {
				listaLanc = LancamentoDAO.findById(Integer.parseInt(id));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("listaLanc", listaLanc);
			
			if (listaLanc.isEmpty()) {
				
				Usuario user = null;
				try {
					user = UsuarioDAO.recuperaPeriodoUsuario(Integer.parseInt(id));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("user", user);
				
				RequestDispatcher saida = request.getRequestDispatcher("listapcmoneysemmov.jsp");
				
			    saida.forward(request, response);
			    
			} else {
			
				Usuario user = null;
				try {
					user = UsuarioDAO.recuperaPeriodoUsuario(Integer.parseInt(id));
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("user", user);
				
				String dataDe = user.getDataDeUs();
				SaldoAnterior lancamentoSoma = null;
				try {
					lancamentoSoma = SaldoAnteriorDAO.SomaValoresPeloId(Integer.parseInt(id), dataDe);
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("lancamentoSoma", lancamentoSoma);
				

			    RequestDispatcher saida = request.getRequestDispatcher("listapcmoney.jsp");
		
			    saida.forward(request, response);
			}

		} else if (acao != null && acao.equals("exclanc")) {
			
			String idExc = request.getParameter("id");
			String idUsu = request.getParameter("idusu");
			String user = request.getParameter("user");
		
			
			lanc.setId(Integer.parseInt(idExc));
			lanc.setIdusu(Integer.parseInt(idUsu));
			lanc.setUser(user);

			try {
				LancamentoDAO.delete(lanc);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			response.sendRedirect("ListaLancamentos?acao=listalanc&id=" + idUsu);

		} else if (acao != null && acao.equals("alteralanc")) {
			//String idAlt = request.getParameter("id");
			String idUsu = request.getParameter("idusu");
			
			// Método para buscar por Id do usuário e Id da lista criado no LancamentoDao.java
			Lancamento lancamento = null;
			try {
				lancamento = LancamentoDAO.buscarListaPorIDeIDUSU(Integer.parseInt(id), Integer.parseInt(idUsu));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("lancamento", lancamento);
			request.getRequestDispatcher("alteralanc.jsp").forward(request, response);

		} else if (acao != null && acao.equals("getUser")) {
			String idGetUser = request.getParameter("id");
			
			//Método para buscar por Id do usuário para recuperar o nome do usuario na opção de criar um novo lançamento
			Usuario usuario = null;
			try {
				usuario = UsuarioDAO.buscarporID(Integer.parseInt(idGetUser));
			} catch (NumberFormatException e) {

				e.printStackTrace();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("lancamentos.jsp").forward(request, response);
			
		} else if (acao != null && acao.equals("getNewUser")) {
			String idGetUser = request.getParameter("id");
		
			//Método para buscar por Id do usuário para recuperar o nome do usuario na opção de adicionar o lançamento inicial
			Usuario usuario = null;
			try {
				usuario = UsuarioDAO.buscarporID(Integer.parseInt(idGetUser));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("saldoinicial.jsp").forward(request, response);
			
		} else if (acao != null && acao.equals("getUserPeriodo")) {
			String idGetUser = request.getParameter("id");
			
			Usuario usuario = null;
			try {
				usuario = UsuarioDAO.buscarporID(Integer.parseInt(idGetUser));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("usuario", usuario);
			request.getRequestDispatcher("periodo.jsp").forward(request, response);
			
		} else if (acao != null && acao.equals("recObs")) {
			//String idObs = request.getParameter("id");
			String idUsuObs = request.getParameter("idusu");
			
			//Método para buscar as Observações por Id do usuário e Id da lista criado no LancamentoDao.java
			Lancamento lancamento = null;
			try {
				lancamento = LancamentoDAO.buscarObsPorIDeIDUSU(Integer.parseInt(id), Integer.parseInt(idUsuObs));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("lancamento", lancamento);
			request.getRequestDispatcher("alteraobs.jsp").forward(request, response);
		
		}else if (acao != null && acao.equals("copiaLanc")) {
			//String idAlt = request.getParameter("id");
			String idUsu = request.getParameter("idusu");
			
			// Método para buscar por Id do usuário e Id da lista criado no LancamentoDao.java
			Lancamento lancamento = null;
			try {
				lancamento = LancamentoDAO.buscarListaPorIDeIDUSU(Integer.parseInt(id), Integer.parseInt(idUsu));
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
			request.setAttribute("lancamento", lancamento);
			request.getRequestDispatcher("copialanc.jsp").forward(request, response);
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// criando objeto lancamento e atribuindo valores da tela
		Lancamento lancamento = new Lancamento();
		
		lancamento.setId(Integer.parseInt(request.getParameter("id")));
		lancamento.setIdusu(Integer.parseInt(request.getParameter("idusu")));
		lancamento.setTplanc((request.getParameter("tplanc")));
		lancamento.setTipo((request.getParameter("tipo")));
		lancamento.setNome((request.getParameter("nome")));
		lancamento.setDescr((request.getParameter("descr")));
		//lancamento.setUser((request.getParameter("user")));

		
		
        String svalor = request.getParameter("valor");
		lancamento.setValor(Double.parseDouble(svalor.replace(".", "").replace(",", ".")));
		
		lancamento.setVenc((request.getParameter("venc")));
		lancamento.setDtpgto((request.getParameter("dtpgto")));
		
		lancamento.setParc(Integer.parseInt(request.getParameter("parc")));
		lancamento.setQtdparc(Integer.parseInt(request.getParameter("qtdparc")));
		lancamento.setObstext((request.getParameter("obstext")));
		lancamento.setPago((request.getParameter("pago")));
	
		// Salvando no banco de dados
		try {
			LancamentoDAO.update(lancamento);
		} catch (UnknownHostException | SQLException e) {
			e.printStackTrace();
		}

		
		String idusu = request.getParameter("idusu");
		
		
		response.sendRedirect("ListaLancamentos?acao=listalanc&id=" + idusu);

	}

}
