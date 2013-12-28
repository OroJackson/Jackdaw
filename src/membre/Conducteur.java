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
	
	public String toString(){
		if (vehicules.size()==0) return "Aucune voiture associée";
		
		String affichage="";
		for (int i=0; i<vehicules.size(); i++){
			affichage = affichage + vehicules.get(i).toString();
		}
		return affichage;
	}
	
}
