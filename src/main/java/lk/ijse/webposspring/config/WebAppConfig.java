package lk.ijse.webposspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.webposspring")
@EnableWebMvc
@EnableJpaRepositories(basePackages = "lk.ijse.webposspring")
@EnableTransactionManagement
public class WebAppConfig {
}