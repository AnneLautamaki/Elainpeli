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
		 for(Kortti kortti: pelaaja1.getPelaajanK�si()) {
	        	System.out.println(kortti);
	        	System.out.println();
	        }
		 
		System.out.println("Pelaajan " +pelaaja2.getPelaajanNimi() +" jaetut kortit:");
		System.out.println();
		 for(Kortti kortti: pelaaja2.getPelaajanK�si()) {
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
		System.out.println("Hauskaa peli� " +nimi +"!");
		System.out.println("");
		
		System.out.println("Pelaaja2 anna nimi:");
		
		String nimi2 = sc.nextLine();
		
		pelaaja2.setPelaajanNimi(nimi2);
		System.out.println("Hauskaa peli� " +nimi2 +"!");
		System.out.println("");
		
	}
		
		
public List<Kortti> luoKorttipakka() {
    ArrayList<Kortti> korttipakka = new ArrayList<Kortti>();

    String[] el�inLajit = {"Kissa", "Koira", "Possu", "Pupu", "Heppa", "Siili", "Kettu", "Lisko", "Marsu", "Kala"};
    String[] perheRoolit = {"�iti", "Is�", "Tytt�", "Poika"};

    
    // luodaan korttipakka
 
        for (int i = 0; i<perheRoolit.length; i++) {
            for(int j=0; j<el�inLajit.length; j++){
                korttipakka.add(new Kortti(perheRoolit[i],el�inLajit[j]));
            }
        }
        
    //sekoita pakka kun se on luotu
        Collections.shuffle(korttipakka);
        
        return korttipakka;
    }
		
	public void jaaKortit(List<Kortti>korttipakka){
	
	// jakaa joka toisen pelaajalle 1 ja jokatoisen pelaajalle 2	
		
		ArrayList<Kortti> pelaajan1K�si = new ArrayList<>();
		ArrayList<Kortti> pelaajan2K�si = new ArrayList<>();
		
		for(int i = 0; i<korttipakka.size(); i++) {
			if(i % 2 == 0) {
				pelaajan1K�si.add(korttipakka.get(i));
			}
			else {
				pelaajan2K�si.add(korttipakka.get(i));
			}
			
		}
		
		pelaaja1.setPelaajanK�si(pelaajan1K�si);
		pelaaja2.setPelaajanK�si(pelaajan2K�si);
	}


			
	public void pelaaVuoro(Pelaaja nostava, Pelaaja luovuttava){ 
		
	// Pelaaja 1 aloittaa aina. Peli kertoo mit� perheit� on jo.
		
		 if(nostava.getPelaajanPerheet().size() > 0) {
			 System.out.println(nostava.getPelaajanNimi() +" Sinulla on jo perheet:" + nostava.getPelaajanPerheet());
			 }
		 else{
			 System.out.println(nostava.getPelaajanNimi() +" Sinulla ei ole viel� yht��n perhett�");
		 }
		 
		 //Tulostus korteista ja t�m�n j�lkeen shuffle, jotta pelaaja ei voi laskea edell� n�ytetyist� korteista mik� kannattaa nostaa:
		 
		 System.out.println("Sinun k�dess� olevat kortit ovat: " +nostava.getPelaajanK�si()); 
		 Collections.shuffle(nostava.getPelaajanK�si());
		
		int nostettu = -1;	 
		 
		Scanner sc = new Scanner(System.in);
		
		// Varmistetaan ett� valitaan kortti oikealta v�lilt�.
		
		boolean sallittuKortti = false;
		while(!sallittuKortti){
								
			System.out.println(nostava.getPelaajanNimi()+" nosta kortti. Valitse numero v�lilt�" +" 1-"  + luovuttava.getPelaajanK�si().size() +".");
			nostettu = sc.nextInt();
			
			if(nostettu >= 1 && nostettu <= luovuttava.getPelaajanK�si().size()) {
				sallittuKortti = true;
			}
			else {
				System.out.println(nostava.getPelaajanNimi()+" Valitsit v��r�n numeron!");	
			}
		}
		
				
		Kortti pelattu = luovuttava.getPelaajanK�si().get(nostettu-1);
		nostava.getPelaajanK�si().add(pelattu);
		luovuttava.getPelaajanK�si().remove(pelattu);
		
		System.out.println("Nostit kortin " + pelattu);
		System.out.println();
	
	}
	
	public void tulikoPerhe(Pelaaja pelaaja) {
	
	// K�ytet��n HashMappia koska tarvitaan avain mit� etsit��n eli el�inlaji ja varmistaan ett� n�it� el�imi� on tarvittava m��r� eli 4 (�iti, is�, tytt� ja poika)	
		
	HashMap<String, Integer> tulikoPerhe = new HashMap<String, Integer>();	
		
	 for(Kortti kortti: pelaaja.getPelaajanK�si()) {
		 
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
			 for(Kortti k : pelaaja.getPelaajanK�si()) {
				 if(k.annaElainlaji().equals(key)) {
					 poistettavat.add(k);
				}
			 }
			pelaaja.getPelaajanK�si().removeAll(poistettavat); 
			 
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
			System.out.println("Voitit perheill�: " +pelaaja1.getPelaajanPerheet());
			return true;
		
		}
		else if	(pelaaja2.getPelaajanPerheet().size() > 2) {
			System.out.println("Voittaja on " + pelaaja2.getPelaajanNimi() + " !");
			System.out.println("Voitit perheill�: " +pelaaja2.getPelaajanPerheet());
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
