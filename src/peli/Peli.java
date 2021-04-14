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
		
		System.out.println("Pelaajan " +pelaaja1.getPelaajanNimi() +" kortit");
		 for(Kortti kortti: pelaaja1.getPelaajanK�si()) {
	        	System.out.println(kortti);
	        }
		 
		System.out.println("Pelaajan " +pelaaja2.getPelaajanNimi() +" kortit");
		 for(Kortti kortti: pelaaja2.getPelaajanK�si()) {
		       	System.out.println(kortti);
		    }
		 
		 tulikoPerhe(pelaaja1);
		 tulikoPerhe(pelaaja2);
		 
		 
		 
		 System.out.println(pelaaja1.getPelaajanPerheet());
		 System.out.println(pelaaja1.getPelaajanK�si());
		 
		 System.out.println(pelaaja2.getPelaajanPerheet());
		 System.out.println(pelaaja2.getPelaajanK�si());
		 
		 boolean onkoVoittaja = false; 
		 
		 
		 while(!onkoVoittaja) {
			 pelaaVuoro(pelaaja1, pelaaja2);
			 tulikoPerhe(pelaaja1);
			 
			 System.out.println(pelaaja1.getPelaajanPerheet()); 
			 System.out.println(pelaaja1.getPelaajanK�si());
			 
			 onkoVoittaja = voittaja();
			 if(onkoVoittaja == true) {
				 break;
			 }
			 pelaaVuoro(pelaaja2, pelaaja1);
			 tulikoPerhe(pelaaja2);
			 
			 System.out.println(pelaaja2.getPelaajanPerheet());
			 System.out.println(pelaaja2.getPelaajanK�si());
			 
			 onkoVoittaja = voittaja();
			 if(onkoVoittaja == true) {
				 break; 
			 }
		 }
		 System.out.println();
		 System.out.println(pelaaja1.getPelaajanPerheet()); 
		 System.out.println(pelaaja1.getPelaajanK�si());
		 
		 System.out.println(pelaaja2.getPelaajanPerheet());
		 System.out.println(pelaaja2.getPelaajanK�si());
		 
	}

	public void pelaajat(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Pelaaja1 anna nimi:");
		
		String nimi = sc.nextLine();
		
		pelaaja1.setPelaajanNimi(nimi);
		System.out.println("Hauskaa peli� " +nimi +"!");
		
		System.out.println("Pelaaja2 anna nimi:");
		
		String nimi2 = sc.nextLine();
		
		pelaaja2.setPelaajanNimi(nimi2);
		System.out.println("Hauskaa peli� " +nimi2 +"!");
		
		
	}
			
		
public List<Kortti> luoKorttipakka() {
    ArrayList<Kortti> korttipakka = new ArrayList<Kortti>();

    String[] el�inLajit = {"Kissa", "Koira","Possu","Pupu","Heppa","Siili"};
    String[] perheRoolit = {"�iti", "Is�", "Lapsi"};

    
    // korttipakka
 
        for (int i = 0; i<perheRoolit.length; i++) {
            for(int j=0; j<el�inLajit.length; j++){
                korttipakka.add(new Kortti(perheRoolit[i],el�inLajit[j]));
            }
        }
        
        //sekoita pakka kun se on luotu
        Collections.shuffle(korttipakka);

        for(Kortti kortti: korttipakka) {
        //	System.out.println(kortti);
        }
        
        return korttipakka;
    }
		
	public void jaaKortit(List<Kortti>korttipakka){
		
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


			
	public void pelaaVuoro(Pelaaja nostava, Pelaaja luovuttava){ // lis�� huomautus jos sy�tt�� v��r�n indeksin
		
	// pelaaja 1 aloittaa aina
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println(nostava.getPelaajanNimi()+" nosta kortti. Valitse numero v�lilt�" +" 1-"  + luovuttava.getPelaajanK�si().size());
		int nostettu = sc.nextInt();
		
		Kortti pelattu = luovuttava.getPelaajanK�si().get(nostettu-1);
		nostava.getPelaajanK�si().add(pelattu);
		luovuttava.getPelaajanK�si().remove(pelattu);
		
		// tarkistaa tuleeko perhe pelaaja2 jos ei voittaja niin jatkuu pelaaja 1 vuorolla
		


		

	
		
		// pit�isi saada toimimaan niin ett� voittajaa ei tulisi heti ja ett� vuorot jatkuisivat tarvitaanko joku silmukka/ehto vai miten?
		
	// indeksi, montako korttia pelaajalla, valinta vastaus scannerilla TEE T�M�
		
	}
	
	public void tulikoPerhe(Pelaaja pelaaja) {
		
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
		 if(tulikoPerhe.get(key) == 3) {
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
			
	 	// tarkastaa tuliko perhe kun nostettiin kortti	TEE TOINEN TULIKOPERHE!!!!!!! tehty
	}
			
	public boolean voittaja(){ 
	
		if(pelaaja1.getPelaajanPerheet().size() > 2 && pelaaja2.getPelaajanPerheet().size() > 2) {
			System.out.println("Tasapeli!");
			return true;	
		}
		
		else if(pelaaja1.getPelaajanPerheet().size() > 2) {
			System.out.println("Voittaja on " + pelaaja1.getPelaajanNimi() + " !");
			return true;
		
		}
		else if	(pelaaja2.getPelaajanPerheet().size() > 2) {
			System.out.println("Voittaja on " + pelaaja2.getPelaajanNimi() + " !");
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
