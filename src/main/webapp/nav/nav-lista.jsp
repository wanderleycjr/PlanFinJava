<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<nav id="menu" class="navbar fixed-top navbar-dark navbar-expand-lg" style="background-color: #2c3e50;">
		<div class="container-fluid">
			<a class="navbar=brand" href="index.jsp"> <img alt="pcmoney"
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
					<li onclick="logout()" class="nav-item"><a class="nav-link text-right-bold"
						name="sair" href="LogoutServlet" title="Fechar o sistema">Sair do Sistema</a>
					</li>
				</ul>
			</div>
		</div>
</nav>