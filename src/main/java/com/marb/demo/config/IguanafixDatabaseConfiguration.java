//package com.marb.demo.config;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "iguanafixEntityManagerFactory",
//					   basePackages = { "com.marb.demo.module.iguanafix" })
//public class IguanafixDatabaseConfiguration {
//
//	@Bean(name = "iguanafixDataSource")
//	@ConfigurationProperties(prefix = "iguanafix.datasource")
//	public DataSource iguanafixDataSource() {
//		return DataSourceBuilder.create().build();
//	}
//
//	@Bean(name = "iguanafixEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean iguanafixEntityManagerFactory(EntityManagerFactoryBuilder builder,
//			@Qualifier("iguanafixDataSource") DataSource dataSource) {
//		return builder.dataSource(dataSource).packages("com.marb.demo.module.iguanafix.domain.model")
//				.persistenceUnit("iguanafix").build();
//	}
//
//	@Bean(name = "iguanafixTransactionManager")
//	public PlatformTransactionManager iguanafixTransactionManager(
//			@Qualifier("iguanafixEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
//		return new JpaTransactionManager(entityManagerFactory);
//	}
//}
