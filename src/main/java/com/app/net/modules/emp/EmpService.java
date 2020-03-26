package com.app.net.modules.emp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class EmpService {
	
	@Autowired
	private EmpDao empDao;
	
	@Cacheable("myCache")
	//@CacheEvict(value="",allEntries=true)
	public Emp get(String empno){
		System.out.println("cache-----");
		return empDao.get(empno);
	}
	
	@CachePut("myCache")
	//@CacheEvict(value="",allEntries=true)
	public Emp get2(String empno){
		System.out.println("cacheput-----");
		return empDao.get(empno);
	}

}
