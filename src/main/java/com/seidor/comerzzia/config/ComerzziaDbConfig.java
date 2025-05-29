package com.seidor.comerzzia.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "comerzziaEntityManagerFactory",
  transactionManagerRef = "comerzziaTransactionManager",
  basePackages = { "com.seidor.comerzzia.domain.repository.comerzzia" }
)
public class ComerzziaDbConfig {
	
	@Bean(name="comerzziaDataSource")
	@ConfigurationProperties(prefix="comerzzia.datasource")
	public DataSource comerzziaDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "comerzziaEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean comerzziaEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("comerzziaDataSource") DataSource comerzziaDataSource) {
		return builder
				.dataSource(comerzziaDataSource)
				.packages("com.seidor.comerzzia.domain.model.comerzzia")
				.build();
	}

	@Bean(name = "comerzziaTransactionManager")
	public PlatformTransactionManager comerzziaTransactionManager(
			@Qualifier("comerzziaEntityManagerFactory") EntityManagerFactory comerzziaEntityManagerFactory) {
		return new JpaTransactionManager(comerzziaEntityManagerFactory);
	}
}