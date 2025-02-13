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
<title>PCMoney | Lançamentos</title>

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
		<h2>Lançamentos</h2>
		<hr />
		<%
		Usuario user = (Usuario) request.getAttribute("usuario");
		%>
		<form class="row g-3" action="CreateAndFindLanc" method="POST">
			<div class="container mt3">
				<div class="col-12">
					<label for="tplanc" class="form-label">Tipo de Lançamento</label> <select
						id="tplanc" name="tplanc" class="form-select mb-3" required
						onchange="updatePagoOptions()">
						<option value="" selected></option>
						<option value="C">Crédito</option>
						<option value="D">Débito</option>
					</select>
				</div>

				<div class="col-12">
					<label for="tipo" class="form-label">Tipo Despesa/Receita</label> <select
						name="tipo" class="form-select mb-3" required>
						<option value="" selected></option>
						<option value="F">Fixa</option>
						<option value="V">Variável</option>
					</select>
				</div>
				<div class="col-12">
					<label for="nome" class="form-label">Nome</label> <input
						type="text" class="form-control" id="nome" name="nome" required>
				</div>
				<br>
				<div class="col-md-12">
					<label for="descr" class="form-label">Descrição</label> <input
						type="text" class="form-control" id="descricao" name="descr"
						required>
				</div>
				<br>

				<div class="row">
					<div class="col-md-4">
						<label for="valor" class="form-label">Valor</label> <input
							type="text" class="form-control" id="valor" name="valor"
							placeholder="0,00" required>
					</div>

					<div class="col-md-4">
						<label for="pago" class="form-label">Pagamento/Recebimento</label>
						<select id="pago" name="pago" class="form-select mb-3" required>
							<option value="" selected></option>
							<option value="1">Pago</option>
							<option value="0">À pagar</option>
						</select>
					</div>

					<div class="col-md-4">
						<label for="qtdparc" class="form-label">Quantidade de
							Parcelas</label> <input type="number" class="form-control" id="qtdparc"
							name="qtdparc" value=1 required>
					</div>
				</div>

				<br>

				<%
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate today = LocalDate.now();
				String dataVenc = today.format(formatter);
				String dataPagamento = today.format(formatter);
				%>

				<div class="row">
					<div class="col-md-6">
						<label for="venc" class="form-label">Vencimento</label> <input
							type="date" class="form-control" id="venc" name="venc"
							value="<%=dataVenc%>" required>
					</div>
					<div class="col-md-6">
						<label for="dtpgto" class="form-label">Data de Pagamento</label> <input
							type="date" class="form-control" id="dtpgto" name="dtpgto"
							value="<%=dataPagamento%>" required>
					</div>
				</div>


				<br>
				<div class="col-md-12">
					<label for="obstext" class="form-label">Observações</label>
					<textarea class="form-control" id="obstext" name="obstext" rows="3"
						cols="50"></textarea>
					<!-- <input type="text" class="form-control" id="obstext" name="obstext"> -->
				</div>


				<div class="col-md-12">
					<!-- <label for="parc" class="form-label">Número da Parcela</label> -->
					<input type="hidden" class="form-control" id="parc" name="parc"
						Value=1 required>
				</div>

				<hr>
				<div class="col-12">
					<button type="submit" class="btn btn-primary">Salvar</button>
					<button type="reset" class="btn btn-primary">Limpar</button>
					<input type="button" value="Cancelar" class="btn btn-primary"
						onCLick="history.go(-1);">
				</div>
				<br>
				<div class="col-md-12">
					<!--  <label for="user" class="form-label">Usuário</label> -->
					<input type="hidden" class="form-control" id="user" name="user"
						value="<%=user.getNome()%>" required>
				</div>
				<br>

				<div class="col-md-12">
					<!-- <label for="idusu" class="form-label">Id Usuário</label> -->
					<input type="hidden" class="form-control" id="idid" name="idid"
						value="<%=user.getId()%>" required>
				</div>
				<br>
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
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
	<script>
		$(document).ready(function() {
			$('#valor').mask('#.###.##0,00', {
				reverse : true
			});
		});
	</script>
	<script>
		$(document).ready(function() {
			$('#juros').mask('##0,00', {
				reverse : true
			});
		});
	</script>
	<script>
		function atualizarDataPagamento() {
			// Obtém o valor do input de vencimento
			var vencimento = document.getElementById('venc').value;
			// Define o valor do input de data de pagamento como o mesmo valor
			document.getElementById('dtpgto').value = vencimento;
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
		function updatePagoOptions() {
			var tplanc = document.getElementById("tplanc").value;
			var pagoSelect = document.getElementById("pago");

			// Limpa as opções atuais de "pago"
			pagoSelect.innerHTML = '';

			if (tplanc === "C") {
				// Adiciona as opções para "Crédito"
				pagoSelect.innerHTML = '<option value="1">Recebido</option><option value="0">À receber</option>';
			} else {
				// Adiciona as opções padrão para "Débito"
				pagoSelect.innerHTML = '<option value="1">Pago</option><option value="0">À pagar</option>';
			}
		}
	</script>
	<script>
		function updatePagoOptions() {
			var tplanc = document.getElementById("tplanc").value;
			var pagoSelect = document.getElementById("pago");

			// Limpa as opções atuais de "pago"
			pagoSelect.innerHTML = '';

			if (tplanc === "C") {
				// Adiciona as opções para "Crédito"
				pagoSelect.innerHTML = '<option value="0">à receber</option><option value="1">recebido</option>';
			} else {
				// Adiciona as opções para "Débito"
				pagoSelect.innerHTML = '<option value="0">à pagar</option><option value="1">pago</option>';
			}
		}
	</script>
	<script>
		document.getElementById('venc').addEventListener('change', function() {
			let vencimento = this.value;
			document.getElementById('dtpgto').value = vencimento;
		});
	</script>
</body>
</html>
