package model.entities;

import java.util.Date;
import java.util.Objects;

public class Seller {
	
	private Department department;
	private String name;
	private String email;
	private Date birthDate;
	private Double baseSalary;
	private Integer id;
	
	
	public Seller(Department department, String name, String email, Date birthDate, Double baseSalary, Integer id) {
		
		this.department = department;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.baseSalary = baseSalary;
		this.id = id;
	}
	
	
	public Seller() {
		super();
	}

	

	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Double getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(Double baseSalary) {
		this.baseSalary = baseSalary;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "Seller [department=" + department + ", name=" + name + ", email=" + email + ", birthDate=" + birthDate
				+ ", baseSalary=" + baseSalary + ", id=" + id + "]";
	}
	
	
	
	
	
}
