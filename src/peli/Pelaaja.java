package peli;

import java.util.ArrayList;
import java.util.List;

public class Pelaaja {

	private String pelaajanNimi; 
	private List<String> pelaajanValmiitPerheet = new ArrayList<>();
	private List<Kortti> pelaajanK�si = new ArrayList<>();
	
	
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
	public List<Kortti> getPelaajanK�si() {
		return pelaajanK�si;
	}
	public void setPelaajanK�si(List<Kortti> pelaajanK�si) {
		this.pelaajanK�si = pelaajanK�si;
	}
	
	public void addPerhe(String lis�tt�v�) {
		pelaajanValmiitPerheet.add(lis�tt�v�);
	}
	
}
