package com.pcmoney.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;

import com.pcmoney.db.MySqlConnectionPcMoney;
import com.pcmoney.model.SaldoAnterior;

public class SaldoAnteriorDAO {
	
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
	
	// Método que soma os valores listados com id do usuário anteriores ao período definido
	public static SaldoAnterior SomaValoresPeloId(Integer id, String dataDe) throws SQLException {
	
		sql = "SELECT * FROM tbpcm02 WHERE LAIDUSU = ? AND LADTPGTO < ? ORDER BY LADTPGTO ASC, LATPLANC ASC";
		

		
		double totalSaldoAnterior = 0.00;

		try {
			
			conectar();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setDate(2, Date.valueOf(dataDe));

			// Retorno da consulta em Resultset 
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				
				String tplanc = resultSet.getString("latplanc");
				
				if (tplanc.equals("C")) {
				totalSaldoAnterior += resultSet.getDouble("lavalor");
				} else {
					totalSaldoAnterior -= resultSet.getDouble("lavalor");
				}
				
			}
			
			SaldoAnterior lancSoma = new SaldoAnterior();
			lancSoma.setValorTotalAnterior(totalSaldoAnterior);
			
			System.out.println("Lançamentos listados corretamente com o Id = " + id);
			
			desconectar();
			
			return lancSoma;

		} catch (SQLException e) {
			System.out.println("Lançamentos não encontrados por Id - " + e.getMessage());
			
			desconectar();
			
			return null;
		}
	}
}
