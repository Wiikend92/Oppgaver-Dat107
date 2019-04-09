package no.hvl.dat107.eao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.entity.Prosjekt;

public class ProsjektEAO {
	
	private EntityManagerFactory emf;

	public ProsjektEAO() {
		emf = Persistence.createEntityManagerFactory("Oblig3PersistenceUnit");
	}
	
	public String lagreIDatabasen(Prosjekt ny) {

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
		return ny.getNavn();
	}
	
	 public Prosjekt finnProsjektMedId(int id) {

	        EntityManager em = emf.createEntityManager();

	        Prosjekt prosjekt = null;
	        try {
	            prosjekt = em.find(Prosjekt.class, id);
	        } finally {
	            em.close();
	        }
	        return prosjekt;
	    }


}
