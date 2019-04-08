package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import no.hvl.dat107.eao.AnsattEAO;
import no.hvl.dat107.eao.AvdelingEAO;
import no.hvl.dat107.eao.ProsjektEAO;
import no.hvl.dat107.eao.ProsjektdeltagelseEAO;
import no.hvl.dat107.entity.Ansatt;
import no.hvl.dat107.entity.Avdeling;
import no.hvl.dat107.entity.Prosjekt;
import no.hvl.dat107.entity.Prosjektdeltagelse;

public class Main {

	public static void main(String[] args) {
		AnsattEAO ansattEAO = new AnsattEAO();
		AvdelingEAO avdelingEAO = new AvdelingEAO();
		ProsjektEAO prosjektEAO = new ProsjektEAO();
		ProsjektdeltagelseEAO deltagelseEAO = new ProsjektdeltagelseEAO();
		Scanner tastatur = new Scanner(System.in);
		int input;
		meny();
		input = tastatur.nextInt();
		tastatur.nextLine();
		while (input != 0) {
			switch (input) {
			case 1:
				nyAnsatt(ansattEAO, avdelingEAO);
				break;
			case 2:
				System.out.println("Oppdatere ansatt med id(1) eller brukernavn(2)?");
				input = tastatur.nextInt();
				tastatur.nextLine();
				Ansatt ans = null;
				switch (input) {
				case 1:
					System.out.println("Skriv inn id du vil oppdatere: ");
					int id = tastatur.nextInt();
					tastatur.nextLine();
					ans = ansattEAO.finnAnsattID(ansattEAO, id);
					updateAnsatt(ansattEAO, ans);
					break;
				case 2:
					System.out.println("Skriv inn brukernavn du vil oppdatere: ");
					String bnavn = tastatur.next();
					ans = ansattEAO.finnAnsattBN(ansattEAO, bnavn);
					updateAnsatt(ansattEAO, ans);
					break;
				}

				break;
			case 3:
				System.out.println("Ansatte i databasen");
				List<Ansatt> a = ansattEAO.getAnsatt();
				for (int i = 0; i < a.size(); i++) {
					System.out.println(a.get(i).toString());
				}
				break;
			case 4:
				System.out.println("Søke etter ansatt med id(1) eller brukernavn(2)?");
				input = tastatur.nextInt();
				tastatur.nextLine();
				switch (input) {
				case 1:
					System.out.println("Skriv inn id du vil søke etter: ");
					int id = tastatur.nextInt();
					tastatur.nextLine();
					System.out.println(ansattEAO.finnAnsattID(ansattEAO, id).toString());
					break;
				case 2:
					System.out.println("Skriv inn brukernavn du vil søke etter: ");
					String bnavn = tastatur.next();
					System.out.println(ansattEAO.finnAnsattBN(ansattEAO, bnavn).toString());
					break;
				}
			case 5:
				System.out.println("Avdelinger i databasen");
				List<Avdeling> b = avdelingEAO.getAvdeling();
				for (int i = 0; i < b.size(); i++) {
					System.out.println(b.get(i).toString());
				}
				break;
			case 6:
				System.out.println("Skriv inn avdelings id du vil søke etter");
				int id6 = tastatur.nextInt();
				tastatur.nextLine();
				System.out.println(avdelingEAO.finnAvdelingID(avdelingEAO, id6));
				break;
			case 7:
				System.out.println("Skriv inn avdelings id");
				int id7 = tastatur.nextInt();
				tastatur.nextLine();
				Avdeling avd = avdelingEAO.finnAvdelingID(avdelingEAO, id7);
				Ansatt ans7 = ansattEAO.finnAnsattID(ansattEAO, avd.getSjef());
				System.out.println("Sjef av avdelingen er: " + ans7.toString());
				List<Ansatt> ansatte = ansattEAO.finnVedAvdelingID(id7);
				for (int i = 0; i < ansatte.size(); i++) {
					System.out.println(ansatte.get(i).toString());
				}
			case 8:
				System.out.println("Skriv inn ansatt id du vil oppdatere avdeling på");
				int id8 = tastatur.nextInt();
				tastatur.nextLine();
				Ansatt ans8 = ansattEAO.finnAnsattID(ansattEAO, id8);
				Avdeling avd8 = avdelingEAO.finnAvdelingID(avdelingEAO, ans8.getAvdeling());
				if (id8 == avd8.getSjef()) {
					System.out.println("Kan ikke oppdatere avdeling på en sjef");
				} else {
					oppdaterAvdeling(ans8);
				}
				break;
			case 9:
				opprettAvdeling(avdelingEAO, ansattEAO);
				break;
			case 10:
				opprettProsjekt(prosjektEAO);
				break;
			case 11:
				System.out.println("Skriv inn prosjekt id");
				int id11 = tastatur.nextInt();
				tastatur.nextLine();
				Prosjekt prosjekt11 = prosjektEAO.finnProsjektMedId(id11);
				List<Prosjektdeltagelse> deltagelse11 = prosjekt11.getDeltagelser();
				System.out.println("Prosjekt navn: " + prosjekt11.getNavn());
				System.out.println("Prosjekt beskrivelse: " + prosjekt11.getBeskrivelse());
				System.out.print("Deltagere i prosjektet: ");
				for (int i = 0; i < deltagelse11.size(); i++) {
					deltagelse11.get(i).skrivUt("\n");
				}
				break;

			case 12:
				System.out.println("Skriv inn ansatt id");
				int id12 = tastatur.nextInt();
				tastatur.nextLine();
				System.out.println("Skriv inn prosjekt id");
				int pid12 = tastatur.nextInt();
				tastatur.nextLine();
				System.out.println("Skriv inn rolle for bruker");
				String rolle12 = tastatur.next();

				Ansatt ans12 = ansattEAO.finnAnsattID(ansattEAO, id12);
				Prosjekt prosjekt12 = prosjektEAO.finnProsjektMedId(pid12);

				ansattEAO.registrerProsjektdeltagelse(ans12, prosjekt12, rolle12);
				break;

			case 13:
				System.out.println("Skriv inn ansatt id");
				int id13 = tastatur.nextInt();
				tastatur.nextLine();
				System.out.println("Skriv inn prosjekt id");
				int pid13 = tastatur.nextInt();
				tastatur.nextLine();
				System.out.println("Skriv inn timer du vil logføre");
				int nytimer = tastatur.nextInt();
				tastatur.nextLine();
				Ansatt ansatt13 = ansattEAO.finnAnsattID(ansattEAO, id13);
				Prosjekt prosjekt13 = prosjektEAO.finnProsjektMedId(pid13);
				
				Prosjektdeltagelse deltagelse13 = ansattEAO.finnProsjektdeltagelse(ansatt13, prosjekt13);
				int timer = deltagelse13.getTimer();
				//deltagelse13.setTimer(timer + nytimer);
					System.out.println("Timer registret = " + timer);			
				break;
			}
			System.out.println("\n");
			meny();
			input = tastatur.nextInt();
			tastatur.nextLine();
		}

	}

