package practice.mcp.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "practice.mcp.model.dto")
public class PracticeMcpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticeMcpApplication.class, args);
	}

}
