package membre;

import java.util.ArrayList;
import java.util.List;

public class Passager {
	
	protected String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String mdp;
	
	private List<Voiture> voitures = new ArrayList<Voiture>();
	
	public Passager (String pseudo,String nom, String prenom, String email,String telephone,String mdp){
		this.pseudo=pseudo;
		this.nom=nom;
		this.prenom=prenom;
		this.email=email;
		this.telephone = telephone;
		this.mdp=mdp;
		
	}

	public boolean estUnConducteur(){
		return voitures.isEmpty();
	}
	
	public void ajouterVoiture(Voiture v){
		voitures.add(0,v);
	}
	
	public void supprimerVoiture (Voiture v){
		for (int i=0; i<voitures.size(); i++){
			if (voitures.get(i).equals(v));
		}
	}

	
	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String toString (){
		return "Pseudo : " + pseudo + "\n Nom : "+ nom +"\n Prénom : "+ prenom +"\n Email : "+ email +"\n Téléphone : "+ telephone;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public Voiture getVoiture() {
		return voitures.get(0);
	}

	public void setVoiturePrincipale(Voiture voiturePrincipale) {
		supprimerVoiture(voiturePrincipale);
		voitures.add(0,voiturePrincipale);
	}
	

}