	private static void opprettProsjekt(ProsjektEAO prosjektEAO) {
		Scanner tastatur = new Scanner(System.in);
		System.out.print("Skriv inn nytt prosjekt navn");
		String navn = tastatur.next();

		System.out.println("Skriv inn prosjekt beskrivelse");
		String beskrivelse = tastatur.next();

		Prosjekt ny = new Prosjekt(navn, beskrivelse);
		prosjektEAO.lagreIDatabasen(ny);

	}

	private static void opprettAvdeling(AvdelingEAO avdelingEAO, AnsattEAO ansattEAO) {
		Avdeling ny;
		Scanner tastatur = new Scanner(System.in);
		System.out.println("Legger til ny avdeling.");
		System.out.println("Skriv inn avdelings navn");
		String navn = tastatur.next();

		System.out.println("Skriv inn sjef id");
		int sjefid = tastatur.nextInt();
		tastatur.nextLine();

		Ansatt sjef = ansattEAO.finnAnsattID(ansattEAO, sjefid);
		ny = new Avdeling(navn, sjefid);
		System.out.println("Ny avdeling har navn: " + avdelingEAO.lagreIDatabasen(ny));
		sjef.setAvdeling(ny.getAvdelingId());
		ansattEAO.updateAnsatt(sjef);

	}

