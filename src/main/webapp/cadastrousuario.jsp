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
<title>PCMoney | Cadastro</title>

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
<!-- Bootstrap Icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.5.0/font/bootstrap-icons.min.css">


</head>

<body>

	<!-- Barra de navegação -->
	<jsp:include page="/nav/nav-login.jsp" />

	<div class="container">
		<h2>Crie sua conta</h2>
		<hr />

		<!-- Exibe mensagem de erro -->
		<c:if test="${mensagemErro != null}">
			<div class="alert alert-danger alert-dismissible fade show">
				<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
				<span><c:out value="${mensagemErro}" /></span>
			</div>
		</c:if>

		<form class="row g-3" action="CadastroUsuario" method="POST"
			id="formCadastro">
			<div class="container mt3">
				<div class="col-md-12">
					<label for="inputName" class="form-label">Nome</label> <input
						type="text" class="form-control" id="inputName" name=nome required
						title="Preencha com seu nome ou como quer ser chamado(a).">
				</div>
				<br>
				<div class="col-md-12">
					<label for="inputEmail" class="form-label">E-mail</label> <input
						type="email" class="form-control" id="inputEmail" name=email
						required title="Preencha com seu e-mail">
				</div>
				<br>
				<div class="col-md-12">
					<label for="inputPassword" class="form-label">Senha</label> 
					<!-- <input type="password" class="form-control" id="inputPassword" name=senha
						required title="Crie sua senha"> -->
						
					<div class="input-group">
						<input type="password" class="form-control" id="senha"
							name="senha" required title="Preencha com sua senha cadastrada no PCMoney."> 
							
						<span class="input-group-text"
							onclick="togglePassword()" style="cursor: pointer;"> <i
							class="bi bi-eye" id="togglePasswordIcon"></i>
						</span>
					</div>
						 
				</div>
				<hr>
				<div class="col-12">
					<button type="submit" class="btn btn-primary">Salvar</button>
					<button type="reset" class="btn btn-primary">Limpar</button>
				</div>
			</div>
		</form>

	</div>
	<script>

	<!-- Validação de senha (com mínimo de 4 caracteres)com JavaScript -->
		document.getElementById("formCadastro").addEventListener("submit",
				function(event) {
					var senha = document.getElementById("senha").value;

					// Verifica se a senha tem pelo menos 4 caracteres
					if (senha.length < 4) {
						alert("A senha deve ter no mínimo 4 caracteres.");
						event.preventDefault(); // Impede o envio do formulário
					}
				});
	</script>

	<!-- JavaScript para alternar a visibilidade do campo de entrar com a nova senha -->
	<script>
		function togglePassword() {
			const passwordField = document.getElementById('senha');
			const toggleIcon = document.getElementById('togglePasswordIcon');

			if (passwordField.type === 'senha') {
				passwordField.type = 'text';
				toggleIcon.classList.remove('bi-eye');
				toggleIcon.classList.add('bi-eye-slash');
			} else {
				passwordField.type = 'senha';
				toggleIcon.classList.remove('bi-eye-slash');
				toggleIcon.classList.add('bi-eye');
			}
		}
	</script>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>

</body>

</html>