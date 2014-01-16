package membreSave;

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
			// Le numero de telephone n'est pas de la bonne taille;
			System.out.println("Numero de t??l??phone incorrect");
			numero="incorrect";
			correct = true;
		}
	}
	
	public void telDeBonneTaille (String tel) throws NotTelException {
		if (numero.length()!=10) throw new NotTelException();
	}

}
