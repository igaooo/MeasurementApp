package com.measurement.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionDataBank {
	private static ConnectionDataBank CONNECTION;
	private static EntityManagerFactory FACTORY;

	private ConnectionDataBank() {
		if (FACTORY == null) {
			FACTORY = getFactory();
		}
	}

	public static ConnectionDataBank getConnection() {
		if (CONNECTION == null) {
			CONNECTION = new ConnectionDataBank();
		}

		return CONNECTION;
	}

	public EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}

	private EntityManagerFactory getFactory() {
		Map<String, String> properties = new HashMap<String, String>();

		properties.put("javax.persistence.schema-generation.database.action", "update");
		properties.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
		properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/measurement");
		properties.put("hibernate.connection.username", "root");
		properties.put("hibernate.connection.password", "");
		properties.put("hibernate.c3p0.min_size", "10");
		properties.put("hibernate.c3p0.max_size", "20");
		properties.put("hibernate.c3p0.acquire_increment", "1");
		properties.put("hibernate.c3p0.idle_text_period", "3000");
		properties.put("hibernate.cep0.max_statements", "50");
		properties.put("hibernate.c3p0.timeout", "1800");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("useUnicode", "true");
		properties.put("characterEncoding", "UTF-8");
		properties.put("hibernate.default_schema", "measurement");

		return Persistence.createEntityManagerFactory("measurement", properties);
	}
}