package com.namee.core.spring.configuration;

import com.namee.core.common.cipher.AESCipher;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "namee")
public class DatabaseConfiguration {
    @Value("${namee.nos.jdbc.password}")
    private String databasePassword;
    @Value("${namee.nos.jdbc.username}")
    private String databaseUsername;
    @Value("${namee.nos.jdbc.driver}")
    private String databaseDriver;
    @Value("${namee.nos.jdbc.port}")
    private String databasePort;
    @Value("${namee.nos.jdbc.dbname}")
    private String databaseDbName;
    @Value("${namee.nos.jdbc.url}")
    private String databaseUrl;

    @Bean(name="dataSource", destroyMethod = "shutdown")
    @Primary
    @ConfigurationProperties(prefix="datasource.primary")
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        AESCipher aesCipher = new AESCipher();
        config.setJdbcUrl("jdbc:mysql://"+aesCipher.decode(databaseUrl)+":3306/"+aesCipher.decode(databaseDbName)+"?autoReconnect=true&useUnicode=true&connectTimeout=60000&socketTimeout=60000");
        config.setDataSourceClassName(databaseDriver);
        config.addDataSourceProperty("serverName", aesCipher.decode(databaseUrl));
        config.addDataSourceProperty("user", aesCipher.decode(databaseUsername));
        config.addDataSourceProperty("password", aesCipher.decode(databasePassword));
        config.addDataSourceProperty("databaseName", aesCipher.decode(databaseDbName));
        config.addDataSourceProperty("port", 3306);
    	config.addDataSourceProperty("cachePrepStmts", "true");
    	config.addDataSourceProperty("prepStmtCacheSize", "250");
    	config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    	config.addDataSourceProperty("useServerPrepStmts", "true");
    	config.addDataSourceProperty("connectionCollation", "utf8mb4_general_ci");
    	config.setIdleTimeout(300000);//5분
    	config.setMaxLifetime(600000);//10분
    	config.setConnectionTestQuery("select 1");
    	config.setConnectionTimeout(30000);//30초
    	config.setValidationTimeout(5000);//5초
    	config.setMinimumIdle(1);
    	config.setMaximumPoolSize(10);
    	HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }


//    @Bean(name="transactionManager")
//    public DataSourceTransactionManager transactionManager() {
//    	return new DataSourceTransactionManager(dataSource());
//    }
}
