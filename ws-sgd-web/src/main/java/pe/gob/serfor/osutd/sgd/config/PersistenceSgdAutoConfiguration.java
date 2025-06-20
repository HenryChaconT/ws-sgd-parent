package pe.gob.serfor.osutd.sgd.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(
  basePackages = "pe.gob.serfor.osutd.sgd.repository.logic",
  entityManagerFactoryRef = "sgdEntityManager",
  transactionManagerRef = "sgdTransactionManager")
public class PersistenceSgdAutoConfiguration {
	
	@Bean@ 
	ConfigurationProperties("spring.datasource")
    public JndiPropertyHolder sgdJndi() {
        return new JndiPropertyHolder();
    }

	@Primary
	@Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties sgdDataSourceProperties() {
        return new DataSourceProperties();
    }
	
	@Primary
    @Bean(name = "sgdDataSource")
    @ConfigurationProperties(prefix="spring.datasource.configuration")
    public DataSource sgdDataSource() {		
		DataSource dataSource = null;
		JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
		
		if(sgdJndi().getJndiName() != null) {
			dataSource = dataSourceLookup.getDataSource(sgdJndi().getJndiName());
		}else {
			dataSource = sgdDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
		}
		
        return dataSource;	
    }
    
	@Bean
	@Primary
    public LocalContainerEntityManagerFactoryBean sgdEntityManager(EntityManagerFactoryBuilder builder) {
		 return builder
	                .dataSource(sgdDataSource())
	                .packages("pe.gob.serfor.osutd.sgd.repository.logic")
	                .build();
    }
	
    @Bean
    @Primary
    public PlatformTransactionManager sgdTransactionManager(
    		final @Qualifier("sgdEntityManager") LocalContainerEntityManagerFactoryBean sgdEntityManager) {
        return new JpaTransactionManager(sgdEntityManager.getObject());
    }
}
