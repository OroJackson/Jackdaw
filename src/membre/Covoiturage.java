package membre;

import java.text.*;
import java.util.*;

/**
 * Classe Covoiturage. Regroupe l'ensemble des fonctionnalité du covoiturage
 * 
 * @author Aurie
 * @version 1.0
 */
public class Covoiturage {
	private List<Trajet> trajets = new ArrayList<Trajet>();
	private List<Passager> membres = new ArrayList<Passager>();
	Passager connecte;
	
	public Covoiturage() throws ParseException{
		Passager tmp=new Passager("admin",null,null,null,null,"mdp");
		membres.add(tmp);
		tmp.ajouterVoiture(new Voiture("C3","Blanche",5,4));
		Trajet t =new Trajet("12:13:2014","test1","test1",1700,tmp);
		trajets.add(t);
		tmp.addTrajet(t);
		t=new Trajet("12:13:2014","test3","test3",1700,tmp);
		trajets.add(t);
		tmp.addTrajet(t);
		t=new Trajet("12:13:2014","test4","test4",1700,tmp);
		trajets.add(t);
		tmp.addTrajet(t);

	}
	
	private Scanner sc= new Scanner (System.in);
	/**
	 * Méthode réalisant l'inscription d'un membre dans le Covoiturage
	 */
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
		
		System.out.println("\nVous voilà inscript sur le Jackdaw !");
		System.out.println("Vous êtes passager, pour ajouter une voiture, allez dans votre profil");
		System.out.println("Bon Voyages "+ prenom +"!");
	}
	
	/**
	 * Méthode de connexion au Covoiturage.
	 * 
	 * @return false si la connexion c'est bien passé,True sinon.
	 */
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
	/**
	 * Méthode donnant le choix a l'utilisateur de réésayer la connexion ou de revenir au Menu
	 */
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
	/**
	 * Méthode réalisant la déconnexion d'un membre du Covoiturage.
	 */
	public void deconnexion(){
		connecte=null;
	}
	/**
	 * Menu proposant à l'utilisateur de se connecter ou de s'inscrire
	 * @return Le numéro de la répond : 1 pour connexion ou 2 pour inscription.
	 */
	public int menuConnexion(){
		
		System.out.println("Bienvenue sur le Jackdaw.\n");
		System.out.println("1. Connexion");
		System.out.println("2. S'inscrire\n");
		System.out.print("Votre choix :");
		
		int reponse = sc.nextInt();
		sc.nextLine();
		System.out.println();

		return reponse;
	}
	
	public void ajoutVoiture(Passager p){
		
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
		p.ajouterVoiture(new Voiture(modele,couleur,confort,nbPlaces));
	}
	public void creationTrajet() throws ParseException{
		System.out.println("Date du Trajet? (JJ:MM:AA)");
		String dateTrajet =sc.nextLine();
		System.out.println("Ville de depart?");
		String villeDepart=sc.nextLine();
		System.out.println("Ville d'arrivee?");
		String villeArrivee=sc.nextLine();
		System.out.println("Heure de depart du trajet ?(HHMM");
		int heureDepart=sc.nextInt();
		sc.nextLine();
		System.out.println("Etes vous le conducteur de ce trajet ?(o/n)");
		String rep=sc.nextLine();
		while(rep.equals("o") && rep.equals("n")){
			System.out.println("Veuillez repondre par 'o' ou 'n'");
			rep=sc.nextLine();
		}
		if(rep.equals("o")){
			Trajet courant=new Trajet(dateTrajet,villeDepart,villeArrivee,heureDepart,connecte);
			trajets.add(courant);
			connecte.addTrajet(courant);
		}else{
			Trajet courant =new Trajet(dateTrajet,villeDepart,villeArrivee,heureDepart);
			courant.addParticipant(connecte);
			trajets.add(courant);
			
		}
	}
	public void chercherTrajet(){
		
	}
	public void mesTrajets(){
		
	}
	public void monProfil(){
		
	}
	public void menuPrincipal() throws ParseException{
		System.out.println("1. Creer un trajet.");
		System.out.println("2. Chercher un trajet.");
		System.out.println("3. Mes trajets.");
		System.out.println("4. Mon profil.");
		System.out.println("5. Déconnexion.");
		int rep=sc.nextInt();
		sc.nextLine();
		switch(rep){
		case 1:
			creationTrajet();
			break;
		case 2:
			chercherTrajet();
			break;
		case 3:
			connecte.afficherMesTrajets();
			break;
		case 4:
			monProfil();
			break;
		case 5:
			System.out.println("Aurevoir "+connecte.getPrenom());
		}
		
	}
}