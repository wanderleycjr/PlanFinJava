<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.List"%>
<%@page import="com.pcmoney.model.Usuario"%>
<%@page import="com.pcmoney.model.Periodo"%>
<%@page import="com.pcmoney.dao.PeriodoDAO"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>

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
	<nav id="menu"
		class="navbar navbar fixed-top navbar-dark bg-dark navbar-expand-lg bg-body-tertiary">

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

					Usuario usuario = (Usuario) request.getAttribute("user");

					String nome = usuario.getNome();
					%>

					<li class="nav-item"><a class="nav-link" name="periodo" href="ListaLancamentos?acao=getUserPeriodo&id=<%=id%>" title="Criar um período de lançamentos entre datas">Definir Período</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						name="novo" href="ListaLancamentos?acao=getUser&id=<%=id%>" title="Criar um novo lançamento">Novo Lançamento</a></li>
					<li class="nav-item"><a class="nav-link text-right-bold"
						name="sair" href="LogoutServlet" title="Fechar o sistema">Sair do Sistema</a></li>

				</ul>
				<ul
					style="list-style: none; padding: 0; margin: 0; display: flex; justify-content: center; align-items: center;">
					<span style="color: white;"><h6 style="margin: 0;">
							Olá,
							<%=nome%>! Seja bem vindo(a) ao PCMoney!
						</h6></span>
				</ul>
			</div>
		</div>

	</nav>

	<div class="container">

		<%
		String dataInicial = usuario.getDataDeUs();
		String dataFinal = usuario.getDataAteUs();

		dataInicial = dataInicial.replaceAll("(\\d{4})\\-(\\d{2})\\-(\\d{2})", "$3/$2/$1");
		dataFinal = dataFinal.replaceAll("(\\d{4})\\-(\\d{2})\\-(\\d{2})", "$3/$2/$1");
		%>

		<div class="row">
			<div class="cold-md-7">
				<table class="table table-borderless">
					<thead>
						<tr>
							<th><h2>Lista de Lançamentos</h2></th>
							<th
								style="text-align: right; font-weight: normal; margin-bottom: 20px;"><p
									class="fundo">
									Período de<span style="color: blue;"> <%=dataInicial%></span> à
									<span style="color: blue;"><%=dataFinal%></span>
								</p></th>
							<th
								style="text-align: right; font-weight: normal; margin-bottom: 20px;"><p>
									Saldo Anterior:<span style="color: blue;"> 0,00 </span>
								</p></th>
							<th
								style="text-align: right; font-weight: normal; margin-bottom: 20px;"><p>
									Saldo Final:<span style="color: blue;"> 0,00 </span>
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
							<th>Nome</th>
							<th>Descrição</th>
							<th>Vencimento</th>
							<th>Déb/Créd</th>
							<th style="text-align: right;">Valor</th>
							<th style="text-align: right;">Saldo</th>
						</tr>
					</thead>
				</table>
			</div>
			<hr class="bold-line" />
		</div>
	</div>
	<div class="centered-container">
	<h6>
	<span style="color: blue;">Não existem lançamentos para este período.</span></h6>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
	</script>
</body>
</html>