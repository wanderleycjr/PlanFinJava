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
<title>PCMoney | Login</title>

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
		<h2>Entre com sua conta</h2>
		<hr />

		<form class="row g-3" action="LoginUsuario" method="POST">
			<div class="container mt3">
			    <div class="col-12">
				    <label for="exampleInputEmail1" class="form-label">Digite
					   seu E-mail</label> <input type="email" class="form-control" id="email"
					   name="email" title="Preencha com seu e-mail cadastrado no PCMoney." required>
				</div>
     			<br>
			    <div class="col-12">
				   <label for="senha" class="form-label">Senha</label>
				
				   <div class="input-group">
				       <input type="password" class="form-control" id="senha" 
				           name="senha" title="Preencha com sua senha cadastrada no PCMoney." required>
			       
			           <span class="input-group-text" onclick="togglePassword()" style="cursor: pointer;"> 
			              <i class="bi bi-eye" id="togglePasswordIcon"></i>
					   </span>
				  </div>
			    </div>
            </div>
            
			<hr>

			<div class="row">
				<div class="col-6">
					<button type="submit" class="btn btn-primary">Entrar</button>
					<button type="reset" class="btn btn-primary">Limpar</button>
				</div>

				<!-- <div class="col-md-6" style="text-align: right;"
					onclick="window.location.href='esqueciminhasenha.jsp';"
					role="button">
					<h6>
						<a href="#" style="text-decoration: none; color: inherit;">Esqueci
							minha senha</a>
					</h6>
				</div> -->

				<div class="col-md-6" style="text-align: right;">
					<h6>
						<a href="esqueciminhasenha.jsp">Esqueci minha senha</a>
					</h6>
				</div>


			</div>
		</form>
		<br>
		<h6>
			<a href="login.jsp">E-mail e/ou senha com erro. Favor tentar
				novamente.</a>
		</h6>

	</div>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/jquery-3.6.0-dist/jquery-3.6.0.min.js">
	</script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/bootstrap-5.1.3-dist/js/bootstrap.min.js">
	</script>
	
	<script>
		function togglePassword() {
			const passwordField = document.getElementById('senha');
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

</body>

</html>