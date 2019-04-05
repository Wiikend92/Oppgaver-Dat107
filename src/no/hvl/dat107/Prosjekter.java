package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "oblig3_jpa", name = "prosjekter")
public class Prosjekter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int prosjektNr;
	private String navn;
	private String beskrivelse;
	@ManyToOne
	@JoinColumn(name = "ansNr", referencedColumnName = "ansNr")
	private Ansatt ansatt;
	
	private int arbeidstimer;

	public int getProsjektNr() {
		return prosjektNr;
	}

	public void setProsjektNr(int prosjektNr) {
		this.prosjektNr = prosjektNr;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

	public Ansatt getAnsatt() {
		return ansatt;
	}

	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}

	public int getArbeidstimer() {
		return arbeidstimer;
	}

	public void setArbeidstimer(int arbeidstimer) {
		this.arbeidstimer = arbeidstimer;
	}

}
