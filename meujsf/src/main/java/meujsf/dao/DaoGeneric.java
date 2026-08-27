package meujsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import meujsf.util.JPAUTIL;

public class DaoGeneric <E> {
	
	public void salvar( E e) {
		EntityManager entityManager = JPAUTIL.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		entityManager.persist(e);
		transaction.commit();
		entityManager.close();
	}
	
	public E merge(E e) {
		EntityManager entityManager = JPAUTIL.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		
		transaction.begin();
		E objetoSalvo  = entityManager.merge(e);
		
		transaction.commit();
		entityManager.close();
		
		return objetoSalvo;
	}
	
	public void delete(E e) {
		EntityManager entityManager = JPAUTIL.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Object id = JPAUTIL.getPrimaryKey(e);
		entityManager.createQuery("delete from "+e.getClass().getCanonicalName()+" where id = "+id).executeUpdate();
		
		
		transaction.commit();
		entityManager.close();
	}
	
	public List<E> findAll(Class<E> entidade){
		EntityManager entityManager = JPAUTIL.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		List<E> objetos = entityManager.createQuery(" from "+entidade.getName()).getResultList();
		transaction.commit();
		entityManager.close();
		
		return objetos;
	}
}
