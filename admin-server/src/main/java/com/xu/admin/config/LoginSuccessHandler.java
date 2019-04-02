package com.xu.admin.config;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @author CharleyXu Created on 2019/3/8.
 */
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws ServletException, IOException {
    Object principal = authentication.getPrincipal();
    if (principal instanceof User) {
      log.warn(((User) principal).getUsername() + "\t" + ((User) principal).getPassword());
    }
    super.onAuthenticationSuccess(request, response, authentication);
  }

}
