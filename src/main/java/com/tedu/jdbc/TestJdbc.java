package com.tedu.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tedu.pojo.Emp;

/**
 *查询yonghe.db表中的所有员工信息，将一条员工信息封装成一个Emp对象，多条记录对应多个Emp对象，
 *将多个Emp在存入一个List集合List<Emp>
 * @author 000
 *
 */
public class TestJdbc {
	public static void main(String[] args) {
		List<Emp> empList = findAll();
		for(Emp e:empList) {
			System.out.println(e);
		}
	}
	
	
	/**
	 * 查询员工变得所有员工信息
	 * 返回值List<Emp>
	 * @return
	 */
	private static List<Emp> findAll() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs =null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/yonghedb?characterEncoding=utf-8","root","root");
			stat = conn.createStatement();
			String sql = "select * from emp";
			rs = stat.executeQuery(sql);
			//处理结果集
			List<Emp> list = new ArrayList<>();
			while(rs.next()) {
				Emp e = new Emp();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setJob(rs.getString("job"));
				e.setSalary(rs.getDouble("salary"));
				list.add(e);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
