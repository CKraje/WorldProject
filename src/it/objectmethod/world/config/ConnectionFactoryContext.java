package it.objectmethod.world.config;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionFactoryContext {
	private static Context ctx;
	public static Connection getConnection() {
		Connection conn = null;
		try {
			if(ctx == null) {
				ctx = new InitialContext();
			} 
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/"+"jdbc/local");
			if (ds==null) {
				throw new Exception("Impossibile trovare la configurazione per la connessione");
			}
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();		
		}
		return conn;
	}
}
