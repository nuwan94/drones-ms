package dev.nuwan.msdds.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {

  private final Environment env;

  public SpringFoxConfig(Environment env) { this.env = env; }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false);
  }

  @Bean
  public ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title(env.getProperty("msdds.titte"))
        .description(env.getProperty("msdds.description"))
        .version(env.getProperty("msdds.version"))
        .build();
  }
}
