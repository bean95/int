package com.app.net.modules.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class ShiroIniTest {
	
	@Test
	public void login(){
		
		IniRealm realm = new IniRealm("classpath:shiro.ini");
		DefaultSecurityManager securityManager = new DefaultSecurityManager(realm);
		SecurityUtils.setSecurityManager(securityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("jason", "123");
		subject.login(token);
		
		System.out.println("login..." + subject.isAuthenticated());
		//System.out.print("roles..." + subject.checkRoles("",""));
		subject.logout();
		
	}

}
