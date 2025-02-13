<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html lang="pt-BR">
<html data-theme="light" style="--100vh: 100vh; color-scheme: light;">


<head>
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>PCmoney - Planej Financeiro</title>

<!-- Bootstrap CSS -->
<!-- <link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous"> -->


<link rel="icon" type="image/png" href="img/favicon.png">
<!-- Bootstrap Icons -->

<!-- Bootstrap CSS (se necessário) -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/styles.css">	

</head>
<style>
.container {
	width: 90%;
	max-width: 1200px;
	margin: auto;
	padding: 20px;
	background-color: #f0f0f0;
}

.section {
	background: white;
	padding: 20px;
	margin: 20px 0;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);

}

h2 {
	color: #2980b9;
}

ul, ol {
	padding-left: 20px;

}

footer {
	background-color: #2c3e50;
	color: white;
	text-align: center;
	padding: 10px;
	position: relative;
	bottom: 0;
	width: 100%;
}

@media ( max-width : 768px) {
	.container {
		width: 95%;
	}
}
</style>
</head>

<body>

	<!-- Barra de navegação -->
	<jsp:include page="/nav/nav-login.jsp" />

	<div class="container">


		<section class="section">
			<div class="row align-items-center">
				<!-- Coluna de Imagem -->
				<div class="col-md-6 text-center no-margin">
					<img src="img/Desktop_pcmoney.png"
						alt="Planejamento Financeiro desktop" class="img-fluid rounded-0"
						style="max-width: 100%; height: auto;">
				</div>

				<!-- Coluna de Texto -->
				<div class="col-md-6 text-left mx-0">
					<h2>PCmoney - Planejamento Financeiro</h2>
					<p>Seu parceiro para alcançar a liberdade financeira.</p>
				</div>

			</div>
		</section>


		<section class="section">
			<div class="row align-items-center">
				<!-- Coluna de Imagem -->
				<div class="col-md-6 text-center no-margin">
					<img src="img/celular.jpg" alt="Planejamento Financeiro celular"
						class="img-fluid rounded-0" style="max-width: 100%; height: auto;">
				</div>
				<!-- Coluna de Texto -->
				<div class="col-md-6 text-left mx-0">
					<h2>Sobre o PCmoney</h2>
					<p>O PCmoney simplifica o controle das suas finanças e te guia
						para o sucesso financeiro.</p>
				</div>

			</div>
		</section>


		<section class="section">
			<div class="row align-items-center">
				<!-- Coluna de Imagem -->
				<div class="col-md-6 text-center no-margin">
					<img src="img/dolar.jpg" alt="Planejamento Financeiro dolar"
						class="img-fluid rounded-0" style="max-width: 100%; height: auto;">
				</div>
				<!-- Coluna de Texto -->
				<div class="col-md-6 text-left mx-0">
					<h2>Funcionalidades do Sistema</h2>
					<ul>
						<li>Controle de Orçamento</li>
						<li>Metas Financeiras</li>
						<li>Análise de Fluxo de Caixa</li>
						<li>Relatórios Detalhados</li>
					</ul>

				</div>
			</div>
		</section>


		<section class="section">
			<div class="row align-items-center">
				<!-- Coluna de Imagem -->
				<div class="col-md-6 text-center no-margin">
					<img src="img/casa.png" alt="Planejamento Financeiro dolar"
						class="img-fluid rounded-0" style="max-width: 100%; height: auto;">
					<!--  <img src="img/casa.png" alt="Planejamento Financeiro casa" class="img-fluid rounded-0" style="max-width: 100%; height: auto;"> -->
				</div>
				<!-- Coluna de Texto -->
				<div class="col-md-6 text-left mx-0">
					<h2>Benefícios do Gerenciamento Financeiro</h2>
					<ul>
						<li>Organização e controle financeiro</li>
						<li>Conscientização dos hábitos de gastos</li>
						<li>Economia e independência financeira</li>
						<li>Segurança para o futuro</li>
					</ul>
				</div>
			</div>
		</section>

		<section class="section">
			<!-- style="background-image: url('img/dolar.png'); background-size: cover; background-position: center;"> -->
			<div class="text-center text-light">
				<h2>Configuração de Metas e Orçamento</h2>
			</div>
			<br>
			<hr>
			<br>
			<div class="row text-center">
				<div class="col-6 col-md-3 mb-4">
					<div class="box p-3 border bg-light">
						Defina metas claras e alcançáveis.
						</bold>
					</div>
				</div>
				<div class="col-6 col-md-3 mb-4">
					<div class="box p-3 border bg-light">Crie um orçamento
						detalhado.</div>
				</div>
				<div class="col-6 col-md-3 mb-4">
					<div class="box p-3 border bg-light">Ajuste e monitore seu
						progresso.</div>
				</div>
				<div class="col-6 col-md-3 mb-4">
					<div class="box p-3 border bg-light">Relatórios Detalhados</div>
				</div>
			</div>
		</section>
	</div>
	<footer>
		<p>&copy; 2025 PCmoney. Todos os direitos reservados.</p>
	</footer>

	<!-- Bootstrap JS e Popper.js (necessário para o menu funcionar) -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
