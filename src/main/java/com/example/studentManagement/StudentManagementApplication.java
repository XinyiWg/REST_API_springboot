package com.example.studentManagement;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.PropertiesRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@SpringBootApplication
//@MapperScan("com.example.studentManagement.mapper")
public class StudentManagementApplication {
	private static Logger log= LoggerFactory.getLogger(StudentManagementApplication.class);

	@ExceptionHandler(AuthorizationException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public void handleException(AuthorizationException e){
		log.debug("{}",e.getClass(),e);

	}
	@Bean
	public Realm realm(){
		PropertiesRealm realm =new PropertiesRealm();
		return realm;
	}
//	need to create two new beans


	public static void main(String[] args) {
		SpringApplication.run(StudentManagementApplication.class, args);
	}

}
