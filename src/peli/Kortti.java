package peli;

public class Kortti {

	private String perherooli;
	private String elšinlaji;
	
	public Kortti(String perherooli, String elšinlaji) {
	
		this.perherooli = perherooli;
		this.elšinlaji = elšinlaji;
	
	}
	
	public String annaPerherooli() {
		return perherooli;	
		
	}
	
	public String annaElainlaji() {
		return elšinlaji;
	}
	
	public void asetaPerherooli(String perherooli) {
		this.perherooli = perherooli;
	}
	
	public void asetaElainlaji(String elšinlaji) {
		this.elšinlaji = elšinlaji;
	}
	
	public String toString() {
		return perherooli + "-" + elšinlaji;
	}
	
}
