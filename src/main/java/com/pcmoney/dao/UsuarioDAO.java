package com.pcmoney.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Time;
//import java.util.ArrayList;
//import java.util.List;

//import javax.servlet.RequestDispatcher;

import java.time.LocalDate;
import java.time.LocalTime;
import org.apache.commons.codec.digest.DigestUtils;

import com.pcmoney.db.MySqlConnectionPcMoney;
//import com.pcmoney.model.Periodo;
import com.pcmoney.model.Usuario;

public class UsuarioDAO {

	LocalTime localTime = LocalTime.now();

	private static Connection connection;

	private static String sql;

	private static void conectar() throws SQLException {

		LocalTime localTime = LocalTime.now();

		if (connection == null || connection.isClosed()) {
			connection = MySqlConnectionPcMoney.getConnection();

			if (connection != null) {
				System.out.println("Conexão aberta às " + Time.valueOf(localTime));
			} else {
				System.out.println("Erro na conexão às " + Time.valueOf(localTime));
			}
		}

	}

	private static void desconectar() throws SQLException {

		LocalTime localTime = LocalTime.now();

		if (connection != null && !connection.isClosed()) {
			connection.close();

			if (connection != null && !connection.isClosed()) {
				System.out.println("Conexão ainda aberta às " + Time.valueOf(localTime));
			} else {
				System.out.println("Conexão encerrada às " + Time.valueOf(localTime));
			}

		}

	}

