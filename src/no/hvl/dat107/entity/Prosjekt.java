package no.hvl.dat107.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3_jpa")
public class Prosjekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String navn;
    private String beskrivelse;
    
    @OneToMany(mappedBy="prosjekt")
    private List<Prosjektdeltagelse> deltagelser;
    
    
    public Prosjekt() {
    	
    }
    public Prosjekt(String navn, String beskrivelse) {
    	this.navn = navn;
    	this.beskrivelse = beskrivelse;
    }
    
    
    public void skrivUt(String innrykk) {
        System.out.printf("%sProsjekt nr %d: %s", innrykk, id, navn);
    }
    
    public void skrivUtMedAnsatte() {
        System.out.println();
        skrivUt("");
        deltagelser.forEach(a -> a.skrivUt("\n   "));
    }

    public void leggTilProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.add(prosjektdeltagelse);
    }

    public void fjernProsjektdeltagelse(Prosjektdeltagelse prosjektdeltagelse) {
        deltagelser.remove(prosjektdeltagelse);
    }

	public int getId() {
		return id;
	}

	public String getNavn() {
		return navn;
	}

	public List<Prosjektdeltagelse> getDeltagelser() {
		return deltagelser;
	}
	
	public String getBeskrivelse() {
		return beskrivelse;
	}

}
