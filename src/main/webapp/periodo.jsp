<%@page import="java.io.PrintWriter"%>
<%@page import="com.pcmoney.model.*"%>
<%@page import="com.pcmoney.dao.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.format.DateTimeFormatter"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PCMoney | Período</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/styles.css">
<link rel="icon" type="image/png" href="img/favicon.png">
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
					<li class="nav-item"><a class="nav-link"
						href="javascript:history.back()" aria-current="page"
						title="Retornar à tela anterior">Voltar</a></li>
					<li onclick="logout()" class="nav-item"><a
						class="nav-link text-right-bold" name="sair" href="LogoutServlet"
						title="Fechar o sistema">Sair do Sistema</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container">
		<h2>Períodos</h2>
		<hr />
		<%
		Usuario userName = (Usuario) request.getAttribute("usuario");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate today = LocalDate.now();
		String dataIni = today.format(formatter);
		String dataFin = today.format(formatter);
		%>
		<form class="row g-3" action="DefinirPeriodo" method="POST"
			onsubmit="return validarDatas()">
			<div class="container mt3">
				<div class="col-md-12">
					<label for="dataInicial" class="form-label">Data Inicial</label> <input
						type="date" class="form-control" id="dataInicial"
						name="dataInicial" value="<%=dataIni%>" required>
				</div>
				<br>
			</div>
			<div class="container mt3">
				<div class="col-md-12">
					<label for="dataFinal" class="form-label">Data Final</label> <input
						type="date" class="form-control" id="dataFinal" name="dataFinal"
						value="<%=dataFin%>" required>
				</div>
			</div>
			<hr />
			<div class="col-12">
				<button type="submit" class="btn btn-primary" name="definir">Definir</button>
				<input type="button" value="Cancelar" class="btn btn-primary"
					onclick="history.go(-1);">
			</div>
			<div class="col-md-12">
				<%
				String parametroId = request.getParameter("id");
				%>
				<input type="hidden" class="form-control" id="id" name="id"
					value="<%=parametroId%>" required>
			</div>
			<div id="mensagemErro" class="col-md-12"></div>
			<!-- Div para exibir a mensagem de erro -->
		</form>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script>
		function validarDatas() {
			var dataInicial = document.getElementById('dataInicial').value;
			var dataFinal = document.getElementById('dataFinal').value;
			var mensagemErro = document.getElementById('mensagemErro');

			if (dataInicial && dataFinal) {
				if (new Date(dataFinal) < new Date(dataInicial)) {
					mensagemErro.textContent = 'A data final não pode ser menor que a data inicial.';
					mensagemErro.style.color = 'red';
					return false; // Impede o envio do formulário
				} else {
					mensagemErro.textContent = ''; // Limpa a mensagem de erro
					return true; // Permite o envio do formulário
				}
			}
			return true; // Permite o envio se qualquer data estiver ausente
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
		document.getElementById('dataInicial').addEventListener('change', function() {
			let datainicial = this.value;
			document.getElementById('dataFinal').value = datainicial;
		});
	</script>
</body>
</html>
