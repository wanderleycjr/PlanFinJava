<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PCMoney | E-mail</title>


<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link
	href="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/css/bootstrap.min.css"
	rel="stylesheet" />

<link rel="stylesheet" href="css/styles.css">
<link rel="icon" type="image/png" href="img/favicon.png">

</head>

<body>

	<!-- Barra de navegação -->
	<jsp:include page="/nav/nav-login.jsp" />

	<div class="container">
		<h2>Entre com seu e-mail cadastrado</h2>
		<hr />

		<!-- Exibe mensagem de sucesso -->
		<c:if test="${mensagem != null}">
			<div class="alert alert-success alert-dismissible fade show">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<span><c:out value="${mensagem}" /></span>
			</div>
		</c:if>

		<!-- Exibe mensagem de e-mail enviado -->
		<c:if test="${mensagemerro != null}">
			<div class="alert alert-danger alert-dismissible fade show">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>

				<span><c:out value="${mensagemerro}" /></span>
			</div>
		</c:if>	


	<form class="row g-3" action="EmailRedefSenha" method="POST">
		<div class="container mt3">
			<div class="col-12">
				<label for="email" class="form-label">Informe seu e-mail
					cadastrado no PCMoney e será enviado um e-mail com o link para
					redefinição da senha.</label> <input type="email" class="form-control"
					id="email" name="recipiente" required>
			</div>
			<br>
			<hr>
			<div class="row">
				<div class="col-md-6">
					<button type="submit" class="btn btn-primary" name="Entrar">Enviar</button>
					<button type="button" class="btn btn-primary" name="Fechar" onclick="fecharPagina()">Fechar</button>
				</div>
			</div>
		</div>
	</form>
</div>


	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>

	<script>
		function fecharPagina() {
			if (confirm("Confirma o fechamento da página?")) {
				window.close();
			}
		}
	</script>

</body>

</html>