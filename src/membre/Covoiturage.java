package membre;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Covoiturage {
	private List<Passager> membres = new ArrayList<Passager>();
	Passager connecte;
	
	private Scanner sc= new Scanner (System.in);
	public void inscription (){
		
		System.out.print("Pseudo :");
		String pseudo=sc.nextLine();
		// VERIFIER UNICITÉ	
		System.out.print("Mot de Passe :");
		String mdp=sc.nextLine();
		System.out.print("Nom :");
		String nom= sc.nextLine();
		System.out.print("Prénom :");
		String prenom= sc.nextLine();
		System.out.print("Email :");
		String email= sc.nextLine();
		System.out.print("Telephone :");
		String telephone= sc.nextLine();
		
		Passager p=new Passager(pseudo,nom,prenom,email,telephone,mdp);
		membres.add(p);
	
		// Verifier la validité de l'email
		// Verifier téléphone
		System.out.print("\nPossedez vous une voiture ? (o/n)");
		String reponse=  sc.nextLine();
		
		
		while (reponse.equals("o") && reponse.equals("n")){
			System.out.print("\nVeuillez répondre par 'o' ou 'n'.");
			sc.nextLine();
			reponse= sc.nextLine();
		}
		if (reponse.equals("o")){
			ajoutVoiture();
		} 
		
		System.out.println("\nVous voilà inscript sur le Jackdaw !");	
	}
	
	//True si connexion reussi false sinon
	public boolean connexion(){
		System.out.println("Identifiant :");
		String pseudo= sc.nextLine();
		System.out.println("Mot de passe :");
		String mdp = sc.nextLine();
		

		for (int i=0;i<membres.size();i++){
			if(membres.get(i).pseudo.equals(pseudo) && membres.get(i).getMdp().equals(mdp)){
				connecte=membres.get(i);
				return true;
			}
		}
		return false;
	}
	public void boucleConnexion(){
		boolean situation= false;
		while (!situation){
			situation = connexion();
			if (!situation){
				System.out.println("Identifiants ou Mdp incorrect");
				System.out.println("1. Reéssayer\n2. Retour menu");
				if (sc.nextInt()==2){
					situation=true;
				}
				sc.nextLine();
			}
		}
	}
	public void deconnexion(){
		connecte=null;
	}
	public int menuConnexion(){
		
		System.out.println("Bienvenue sur le Jackdaw.\n");
		System.out.println("1. Connexion");
		System.out.println("2. S'inscrire\n");
		System.out.println("Votre choix :");
		
		int reponse = sc.nextInt();
		sc.nextLine();

		return reponse;
	}
	
	public void ajoutVoiture(){
		
		System.out.println("Modéle de la voiture :");
		String modele= sc.nextLine();
		System.out.println("Couleur de la voiture :");
		String couleur= sc.nextLine();
		System.out.println("Confort de la voiture (sur 5):");
		int confort= sc.nextInt();
		while (confort<0 || confort>5){
			sc.nextLine();
			System.out.println("Le confort se note sur 5");
			confort= sc.nextInt();
			sc.nextLine();
		}

		System.out.println("Nombre de place de la voiture:(hors conducteur)");
		int nbPlaces=sc.nextInt();
		while (nbPlaces<1 || nbPlaces>8 ){
			sc.nextLine();
			System.out.println("Le nombres de place est compris entre 1 et 8");
			nbPlaces= sc.nextInt();
			sc.nextLine();
		}
		connecte.ajouterVoiture(new Voiture(modele,couleur,confort,nbPlaces));
	}
	public void creationTrajet(){
		System.out.println("Date du Trajet?");
	}
	public void chercherTrajet(){
		
	}
	public void mesTrajets(){
		
	}
	public void monProfil(){
		
	}
	public void menuPrincipal(){
		System.out.println("1. Creer un trajet.");
		System.out.println("2. Chercher un trajet.");
		System.out.println("3. Mes trajets.");
		System.out.println("4. Mon profil.");
		
		switch(sc.nextInt()){
		case 1:
			creationTrajet();
			break;
		case 2:
			chercherTrajet();
			break;
		case 3:
			mesTrajets();
			break;
		case 4:
			monProfil();
			break;
		}
		sc.nextLine();
	}
/*
	public static void main(String[] args) {
		System.out.println("Hello");
		
		Voiture v1= new Voiture("C4","blanche",5,5);
		
		System.out.println(v1);
		

	}*/
	
}