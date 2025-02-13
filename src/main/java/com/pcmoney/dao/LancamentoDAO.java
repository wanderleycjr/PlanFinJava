package com.pcmoney.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalTime;

import com.pcmoney.db.MySqlConnectionPcMoney;
import com.pcmoney.model.Lancamento;
//import com.pcmoney.model.Periodo;

public class LancamentoDAO {

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
	

	
	// Método para listar os lançamentos apenas pelo id do usuário
	public static List<Lancamento> recuperaListaPeloUsuario(Integer id) throws SQLException {

		sql = "SELECT * FROM tbpcm02 WHERE LAIDUSU = ? ORDER BY LADTPGTO ASC, LATPLANC ASC";
		
		conectar();

		List<Lancamento> lancamentos = new ArrayList<Lancamento>();

		try {

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			// Retorno da consulta em Resultset
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Lancamento lancLista = new Lancamento();

				lancLista.setId(resultSet.getInt("laid"));
				lancLista.setTplanc(resultSet.getString("latplanc"));
				lancLista.setTipo(resultSet.getString("latipo"));
				lancLista.setNome(resultSet.getString("lanome"));
				lancLista.setDescr(resultSet.getString("ladescr"));
				lancLista.setValor(resultSet.getDouble("lavalor"));
				lancLista.setVenc(resultSet.getString("lavenc"));
				lancLista.setDtpgto(resultSet.getString("ladtpgto"));
				lancLista.setParc(resultSet.getInt("laparc"));
				lancLista.setQtdparc(resultSet.getInt("laqtdparc"));
				lancLista.setMaq(resultSet.getString("lamaq"));
				lancLista.setUser(resultSet.getString("lauser"));
				lancLista.setData(resultSet.getString("ladata"));
				lancLista.setIdusu(resultSet.getInt("laidusu"));
				lancLista.setObstext(resultSet.getString("laobstext"));

				lancamentos.add(lancLista);

			}

			desconectar();
			
			System.out.println("Lançamentos listados corretamente com o Id = " + id);

			return lancamentos;

		} catch (SQLException e) {
			
			desconectar();

			System.out.println("Lançamentos não encontrados por Id - " + e.getMessage());
			return null;
			

		}

	}


	// Método para criar um lançamento
	public static void create(Lancamento lancamento) throws UnknownHostException, SQLException {

		sql = "INSERT INTO tbpcm02 VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			
			conectar();

			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();

			int somaQtd = 1;
			int qtdParc = lancamento.getQtdparc();
			LocalDate vencimento = LocalDate.parse(lancamento.getVenc());
			LocalDate pagamento = LocalDate.parse(lancamento.getDtpgto());

			while (somaQtd <= qtdParc) {

				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setString(1, lancamento.getTplanc());
				preparedStatement.setString(2, lancamento.getTipo());
				preparedStatement.setString(3, lancamento.getNome());
				preparedStatement.setString(4, lancamento.getDescr());
				preparedStatement.setDouble(5, (lancamento.getValor()));

				if (somaQtd > 1) {
					vencimento = vencimento.plusMonths(1); // Adiciona 1 mês ao vencimento
					pagamento = pagamento.plusMonths(1); // Adiciona 1 mês ao pagamento
				}

				// preparedStatement.setString(6, lancamento.getVenc());
				preparedStatement.setString(6, vencimento.toString());

				// preparedStatement.setString(7, lancamento.getDtpgto());
				preparedStatement.setString(7, pagamento.toString());

				// preparedStatement.setInt(8, lancamento.getParc());
				preparedStatement.setInt(8, somaQtd);

				preparedStatement.setInt(9, lancamento.getQtdparc());
				preparedStatement.setString(10, InetAddress.getLocalHost().getHostAddress());
				preparedStatement.setString(11, lancamento.getUser());
				preparedStatement.setDate(12, Date.valueOf(localDate));
				preparedStatement.setDate(13, Date.valueOf(localDate));
				preparedStatement.setTime(14, Time.valueOf(localTime));
				preparedStatement.setTime(15, Time.valueOf(localTime));
				preparedStatement.setInt(16, lancamento.getIdusu());
				preparedStatement.setString(17, lancamento.getObstext());
				preparedStatement.setString(18, lancamento.getPago());

				preparedStatement.execute();
				preparedStatement.close();

				somaQtd += 1;

			}

			System.out.println("Criação do(s) lançamento(s) efetuado(s) corretamente!");

			desconectar();

		} catch (SQLException e) {

			System.out.println("Erro - Lançamento não efetuado." + e.getMessage());
			
			desconectar();

		}

	}

	// Método para excluir um lançamento
	public static void delete(Lancamento lancamento) throws SQLException {

		sql = "DELETE FROM tbpcm02 WHERE laid = ?";

		try {
			
			conectar();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, lancamento.getId());
			preparedStatement.execute();
			preparedStatement.close();

			System.out.println("Lançamento excluído com sucesso!");

			desconectar();
			
		} catch (SQLException e) {
			System.out.println("Erro - Lançamento não foi excluído." + e.getMessage());
			
			desconectar();
		}

	}

	// Método pára listar os lançamentos
	public static List<Lancamento> find(Lancamento lancamento) throws SQLException {

		sql = String.format("SELECT * FROM tbpcm02");
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();

		try {
			
			

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				Lancamento lancLista = new Lancamento();

				lancLista.setId(resultSet.getInt("laid"));
				lancLista.setTplanc(resultSet.getString("latplanc"));
				lancLista.setTipo(resultSet.getString("latipo"));
				lancLista.setNome(resultSet.getString("lanome"));
				lancLista.setDescr(resultSet.getString("ladescr"));

				lancLista.setValor(resultSet.getDouble("lavalor"));

				// Convertendo ponto por virgula
				// Double val = lancLista.setValor(resultSet.getDouble("lavalor"));
				// lancLista.setValor(Double.parseDouble(val.replace(',','.')));

				lancLista.setVenc(resultSet.getString("lavenc"));
				lancLista.setDtpgto(resultSet.getString("ladtpgto"));
				lancLista.setParc(resultSet.getInt("laparc"));
				lancLista.setQtdparc(resultSet.getInt("laqtdparc"));
				lancLista.setMaq(resultSet.getString("lamaq"));
				lancLista.setUser(resultSet.getString("lauser"));
				lancLista.setData(resultSet.getString("ladata"));
				lancLista.setIdusu(resultSet.getInt("laidusu"));
				lancLista.setObstext(resultSet.getString("laobstext"));

				lancamentos.add(lancLista);

			}

			System.out.println("Lançamentos listados corretamente!");
			
			desconectar();
			
			return lancamentos;

		} catch (SQLException e) {
			System.out.println("Lançamentos não encontrados" + e.getMessage());
			
			desconectar();
			
			return null;
		}

	}

	// Método para listar os lançamentos pelo id do usuário e pelo período definido
	public static List<Lancamento> findById(Integer id) throws SQLException {
		sql = "SELECT * FROM tbpcm02 INNER JOIN tbpcm03 ON tbpcm02.LAIDUSU = tbpcm03.DTIDUSU "
				+ "WHERE tbpcm03.DTIDUSU = ? AND tbpcm02.LADTPGTO >= tbpcm03.DTINICIAL AND tbpcm02.LADTPGTO <= tbpcm03.DTFINAL "
				+ "ORDER BY tbpcm02.LADTPGTO ASC, tbpcm02.LATPLANC ASC";

		List<Lancamento> lancamentos = new ArrayList<Lancamento>();

		try {
			
			conectar();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);

			// Retorno da consulta em Resultset
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {

				Lancamento lancLista = new Lancamento();

				lancLista.setId(resultSet.getInt("laid"));
				lancLista.setTplanc(resultSet.getString("latplanc"));
				lancLista.setTipo(resultSet.getString("latipo"));
				lancLista.setNome(resultSet.getString("lanome"));
				lancLista.setDescr(resultSet.getString("ladescr"));
				lancLista.setValor(resultSet.getDouble("lavalor"));
				lancLista.setVenc(resultSet.getString("lavenc"));
				lancLista.setDtpgto(resultSet.getString("ladtpgto"));
				lancLista.setParc(resultSet.getInt("laparc"));
				lancLista.setQtdparc(resultSet.getInt("laqtdparc"));
				lancLista.setMaq(resultSet.getString("lamaq"));
				lancLista.setUser(resultSet.getString("lauser"));
				lancLista.setData(resultSet.getString("ladata"));
				lancLista.setIdusu(resultSet.getInt("laidusu"));
				lancLista.setObstext(resultSet.getString("laobstext"));
				lancLista.setPago(resultSet.getString("lapago"));

				lancLista.setDataDedt(resultSet.getString("dtinicial"));
				lancLista.setDataAtedt(resultSet.getString("dtfinal"));

				//String lapago = resultSet.getString("lapago");

				lancamentos.add(lancLista);

			}

			System.out.println("Lançamentos listados corretamente com o Id = " + id);
			
			desconectar();

			return lancamentos;

		} catch (SQLException e) {

			System.out.println("Lançamentos não encontrados por Id - " + e.getMessage());
			
			desconectar();
			
			return null;
		}

	}

	public static Lancamento findLancByPk(int lancamentoId) throws SQLException {

		//sql = String.format("SELECT * FROM tbpcm02 WHERE laid = %d ", lancamentoId);
		sql = String.format("SELECT * FROM tbpcm02 WHERE laid = ?");

		try {
			
			conectar();

			// Retorno da consulta em Resultset
			// ResultSet resultado = statement.executeQuery();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			Lancamento lancamento = new Lancamento();

			while (resultSet.next()) {

				// lancamento.setId(resultSet.getInt("id"));
				lancamento.setTplanc(resultSet.getString("latplanc"));
				// lancamento.setTipo(resultSet.getString("latipo"));
				lancamento.setNome(resultSet.getString("nome"));
				lancamento.setDescr(resultSet.getString("descr"));
				lancamento.setValor(resultSet.getDouble("valor"));
				lancamento.setVenc(resultSet.getString("venc"));
				lancamento.setDtpgto(resultSet.getString("dtpgto"));
				lancamento.setParc(resultSet.getInt("parc"));
				lancamento.setQtdparc(resultSet.getInt("qtdparc"));
				lancamento.setMaq(resultSet.getString("maq"));
				lancamento.setUser(resultSet.getString("user"));
				lancamento.setData(resultSet.getString("data"));
				lancamento.setIdusu(resultSet.getInt("idusu"));
				lancamento.setObstext(resultSet.getString("laobstext"));

			}

			System.out.println("--correct find by pk lançamentos");
			
			desconectar();
			
			return lancamento;

		} catch (SQLException e) {

			System.out.println("--correct find by pk lançamentos" + e.getMessage());
			
			desconectar();
			
			return null;
		}

	}

	// public static void update(Integer id, Integer idusu){
	public static void update(Lancamento lancamento) throws UnknownHostException, SQLException {
		sql = "UPDATE tbpcm02 SET latplanc=?, latipo=?, lanome=?, ladescr=?, lavalor=?, lavenc=?, ladtpgto=?, laparc=?, laqtdparc=?, ladtat=?, lahrat=?, laobstext = ?, lapago=? WHERE laid=? AND laidusu=?";

		try {
			
			conectar();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();

			preparedStatement.setString(1, lancamento.getTplanc());
			preparedStatement.setString(2, lancamento.getTipo());
			preparedStatement.setString(3, lancamento.getNome());
			preparedStatement.setString(4, lancamento.getDescr());
			preparedStatement.setDouble(5, lancamento.getValor());
			preparedStatement.setString(6, lancamento.getVenc());
			preparedStatement.setString(7, lancamento.getDtpgto());
			preparedStatement.setInt(8, lancamento.getParc());

			preparedStatement.setInt(9, lancamento.getQtdparc());

			preparedStatement.setDate(10, Date.valueOf(localDate));
			preparedStatement.setTime(11, Time.valueOf(localTime));
			preparedStatement.setString(12, lancamento.getObstext());
			preparedStatement.setString(13, lancamento.getPago());
			preparedStatement.setInt(14, lancamento.getId());
			preparedStatement.setInt(15, lancamento.getIdusu());

			String pgo = lancamento.getPago();

			System.out.println("este é o valor do lapago para atualizer " + pgo);

			preparedStatement.execute();
			preparedStatement.close();

			System.out.println("--correct update on database.");
			
			desconectar();

		} catch (SQLException e) {

			System.out.println("--incorrect update on database." + e.getMessage());
			
			desconectar();

		}
	}

	// Método para buscar infornações de lançamentos pelo ID na lista de lançamentos
	public static Lancamento buscarListaPorIDeIDUSU(Integer id, Integer idusu) throws SQLException {// Objeto de retorno do método

		Lancamento lancRetorno = null;
		String sql = "SELECT * FROM tbpcm02 WHERE laid = ? AND laidusu = ?";

		try {
			
			conectar();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setInt(2, idusu);

			// Retorno da consulta em Resultset
			ResultSet resultado = statement.executeQuery();

			// Se tem registro
			if (resultado.next()) {

				lancRetorno = new Lancamento();
				lancRetorno.setId(resultado.getInt("laid"));
				lancRetorno.setTplanc(resultado.getString("latplanc"));
				lancRetorno.setTipo(resultado.getString("latplanc"));
				lancRetorno.setTipo(resultado.getString("latipo"));
				lancRetorno.setNome(resultado.getString("lanome"));
				lancRetorno.setDescr(resultado.getString("ladescr"));
				lancRetorno.setValor(resultado.getDouble("lavalor"));
				lancRetorno.setVenc(resultado.getString("lavenc"));
				lancRetorno.setDtpgto(resultado.getString("ladtpgto"));
				lancRetorno.setIdusu(resultado.getInt("laidusu"));
				lancRetorno.setParc(resultado.getInt("laparc"));
				lancRetorno.setQtdparc(resultado.getInt("laqtdparc"));
				lancRetorno.setObstext(resultado.getString("laobstext"));
				lancRetorno.setPago(resultado.getString("lapago"));
				lancRetorno.setUser(resultado.getString("lauser"));

			}

			System.out.println("Lançamento encontrado com sucesso!");
			
			desconectar();

		} catch (Exception e) {

			System.out.println("Lançamento não encontrado com sucesso!" + e.getMessage());
			
			desconectar();
		}
		return lancRetorno;
	}

	// Método para criar um lançamento
	public static void saldoInicial(Lancamento lancamento) throws UnknownHostException, SQLException {

		sql = "INSERT INTO tbpcm02 VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			
			conectar();

			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();
			
			String pago = "1";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, lancamento.getTplanc());
			preparedStatement.setString(2, lancamento.getTipo());
			preparedStatement.setString(3, lancamento.getNome());
			preparedStatement.setString(4, lancamento.getDescr());
			preparedStatement.setDouble(5, (lancamento.getValor()));
			preparedStatement.setDate(6, Date.valueOf(localDate));
			preparedStatement.setDate(7, Date.valueOf(localDate));
			preparedStatement.setInt(8, lancamento.getParc());
			preparedStatement.setInt(9, lancamento.getQtdparc());
			preparedStatement.setString(10, InetAddress.getLocalHost().getHostAddress());
			preparedStatement.setString(11, lancamento.getUser());
			preparedStatement.setDate(12, Date.valueOf(localDate));
			preparedStatement.setDate(13, Date.valueOf(localDate));
			preparedStatement.setTime(14, Time.valueOf(localTime));
			preparedStatement.setTime(15, Time.valueOf(localTime));
			preparedStatement.setInt(16, lancamento.getIdusu());
			preparedStatement.setString(17, lancamento.getObstext());
			preparedStatement.setString(18, String.valueOf(pago));

			preparedStatement.execute();
			//preparedStatement.close();

			System.out.println("Criação do lançamento inicial efetuado corretamente!");
			
			desconectar();

		} catch (SQLException e) {
			
			desconectar();

			System.out.println("Erro - Lançamento inicial não efetuado." + e.getMessage());

		}

	}



	// Método para buscar infornações das observações pelo ID na lista de
	// lançamentos
	public static Lancamento buscarObsPorIDeIDUSU(Integer id, Integer idusu) throws SQLException {// Objeto de retorno do método

		Lancamento lancRetorno = null;
		String sql = "SELECT * FROM tbpcm02 WHERE laid = ? AND laidusu = ?";

		try {
			
			conectar();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			statement.setInt(2, idusu);

			// Retorno da consulta em Resultset
			ResultSet resultado = statement.executeQuery();

			// Se tem registro
			if (resultado.next()) {
				lancRetorno = new Lancamento();
				lancRetorno.setId(resultado.getInt("laid"));
				lancRetorno.setIdusu(resultado.getInt("laidusu"));
				lancRetorno.setObstext(resultado.getString("laobstext"));
			}
			System.out.println("Observações encontradas com sucesso!");
			
			desconectar();

		} catch (Exception e) {
			
			desconectar();

			System.out.println("Observações não encontradas com sucesso!" + e.getMessage());
		}
		return lancRetorno;
	}

	// Método para atualizar as observações do lançamento
	public static void updateObs(Lancamento lancamento) throws UnknownHostException, SQLException {
		sql = "UPDATE tbpcm02 SET laobstext = ? WHERE laid=? AND laidusu=?";

		try {
			
			conectar();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, lancamento.getObstext());
			preparedStatement.setInt(2, lancamento.getId());
			preparedStatement.setInt(3, lancamento.getIdusu());

			preparedStatement.execute();
			preparedStatement.close();

			System.out.println("--correct update on database - observação.");
			
			desconectar();

		} catch (SQLException e) {
			
			desconectar();

			System.out.println("--incorrect update on database. - observação" + e.getMessage());

		}
	}

	// Método para atualizar as observações do lançamento
	public static void updatePgto(Lancamento lancamento) throws UnknownHostException, SQLException {
		sql = "UPDATE tbpcm02 SET lapago = ? WHERE laid=?";

		try {
			
			conectar();
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, lancamento.getPago());
			preparedStatement.setInt(2, lancamento.getId());
			// preparedStatement.setInt(3, lancamento.getIdusu());

			preparedStatement.execute();
			preparedStatement.close();
			
			desconectar();

			System.out.println("--correct update on database - pagamento.");

		} catch (SQLException e) {
			
			desconectar();

			System.out.println("--incorrect update on database. - pagamento" + e.getMessage());

		}
	}
	
	// Método para copiar um lançamento
	public static void copiaLanc(Lancamento lancamento) throws UnknownHostException, SQLException {

		sql = "INSERT INTO tbpcm02 VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			
			conectar();

			LocalDate localDate = LocalDate.now();
			LocalTime localTime = LocalTime.now();

			int somaQtd = 1;
			int qtdParc = lancamento.getQtdparc();
			LocalDate vencimento = LocalDate.parse(lancamento.getVenc());
			LocalDate pagamento = LocalDate.parse(lancamento.getDtpgto());

			while (somaQtd <= qtdParc) {

				PreparedStatement preparedStatement = connection.prepareStatement(sql);

				preparedStatement.setString(1, lancamento.getTplanc());
				preparedStatement.setString(2, lancamento.getTipo());
				preparedStatement.setString(3, lancamento.getNome());
				preparedStatement.setString(4, lancamento.getDescr());
				preparedStatement.setDouble(5, (lancamento.getValor()));

				if (somaQtd > 1) {
					vencimento = vencimento.plusMonths(1); // Adiciona 1 mês ao vencimento
					pagamento = pagamento.plusMonths(1); // Adiciona 1 mês ao pagamento
				}

				// preparedStatement.setString(6, lancamento.getVenc());
				preparedStatement.setString(6, vencimento.toString());

				// preparedStatement.setString(7, lancamento.getDtpgto());
				preparedStatement.setString(7, pagamento.toString());

				// preparedStatement.setInt(8, lancamento.getParc());
				preparedStatement.setInt(8, somaQtd);

				preparedStatement.setInt(9, lancamento.getQtdparc());
				preparedStatement.setString(10, InetAddress.getLocalHost().getHostAddress());
				preparedStatement.setString(11, lancamento.getUser());
				preparedStatement.setDate(12, Date.valueOf(localDate));
				preparedStatement.setDate(13, Date.valueOf(localDate));
				preparedStatement.setTime(14, Time.valueOf(localTime));
				preparedStatement.setTime(15, Time.valueOf(localTime));
				preparedStatement.setInt(16, lancamento.getIdusu());
				preparedStatement.setString(17, lancamento.getObstext());
				preparedStatement.setString(18, lancamento.getPago());

				preparedStatement.execute();
				preparedStatement.close();

				somaQtd += 1;

			}

			System.out.println("Cópia do(s) lançamento(s) efetuada(s) corretamente!");

			desconectar();

		} catch (SQLException e) {

			System.out.println("Erro - Cópia do lançamento não efetuado." + e.getMessage());
			
			desconectar();

		}

	}

}
