package membre;

import vehicules.Voiture;
import java.util.ArrayList;
import java.util.List;

public class Conducteur extends Passager {
	private List<Voiture> vehicules;
	
	public Conducteur(String pseudo, String nom, String prenom, String email,
			String telephone) {
		super(pseudo, nom, prenom, email, telephone);
		this.vehicules = new ArrayList<Voiture>();
	}
	
<<<<<<< HEAD
	public void ajouterVoiture(Voiture aAjouter){
		if (!possede(aAjouter)) vehicules.add(aAjouter);
	}
	
	public boolean possede(Voiture v){
		return vehicules.contains(v);
	}
	
	public boolean aUneVoiture(){
		return vehicules.size()!=0;
	}
	
=======
>>>>>>> 78ea46db84045bff1b2dd790e0667ff0ffbcdc2b
	public String toString(){
		if (vehicules.size()==0) return "Aucune voiture associée";
		
		String affichage="";
		for (int i=0; i<vehicules.size(); i++){
<<<<<<< HEAD
			affichage = affichage + vehicules.get(i).toString();
=======
			affichage = affichage + vehicules.get(i);
>>>>>>> 78ea46db84045bff1b2dd790e0667ff0ffbcdc2b
		}
		return affichage;
	}
	
}
