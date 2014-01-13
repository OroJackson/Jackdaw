package membre;

public class Voiture {
	private String modele;
	private String couleur;
	private int nbPlaces;
	private int confort;
	
	public Voiture (String modele, String couleur, int confort, int nbPlaces){
		this.modele=modele;
		this.couleur=couleur;
		this.nbPlaces=nbPlaces;
		if (confort < 0){
			this.confort=0;
		} else if (confort > 5){
			this.confort=5;
		} else {
			this.confort=confort;
		}
	}
	public boolean equals(Voiture v){
		return (modele.equals(v.modele) && couleur.equals(v.couleur) && confort==v.confort);
	}
	
	
	public String toString(){
		String affichage = " Modele : " + modele +"\n Couleur : "+ couleur+ "\n Confort : ";
		for (int i=0; i<confort; i++){
			affichage =affichage+"* ";
		}
		return affichage;
	}
	
	public int getPlace(){
		return nbPlaces;
	}
}

