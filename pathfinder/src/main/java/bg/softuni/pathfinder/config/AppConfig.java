package bg.softuni.pathfinder.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
//        TODO: replace with MapStruct
        return new ModelMapper();
    }


    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}
