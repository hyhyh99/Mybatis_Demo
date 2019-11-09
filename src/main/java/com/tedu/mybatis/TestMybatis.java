package com.tedu.mybatis;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * Mybatis的快速入门
 */
import org.junit.Test;

import com.tedu.pojo.Emp;

public class TestMybatis {
	
	/**
	 * 联系1：查询员工表中的所有员工信息
	 * 将所有员工信息封装到list<Emp>集合中
	 * @throws IOException 
	 */
	@Test
	public void findAll() throws IOException {
		//1.读取mybatis的核心配置文件(mybatis-config.xml)
		InputStream in = Resources.getResourceAsStream("./mybatis-config.xml");
		
		//2.通过配置获取一个SqlSessionFactory
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		
		//3.通过工厂获取一个SqlSession对象
		SqlSession session = factory.openSession();
		
		//4.执行SQL语句(通过namespace+id对sql语句进行定位)，返回结果List<Emp> empList = ...
		List<Emp> list = session.selectList("EmpMapper.findAll");
		
		//5.输出结果
		for(Emp emp:list) {
			System.out.println(emp);
		}
		
	}
}
