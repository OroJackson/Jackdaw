package membre;

public class Telephone {
	protected String numero;
	protected boolean correct;
	
	public Telephone (){
		correct = false;
	}
	
	public Telephone (String n){
		try {
			telDeBonneTaille(n);
		}
		catch (NotTelException e){
			// Le telephone n'est pas de la bonne taille;
			System.out.println("Numero de téléphone incorrect");
			numero="incorrect";
			corretc = true;
		}
	}
	
	public void telDeBonneTaille (String tel) throws NotTelException {
		if (telephone.length()!=10) throw new NotTelException();
	}

}
