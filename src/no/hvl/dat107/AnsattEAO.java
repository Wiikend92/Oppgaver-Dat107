package no.hvl.dat107;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class AnsattEAO {

	private EntityManagerFactory emf;

	public AnsattEAO() {
		emf = Persistence.createEntityManagerFactory("Oblig3PersistenceUnit");
	}

	public String lagreIDatabasen(Ansatt ny) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(ny);
			tx.commit();

		} catch (Throwable e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
		return ny.getBrukernavn();
	}

	public Ansatt finnAnsattBN(AnsattEAO eao, String brukernavn) {
		EntityManager em = emf.createEntityManager();
		
		Ansatt dt = null;
		try {
			dt = (Ansatt) em.createQuery("SELECT e FROM Ansatt e where e.brukernavn = :value1").setParameter("value1", brukernavn).getSingleResult();
		}finally {
			em.close();
		}
		return dt;
	}
	
	
	public Ansatt finnAnsattID(AnsattEAO eao, int id) {
		EntityManager em = emf.createEntityManager();

		Ansatt dt = null;
		try {
			dt = em.find(Ansatt.class, id);
		} finally {
			em.close();
		}
		return dt;
	}

	public void updateAnsatt(Ansatt p) {

		EntityManager em = emf.createEntityManager();

		try {
			Ansatt q = em.merge(p);
			em.getTransaction().begin();
			em.getTransaction().commit();

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}
	
	public List<Ansatt> getAnsatt(){
		EntityManager em = emf.createEntityManager();
		List<Ansatt> ansatte = null;
		
		try {
			ansatte = em.createQuery("Select f from ansatt f").getResultList();
		}catch(IllegalArgumentException iae){
			iae.printStackTrace(System.err);
		}finally {
			em.close();
		}
		
		
		return ansatte;
		
	}

}