	private static void oppdaterAvdeling(Ansatt ans8) {
		System.out.println("Skriv inn avdelingId på ny avdeling for ansatt");
		Scanner tastatur = new Scanner(System.in);
		int input = tastatur.nextInt();
		tastatur.nextLine();
		ans8.setAvdeling(input);
		System.out.println(ans8.toString());
	}

	private static void updateAnsatt(AnsattEAO eao, Ansatt update) {
		Scanner tastatur = new Scanner(System.in);

		System.out.println("Skal du oppdatere lønn(1), tittel(2) eller begge(3)?");
		int input = tastatur.nextInt();
		tastatur.nextLine();

		switch (input) {
		case 1:
			System.out.println("Skriv inn maanedslønn: ");
			BigDecimal maanedslonn = tastatur.nextBigDecimal();
			update.setMaanedslonn(maanedslonn);
			break;
		case 2:
			System.out.println("Skriv inn stillingstittel: ");
			update.setStilling(tastatur.next());
			break;
		case 3:
			System.out.println("Skriv inn maanedslønn: ");
			BigDecimal maanedslonn1 = tastatur.nextBigDecimal();
			update.setMaanedslonn(maanedslonn1);

			System.out.println("Skriv inn stillingstittel: ");
			update.setStilling(tastatur.next());
			break;
		}

		eao.updateAnsatt(update);
	}

	public static LocalDate dateInput(String userInput) {
		int arg0, arg1, arg2;
		arg0 = Integer.parseInt(userInput.substring(0, 2));
		arg1 = Integer.parseInt(userInput.substring(3, 5));
		arg2 = Integer.parseInt(userInput.substring(6, 10));

		System.out.println(arg0 + " " + arg1 + " " + arg2);
		LocalDate date = LocalDate.of(arg2, arg1, arg0);

		return date;
	}

	public static void nyAnsatt(AnsattEAO eao, AvdelingEAO avdeao) {
		Ansatt ny;
		Scanner tastatur = new Scanner(System.in);
		String input = "";
		int count = 1;
		while (count != 0) {
			System.out.println("Legger til ny ansatt.");
			System.out.println("Skriv inn brukernavn (maks 5 tegn): ");
			String brukernavn = tastatur.next();

			System.out.println("Skriv inn fornavn: ");
			String fornavn = tastatur.next();

			System.out.println("Skriv inn etternavn: ");
			String etternavn = tastatur.next();

			System.out.println("Skriv inn annsettelses dato på form dd/mm/yyyy: ");
			String userInput = tastatur.next();
			LocalDate date = dateInput(userInput);

			System.out.println("Skriv inn stillingstittel: ");
			String stilling = tastatur.next();

			System.out.println("Skriv inn maanedslønn: ");
			BigDecimal maanedslonn = tastatur.nextBigDecimal();
			tastatur.nextLine();

			System.out.println("Skriv inn avdelings nummer: ");
			int avdNr = tastatur.nextInt();
			tastatur.nextLine();

			ny = new Ansatt(brukernavn, fornavn, etternavn, date, stilling, maanedslonn, avdNr);
			System.out.println("Ny bruker har brukernavn: " + eao.lagreIDatabasen(ny));
			tastatur.nextLine();
			System.out.println("Legge til flere? (y/n)");
			input = tastatur.next();

			if (input == "n") {
				count++;
			}
			count--;

		}
	}

	public static void meny() {
		System.out.println("0: Avslutt");
		System.out.println("1: Legg til ny Ansatt");
		System.out.println("2: Oppdater Ansatt");
		System.out.println("3: Print ut alle Ansatte");
		System.out.println("4: Søke etter ansatt");
		System.out.println("5: Print ut alle Avdelinger");
		System.out.println("6: Søk etter avdeling med Id");
		System.out.println("7: Print ut alle annsatte med avdeling id");
		System.out.println("8: Oppdatere avdeling en ansatt jobber på");
		System.out.println("9: Opprette ny avdeling");
		System.out.println("10: Opprette nytt prosjekt");
		System.out.println("11: Print ut info om prosjekt med id");
		System.out.println("12: Registrere prosjektdeltagelse for ansatt med id");
		System.out.println("13: Registrere timer for ansatt med id på prosjekt med id");
	}

}
