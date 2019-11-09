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
import org.junit.Test;

import com.tedu.dao.EmpMapper;
import com.tedu.pojo.Emp;
/**
 * Mapper的接口开发	
 * (1)创建一个接口，接口的全路径要和mapper文件的namespace值相同
 * (2)mapper文件中每一条sql语句都要对应接口中的方法，并且sql的id值要和方法名相同
 * (3)接口中方法的返回值类型要和sql语句上的resultType值相同（方法若返回集合，resultTpe指定为集合中的泛型即可）
 * (4)接口中方法的参数类型要和sql语句上的参数类型相同
 * @author 000
 *
 */
public class TestMybatis4 {
	
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
	 *1.查询emp表所有员工信息
	 */
	@Test
	public void testFindAll() {
		//1.获取EmpMapper接口子类的实例
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		//2.调用子类实例的findAll()方法
		//findAll方法底层是怎么执行的？
		List<Emp> list = mapper.findAll();
		for(Emp e:list) {
			System.out.println(e);
		}	
	}
	
	
	/**
	 * 2.往emp表中添加一条员工信息
	 * 张飞      保安      3000	
	 */
	@Test
	public void testInsert() {
		//获取EmpMapper接口子类的实例
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		Emp emp = new Emp(null,"张飞","保安",3000D);
		int rows = mapper.insert(emp);
		session.commit();
		System.out.println("影响行数："+rows);
	}
	
	
	/**
	 * 练习3：修改name为张飞的员工信息
	 * 张飞飞     程序员鼓励师      10000D
	 */
	@Test
	public void testUpdate() {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		Map map = new HashMap<>();
		map.put("name1","张飞飞");
		map.put("job","程序员鼓励师");
		map.put("salary",10000D);
		map.put("name2","张飞");
		
		int rows = mapper.update(map);
		session.commit();
		System.out.println(rows);
	}
	
	
	/**
	 * 练习4：查询id为8的员工信息
	 */
	@Test
	public void testFindById() {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		Emp emp = mapper.findById(8);
		System.out.println(emp);
	}
	
	
	
}
