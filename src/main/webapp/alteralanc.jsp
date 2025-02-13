<%@page import="com.pcmoney.model.*"%>
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
<title>PCMoney | Alterar</title>

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
		<h2>Alterar Lançamento</h2>
		<hr />

		<%
		Lancamento lanc = (Lancamento) request.getAttribute("lancamento");
		%>

		<form class="row g-3" action="ListaLancamentos" method="POST">
			<div class="container mt3">

				<div class="col-12">

					<label for="tplanc" class="form-label">Selecione o Tipo de
						Lançamento</label> <select id="tplanc" name="tplanc"
						class="form-select mb-3" required onchange="updatePagoOptions()">

						<%
						String cred = "C";
						String deb = "D";
						String tplanc = lanc.getTplanc();
						if (tplanc.equals(cred)) {
						%>
						<option value="<%=lanc.getTplanc()%>" selected>Crédito</option>
						<%
						} else {
						if (tplanc.equals(deb)) {
						%>
						<option value="<%=lanc.getTplanc()%>" selected>Débito</option>
						<%
						}
						}
						%>

						<option value="C">Crédito</option>
						<option value="D">Débito</option>
					</select>
				</div>

				<div class="col-12">
					<label for="tipo" class="form-label">Selecione o Tipo
						Despesa/Receita</label> <select name="tipo" class="form-select mb-3"
						required>
						<%
						String fixa = "F";
						String vari = "V";
						String tipo = lanc.getTipo();
						if (tipo.equals(fixa)) {
							out.print("fixa");
						%>
						<option value="<%=lanc.getTipo()%>" selected>Fixa</option>
						<%
						} else {
						if (tipo.equals(vari)) {
						%>
						<option value="<%=lanc.getTipo()%>" selected>Variável</option>
						<%
						}
						}
						%>

						<option value="F">Fixa</option>
						<option value="V">Variável</option>
					</select>
				</div>
				<div class="col-12">
					<label for="nome" class="form-label">Nome</label> <input
						type="text" class="form-control" id="nome" name="nome"
						value="<%=lanc.getNome()%>" required>
				</div>
				<br>
				<div class="col-12">
					<label for="descr" class="form-label">Descrição</label> <input
						type="text" class="form-control" id="descricao" name="descr"
						value="<%=lanc.getDescr()%>" required>
				</div>
				<br>

				<div class="row">
					<div class="col-3">
						<label for="valor" class="form-label">Valor</label>
						<%
						String padrao = "#,###,###,###,##0.00";
						DecimalFormat df = new DecimalFormat(padrao);
						df.applyPattern(padrao);
						String valor = (df.format(lanc.getValor()));
						%>
						<input type="text" class="form-control" id="valor" name="valor"
							value="<%=valor%>" required>
					</div>

					<div class="col-3">

						<label for="pago" class="form-label">Pagamento/Recebimento</label>
						<select id="pago" name="pago" class="form-select mb-3" required>
							<%
							if ("C".equals(lanc.getTplanc())) {
								if ("1".equals(lanc.getPago())) {
							%>
							<option value="1" selected>recebido</option>
							<option value="0">à receber</option>
							<%
							} else {
							%>
							<option value="1">recebido</option>
							<option value="0" selected>à receber</option>
							<%
							}
							} else if ("D".equals(lanc.getTplanc())) {
							if ("1".equals(lanc.getPago())) {
							%>
							<option value="1" selected>pago</option>
							<option value="0">à pagar</option>
							<%
							} else {
							%>
							<option value="1">pago</option>
							<option value="0" selected>à pagar</option>
							<%
							}
							}
							%>
						</select>

					</div>

					<div class="col-md-3">
						<label for="parc" class="form-label">Parcela</label> <input
							type="text" class="form-control" id="disabledInput" name="parc"
							value="<%=lanc.getParc()%>" required>
					</div>

					<div class="col-md-3">
						<label for="qtdparc" class="form-label">Quantidade
							Parcelas</label> <input type="text" class="form-control" id="parc"
							name="qtdparc" value="<%=lanc.getQtdparc()%>" required>
					</div>
				</div>

				<br>

				<div class="row">
					<div class="col-md-6">
						<label for="venc" class="form-label">Vencimento</label> <input
							type="date" class="form-control" id="venc" name="venc"
							value="<%=lanc.getVenc()%>" required>
					</div>
					<br>
					<div class="col-md-6">
						<label for="dtpgto" class="form-label">Data de Pagamento</label> <input
							type="date" class="form-control" id="dtpgto" name="dtpgto"
							value="<%=lanc.getDtpgto()%>" required>
					</div>
				</div>


				<br>


				<div class="col-md-12">
					<label for="obstext" class="form-label">Observações</label>
					<textarea class="form-control" id="obstext" name="obstext" rows="3"
						cols="50"><%=lanc.getObstext()%></textarea>
				</div>
				<br>


				<hr />
				<div class="col-12">
					<button onclick="alterar()" type="submit" class="btn btn-primary">Alterar</button>
					<input type="button" value="Cancelar" class="btn btn-primary"
						onCLick="history.go(-1);">
				</div>
				<div class="col-md-12">
					<!-- <label for="user" class="form-label">Id do Lançamento</label> -->
					<input type="hidden" class="form-control" id="id" name="id"
						value="<%=lanc.getId()%>" required>
				</div>
				<div class="col-md-12">
					<!-- <label for="idusu" class="form-label">Id Usuário</label>  -->
					<input type="hidden" class="form-control" id="idusu" name="idusu"
						value="<%=lanc.getIdusu()%>">
				</div>
				<div class="col-md-12">
					<%
					String user = request.getParameter("user");
					%>
					<!-- <label for="user" class="form-label">Usuário</label>  -->
					<input type="hidden" class="form-control" id="user" name="user"
						value="<%=lanc.getUser()%>">
				</div>
			</div>
		</form>


	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.js"></script>
	<script>
		$('#valor').mask("#.##0,00", {
			reverse : true
		});
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
			var pagoAtual = pagoSelect.value; // Salva o valor atual selecionado

			// Limpa as opções atuais de "pago"
			pagoSelect.innerHTML = '';

			if (tplanc === "C") { // Crédito
				if (pagoAtual === "1") {
					pagoSelect.innerHTML = '<option value="1" selected>recebido</option><option value="0">à receber</option>';
				} else {
					pagoSelect.innerHTML = '<option value="1">recebido</option><option value="0" selected>à receber</option>';
				}
			} else if (tplanc === "D") { // Débito
				if (pagoAtual === "1") {
					pagoSelect.innerHTML = '<option value="1" selected>pago</option><option value="0">à pagar</option>';
				} else {
					pagoSelect.innerHTML = '<option value="1">pago</option><option value="0" selected>à pagar</option>';
				}
			}
		}
	</script>
	<script>
		document.getElementById('venc').addEventListener('change', function() {
			let vencimento = this.value;
			document.getElementById('dtpgto').value = vencimento;
		});
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