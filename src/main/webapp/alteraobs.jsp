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
	<nav id="menu" class="navbar navbar fixed-top navbar-dark bg-dark navbar-expand-lg bg-body-tertiary">
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
					    href="javascript:history.back()" aria-current="page" title="Retornar à tela anterior">Voltar</a></li>
					<li onclick="logout()" class="nav-item"><a class="nav-link text-right-bold"
						name="sair" href="LogoutServlet" title="Fechar o sistema">Sair do Sistema</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<h2>Criar/Alterar Observações</h2>
		<hr />

		<%
		   Lancamento lanc = (Lancamento)request.getAttribute("lancamento");
		%>

		<form class="row g-3" action="ObsServlet" method="POST">
		
			<div class="col-md-12">
					<label for="obstext" class="form-label">Observações</label> 
					<textarea class="form-control" id="obstext" name="obstext" rows="3" cols="50"><%=lanc.getObstext()%></textarea>
				</div><br>
				
				
					<hr />
				<div class="col-md-12">
					<!-- <label for="parc" class="form-label">Número da Parcela</label> -->
					<input type="hidden" class="form-control" id="disabledInput"
						name="parc" value="<%=lanc.getParc()%>" required>
				</div>
				<div class="col-md-12">
					<!-- <label for="qtdparc" class="form-label">Quantidade de Parcelas</label> -->
					<input type="hidden" class="form-control" id="parc" name="qtdparc"
						value="<%=lanc.getQtdparc()%>" required>
				</div>

				<div class="col-12">
					<button type="submit" class="btn btn-primary">Salvar</button>
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
	     $('#valor').mask("#.##0,00", {reverse: true});
	</script>
    <script>
		function logout() {
			var resultado = confirm("Confirma a saída do PCMoney?");
			if (resultado == false) {
				event.preventDefault();
			}
		}
	</script>
</body>

</html>