package com.tedu.pojo;

public class Emp {
	private Integer id;
	private String name;
	private String job;
	private Double salary;
	
	
	public Emp() {
		
	}
	public Emp(Integer id, String name, String job, Double salary) {
		this.id = id;
		this.name = name;
		this.job = job;
		this.salary = salary;
	}
	//提供对应的get和set方法
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	
	//重写toString方法
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", job=" + job + ", salary=" + salary + "]";
	}
	
	
	
	
	
}
