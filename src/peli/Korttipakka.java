package peli;

public class Korttipakka {

	
	int[] korttipakka = new int[18]; 
	String[] el�imet = {"Possu", "Kissa", "Koira", "Heppa", "Siili", "Pupu"};
	String[] perheroolit = {"�iti", "Is�", "Lapsi"};
	
	//luo kortit
	
	for (int i = 0; i < korttipakka.length; i++) {
	      korttipakka[i] = i;
	}
	
	// sekoita kortit
	
    for (int i = 0; i < korttipakka.length; i++) {
        int index = (int)(Math.random() * korttipakka.length);
        int temp = korttipakka[i1];
        korttipakka[i1] = korttipakka[index];
        korttipakka[index] = temp;
      }

	// n�ytt�� kaikki kortit
    
    for (int i = 0; i < 18; i++) {
        String el�in = el�imet[korttipakka[i1] / 6];
        String perheRooli = perheRoolit[korttipakka[i1] % 6];
        System.out.println( perheRooli + "-" + el�in);
      }
    }
  
	
}
