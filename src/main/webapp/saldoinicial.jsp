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
<title>PCMoney | Saldo Incicial</title>

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
		<!-- Barra de navegação -->
		<jsp:include page="/nav/nav-lista.jsp" />

	<div class="container">
		<h2>Lançamento do Saldo Inicial</h2>
		<hr />
		<%
		Usuario userName = (Usuario) request.getAttribute("usuario");
		
		%>
		<form class="row g-3" action="CreateSaldoInicial" method="POST">
			<div class="container mt3">
				<div class="col-4">
					<label for="tplanc" class="form-label">Saldo Credor/Devedor</label>
					<select name="tplanc" class="form-select mb-3" required>
						<option value="" selected></option>
						<option value="C">Credor</option>
						<option value="D">Devedor</option>
					</select>
				</div>
				<div class="col-4">
					<label for="valor" class="form-label">Valor</label> <input
						type="text" class="form-control" id="valor" name="valor" required>
				</div>
			</div>
			<div class="col-2">
				<!-- <label for="tipo" class="form-label">Tipo</label> -->
				<input type="hidden" class="form-control" id="tipo" name="tipo"
					value="F" readonly>
			</div>
			<div class="col-2">
				<!-- <label for="nome" class="form-label">Nome</label> -->
				<input type="hidden" class="form-control" id="nome" name="nome"
					value="Lançamento Saldo Inicial" readonly>
			</div>
			<div class="col-md-2">
				<!-- <label for="descr" class="form-label">Descrição</label> -->
				<input type="hidden" class="form-control" id="descricao"
					name="descr" value="Saldo Inicial" readonly>
			</div>

			<div class="col-md-3">
				<!-- <label for="parc" class="form-label">Número da Parcela</label> -->
				<input type="hidden" class="form-control" id="parc" name="parc"
					Value=0 readonly>
			</div>
			<div class="col-md-3">
				<!-- <label for="qtdparc" class="form-label">Quantidade de Parcelas</label> -->
				<input type="hidden" class="form-control" id="qtdparc"
					name="qtdparc" Value=0 readonly>
			</div>
			<hr />
			<div class="col-12">
				<button type="submit" class="btn btn-primary">Salvar</button>
				<button type="reset" class="btn btn-primary">Limpar</button>
				<input type="button" value="Cancelar" class="btn btn-primary"
					onCLick="history.go(-1);">
			</div>

			<div class="col-md-8">

				<!-- <label for="user" class="form-label">Usuário</label> -->
				<input type="hidden" class="form-control" id="user" name="user"
					value="<%=userName.getNome()%>" required>
			</div>
			<div class="col-md-12">
				<!-- <label for="idusu" class="form-label">Id Usuário</label> -->

				<!-- recupera o parametro id da URL -->
				<%
				String parametroId = request.getParameter("id");
				%>
				<input type="hidden" class="form-control" id="idid" name="idid"
					value="<%=parametroId%>" required>
			</div>
			<div>
				<!-- Datas para inserir na tabela do período -->
				<%
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate today = LocalDate.now();
				LocalDate newDate = today.plusYears(1);
				String dataInicial = today.format(formatter);
				String dataFinal = newDate.format(formatter);
				%>

				<input type="hidden" class="form-control" id="dataIncial"
					name="dataIncial" value="<%=dataInicial%>" required> <input
					type="hidden" class="form-control" id="dataFinal" name="dataFinal"
					value="<%=dataFinal%>" required>
			</div>


		</form>

	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js">
		
	</script>
	<script>
		$(document).ready(function() {
			$('#valor').mask('#.###.##0,00', {
				reverse : true
			});
		});
	</script>
</body>
</html>
