package membre;

public class Passager {
	
	protected String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private Telephone telephone;
	
	
	public Passager (String pseudo,String nom, String prenom, String email,String telephone){
		this.pseudo=pseudo;
		this.nom=nom;
		this.prenom=prenom;
		this.email=email;
		this.telephone = new Telephone (telephone);
		
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


	public Telephone getTelephone() {
		return telephone;
	}


	public void setTelephone(Telephone telephone) {
		this.telephone = telephone;
	}
	
	public String toString (){
		return "Pseudo : " + pseudo + "\n Nom : "+ nom +"\n Prénom : "+ prenom +"\n Email : "+ email +"\n Téléphone : "+ telephone;
	}
	

}
