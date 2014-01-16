package membre;
/**
 * Class Voiture, representant les voitures des conducteurs, pour de futur covoiturage
 * @author AurelieDigeon
 *@version 1.0
 */
public class Voiture {
	private String modele;
	private String couleur;
	private int nbPlaces;
	private int confort;
	/**
	 * Constructeur unique permettant la cr��ation d'une voiture
	 * 
	 * @param modele modele de la voiture
	 * @param couleur Couleur de la voiture 
	 * @param confort Confort estim�� par le proprietaire de la voiture
	 * @param nbPlaces Nombre de place hors conducteur de la voiture
	 */
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
	/**
	 * methode permettant de test�� l'��galit�� de deux voitures.
	 * @param v voiture avec laquel on veut test�� l'��galit��.
	 * @return un booleen vrai si les deux voitures sont en fait la m��me, faux sinon.
	 */
	public boolean equals(Voiture v){
		return (modele.equals(v.modele) && couleur.equals(v.couleur) && confort==v.confort && nbPlaces==v.nbPlaces);
	}
	
	/**
	 * methode toString permettant l'affichage d'une voiture
	 */
	public String toString(){
		String affichage = " Modele : " + modele +"\n Couleur : "+ couleur+ "\n Confort : ";
		for (int i=0; i<confort; i++){
			affichage =affichage+"* ";
		}
		return affichage;
	}
	/**
	 * methode permettant de savoir combien de place contien une voiture
	 * @return un int nbPlaces representant le nombre de place de la voiture hors conducteur
	 */
	public int getPlace(){
		return nbPlaces;
	}
	public String toStringCourt(){
		return modele + " "+ couleur+" (confort: "+confort+"/5)\n";
	}
}

