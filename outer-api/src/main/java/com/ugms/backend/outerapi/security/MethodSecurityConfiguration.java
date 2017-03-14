package com.ugms.backend.outerapi.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * Created by Roy on 2017/3/8.
 */

@EnableGlobalMethodSecurity(securedEnabled = true)
public class MethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

}
