package bg.softuni.pathfinder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        classpath at projectFolder/src/main/resources!
//
//        registry.addResourceHandler("/pictures/**")
////                other locations can be added. file:/ -> absolute path, classpath:/ -> relative at /resources
//                .addResourceLocations("classpath:/storage/pictures")
////                up to here works
//                .setCachePeriod(3900)
//                .resourceChain(true)
////                allows for other type resolvers as versioning ones:
////                addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
//                .addResolver(new PathResourceResolver());
//    }

}
