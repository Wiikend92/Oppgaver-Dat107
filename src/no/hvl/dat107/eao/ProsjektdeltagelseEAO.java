package no.hvl.dat107.eao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import no.hvl.dat107.entity.Prosjektdeltagelse;

public class ProsjektdeltagelseEAO {
	private EntityManagerFactory emf;

	public ProsjektdeltagelseEAO() {
		emf = Persistence.createEntityManagerFactory("Oblig3PersistenceUnit");
	}
	
	
	public Prosjektdeltagelse finnDeltagelseMedId(int id) {

        EntityManager em = emf.createEntityManager();

        Prosjektdeltagelse prosjekt = null;
        try {
            prosjekt = em.find(Prosjektdeltagelse.class, id);
        } finally {
            em.close();
        }
        return prosjekt;
    }
}
