package com.app.net.modules.emp;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/emp")
public class EmpController {
	
	@Autowired
	private EmpService empService;

	@RequiresRoles("reader")
	@RequestMapping("/list")
	public String list(){
		return "/modules/emp/empList";
	}
	
	@RequestMapping("/list2")
	@RequiresRoles("reader2")
	public String list2(){
		return "/modules/emp/empList";
	}
	
	@RequestMapping("get/{empno}")
	@ResponseBody
	public Emp get(@PathVariable("empno") String empno){
		Emp e = empService.get(empno);
		System.out.println(e==empService.get2(empno));
		return e;
	}
}
