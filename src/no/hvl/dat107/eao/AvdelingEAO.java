package no.hvl.dat107.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import no.hvl.dat107.entity.Avdeling;

public class AvdelingEAO {

	private EntityManagerFactory emf;
	
	public AvdelingEAO() {
		emf = Persistence.createEntityManagerFactory("Oblig3PersistenceUnit");
	}
	public String lagreIDatabasen(Avdeling ny) {

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
	
	public Avdeling finnAvdelingID(AvdelingEAO eao, int id) {
		EntityManager em = emf.createEntityManager();

		Avdeling dt = null;
		try {
			dt = em.find(Avdeling.class, id);
		} finally {
			em.close();
		}
		return dt;
	}
	
	public List<Avdeling> getAvdeling(){
		EntityManager em = emf.createEntityManager();
		List<Avdeling> avdeling;
		try {
			avdeling = em.createNamedQuery("Avdeling.finnAlle").getResultList();
		}finally {
			em.close();
		}
		return avdeling;
	}
}
