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
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name="business")
@Entity
@SQLDelete(sql ="Update business set record_activity =0 where id =?")
@Where(clause ="record_activity = 1")
public class Business implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
	@SequenceGenerator(name="sequenceGenerator")
	private Long id;
	
	@NonNull
	@Column(name="person_name")
	private String person_name;

	@NonNull
	@Column(name="business_name")
	private String business_name;
	
	@NonNull
	@Column(name="business_gst_number")
	private String business_gst_number;
	
	@Column(name="record_activity")
	@JsonIgnore
	public int record_activity;
	
	public int getRecord_activity() {
		return record_activity;
	}

	public void setRecord_activity(int record_activity) {
		this.record_activity = record_activity;
	}

	public String getPerson_name() {
		return person_name;
	}
	public void setPerson_name(String person_name) {
		this.person_name = person_name;
	}
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public String getBusiness_gst_number() {
		return business_gst_number;
	}
	public void setBusiness_gst_number(String business_gst_number) {
		this.business_gst_number = business_gst_number;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
}
