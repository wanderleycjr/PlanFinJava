package com.pcmoney.dao;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import com.pcmoney.db.MySqlConnectionPcMoney;
import com.pcmoney.model.Periodo;
//import com.pcmoney.model.Periodo;
//mport com.pcmoney.model.Usuario;


public class PeriodoDAO {
	

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
	
	//Método para criar período na criação do cadastro do usuário
	public static void createPeriodo(Periodo periodo)  throws UnknownHostException, SQLException {
		
		sql = "INSERT INTO tbpcm03 VALUES (null, ?, ?, ?)";
		
		try {
			
			conectar();	
			
     		PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, periodo.getDataDe());
			preparedStatement.setString(2, periodo.getDataAte());
			preparedStatement.setInt(3, periodo.getIdusu());

			preparedStatement.execute();	
			preparedStatement.close();

			System.out.println("--Período criado.");
			
			desconectar();	

		} catch (SQLException e) {
			System.out.println("--Período não criado." + e.getMessage());
			
			desconectar();	
		}

	}
	
	
	//método para deletar periodo
	public static void deletaPeriodo(Periodo periodo) throws SQLException {
		
		sql = "DELETE FROM tbpcm03 WHERE dtidusu = ?";
		
	
		try {
			
			conectar();	
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, periodo.getIdusu());
			preparedStatement.execute();
			preparedStatement.close();
			
			System.out.println("Informação excluída na tabela tbpcm01 com sucesso!");
			
			desconectar();
			
		} catch (SQLException e) {

			System.out.println("Erro na exclusão." + e.getMessage());
			desconectar();
			
		} 
		
	}
	

	//Método para atualizar o período
	/*public static void atualizaPeriodo(Periodo periodo)  throws UnknownHostException {
		

		
		sql = "UPDATE tbpcm03 SET DTINICIAL=?, DTFINAL=? WHERE DTIDUSU=?";
		
				try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, periodo.getDataDe());
			preparedStatement.setString(2, periodo.getDataAte());
			preparedStatement.setInt(3, periodo.getIdusu());

			preparedStatement.execute();	
			preparedStatement.close();

			System.out.println("--Período atualizado.");

		} catch (SQLException e) {
			System.out.println("--Período não atualizado." + e.getMessage());
		}
	}*/

}