package com.globalmart.productcatalog.service.test.factory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.h2.tools.RunScript;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DatabaseFactory {
	
	private static final String dataSourceBeanId = "dataSource";

	public static void createMockDatabase(Class<?> testClass, String springContextFileName, String... sqlScripts) 
				throws SQLException, FileNotFoundException {
		
		try(ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(springContextFileName)) {
		
			DriverManagerDataSource dataSource = (DriverManagerDataSource) springContext.getBean(dataSourceBeanId); 
			Connection connection = dataSource.getConnection();
			
			for (String sqlScript: sqlScripts) {
				RunScript.execute(connection, getReader(testClass, sqlScript));
			}
		}
	}
	
	private static FileReader getReader(Class<?> clazz, String path) throws FileNotFoundException {
		return new FileReader(new File(clazz.getClassLoader().getResource(path).getFile()));
	}

}
