package com.nks.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name ="employee")
@SQLDelete(sql = "update employee set record_activity = 0 where id = ? ")
@Where(clause="record_activity = 1")
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name ="sequenceGenerator")
	@JsonProperty("employee_id")	
	private Long id;
	
	@Column(name="age")
	private int age;
	
	@Column(name="name")
	private String name;

	@Column(name="record_activity")
	@JsonIgnore
	public int record_activity;
	
	public int getRecord_activity() {
		return record_activity;
	}

	public void setRecord_activity(int record_activity) {
		this.record_activity = record_activity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
