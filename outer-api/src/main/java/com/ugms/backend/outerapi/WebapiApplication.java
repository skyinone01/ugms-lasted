package com.ugms.backend.outerapi;

import com.ugms.backend.RootMarker;
import com.ugms.backend.utils.ContextUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ApplicationContext;

@SpringBootApplication(scanBasePackageClasses = RootMarker.class, exclude = MongoAutoConfiguration.class)
public class WebapiApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(WebapiApplication.class, args);
        ContextUtils.setApplicationContext(context);

    }

}