	// Método para criar usuário chmado pelo UsuarioServlet
	public static Usuario criarUsuario(Usuario usuario) throws UnknownHostException, SQLException {
		sql = "INSERT INTO tbpcm01 VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		conectar();

		try {

			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();

			int privi = 1; // será implementado campo no cadastro.jsp para configurar o privilégio do
							// usuário pelo gestor
			int status = 1;// será implementado campo com status no cadastro.jsp com o status default "1"
							// (ficará oculto)

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, privi);
			preparedStatement.setString(2, usuario.getNome());
			preparedStatement.setString(3, usuario.getEmail());
			preparedStatement.setString(4, DigestUtils.md5Hex(usuario.getSenha()));
			preparedStatement.setString(5, InetAddress.getLocalHost().getHostAddress());
			preparedStatement.setDate(6, Date.valueOf(localDate));
			preparedStatement.setDate(7, Date.valueOf(localDate));
			preparedStatement.setTime(8, Time.valueOf(localTime));
			preparedStatement.setTime(9, Time.valueOf(localTime));
			preparedStatement.setInt(10, status);

			preparedStatement.executeUpdate();

			desconectar();

			System.out.println("Bem vindo ao PCmoney! Cadastro efetuado com sucesso!");

		} catch (SQLException e) {

			String mensagemErro;

			// Verifica se a exceção é causada por duplicidade de chave
			if (e.getMessage().contains("Duplicate entry")) {
				mensagemErro = "Este e-mail já está cadastrado no PCMoney. Por favor, use um e-mail diferente.";
			} else {
				mensagemErro = "Erro ao cadastrar usuário: " + e.getMessage();
			}

			desconectar();

			System.out.println(mensagemErro); // Log da mensagem de erro em português
			throw new SQLException(mensagemErro);

		} finally {
			desconectar();
		}
		return usuario;
	}

	// Método para o processo de login ao sistema chamado pelo UsuarioServlet
	public Usuario autenticaUsuario(Usuario usuario) throws SQLException {// Objeto de retorno do método

		Usuario usuRetorno = null;
		sql = "SELECT * FROM tbpcm01 WHERE usemail = ? AND ussenha = ?";

		conectar();

		try {

			System.out.println("email depois do select: " + usuario.getEmail());

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getSenha());

			System.out.println("email: " + usuario.getEmail());

			// Retorno da consulta em Resultset
			ResultSet resultado = statement.executeQuery();

			// Se tem registro
			if (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("usid"));
				usuRetorno.setNome(resultado.getString("usnome"));
				usuRetorno.setEmail(resultado.getString("usemail"));
				usuRetorno.setStatus(resultado.getInt("usstatus"));

				System.out.println("Usuario encontrado com sucesso!");
				desconectar();
			} else

				System.out.println("Usuario não encontrado.");
			desconectar();

		} catch (Exception e) {
			System.out.println("Erro - Na execução da query. - " + e.getMessage());
			desconectar();
		}
		return usuRetorno;
	}

	// Método para verificar se e-mail existe na tabela de usuários
	public Usuario verificaEmail(Usuario usuario) throws SQLException {// Objeto de retorno do método

		Usuario usuRetorno = null;
		String sql = "SELECT * FROM tbpcm01 WHERE usemail = ?";

		conectar();

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, usuario.getEmail());

			System.out.println("email: " + usuario.getEmail());

			// Retorno da consulta em Resultset
			ResultSet resultado = statement.executeQuery();

			// Se tem registro
			if (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("usid"));
				usuRetorno.setNome(resultado.getString("usnome"));
				usuRetorno.setEmail(resultado.getString("usemail"));
				usuRetorno.setStatus(resultado.getInt("usstatus"));

				System.out.println("E-mail encontrado com sucesso!");

				desconectar();

			} else

				System.out.println("Usuario não encontrado.");

			desconectar();

		} catch (Exception e) {
			System.out.println("E-mail - Na execução da query. - " + e.getMessage());

			desconectar();
		}
		return usuRetorno;
	}
	

	// Método para redefinir senha no registro do usuário
	public static void redefineNovaSenha(Usuario usuario) throws SQLException {

		sql = "UPDATE tbpcm01 SET ussenha=?, usdtat=?, ushrat=? WHERE usemail=?";

		LocalDate localDate = LocalDate.now();
		LocalTime localTime = LocalTime.now();

		try {
  
			conectar();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, DigestUtils.md5Hex(usuario.getSenha()));
			preparedStatement.setDate(2, Date.valueOf(localDate));
			preparedStatement.setTime(3, Time.valueOf(localTime));
			preparedStatement.setString(4, usuario.getEmail());

			preparedStatement.execute();

			desconectar();

			System.out.println("Alteração efetuada com sucesso!");

			System.out.println("Conexão encerrada!");

		} catch (SQLException e) {

			// MySqlConnectionPcMoney.closeDataSource();
			
			desconectar();

			System.out.println("Erro na alteração." + e.getMessage());

		}

	}
	
	// Método para buscar infornações de usuários pelo ID na lista de usuários
		public static Usuario buscarporID(Integer id) throws SQLException {// Objeto de retorno do método
			
			Usuario usuTest = new Usuario();
			
			System.out.println("teste de usuário: " + usuTest);

			Usuario usuRetorno = null;
			String sql = "SELECT * FROM tbpcm01 WHERE usid = ?";
			
			conectar();

			try {

				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, id);
				
				

				// Retorno da consulta em Resultset
				ResultSet resultado = statement.executeQuery();

				// Se tem registro
				if (resultado.next()) {
					usuRetorno = new Usuario();
					usuRetorno.setId(resultado.getInt("usid"));
					usuRetorno.setNome(resultado.getString("usnome"));
					usuRetorno.setEmail(resultado.getString("usemail"));
					usuRetorno.setStatus(resultado.getInt("usstatus"));
				}
				

				desconectar();
			} catch (Exception e) {
				System.out.println("Usuario não encontrado com sucesso!" + e.getMessage());
				desconectar();

			}
			return usuRetorno;
		}

		// Método para o processo de login ao sistema
		public Usuario autenticacao(Usuario usuario) throws SQLException {// Objeto de retorno do método

			Usuario usuRetorno = null;
			String sql = "SELECT * FROM tbpcm01 WHERE usemail = ? AND ussenha = ?";

			try {
				
				conectar();

				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, usuario.getEmail());
				statement.setString(2, usuario.getSenha());

				System.out.println("email: " + usuario.getEmail());

				// Retorno da consulta em Resultset
				ResultSet resultado = statement.executeQuery();

				// Se tem registro
				if (resultado.next()) {
					usuRetorno = new Usuario();
					usuRetorno.setId(resultado.getInt("usid"));
					usuRetorno.setNome(resultado.getString("usnome"));
					usuRetorno.setEmail(resultado.getString("usemail"));
					usuRetorno.setStatus(resultado.getInt("usstatus"));

					System.out.println("Usuario encontrado com sucesso!");
					
					//MySqlConnectionPcMoney.closeDataSource();

				} else

					System.out.println("Usuario não encontrado.");
				
				desconectar();

			} catch (Exception e) {
				System.out.println("Erro - Na execução da query. - " + e.getMessage());
				
				desconectar();
			}
			return usuRetorno;
		}

		// Método para recuperar os valores do usuário/período
		public static Usuario recuperaPeriodoUsuario(Integer id) throws SQLException {

			Usuario periodoRetorno = null;
			sql = "SELECT * FROM tbpcm01 INNER JOIN tbpcm03 ON tbpcm01.USID = tbpcm03.DTIDUSU\r\n"
					+ "WHERE tbpcm01.USID = ?";
			
			conectar();
			
			try {
				


				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, id);

				// Retorno da consulta em Resultset
				ResultSet recupera = statement.executeQuery();

				// Se tem registro
				if (recupera.next()) {
					periodoRetorno = new Usuario();
					periodoRetorno.setNome(recupera.getString("usnome"));
					periodoRetorno.setDataDeUs(recupera.getString("dtinicial"));
					periodoRetorno.setDataAteUs(recupera.getString("dtfinal"));
				}
				

				
				System.out.println("Recupera do Periodo/Usuario: " + periodoRetorno);
				
				desconectar();

			} catch (SQLException e) {
				

				
				System.out.println("-- Recuperação não realizada." + e.getMessage());
				
				desconectar();
			}
			return periodoRetorno;
		}
	
	

}



