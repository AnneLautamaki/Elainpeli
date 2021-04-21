package peli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Peli {
	
	private Pelaaja pelaaja1 = new Pelaaja();
	private Pelaaja pelaaja2 = new Pelaaja();
		
	
	public void aloitaPeli() {
		
		pelaajat();
		
		List<Kortti> korttipakka = luoKorttipakka();
		jaaKortit(korttipakka);
		
		System.out.println("Pelaajan " +pelaaja1.getPelaajanNimi() +" jaetut kortit:");
		System.out.println();
		 for(Kortti kortti: pelaaja1.getPelaajanKäsi()) {
	        	System.out.println(kortti);
	        	System.out.println();
	        }
		 
		System.out.println("Pelaajan " +pelaaja2.getPelaajanNimi() +" jaetut kortit:");
		System.out.println();
		 for(Kortti kortti: pelaaja2.getPelaajanKäsi()) {
		       	System.out.println(kortti);
		       	System.out.println();
		    }
		 
		 tulikoPerhe(pelaaja1);
		 tulikoPerhe(pelaaja2);
	 		 		 
	 	 boolean onkoVoittaja = false; 
		 		 
		 while(!onkoVoittaja) {
			 pelaaVuoro(pelaaja1, pelaaja2);
			 tulikoPerhe(pelaaja1);
		 			 
			 onkoVoittaja = voittaja();
			 if(onkoVoittaja == true) {
				 break;
			 }
			 pelaaVuoro(pelaaja2, pelaaja1);
			 tulikoPerhe(pelaaja2);
			  
			 onkoVoittaja = voittaja();
			 if(onkoVoittaja == true) {
				 break; 
			 }
		 }
		 
		 System.out.println();

	}

	public void pelaajat(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Pelaaja1 anna nimi:");
		
		String nimi = sc.nextLine();
		
		pelaaja1.setPelaajanNimi(nimi);
		System.out.println("Hauskaa peliä " +nimi +"!");
		System.out.println("");
		
		System.out.println("Pelaaja2 anna nimi:");
		
		String nimi2 = sc.nextLine();
		
		pelaaja2.setPelaajanNimi(nimi2);
		System.out.println("Hauskaa peliä " +nimi2 +"!");
		System.out.println("");
		
	}
		
		
public List<Kortti> luoKorttipakka() {
    ArrayList<Kortti> korttipakka = new ArrayList<Kortti>();

    String[] eläinLajit = {"Kissa", "Koira", "Possu", "Pupu", "Heppa", "Siili", "Kettu", "Lisko", "Marsu", "Kala"};
    String[] perheRoolit = {"Äiti", "Isä", "Tyttö", "Poika"};

    
    // luodaan korttipakka
 
        for (int i = 0; i<perheRoolit.length; i++) {
            for(int j=0; j<eläinLajit.length; j++){
                korttipakka.add(new Kortti(perheRoolit[i],eläinLajit[j]));
            }
        }
        
    //sekoita pakka kun se on luotu
        Collections.shuffle(korttipakka);
        
        return korttipakka;
    }
		
	public void jaaKortit(List<Kortti>korttipakka){
	
	// jakaa joka toisen pelaajalle 1 ja jokatoisen pelaajalle 2	
		
		ArrayList<Kortti> pelaajan1Käsi = new ArrayList<>();
		ArrayList<Kortti> pelaajan2Käsi = new ArrayList<>();
		
		for(int i = 0; i<korttipakka.size(); i++) {
			if(i % 2 == 0) {
				pelaajan1Käsi.add(korttipakka.get(i));
			}
			else {
				pelaajan2Käsi.add(korttipakka.get(i));
			}
			
		}
		
		pelaaja1.setPelaajanKäsi(pelaajan1Käsi);
		pelaaja2.setPelaajanKäsi(pelaajan2Käsi);
	}


			
	public void pelaaVuoro(Pelaaja nostava, Pelaaja luovuttava){ 
		
	// Pelaaja 1 aloittaa aina. Peli kertoo mitä perheitä on jo.
		
		 if(nostava.getPelaajanPerheet().size() > 0) {
			 System.out.println(nostava.getPelaajanNimi() +" Sinulla on jo perheet:" + nostava.getPelaajanPerheet());
			 }
		 else{
			 System.out.println(nostava.getPelaajanNimi() +" Sinulla ei ole vielä yhtään perhettä");
		 }
		 
		 //Tulostus korteista ja tämän jälkeen shuffle, jotta pelaaja ei voi laskea edellä näytetyistä korteista mikä kannattaa nostaa:
		 
		 System.out.println("Sinun kädessä olevat kortit ovat: " +nostava.getPelaajanKäsi()); 
		 Collections.shuffle(nostava.getPelaajanKäsi());
		
		int nostettu = -1;	 
		 
		Scanner sc = new Scanner(System.in);
		
		// Varmistetaan että valitaan kortti oikealta väliltä.
		
		boolean sallittuKortti = false;
		while(!sallittuKortti){
								
			System.out.println(nostava.getPelaajanNimi()+" nosta kortti. Valitse numero väliltä" +" 1-"  + luovuttava.getPelaajanKäsi().size() +".");
			nostettu = sc.nextInt();
			
			if(nostettu >= 1 && nostettu <= luovuttava.getPelaajanKäsi().size()) {
				sallittuKortti = true;
			}
			else {
				System.out.println(nostava.getPelaajanNimi()+" Valitsit väärän numeron!");	
			}
		}
		
				
		Kortti pelattu = luovuttava.getPelaajanKäsi().get(nostettu-1);
		nostava.getPelaajanKäsi().add(pelattu);
		luovuttava.getPelaajanKäsi().remove(pelattu);
		
		System.out.println("Nostit kortin " + pelattu);
		System.out.println();
	
	}
	
	public void tulikoPerhe(Pelaaja pelaaja) {
	
	// Käytetään HashMappia koska tarvitaan avain mitä etsitään eli eläinlaji ja varmistaan että näitä eläimiä on tarvittava määrä eli 4 (äiti, isä, tyttö ja poika)	
		
	HashMap<String, Integer> tulikoPerhe = new HashMap<String, Integer>();	
		
	 for(Kortti kortti: pelaaja.getPelaajanKäsi()) {
		 
		 if(tulikoPerhe.containsKey(kortti.annaElainlaji())) {
			 int montako = tulikoPerhe.get(kortti.annaElainlaji());
			 tulikoPerhe.put(kortti.annaElainlaji(), ++montako);
		 }
		 else {
			 tulikoPerhe.put(kortti.annaElainlaji(), 1);
		 }
     }
	
	 for(String key : tulikoPerhe.keySet()) {
		 if(tulikoPerhe.get(key) == 4) {
			 pelaaja.addPerhe(key);
			 ArrayList<Kortti> poistettavat = new ArrayList<>();
			 for(Kortti k : pelaaja.getPelaajanKäsi()) {
				 if(k.annaElainlaji().equals(key)) {
					 poistettavat.add(k);
				}
			 }
			pelaaja.getPelaajanKäsi().removeAll(poistettavat); 
			 
		 }
	}
			
	}
			
	public boolean voittaja(){ 
	
		if(pelaaja1.getPelaajanPerheet().size() > 2 && pelaaja2.getPelaajanPerheet().size() > 2) {
			System.out.println("Tasapeli!");
			return true;	
		}
		
		else if(pelaaja1.getPelaajanPerheet().size() > 2) {
			System.out.println("Voittaja on " + pelaaja1.getPelaajanNimi() + " !");
			System.out.println("Voitit perheillä: " +pelaaja1.getPelaajanPerheet());
			return true;
		
		}
		else if	(pelaaja2.getPelaajanPerheet().size() > 2) {
			System.out.println("Voittaja on " + pelaaja2.getPelaajanNimi() + " !");
			System.out.println("Voitit perheillä: " +pelaaja2.getPelaajanPerheet());
			return true;
		}
		
		return false;
	}

	public Pelaaja getPelaaja1() {
		return pelaaja1;
	}

	public void setPelaaja1(Pelaaja pelaaja1) {
		this.pelaaja1 = pelaaja1;
	}

	public Pelaaja getPelaaja2() {
		return pelaaja2;
	}

	public void setPelaaja2(Pelaaja pelaaja2) {
		this.pelaaja2 = pelaaja2;
	}
			
}
