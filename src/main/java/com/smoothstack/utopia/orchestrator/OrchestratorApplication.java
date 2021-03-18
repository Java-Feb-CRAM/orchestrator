package com.smoothstack.utopia.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class OrchestratorApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrchestratorApplication.class, args);
  }

  @Bean
  @LoadBalanced
  public RestTemplate getRestTemplate() {
    return new RestTemplate();
  }

}
