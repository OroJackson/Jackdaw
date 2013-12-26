package membre;

import java.util.ArrayList;
import java.util.List;

public class Conducteur extends Passager {
	private List<Voiture> vehicules;
	
	public Conducteur(String pseudo, String nom, String prenom, String email,
			String telephone) {
		super(pseudo, nom, prenom, email, telephone);
		this.vehicules = new ArrayList<Voiture>();
	}
	
	
}
