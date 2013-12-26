package vehicules;

public class Voiture {
	private String modele;
	private String couleur;
	private int confort;
	
	public Voiture (String modele, String couleur, int confort){
		this.modele=modele;
		this.couleur=couleur;
		if (confort < 0){
			this.confort=0;
		} else if (confort > 5){
			this.confort=5;
		} else {
			this.confort=confort;
		}
	}
}
