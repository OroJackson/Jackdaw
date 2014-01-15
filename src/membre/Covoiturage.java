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

		devCreationTrajetACChauffeur("12:13:2014","test1","test1",1700,tmp);
		devCreationTrajetACChauffeur("12:13:2014","test2","test3",1700,tmp);
		devCreationTrajetACChauffeur("12:13:2014","test3","test4",1700,tmp);
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
		System.out.print("Identifiant : ");
		String pseudo= sc.nextLine();
		System.out.print("Mot de passe : ");
		String mdp = sc.nextLine();
		System.out.println();

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
		System.out.println("--------------------------");
		System.out.println("Bienvenue sur le Jackdaw.\n");
		System.out.println("1. Connexion");
		System.out.println("2. S'inscrire");
		System.out.println("\n3.Quitter.\n");
		System.out.print("Votre choix : ");
		
		int reponse = sc.nextInt();
		sc.nextLine();
		System.out.println();
		System.out.println("--------------------------");

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
		System.out.println("Heure de depart du trajet ?(HHMM)");
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
			connecte.addTrajet(courant);
		}
	}
	public void devCreationTrajetACChauffeur(String d,String villeD,String villeA,int heure,Passager p) throws ParseException{
		Trajet t =new Trajet(d,villeD,villeA,heure,p);
		trajets.add(t);
		p.addTrajet(t);
	}
	public void devCreationTrajetSSChauffeur(String d,String villeD,String villeA,int heure) throws ParseException{
		Trajet t =new Trajet(d,villeD,villeA,heure);
		trajets.add(t);
	}
	public void chercherTrajet(){
		System.out.print("Entrez la ville de départ : ");
		String villeD = sc.nextLine();
		System.out.println();
		
		List<Trajet> trajetsRecherche =chercherTrajetAvecVilleDepart(villeD);
		if (trajetsRecherche.size()==0){
			System.out.println("Aucun trajet ne correspond à votre demande.");
		} else {
			for (int i=0; i<trajetsRecherche.size(); i++){
				System.out.println((i+1) +" "+trajetsRecherche);
			}
			menuTrajetRecherche(trajetsRecherche);
		}
	}
	public void menuTrajetRecherche(List<Trajet> trajetsRecherche){
		System.out.println("1. S'inscrire à un trajet.");
		System.out.println("2. Retour.");
		System.out.print("Votre choix : ");
		int reponse = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		if (reponse==1){
			System.out.println("(Taper 0 pour quitter)");
			System.out.println("Numéro du trajet : ");
			System.out.print("Votre choix : ");
			reponse = sc.nextInt();
			sc.nextLine();
			System.out.println();
			while (reponse<0 || reponse >trajetsRecherche.size()){
				System.out.println("Ce trajet n'existe pas.");
				System.out.print("Votre choix : ");
				reponse = sc.nextInt();
				sc.nextLine();
				System.out.println();
			}
			if (reponse!=0){
				boolean conducteur=false;
				Trajet courant= trajetsRecherche.get(reponse-1);
				if (courant.estUnParticipant(connecte) || courant.estLeConducteur(connecte)){
					System.out.println("Vous etes déjà inscrit à ce trajet.");
				} else if (courant.estPlein()==1){
					System.out.println("Le trajet est plein.");
				} else {
					if(!courant.aUnConducteur() && connecte.estUnConducteur()){
						System.out.println("Voulez-vous vous incrire en tant que conducteur ?(o/n)");
						String choix =sc.nextLine();
						if (choix.equals("o")){
							conducteur=true;
						} 
					} 
					if (conducteur){
						courant.addConducteur(connecte);
						connecte.addTrajet(courant);
					} else {
						courant.addParticipant(connecte);
						connecte.addTrajet(courant);
					}
					System.out.println("Vous étes inscrit à ce trajet.");
				}
			}
		}
	}
	public List<Trajet> chercherTrajetAvecVilleDepart(String villeD){
		List<Trajet> trajetsRecherche = new ArrayList<Trajet>();
		for (int i=0; i<trajets.size(); i++){
			if (trajets.get(i).getVilleDepart().equals(villeD)){
				trajetsRecherche.add(trajets.get(i));
			}
		}
		return trajetsRecherche;
	}
	
	public void monProfil(){
		System.out.println(connecte);
		System.out.println("\nSouhaitez vous :");
		System.out.println("1. Voir vos voitures.");
		System.out.println("2. Ajouter une voiture");
		System.out.println("3. Supprimer une voiture");
		System.out.println("4. Modifier votre mot de passe");
		System.out.println("\nVotre choix : ");
		int rep=sc.nextInt();
		System.out.println();
		
		sc.nextLine();
		switch(rep){
		case 1:
			connecte.afficherVoitures();
			break;
		case 2:
			ajoutVoiture(connecte);
			break;
		case 3:
			supprimerVoiture();
			break;
		case 4:
			changeMdp();
			break;
		}
	}
	private void changeMdp() {
		System.out.print("Quelle est votre mot de passe actuel: ");
		String rep=sc.nextLine();
		if(rep.equals(connecte.getMdp())){
			System.out.print("\nEntrez un nouveau mot de passe: ");
			rep=sc.nextLine();
			System.out.println();
			connecte.setMdp(rep);
		}
	}

	private void supprimerVoiture() {
		// TODO Auto-generated method stub
		
	}
	public void menuTrajet(){
		System.out.println("--------------------------");
		System.out.println("1. Annuler un trajet. (Conducteur)");
		System.out.println("2. Se desinscrire. (Passager)\n");
		System.out.println("3. Retour.\n");
		
		System.out.print("Votre choix : ");
		
		int reponse = sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		switch(reponse){
		case 1 :
			annulerTrajet();
			break;
		case 2 : 
			desinscriptionTrajet();
			break;
		case 3 :
			break;
		}
	}
	public void annulerTrajet(){
		System.out.println("Entrez le numéro du Trajet que vous voulez supprimer.");
		System.out.println("Pour annuler taper 0");
		System.out.println("(ATTENTION Tout ceux qui était inscrit a votre Trajet recevront un message de suppression)\n");
		
		System.out.print("Votre choix : ");
		int reponse= sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		if (reponse>0){
			while (reponse>connecte.nbDeTrajets() && reponse>0){
				System.out.println("Ce trajet n'existe pas.");
				System.out.print("Votre choix : ");
				reponse= sc.nextInt();
				sc.nextLine();
				System.out.println();
			}
			if (reponse>0){
				if (connecte.trajetA(reponse-1).getChauffeur().getPseudo().equals(connecte.getPseudo())){
					envoyerMessageSuppression(connecte.trajetA(reponse-1));
					connecte.supprimerTrajet(connecte.trajetA(reponse-1));
					System.out.println("Le trajet "+ reponse+" a été supprimé.");
				} else {
					System.out.println("Vous etes passager. \nUtiliser l'option 'Se desinscrire'.");
				}
				
			}	
		}
	}
	public void envoyerMessageSuppression(Trajet t){
		List<Passager> inscrit = t.getInscrit();
		for (int i=0; i<inscrit.size(); i++){
			inscrit.get(i).ajouterMessage(t.toStringNotif());
		}
	}
	public void desinscriptionTrajet(){
		System.out.println("Entrez le numéro du Trajet dont vous voulez vous desinscrire.");
		System.out.println("Pour annuler taper 0");
		
		System.out.print("Votre choix : ");
		int reponse= sc.nextInt();
		sc.nextLine();
		System.out.println();
		
		if (reponse>0){
			while (reponse>connecte.nbDeTrajets() && reponse>0){
				System.out.println("Ce trajet n'existe pas.");
				System.out.print("Votre choix : ");
				reponse= sc.nextInt();
				sc.nextLine();
				System.out.println();
			}
			if (reponse>0){
				if (!connecte.trajetA(reponse-1).getChauffeur().getPseudo().equals(connecte.getPseudo())){
					connecte.trajetA(reponse-1).enleverParticipant(connecte);
				} else {
					System.out.println("Vous etes le conducteur. \nPour supprimer utiliser l'option 'Annuler Trajet'.");
				}
			}	
		}
	}
	
	public void menuPrincipal() throws ParseException{
		System.out.println("--------------------------");
		System.out.println("1. Creer un trajet.");
		System.out.println("2. Chercher un trajet.");
		System.out.println("3. Mes trajets.");
		System.out.println("4. Mon profil.");
		System.out.println("5. Déconnexion.");
		System.out.print("\nVotre choix : ");
		int rep=sc.nextInt();
		System.out.println();
		System.out.println("--------------------------");

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
			menuTrajet();
			break;
		case 4:
			monProfil();
			break;
		case 5:
			System.out.println("Aurevoir "+connecte.getPrenom());
			connecte=null;
			break;
		}
		
	}
}