<%@page import="java.util.List"%>
<%@page import="com.pcmoney.model.Lancamento"%>
<%@page import="com.pcmoney.model.Periodo"%>
<%@page import="com.pcmoney.model.SaldoAnterior"%>
<%@page import="com.pcmoney.dao.PeriodoDAO"%>
<%@page import="com.pcmoney.dao.SaldoAnteriorDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.DecimalFormatSymbols"%>
<%@page import="java.util.Locale"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PCMoney | Lançamentos</title>
<link rel="icon" type="image/png" href="img/favicon.png">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/styles.css">

</head>

<body>
	<nav id="menu" class="navbar fixed-top navbar-dark navbar-expand-lg" style="background-color: #2c3e50;">

		<div class="container-fluid">
			<a class="navbar=brand" href="login.jsp"> <img alt="pcmoney"
				src="img/pc_icone_6.png">
			</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">

					<%
					//Recupera o Id que vem na URL que chama esta página
					String id = request.getParameter("id");

					//Recupera as infomações da lista de lançamentos
					List<Lancamento> listaLancamento = (List<Lancamento>) request.getAttribute("listaLanc");

					//Recupera as informações da lista de lançamentos, mas apenas a primeira linha da lista para recupearar o valor inicial
					Lancamento lancamento = listaLancamento.get(0);

					//Recupera o nome do usuário que vem nas informações da lista de lanaçamentos
					String nome = lancamento.getUser();
					%>

					<li class="nav-item"><a class="nav-link" name="periodo"
						href="ListaLancamentos?acao=getUserPeriodo&id=<%=id%>"
						title="Criar um período de lançamentos entre datas">Definir
							Período</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						name="novo" href="ListaLancamentos?acao=getUser&id=<%=id%>"
						title="Criar um novo lançamento">Novo Lançamento</a></li>
					<li class="nav-item"><a onclick="logout()"
						class="nav-link text-right-bold" name="sair" href="LogoutServlet"
						title="Fechar o sistema">Sair do Sistema</a></li>

				</ul>
				<ul
					style="list-style: none; padding: 0; margin: 0; display: flex; justify-content: center; align-items: center;">
					<span style="color: white;"><h7 style="margin: 0;">
						Olá, <%=nome%>! Seja bem vindo(a) ao PCMoney!</h7></span>
				</ul>
			</div>
		</div>
		</div>

	</nav>

	<div class="container">

		<%
		//Recupera o período e trata as datas do período(DD/MM/YYYY)
		String dataDedt = lancamento.getDataDedt();
		dataDedt = dataDedt.replaceAll("(\\d{4})\\-(\\d{2})\\-(\\d{2})", "$3/$2/$1");
		String dataAtedt = lancamento.getDataAtedt();
		dataAtedt = dataAtedt.replaceAll("(\\d{4})\\-(\\d{2})\\-(\\d{2})", "$3/$2/$1");

		//Recupera o saldo anterior e trata pos valores (0,00)
		double saldoAnterior = ((SaldoAnterior) request.getAttribute("lancamentoSoma")).getValorTotalAnterior();
		String padraoSA = "#,###,###,###,##0.00";
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		DecimalFormat dfSA = new DecimalFormat(padraoSA, symbols);
		String saldoAnt = dfSA.format(saldoAnterior);

		//Recupera o saldo salod final, decrementando ou incrementando os valores e trata pos valores (0,00)
		double saldoFinal = 00.00;
		if (listaLancamento != null) {
			for (Lancamento sa : listaLancamento) {
				if (sa.getTplanc().equals("D")) {
			saldoFinal -= sa.getValor();
				} else {
			saldoFinal += sa.getValor();
				}
			}
			saldoFinal += saldoAnterior;
			String padraoSF = "#,###,###,###,##0.00";
			DecimalFormatSymbols symbolsSaldoFinal = new DecimalFormatSymbols(new Locale("pt", "BR"));
			symbolsSaldoFinal.setDecimalSeparator(',');
			symbolsSaldoFinal.setGroupingSeparator('.');
			DecimalFormat dfSF = new DecimalFormat(padraoSF, symbolsSaldoFinal);
			String saldoFin = dfSA.format(saldoFinal);
		%>

		<div class="row">
			<div class="cold-md-7">
				<table class="table table-borderless">
					<thead>
						<tr>
							<th><h2>Lista de Lançamentos</h2></th>
							<th
								style="text-align: right; font-weight: normal; margin-bottom: 20px;"><p>
									Período de<span style="color: blue;"> <%=dataDedt%></span> à <span
										style="color: blue;"><%=dataAtedt%></span>
								</p></th>
							<th
								style="text-align: right; font-weight: normal; margin-bottom: 20px;"><p>
									Saldo Anterior:<span style="color: blue;"> <%=saldoAnt%></span>
								</p></th>
							<th
								style="text-align: right; font-weight: normal; margin-bottom: 20px;"><p>
									Saldo Final:<span style="color: blue;"> <%=saldoFin%></span>
								</p></th>
						</tr>
					</thead>
				</table>
			</div>

			<hr class="bold-line" />

			<div class="cold-md-7">

				<table class="table table-hover">
					<thead>
						<tr>
							<!-- <th>Id</th> -->
							<th>Nome</th>
							<th>Descrição</th>
							<th>Data Venc</th>
							<th>Data Pgto</th>
							<th>Déb/Créd</th>
							<th>Parcela</th>
							<th style="text-align: right;">Valor</th>
							<th style="text-align: right;">Saldo</th>
							<th style="text-align: center;">Status</th>
							<th style="text-align: center;">Alterar</th>
							<th style="text-align: center;">Copiar</th>
							<th style="text-align: center;">Obs</th>
							<th style="text-align: center;">Excluir</th>
						</tr>
					</thead>

					<%
					Double saldoTotal = 0.00;
					String cred = "C";
					String deb = "D";
					String somaSA = "1";

					//for (Lancamento u : listaLancamento) {
					%>

					<tbody>

						<% for (Lancamento u : listaLancamento) { %>

						<!--<tr
							onclick="window.location.href='ListaLancamentos?acao=copiaLanc&id=<%=u.getId()%>&idusu=<%=u.getIdusu()%>'"
							style="cursor: pointer;" title="Clique aqui para copiar este lançamento"> -->
							
							
							<td hidden><%=u.getId()%></td>
							<td hidden><%=u.getTplanc()%></td>
							<td><%=u.getNome()%></td>
							<td><%=u.getDescr()%></td>

							<%
							//Convertento data de yyyy-MM-dd para dd/MM/yyyy							
							String pgto = u.getDtpgto();
							pgto = pgto.replaceAll("(\\d{4})\\-(\\d{2})\\-(\\d{2})", "$3/$2/$1");

							//Convertento data de yyyy-MM-dd para dd/MM/yyyy							
							String venc = u.getVenc();
							venc = venc.replaceAll("(\\d{4})\\-(\\d{2})\\-(\\d{2})", "$3/$2/$1");
							%>

							<td><%=venc%></td>
							<td><%=pgto%></td>


							<td><%=u.getTplanc()%></td>
							<td><%=u.getParc()%>/<%=u.getQtdparc()%></td>

							<%
							//Formatando valores com casas decimais 0,00
							String padrao = "#,###,###,###,##0.00";
							DecimalFormat df = new DecimalFormat(padrao);
							df.applyPattern(padrao);
							String valor = (df.format(u.getValor()));
							%>
							<td style="text-align: right;"><%=valor%></td>

							<%
							String tipoLanc = u.getTplanc();
							if (tipoLanc.equals(cred)) {
								saldoTotal = saldoTotal + u.getValor();
								if (somaSA.equals("1")) {
									saldoTotal += saldoAnterior;
									somaSA = "0";
								}

								String padraoCred = "#,###,###,###,##0.00";
								DecimalFormat dfCred = new DecimalFormat(padraoCred);
								dfCred.applyPattern(padraoCred);
								String valorCred = (dfCred.format(saldoTotal));
							%>
							<td style="text-align: right;"><%=valorCred%></td>
							<%
							} else {
							saldoTotal = saldoTotal - u.getValor();
							if (somaSA.equals("1")) {
								saldoTotal += saldoAnterior;
								somaSA = "0";
							}

							String padraoDeb = "#,###,###,###,##0.00";
							DecimalFormat dfDeb = new DecimalFormat(padraoDeb);
							dfDeb.applyPattern(padraoDeb);
							String valorDeb = (dfDeb.format(saldoTotal));
							%>
							<td style="text-align: right;"><%=valorDeb%></td>
							<%
							}
							%>

							<td hidden><%=u.getObstext()%></td>
							<td hidden><%=u.getIdusu()%></td>

							<td style="text-align: center;"><a
								href="AtualizaPagamentoServlet?id=<%=u.getId()%>&idusu=<%=u.getIdusu()%>&pago=<%=u.getPago()%>">
									<img
									src="img/checkbox_<%="1".equals(u.getPago()) ? "1" : "0"%>.png"
									alt="Pago" style="width: 15px; height: 15px;"
									title="<%
									if ("D".equals(u.getTplanc()) && "1".equals(u.getPago())) {
	                                    out.print("Pago");
                                    } else if ("D".equals(u.getTplanc()) && "0".equals(u.getPago())) {
	                                    out.print("À pagar");
                                    } else if ("C".equals(u.getTplanc()) && "1".equals(u.getPago())) {
	                                    out.print("Recebido");
                                    } else if ("C".equals(u.getTplanc()) && "0".equals(u.getPago())) {
	                                    out.print("À receber");
                                    }%>">
							</a></td>
							
							<!-- ícone de alterar -->
							<td style="text-align: center;"><a
								href="ListaLancamentos?acao=alteralanc&id=<%=u.getId()%>&idusu=<%=u.getIdusu()%>"
								title="Alterar lançamento"> <img src="img/modify2.png"
									style="width: 15px; height: 15px;"></a>

							</td>
							
							<!-- ícone de copiar -->
							<td style="text-align: center;"><a
								href="ListaLancamentos?acao=copiaLanc&id=<%=u.getId()%>&idusu=<%=u.getIdusu()%>"
								title="Copiar lançamento"> <img src="img/modify3.png"
									style="width: 15px; height: 15px;"></a> 

							</td>

                            <!-- ícone de observação -->
							<td style="text-align: center;"><a
								href="ObsServlet?acao=recObs&id=<%=u.getId()%>&idusu=<%=u.getIdusu()%>"
								title="Criar/Alterar Observações"> <img
									src="img/modify1.png" style="width: 15px; height: 15px;"></a></td>
									
                            <!-- ícone de exclusão -->
							<td style="text-align: center;"><a onclick="exclusao(event)"
								href="ListaLancamentos?acao=exclanc&id=<%=u.getId()%>&idusu=<%=u.getIdusu()%>"
								title="Excluir lançamento"> <img src="img/trash.png"
									style="width: 15px; height: 15px;">
							</a></td>

						</tr>
						<%
						}
						}
						%>


					</tbody>

				</table>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>
	<script>
		function exclusao(event) {
			// Impede o clique de propagar para o <tr>
			event.stopPropagation();

			// Exibe a mensagem de confirmação
			var resultado = confirm("Confirma a exclusão do lançamento?");

			// Impede o redirecionamento se o usuário clicar em "Cancelar"
			if (!resultado) {
				event.preventDefault();
			}
		}
	</script>
	<script>
		function logout() {
			var resultado = confirm("Confirma a saída do PCMoney?");
			if (resultado == false) {
				event.preventDefault();
			}
		}
	</script>
	<script>
		function alterar() {
			var resultado = confirm("Confirma a alteração do lançamento?");
			if (resultado == false) {
				event.preventDefault();
			}
		}
	</script>
</body>
</html>