package membre;

import java.io.NotActiveException;

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
		// Je vérifie que le téléphone est bien un entier , de 10 chiffres.
		try { 
			
			int i = Integer.parseInt(telephone); 
		} 
		catch (Exception e) { 
			System.out.println("Je ne suis pas un entier, et alors ca te derange ?"); 
		}
		catch (NotTelException e)
		{
			
		}
		
	}

}
