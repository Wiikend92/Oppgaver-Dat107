package no.hvl.dat107;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		AnsattEAO aeao = new AnsattEAO();
		AvdelingEAO avdeao = new AvdelingEAO();
		Scanner tastatur = new Scanner(System.in);
		int input;
		String strenginn;
		meny();
		input = tastatur.nextInt();
		tastatur.nextLine();
		switch (input) {
		case 1:
			nyAnsatt(aeao, avdeao);
			break;
		case 2:
			System.out.println("Hvilket brukernavn skal oppdaterers?");
			strenginn = tastatur.next();
			updateAnsatt(aeao, strenginn);
			break;
		case 3:
			System.out.println("Ansatte i databasen");
			List<Ansatt> a = aeao.getAnsatt();
			for(int i = 0; i < a.size(); i++) {
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
				System.out.println(aeao.finnAnsattID(aeao, id).toString());
				break;
			case 2:
				System.out.println("Skriv inn brukernavn du vil søke etter: ");
				String bnavn = tastatur.next();
				System.out.println(aeao.finnAnsattBN(aeao, bnavn).toString());
				break;
			}
		case 5:
			System.out.println("Ansatte i databasen");
			List<Avdeling> b = avdeao.getAvdeling();
			for(int i = 0; i < b.size(); i++) {
				System.out.println(b.get(i).toString());
			}
			break;
		}

	}

	private static void updateAnsatt(AnsattEAO eao, String brukernavn) {
		Scanner tastatur = new Scanner(System.in);
		Ansatt update = eao.finnAnsattBN(eao, brukernavn);
		
		System.out.println("Skal du oppdatere lønn(1), tittel(2) eller begge(3)?");
		int input = tastatur.nextInt();
		tastatur.nextLine();
		
		switch(input){
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
			
			System.out.println("Skriv inn avdelings nummer: ");
			tastatur.nextLine();
			int avdNr = tastatur.nextInt();
			tastatur.nextLine();
			Avdeling avdeling = avdeao.finnAvdelingID(avdeao, avdNr);
			

			ny = new Ansatt(brukernavn, fornavn, etternavn, date, stilling, maanedslonn, avdeling);
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
		System.out.println("1: Legg til ny Ansatt");
		System.out.println("2: Oppdater Ansatt med brukernavn");
		System.out.println("3: Print ut alle Ansatte: ");
		System.out.println("4: Søke etter ansatt: ");
		System.out.println("5: Print ut alle Avdelinger: ");
	}

}
