package com.landq.account;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration class Configure the databases and entities and it contains
 * beans.
 * 
 * @author Anjali
 *
 */
@ComponentScan("com.landq.account")
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.landq.account.dao")
public class Config {

	@Bean /** It will create the bean for DataSource */
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/landq");
		dataSource.setUsername("root");
		dataSource.setPassword("Test#123");
		return dataSource;
	}

	@Bean /** It will create the bean for LocalContainerEntityManagerFactoryBean */
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "com.landq.account.domain" });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean /** It will create the bean for PlatformTransactionManager. */
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tManager = new JpaTransactionManager();

		tManager.setEntityManagerFactory(emf);

		return tManager;
	}

	Properties additionalProperties() {

		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

		return properties;
	}

}
