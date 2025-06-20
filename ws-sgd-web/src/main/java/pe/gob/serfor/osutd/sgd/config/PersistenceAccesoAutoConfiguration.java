package pe.gob.serfor.osutd.sgd.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "pe.gob.serfor.osutd.sgd.repository.acceso", 
					   entityManagerFactoryRef = "accesoEntityManager", 
					   transactionManagerRef = "accesoTransactionManager")
public class PersistenceAccesoAutoConfiguration {

	@Bean
	@ConfigurationProperties("spring.acceso-datasource")
	public JndiPropertyHolder accesoJndi() {
		return new JndiPropertyHolder();
	}

	@Bean
	@ConfigurationProperties("spring.acceso-datasource")
	public DataSourceProperties accesoDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean(name = "accesoDataSource")
	@ConfigurationProperties(prefix = "spring.acceso-datasource.configuration")
	public DataSource accesoDataSource() {
		DataSource dataSource = null;
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();

		if (accesoJndi().getJndiName() != null) {
			dataSource = dataSourceLookup.getDataSource(accesoJndi().getJndiName());
		} else {
			dataSource = accesoDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class)
					.build();
		}

		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean accesoEntityManager(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(accesoDataSource()).packages("pe.gob.serfor.osutd.sgd.repository.acceso").build();
	}

	@Bean
	public PlatformTransactionManager accesoTransactionManager(
			final @Qualifier("accesoEntityManager") LocalContainerEntityManagerFactoryBean accesoEntityManager) {
		return new JpaTransactionManager(accesoEntityManager.getObject());
	}
}