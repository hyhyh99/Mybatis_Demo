package com.tedu.dao;

import java.util.List;
import java.util.Map;

import com.tedu.pojo.Emp;

//接口全路径：com.tedu.dao.EmpMapper
public interface EmpMapper {

	public List<Emp> findAll();
	
	public int insert(Emp emp);
	
	public int update(Map map);
	
	public Emp findById(Integer id);
}
