package peli;

public class Kortti {

	private String perherooli;
	private String eläinlaji;
	
	public Kortti(String perherooli, String eläinlaji) {
	
		this.perherooli = perherooli;
		this.eläinlaji = eläinlaji;
	
	}
	
	public String annaPerherooli() {
		return perherooli;	
		
	}
	
	public String annaElainlaji() {
		return eläinlaji;
	}
	
	public void asetaPerherooli(String perherooli) {
		this.perherooli = perherooli;
	}
	
	public void asetaElainlaji(String eläinlaji) {
		this.eläinlaji = eläinlaji;
	}
	
	public String toString() {
		return perherooli + "-" + eläinlaji;
	}
	
}
