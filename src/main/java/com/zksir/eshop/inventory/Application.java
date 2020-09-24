package com.zksir.eshop.inventory;

import com.zksir.eshop.inventory.listener.InitListener;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * 库存服务的Application入口类
 */
@SpringBootApplication
@MapperScan("com.zksir.eshop.inventory.mapper")
public class Application {

    /**
     * 构建数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new DataSource();
    }

    /**
     * 构建Mybatis的入口类：SqlSessionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 构建事务管理器
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean
    public JedisCluster JedisClusterFactory() {
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        jedisClusterNodes.add(new HostAndPort("47.104.174.39",7000));
        jedisClusterNodes.add(new HostAndPort("47.104.174.39",7001));
        jedisClusterNodes.add(new HostAndPort("47.104.174.39",7002));
        JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
        return jedisCluster;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new InitListener());
        return servletListenerRegistrationBean;
    }

    /**
     * Spring Boot Application启动入口main方法
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
