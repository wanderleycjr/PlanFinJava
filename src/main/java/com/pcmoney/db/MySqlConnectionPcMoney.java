package com.pcmoney.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MySqlConnectionPcMoney {

	private static final String RESOURCE = "java:/comp/env/jdbc/mysql";

	public static Connection getConnection() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(RESOURCE);
			return ds.getConnection();
		} catch (SQLException | NamingException e) {
			throw new RuntimeException(e);

		}

	}

}

