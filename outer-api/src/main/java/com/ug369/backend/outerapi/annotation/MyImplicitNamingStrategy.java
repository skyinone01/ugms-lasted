package com.ug369.backend.outerapi.annotation;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitBasicColumnNameSource;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.springframework.stereotype.Component;

/**
 * Created by Roy on 2017/4/7.
 */
@Component
public class MyImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {
    @Override
    public Identifier determineBasicColumnName(ImplicitBasicColumnNameSource source) {
        return convert(source);
    }

    private Identifier convert(ImplicitBasicColumnNameSource source) {
        if (StringUtils.isBlank(transformAttributePath(source.getAttributePath()))) {
            return toIdentifier(transformAttributePath(source.getAttributePath()),
                    source.getBuildingContext());
        }

        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        String newName = transformAttributePath(source.getAttributePath())
                .replaceAll(regex, replacement).toLowerCase();
        return toIdentifier(newName, source.getBuildingContext());
    }
}