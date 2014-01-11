package membre;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import vehicules.Voiture;

public class Covoiturage {
	private List<Passager> membres = new ArrayList<Passager>();
	
	public void inscription (){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Pseudo :");
		String pseudo=sc.nextLine();
		System.out.println("Nom :");
		String nom= sc.nextLine();
		System.out.println("Prénom :");
		String prenom= sc.nextLine();
		System.out.println("Email :");
		String email= sc.nextLine();
		System.out.println("Telephone :");
		String telephone= sc.nextLine();
		
		sc.close();
		// Verifier la validité de l'email
		membres.add(new Passager(pseudo,nom,prenom,email,telephone));
		System.out.println("Vous voilà inscript sur le Jackdaw !");
	}

	public static void main(String[] args) {
		System.out.println("Hello");
		
		Voiture v1= new Voiture("C4","blanche",5);
		
		System.out.println(v1);

	}
}