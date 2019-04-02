package com.xu.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author CharleyXu Created on 2019/3/8.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final String adminContextPath;

  public SecurityConfig(AdminServerProperties adminServerProperties) {
    this.adminContextPath = adminServerProperties.getContextPath();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
        .antMatchers(adminContextPath + "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage(adminContextPath + "/login")
        .permitAll()
        .successHandler(getLoginSuccessHandler())
        .and()
        .logout()
        .logoutSuccessUrl(adminContextPath + "/")
        .deleteCookies("JSESSIONID")
        .permitAll()
        .invalidateHttpSession(true)
        .and()
        .httpBasic()
        .and().csrf().disable();
//        .rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表  
//        .tokenValiditySeconds(1209600);

  }

  @Bean
  public LoginSuccessHandler getLoginSuccessHandler() {
    return new LoginSuccessHandler();
  }
}
