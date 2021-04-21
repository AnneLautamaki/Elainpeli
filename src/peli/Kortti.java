package peli;

public class Kortti {

	private String perherooli;
	private String el�inlaji;
	
	public Kortti(String perherooli, String el�inlaji) {
	
		this.perherooli = perherooli;
		this.el�inlaji = el�inlaji;
	
	}
	
	public String annaPerherooli() {
		return perherooli;	
		
	}
	
	public String annaElainlaji() {
		return el�inlaji;
	}
	
	public void asetaPerherooli(String perherooli) {
		this.perherooli = perherooli;
	}
	
	public void asetaElainlaji(String el�inlaji) {
		this.el�inlaji = el�inlaji;
	}
	
	public String toString() {
		return perherooli + "-" + el�inlaji;
	}
	
}
