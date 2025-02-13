
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav id="menu" class="navbar fixed-top navbar-dark navbar-expand-lg"
	style="background-color: #2c3e50;">
	<div class="container-fluid">
		<a class="navbar-brand" href="index.jsp"> <img alt="PCMoney"
			src="img/pc_icone_6.png">
		</a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="cadastrousuario.jsp" title="Opção para ter acesso ao PCMoney">Cadastre-se</a>
				</li>
				<li class="nav-item">
					<!-- Botão que abre o Modal --> <a class="nav-link" href="#"
					data-bs-toggle="modal" data-bs-target="#loginModal"
					title="Acessa o PCMoney">Entrar</a>
				</li>
			</ul>
		</div>
	</div>
</nav>

<!-- Modal de Login -->
<div class="modal fade" id="loginModal" tabindex="-1"
	aria-labelledby="loginModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="loginModalLabel">Login no PCMoney</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Fechar"></button>
			</div>
			<div class="modal-body">
				<!-- Carregar o conteúdo do login.jsp -->
				<iframe src="login.jsp"
					style="width: 100%; height: 400px; border: none;"></iframe>
			</div>
		</div>
	</div>
</div>


