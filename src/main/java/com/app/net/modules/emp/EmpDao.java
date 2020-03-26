package com.app.net.modules.emp;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface EmpDao {
	Emp get(String empno);
	List<Emp> findList(Emp emp);
}
