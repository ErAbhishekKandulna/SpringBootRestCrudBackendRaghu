package com.app.raghu.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.raghu.entity.Student;
import com.app.raghu.exception.StudentNotFoundException;
import com.app.raghu.service.IStudentService;

@RestController
@RequestMapping("/v1/api/student")
@CrossOrigin("http://localhost:4200/")
public class StudentRestController 
{
	@Autowired
	private IStudentService service;
	
	//1. CREATE STUDENT
	@PostMapping("/create")
	public ResponseEntity<String> createStudent(@RequestBody @Valid Student student)
	{
		Integer id = service.saveStudent(student);
		String message="STUDENT '"+id+"' CREATED";
		//return new ResponseEntity<String>(message,HttpStatus.OK);
		return new ResponseEntity<String>(message,HttpStatus.CREATED);
	}
	
	//2. FETCH ALL STUDENT
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudent()
	{
		List<Student> list = service.getAllStudent();
		//return new ResponseEntity<List<Student>>(list,HttpStatus.OK);
		return ResponseEntity.ok(list);
	}
	
	//3. FETCH ONE STUDENT BY ID
	@GetMapping("/find/{id}")
	public ResponseEntity<Student> getOneStudent(@PathVariable("id")Integer id)
	{
		ResponseEntity<Student> response=null;
		try 
		{
			Student student = service.getOneStudent(id);
			response = ResponseEntity.ok(student);
		}
		catch (StudentNotFoundException e) 
		{
			e.printStackTrace();
			throw e;
		}
		return response;
	}
	
	//4. REMOVE ONE BY ID
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id")Integer id)
	{
		ResponseEntity<String> response=null;
		try 
		{
			service.deleteStudent(id);
			response = ResponseEntity.ok("STUDENT '"+id+"' DELETED");
		}
		catch (StudentNotFoundException e) 
		{
			e.printStackTrace();
			throw e;
		}
		return response;
	}
	
	//5. UPDATE STUDENT
	@PutMapping("/modify")
	public ResponseEntity<String> updateStudent(@RequestBody Student student)
	{
		ResponseEntity<String> response = null;
		try 
		{
			service.updateStudent(student);
			response = ResponseEntity.ok("STUDENT '"+student.getStdId()+"' UPDATED");
		}
		catch (StudentNotFoundException e) 
		{
			e.printStackTrace();
			throw e;
		}
		return response;
	}
	
}
