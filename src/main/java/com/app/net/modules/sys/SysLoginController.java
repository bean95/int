package com.app.net.modules.sys;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.net.modules.emp.Emp;

@Controller
@RequestMapping("/sys")
public class SysLoginController {

	@RequestMapping(value = "/login",method=RequestMethod.GET)
	public String index(){
		return "/modules/sys/sysLogin";
	}
	
	@RequestMapping(value = "/logoff",method=RequestMethod.GET)
	public String logoff(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "/modules/sys/logoff";
	}
	
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public String login(Emp emp){

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(emp.getEname(), emp.getEmpno());
		token.setRememberMe(true);
		try{
			subject.login(token);
			subject.hasRole("reader");
		}catch(IncorrectCredentialsException ex){
			return "/modules/sys/sysLogin";
		}
		
		return "/modules/sys/success";
	}
	
}
