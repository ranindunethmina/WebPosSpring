package lk.ijse.webposspring.config;

import jakarta.persistence.EntityManagerFactory;
import lk.ijse.webposspring.util.ImageUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "lk.ijse.webposspring")
@EnableJpaRepositories(basePackages = "lk.ijse.webposspring")
@EnableTransactionManagement
public class WebAppRootConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ImageUtil imageUtil() {
        return new ImageUtil();
    }

    @Bean
    public DataSource dataSource() {
        var dmds = new DriverManagerDataSource();
        dmds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dmds.setUrl("jdbc:mysql://localhost:3306/webpos?createDatabaseIfNotExist=true");
        dmds.setUsername("root");
        dmds.setPassword("Ranindu@12");
        return dmds;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("lk.ijse.webposspring.entity");
        factory.setDataSource(dataSource());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }
}