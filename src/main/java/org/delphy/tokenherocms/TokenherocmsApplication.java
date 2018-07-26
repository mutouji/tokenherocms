package org.delphy.tokenherocms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author mutouji
 * @PropertySource("file:${tokenherocms_config_path:D:/tokenhero/tokenherocms/src/main/resources}/application.properties")
 */

@SpringBootApplication
public class TokenherocmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenherocmsApplication.class, args);
	}
}
