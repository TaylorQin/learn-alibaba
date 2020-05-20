package com.taylor;

import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class SeataOrderSercviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeataOrderSercviceApplication.class, args);
	}

}
