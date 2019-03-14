class Musica {
	private String titulo;	
	private String interprete;
	private String compositor;
	private int duracao;

	public Musica(String titulo, String interprete, String compositor, int duracao){
		this.titulo = titulo;
		this.interprete = interprete;
		this.compositor = compositor;
		this.duracao = duracao;		
	}

	public String getTitulo(){
		return this.titulo;
	}

	public String getInterprete(){
		return this.interprete;
	}

	public String getCompositor(){
		return this.compositor;
	}

	public int getDuracao() {
		return this.duracao;
	}		
}

class Playlist{
	private Musica[] musicas;
	private int maxMusicas;
	private int indArray;

	public Playlist(){
		this.maxMusicas = 20;
		this.musicas = new Musica[this.maxMusicas];
		this.indArray =0;
	}

	public Playlist(int maxMusicas){
		this.maxMusicas = maxMusicas;
		this.musicas = new Musica[this.maxMusicas];
		this.indArray =0;
	}
    
	public boolean addMidia(Musica m){
		if (this.indArray < this.maxMusicas){
			this.musicas[indArray] = m;
			this.indArray++;
			return true;
		} else {
			return false;
		}
	}

	public int getDuracaoLista() {
		int minutos = 0;
		if (this.indArray > 0){
			for (int i = 0; i < this.indArray; i++ ) {
				minutos += this.musicas[i].getDuracao();
			}
		}
		return minutos;
	}

	public String toString(){
		int horas = 0;
		int minutos = 0;
		int duracaoLista = getDuracaoLista();
		if (duracaoLista == 0){
			return "Playlist Vazia";
		} else {
			horas = duracaoLista/60;
			minutos = duracaoLista % 60;
			if (horas == 0){
				return minutos + " min";
			} else if (minutos == 0){
				return horas + " h";
			} else {
				return horas + " h e " + minutos + " min";
			}
		}
	}
}

public class AD1P1 {
	 public static void main(String[] args) {
	 	Musica m1 = new Musica("Agua de beber","Astrud Gilberto","Antonio Carlos Jobim", 140);
	 	Musica m2 = new Musica("O mar serenou","Clara Nunes","Candeia", 179);
	 	Musica m3 = new Musica("Rapaz Folgado","Martinho da Vila e Mart'nália","Noel Rosa", 180);

	 	Playlist p = new Playlist();
	 	p.addMidia(m1);
	 	p.addMidia(m2);
	 	p.addMidia(m3);

	 	System.out.println(p);

	 }
}