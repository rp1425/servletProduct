package productservlet.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import productservlet.dto.Product;

public class ProductDao {

	
	public EntityManager getEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("nanditha") ;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager;
		
	}
	
	public void save (Product product) {
		
		EntityManager entityManager= getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
	    transaction.begin();
	    entityManager.persist(product);
	    transaction.commit();
	}
	
	
}
