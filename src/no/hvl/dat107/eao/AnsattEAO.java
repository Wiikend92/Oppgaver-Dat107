package no.hvl.dat107.eao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

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
			dt = (Ansatt) em.createQuery("SELECT e FROM Ansatt e where e.brukernavn = :value1")
					.setParameter("value1", brukernavn).getSingleResult();
		} finally {
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
			em.merge(p);
			em.getTransaction().begin();
			em.getTransaction().commit();

		} catch (Throwable e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	public List<Ansatt> getAnsatt() {
		EntityManager em = emf.createEntityManager();
		List<Ansatt> ansatte = null;

		try {
			ansatte = em.createQuery("Select f FROM Ansatt f", Ansatt.class).getResultList();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace(System.err);
		} finally {
			em.close();
		}

		return ansatte;

	}

	public List<Ansatt> finnVedAvdelingID(int id7) {
		EntityManager em = emf.createEntityManager();
		List<Ansatt> ansatte = null;
		try {
			ansatte = em.createQuery("Select f FROM Ansatt f where f.sted = :value1").setParameter("value1", id7)
					.getResultList();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace(System.err);
		} finally {
			em.close();
		}

		return ansatte;
	}

	public void registrerProsjektdeltagelse(Ansatt a, Prosjekt p, String rolle) {

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			a = em.merge(a);
			p = em.merge(p);

			Prosjektdeltagelse pd = new Prosjektdeltagelse(a, p, 0, rolle);

			a.leggTilProsjektdeltagelse(pd);
			p.leggTilProsjektdeltagelse(pd);

			em.persist(pd);

			tx.commit();
		} catch (Throwable e) {
			e.printStackTrace();
			if (tx.isActive()) {
				tx.rollback();
			}
		} finally {
			em.close();
		}

	}

	public Prosjektdeltagelse finnProsjektdeltagelse(Ansatt ansattId, Prosjekt prosjektId) {

		String queryString = "SELECT pd FROM Prosjektdeltagelse pd "
				+ "WHERE pd.ansatt = :ansattId AND pd.prosjekt = :prosjektId";
		
		EntityManager em = emf.createEntityManager();

		Prosjektdeltagelse pd = null;
		try {
			TypedQuery<Prosjektdeltagelse> query = em.createQuery(queryString, Prosjektdeltagelse.class);
			query.setParameter("ansattId", ansattId);
			query.setParameter("prosjektId", prosjektId);
			pd = query.getSingleResult();

		} catch (NoResultException e) {
			// e.printStackTrace();
		} finally {
			em.close();
		}
		return pd;
	}
	
	public void oppdaterTimer(Prosjektdeltagelse deltagelse) {
		EntityManager em = emf.createEntityManager();
		
		try {
			deltagelse = em.merge(deltagelse);
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch(Throwable e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			em.close();
		}
	}

}
