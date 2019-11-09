package com.tedu.mybatis;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 * Mybatis的快速入门
 */
import org.junit.Test;

import com.tedu.pojo.Emp;
/**
 * Mybatis的动态SQL	
 * @author 000
 *
 */
public class TestMybatis3 {
	
	private static SqlSession session = null;
	
	static {
		try {
			//1.读取mybatis的核心配置文件(mybatis-config.xml)
			InputStream in = Resources.getResourceAsStream("./mybatis-config.xml");
					
			//2.通过配置获取一个SqlSessionFactory
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
					
			//3.通过工厂获取一个SqlSession对象
			session = factory.openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 	练习11：查询emp表中所有员工的信息
	 * 	如果传递了minSal(最低薪资)和maxSal(最高薪资)，则查询薪资大于minSal和小于maxSal的员工信息；
				如果只传递了minSal，则查询薪资大于minSal的所有员工信息；
				如果只传递了maxSal，则查询薪资小于maxSal的所有员工信息；
				<where></where>  <if></if>标签
	 */
	@Test
	public void testFindAll3() {
		Map map = new HashMap();
		map.put("minSal",3000);
		map.put("maxSal",4000);
		List<Emp> list = session.selectList("EmpMapper.findAll3",map);
		for(Emp e:list) {
			System.out.println(e);
		}
		
	}
	
	
	/**
	 * 练习12：修改员工信息
	 */
	@Test
	public void testUpdateById() {
		//执行sql语句
		Emp emp = new Emp();
		emp.setId(4);
		emp.setName("陈总监");
		emp.setJob("总经理");
		
		int rows = session.update("EmpMapper.updateById2",emp);
		session.commit();
		System.out.println("影响行数："+rows);
	}
	
	
	
	/**
	 * 练习13：通过id实现批量删除
	 * delete from emp where id in(1,2,3,4)
	 */
	@Test
	public void testDeleteById() {
		//准备删除
		Integer[] ids = {1,2,3,5};
		int rows = session.update("EmpMapper.deleteByIds",ids);
		session.commit();
		System.out.println("影响行数："+rows);
		
	}
	
}
