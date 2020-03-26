package com.app.net.modules.emp;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmpDaoTest {

	@Autowired
	private EmpDao empDao;
	
	@Test
	public void testGet(){
		Emp emp = empDao.get("7654");
		System.out.println(emp);
	}
	
	@Test
	public void testFindList(){
		List<Emp> list = empDao.findList(new Emp());
		System.out.println(list);
	}
}
