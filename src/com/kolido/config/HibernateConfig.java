package com.kolido.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.kolido.config"})
@PropertySource(value = "classpath:application.properties")
public class HibernateConfig {
	
	@Autowired
	private Environment enviroment;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory(){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setHibernateProperties(hibernateProperties());
		sessionFactory.setPackagesToScan("com.kolido.model");
		return sessionFactory;
	}
	
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(enviroment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(enviroment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(enviroment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(enviroment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}
	
	@Bean
	public Properties hibernateProperties(){
		Properties properties = new Properties();
		properties.put("hibernate.dialect", enviroment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", enviroment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", enviroment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}
	
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s){
		return new HibernateTransactionManager(s);
	}
}


















