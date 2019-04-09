package no.hvl.dat107.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3_jpa")
public class Prosjektdeltagelse {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prosjektdeltagelse_Id;
    
    private int timer;
    private String rolle;
    @ManyToOne
    @JoinColumn(name="Ansatt_Id")
    private Ansatt ansatt;
    
    @ManyToOne
    @JoinColumn(name="Prosjekt_Id")
    private Prosjekt prosjekt;

    
public Prosjektdeltagelse() {}
    
    public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, int timer, String rolle) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
        this.timer = timer;
        this.rolle = rolle;
        
        
        //Hvis vi gjør dette her slipper vi å gjøre det i EAO
        ansatt.leggTilProsjektdeltagelse(this);
        prosjekt.leggTilProsjektdeltagelse(this);
    }
    
    public void skrivUt(String innrykk) {
        System.out.printf("%sDeltagelse: %s %s, %s, %d timer, %s", innrykk, 
                ansatt.getFornavn(), ansatt.getEtternavn(), prosjekt.getNavn(), timer, rolle);
    }

	public int getTimer() {
		return timer;
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public void leggtilTimer(int timer) {
		this.timer += timer;
		
	}
    
    
}
