package com.ampel.drugsearch.model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DrugDao {

	@Autowired
	private SessionFactory _sessionFactory;
	
	private Session getSession(){
		return _sessionFactory.getCurrentSession();
	}
	
	public void save(Drug drug){
		getSession().save(drug);
	}
	
	public void delete(Drug drug){
		getSession().delete(drug);
	}
	
	@SuppressWarnings("unchecked")
	public List<Drug> getAll(){
		return getSession().createQuery("from Drug").list();
	}
	
	public List<Drug> getByTarget(String target){
		return  getSession().createQuery("from Drug where target = :target")
				.setParameter("target",target).list();
	}
	
	public List<Drug> getByDrug(String drug){
		return  getSession().createkotla#Query("from Drug where drug_name = :drug_name")
				.setParameter("drug_name",drug).list();
	}
}
