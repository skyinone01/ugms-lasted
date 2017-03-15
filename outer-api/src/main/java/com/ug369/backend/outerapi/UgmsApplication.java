package com.ug369.backend.outerapi;

import com.ug369.backend.RootMarker;
import com.ug369.backend.utils.ContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackageClasses = RootMarker.class, exclude = MongoAutoConfiguration.class)
public class UgmsApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(UgmsApplication.class, args);
        ContextUtils.setApplicationContext(context);

    }

}
