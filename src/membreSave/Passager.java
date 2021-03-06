package membreSave;

import java.util.ArrayList;
import java.util.List;

public class Passager implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String pseudo;
	protected String nom;
	protected String prenom;
	protected String email;
	protected String telephone;
	protected String mdp;
	protected String message="";
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	private List<Trajet> mesTrajets= new ArrayList<Trajet>();
	private List<Voiture> voitures = new ArrayList<Voiture>();
	
	public List<Voiture> getVoitures() {
		return voitures;
	}
	protected Passager(){}
	
	public Passager (String pseudo,String nom, String prenom, String email,String telephone,String mdp){
		this.pseudo=pseudo;
		this.nom=nom;
		this.prenom=prenom;
		this.email=email;
		this.telephone = telephone;
		this.mdp=mdp;
	}
	public int nbDeTrajets(){
		return mesTrajets.size();
	}
	
	public Trajet trajetA(int index){
		return mesTrajets.get(index);
	}
	
	public String afficherMessage(){
		String mess=message;
		viderMessage();
		return mess;
	}
	public void viderMessage(){
		message="";
	}
	public int nbVoitures(){
		return voitures.size();
	}
	public void addTrajet(Trajet t){
		boolean ajouter=false;
		int i=0;
		while(i<mesTrajets.size()){

			if (t.getDateTrajet().compareTo(mesTrajets.get(i).getDateTrajet())<0){
				mesTrajets.add(i,t);
				ajouter=true;
				i+=mesTrajets.size();
			}
			i++;
		}
		if(!ajouter){
			mesTrajets.add(t);
		}
	}
	public void ajouterMessage(String m){
		message=message+m+"\n";
	}
	
	public void supprimerTrajet(Trajet t){
		mesTrajets.remove(t);
	}
	
	public boolean equals(Passager p){
		return pseudo.equals(p.pseudo);
	}

	public boolean estUnConducteur(){
		return voitures.isEmpty();
	}
	
	public void ajouterVoiture(Voiture v){
		voitures.add(0,v);
	}
	
	public void supprimerVoiture (Voiture v){
		for (int i=0; i<voitures.size(); i++){
			if (voitures.get(i).equals(v)){
				voitures.remove(i);
			}
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
		return "  Pseudo : " + pseudo + "\n  Nom : "+ nom +"\n  Pr??nom : "+ prenom +"\n  Email : "+ email +"\n  T??l??phone : "+ telephone;
	}
	
	public String afficherVoitures(){
		if (voitures.size()==0){
			return "Vous n'avez aucune voiture";
		} else {
			String affichage ="";
			for (int i=0; i<voitures.size();i++){
				affichage=affichage+""+(i+1)+" "+voitures.get(i).toString();
			}
			return affichage;
		}
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp=mdp;
 	}

	public Voiture getVoiture() {
		if (voitures.size()!=0){
		return voitures.get(0);
		}else{
			return null;
		}
	}

	public void setVoiturePrincipale(Voiture voiturePrincipale) {
		supprimerVoiture(voiturePrincipale);
		voitures.add(0,voiturePrincipale);
	}
	
	public void afficherMesTrajets(){
		if (mesTrajets.size()==0) {
			System.out.println("Vous n'avez aucun trajet.\n");
		}
		for (int i=0; i<mesTrajets.size(); i++){
			System.out.print((i+1)+"  "+mesTrajets.get(i).toString());	
			if (mesTrajets.get(i).estPlein()==-1){
				System.out.print("   (Trajet en attente d'un conducteur)");
			}else{
				if (mesTrajets.get(i).getChauffeur().equals(this)){
					System.out.print("   (Vous etes le conducteur) ");
				}
			}
			if (mesTrajets.get(i).estPlein()==1){
				System.out.print(" (Trajet Plein) ");
			}
			System.out.println("\n");
		}
	}

	public String getPseudo() {
		return pseudo;
	}
	

}
