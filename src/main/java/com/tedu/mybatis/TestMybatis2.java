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

public class TestMybatis2 {
	
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
	 * 练习1：往emp表中插入一条员工信息
	 * 张三     高级java开发工程师      25000
	 * @throws IOException 
	 */
	@Test
	public void testInsert() throws IOException {
		//4.执行sql语句，返回执行结果
		int rows = session.update("EmpMapper.insert");//寻找namespace为EmpMapper的mapper，再在EmpMapper中寻找到id为insert的语句
		//提交事务
		session.commit();
		
		System.out.println(rows);
		
	}
	
	/**
	 * 查询员工表中name为'王海涛'的员工信息
	 */
	@Test
	public void testFindById() {
		//4.执行sql语句，返回执行结果
		Emp emp = session.selectOne("EmpMapper.findById");
		
		System.out.println(emp);
	}
	
	
	
	/*******************   #{}占位符 *************/
	/**       
	 * 修改emp表中id为8的员工信息
	 * name改为马云，job改为教师，salary改为1000,
	 */
	
	@Test
	public void testUpdateById() {
		//4.执行语句
		Emp e = new Emp();
		e.setId(888);
		e.setName("马云");
		e.setJob("教师");
		e.setSalary(1000D);
		int rows = session.update("EmpMapper.updateById",e);
		//5.提交事务
		session.commit();
		
	}
	
	
	
	@Test
	public void testUpdateById2() {
		//4.执行语句
		Map map = new HashMap();
		map.put("id",8);
		map.put("name", "赵云");
		map.put("job", "保镖");
		map.put("salary", 3000D);
		int rows = session.update("EmpMapper.updateById",map);
		//5.提交事务
		session.commit();
		
	}
	
	/***
	 * 练习9    
	 * 动态查询emp表中的所有员工信息
	 * ，要查询的列是动态传过来的
	 */
	@Test
	public void testFindAll2() {
		//4.执行语句
		Map map = new HashMap();
		map.put("cols", "id,name");
		
		List<Emp> list = session.selectList("EmpMapper.findAll2",map);
		for(Emp e:list) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * 练习10：根据name模糊查询员工信息
	 * slect * from emp where name like '%刘%'
	 */
	@Test
	public void testFindByName() {
		Map map = new HashMap();
		map.put("name", "涛");
		List<Emp> list = session.selectList("EmpMapper.findByName",map);
		for(Emp e:list) {
			System.out.println(e);
		}
		
		
		
		
	}
}
