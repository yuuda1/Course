package car_prokat.infiromation_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class InfiromationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfiromationSystemApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/carprokat/**")
//                .allowedOrigins("http://localhost:3000")
                        .allowedMethods("PUT", "DELETE", "GET", "POST")
                        .allowedHeaders("Access-Control-Allow-Origin", " $http_origin", "Origin", "Content-Type", "Accept")
                        .exposedHeaders("Access-Control-Allow-Origin", " $http_origin", "Origin", "Content-Type", "Accept")
                        .allowCredentials(false).maxAge(3600);
            }
        };
    }

}
