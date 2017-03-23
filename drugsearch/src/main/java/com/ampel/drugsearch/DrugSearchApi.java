package com.ampel.drugsearch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JndiConnectionFactoryAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ampel.drugsearch.model.Drug;
import com.ampel.drugsearch.model.DrugDao;

@RestController

@ComponentScan(value="com.ampel.drugsearch")
@EnableAutoConfiguration (exclude={JndiConnectionFactoryAutoConfiguration.class,DataSourceAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class,JpaRepositoriesAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})

public class DrugSearchApi {

	private static Map<Integer,Student> studentDB;
	
	@Autowired
	private DrugDao drugDao;
	
	@CrossOrigin
	@RequestMapping("/drugs")
	public List<Drug> getDrugsbyTarget(@RequestParam(name="target",required=true)String target){
		return drugDao.getByTarget(target);
	}
	@CrossOrigin
	@RequestMapping("/alldrugs")
	public List<Drug> getAllDrugs(){
		return drugDao.getAll();
	}
	
	@CrossOrigin
	@RequestMapping("/genes")
	public List<Drug> getGeneseByDrug(@RequestParam(name="drug_name",required=true)String drug_name){
		return drugDao.getByDrug(drug_name);
	}
	
	public static void main(String[] args){
		SpringApplication.run(DrugSearchApi.class, args);
	}
	
	@RequestMapping("/students")
	public Student searchStudents(@RequestParam(name="studentId",required=true) Integer studentId){
		return studentDB.get(studentId);
	}
	
	
	
	public static void createStudentlist(){
		studentDB = new HashMap<Integer, Student>();
		for(int i=0; i < 100; i++){
			Student student = new Student(i, "Name-" + i, "CS-" + i, "Computer Science", "CS", "2nd");
			studentDB.put(student.getStudentId(), student);
		}
	}
	
}
