abstract class Item {
	String nome;
	
	public Item(String n) {
		this.nome = n;
	}
	
	public abstract double obterTam();
	
}

class Arquivo extends Item{
	private String extensao;
	private double tamanho;
	
	public Arquivo(String nome, String e, double t) {
		super(nome);
		this.extensao = e;
		this.tamanho = t;
	}
	
	public String toString() {
		return this.nome + "." + this.extensao + ", tamanho: " + this.tamanho + " bytes";
	}
	
	//M�todo que retorna o tamanho do arquivo
	public double obterTam() {
		return this.tamanho;
	}
	

	//Getters
	public String getNome() {
		return this.nome;
	}
	
	public String getExtensao() {
		return this.extensao;
	}
	
	public double getTamanho() {
		return this.tamanho;
	}
	
	//Setters
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
	
	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}
}

class Diretorio extends Item {
	public Arquivo[] arquivos;
	public Diretorio[] diretorios;
	
	//Campos da classe: seu nome, sua lista de arquivos e sua lista de diret�rios
	public Diretorio(String nome, Arquivo[] arqs, Diretorio[] dirs) {
		super(nome);
		this.arquivos = arqs;
		this.diretorios = dirs;
	}
	
	public String toString() {
		String s = "";
        s+="Diret�rio: " + this.nome + "\n";
        s+="Arquivos: \n" ;
        if (this.arquivos != null) {
	        for(int i=0;i<this.arquivos.length;i++){
	            s+= this.arquivos[i].toString() + "\n";
	        }        	
        }

        s+="Subdiret�rios: \n" ;
        if (this.diretorios != null){
	        for(int i=0;i<this.diretorios.length;i++){
	            s+= this.diretorios[i].nome + " - "+ this.obterTam(this.diretorios[i]) + " bytes" + "\n";
	        }        	
        }

    	return s;
	}
	
	//M�todo que retorna o tamanho do diret�rio de forma recursiva. 
	public double obterTam() {
		return this.obterTam(this);
	}

	public double obterTam(Diretorio dir) {
		double somaArq = 0;
		// Soma o tamanho dos arquivos que h� no diret�rio.
		if (dir.arquivos!= null){
			for(int i=0; i < dir.arquivos.length; i++) {
				somaArq += dir.arquivos[i].obterTam();
			}			
		}

		// Soma o tamanho de cada diret�rio.
		if (dir.diretorios != null){
			for(int j=0; j < dir.diretorios.length; j++) {
				if (dir.diretorios[j] != null) {
					somaArq += obterTam(dir.diretorios[j]);
				}
			}			
		}

		return somaArq;
	}
		
	//Getters e setters
	public String getNome() {
		return this.nome;
	}
	
	

}

// C�digo para realizar os testes na estrutura pedida na AD.
public class AD1 {
	
	public static void main(String [] args) {
		
		//Criando os arquivos do subdiret�rio "APs"
		Arquivo[] arqDir1 = new Arquivo[2];
		arqDir1[0] = new Arquivo("Ap1-gabarito", "pdf", 12);
		arqDir1[1] = new Arquivo("Ap1", "odt", 7);
		
		//Criando os arquivos do subdiret�rio "ADs"
		Arquivo[] arqDir2 = new Arquivo[2];
		arqDir2[0] = new Arquivo("Ad1-gabarito", "pdf", 10);
		arqDir2[1] = new Arquivo("Ad1", "odt", 5);
		
		//Criando os diret�rios. Lembrando que seu formato � nome + lista de arquivos + lista de diret�rios
		
		//Diret�rios ADs e Aps
		Diretorio[] adsAps = new Diretorio[2];
		adsAps[0] = new Diretorio("APs", arqDir1, null);
		adsAps[1] = new Diretorio("ADs", arqDir2, null);
		
		//Diret�rio POO
		Diretorio princ = new Diretorio("POO", null, adsAps);

		
		//Imprimindo respeitando a estrutura pedida
		System.out.println(princ);
		System.out.println("");
		System.out.println(adsAps[0]);
		System.out.println("");
		System.out.println(adsAps[1]);
		System.out.println("");
	}

}
