package peli;

import java.util.ArrayList;
import java.util.List;

public class Pelaaja {

	private String pelaajanNimi; 
	private List<String> pelaajanValmiitPerheet = new ArrayList<>();
	private List<Kortti> pelaajanKäsi = new ArrayList<>();
	
	
	public String getPelaajanNimi() {
		return pelaajanNimi;
	}
	public void setPelaajanNimi(String pelaajanNimi) {
		this.pelaajanNimi = pelaajanNimi;
	}
	public List<String> getPelaajanPerheet() {
		return pelaajanValmiitPerheet;
	}
	public void setPelaajanPerheet(List<String> pelaajanValmiitPerheet) {
		this.pelaajanValmiitPerheet = pelaajanValmiitPerheet;
	}
	public List<Kortti> getPelaajanKäsi() {
		return pelaajanKäsi;
	}
	public void setPelaajanKäsi(List<Kortti> pelaajanKäsi) {
		this.pelaajanKäsi = pelaajanKäsi;
	}
	
	public void addPerhe(String lisättävä) {
		pelaajanValmiitPerheet.add(lisättävä);
	}
	
}
