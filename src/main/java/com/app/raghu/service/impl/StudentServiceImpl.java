package com.app.raghu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.raghu.entity.Student;
import com.app.raghu.exception.StudentNotFoundException;
import com.app.raghu.repo.StudentRepository;
import com.app.raghu.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService
{

	@Autowired
	private StudentRepository repository;
	
	@Override
	public Integer saveStudent(Student student) 
	{
		student=repository.save(student);
		return student.getStdId();
	}

	@Override
	public void updateStudent(Student student) 
	{
		if(student.getStdId()==null || !repository.existsById(student.getStdId()))
		{
			throw new StudentNotFoundException("STUDENT '"+student.getStdId()+"' NOT EXIST");
		}
		else
		{
			repository.save(student);
		}
	}

	@Override
	public void deleteStudent(Integer id) 
	{
		repository.delete(getOneStudent(id));
	}

	@Override
	public Student getOneStudent(Integer id) 
	{
		return repository.findById(id)
				.orElseThrow(()-> new StudentNotFoundException("STUDENT '"+id+"' NOT EXIST")
				);
	}

	@Override
	public List<Student> getAllStudent() 
	{
		List<Student> list = repository.findAll();
		return list;
	}

}
