<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PCMoney | Redefinir Senha</title>

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
		<h2>Redefinir Senha</h2>
		<hr />
		<%
		String email = request.getParameter("email");
		%>

		<form class="row g-3" action="GravaNovaSenha" method="POST"
			id="formSenha">
			<div class="container mt3">
				<div class="col-12">
					<label for="password" class="form-label">Entre com a nova
						senha</label>
					<!-- <input type="password" class="form-control" id="password" name="password" required> -->
					<div class="input-group">
						<input type="password" class="form-control" id="password"
							name="password" required> <span class="input-group-text"
							onclick="togglePassword()" style="cursor: pointer;"> <i
							class="bi bi-eye" id="togglePasswordIcon"></i>
						</span>
					</div>
				</div>
				</br>
				<div class="col-12">
					<label for="confirmPassword" class="form-label">Confirme a
						nova senha</label>
					<!-- <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required> -->
					<div class="input-group">
						<input type="password" class="form-control" id="confirmPassword"
							name="confirmPassword" required> <span
							class="input-group-text" onclick="togglePasswordConfirm()"
							style="cursor: pointer;"> <i class="bi bi-eye"
							id="togglePasswordIcon"></i>
						</span>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-6">
						<button type="submit" class="btn btn-primary" name="Redefinir">Redefinir
							Senha</button>
						<button type="reset" class="btn btn-primary" name="Limpar">Limpar</button>
					</div>
				</div>
				</br>
				<div>
					<input type="hidden" id="email" name="email" value=<%=email%>
						required>
				</div>

			</div>
		</form>
	</div>
	<script>
	<!-- Validação de senha (com mínimo de 4 caracteres)com JavaScript -->
		document
				.getElementById("formSenha")
				.addEventListener(
						"submit",
						function(event) {
							var senha = document.getElementById("password").value;
							var confirmaSenha = document
									.getElementById("password").value;

							// Verifica se a senha tem pelo menos 8 caracteres
							if (senha.length < 4 || confirmaSenha.length < 4) {
								alert("A senha deve ter pelo menos 4 caracteres.");
								event.preventDefault(); // Impede o envio do formulário
							}
						});
	</script>

	<!-- Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script>
		// Script to validate that both password fields match
		const form = document.querySelector('form');
		form
				.addEventListener(
						'submit',
						function(event) {
							const password = document
									.getElementById('password').value;
							const confirmPassword = document
									.getElementById('confirmPassword').value;

							if (password !== confirmPassword) {
								alert('As senhas não coincidem. Por favor, verifique.');
								event.preventDefault(); // Prevent form submission
							}
						});
	</script>

	<!-- JavaScript para alternar a visibilidade do campo de entrar com a nova senha -->
	<script>
		function togglePassword() {
			const passwordField = document.getElementById('password');
			const toggleIcon = document.getElementById('togglePasswordIcon');

			if (passwordField.type === 'password') {
				passwordField.type = 'text';
				toggleIcon.classList.remove('bi-eye');
				toggleIcon.classList.add('bi-eye-slash');
			} else {
				passwordField.type = 'password';
				toggleIcon.classList.remove('bi-eye-slash');
				toggleIcon.classList.add('bi-eye');
			}
		}
	</script>

	<!-- JavaScript para alternar a visibilidade do campo de confirmação da senha -->
	<script>
		function togglePasswordConfirm() {
			const passwordField = document.getElementById('confirmPassword');
			const toggleIcon = document.getElementById('togglePasswordConfirm');

			if (passwordField.type === 'password') {
				passwordField.type = 'text';
				toggleIcon.classList.remove('bi-eye');
				toggleIcon.classList.add('bi-eye-slash');
			} else {
				passwordField.type = 'password';
				toggleIcon.classList.remove('bi-eye-slash');
				toggleIcon.classList.add('bi-eye');
			}
		}
	</script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js"></script>

</body>

</html>
