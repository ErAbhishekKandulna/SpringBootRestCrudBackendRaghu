package com.app.raghu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "std_tab")
public class Student 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sid")
	private Integer stdId;
	
	@Column(name = "sname")
	//@NotNull(message = "NAME CAN NOT BE NULL")
	//@NotEmpty(message = "Student name can not be empty")
	@NotBlank(message = "STUDENT NAME CAN NOT BE EMPTY")
	@Size(min = 2,max = 20,message = "NAME MUST BE 2-6 CHARS ONLY")
	//@Pattern(regexp = "[A-Za-z]{2-6}",message = "ONLY CHARS ARE ALLOWED.")
	private String stdName;
	
	@Column(name = "sgen")
	private String stdGen;
	
	@Column(name = "scourse")
	private String stdCourse;
	
	@Column(name = "sddress")
	//@Pattern(regexp = "[A-Za-z0-9\\.\\-\\?]{10,250}",message = "INVALID ADDRESS DETAILS")
	private String stdAddr;
}
