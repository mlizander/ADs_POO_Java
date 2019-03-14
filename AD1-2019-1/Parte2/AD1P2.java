class Midia {
	String titulo;
	int duracao;

	public Midia(String titulo, int duracao){
		this.titulo = titulo;
		this.duracao = duracao;
	}

	public String getTitulo(){
		return this.titulo;
	}

	public int getDuracao() {
		return this.duracao;
	}
}

class Video extends Midia {
	private String assunto;
	private String canal;
	private int visualizacoes;

	public Video(String titulo, String assunto, String canal, int visualizacoes, int duracao) {
		super(titulo, duracao);
		this.assunto = assunto;
		this.canal = canal;
		this.visualizacoes = visualizacoes;
	}

	public int getDuracao() {
		if (this.visualizacoes < 1000){
			return super.getDuracao();
		} else if (this.visualizacoes > 10000){
			return (int)(super.getDuracao() * 1.05);
		} else {
			return (int)(super.getDuracao() * 1.02);
		}
	}	
}

class Musica extends Midia {
	private String interprete;
	private String compositor;

	public Musica(String titulo, String interprete, String compositor, int duracao){
		super(titulo, duracao);
		this.interprete = interprete;
		this.compositor = compositor;
	}

	public String getInterprete(){
		return this.interprete;
	}

	public String getCompositor(){
		return this.compositor;
	}
}

class Playlist{
	private Midia[] midias;
	private int maxMidias;
	private int indArray;

	public Playlist(){
		this.maxMidias = 20;
		this.midias = new Midia[this.maxMidias];
		this.indArray =0;
	}

	public Playlist(int maxMidias){
		this.maxMidias = maxMidias;
		this.midias = new Midia[this.maxMidias];
		this.indArray =0;
	}
    
	public boolean addMidia(Midia m){
		if (this.indArray < this.maxMidias){
			this.midias[indArray] = m;
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
				minutos += this.midias[i].getDuracao();
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

public class AD1P2 {
	 public static void main(String[] args) {
	 	Musica m1 = new Musica("Agua de beber","Astrud Gilberto","Antonio Carlos Jobim", 140);
	 	Musica m2 = new Musica("O mar serenou","Clara Nunes","Candeia", 179);
	 	Musica m3 = new Musica("Rapaz Folgado","Martinho da Vila e Mart'nália","Noel Rosa", 180);
	 	Video v1 = new Video("Despacito", "Musica", "Luis Fonsi", 5926796, 280);
	 	Video v2 = new Video("Gangnam Style", "Musica", "Officialpsyi", 3276192, 252);

	 	Playlist p = new Playlist();
	 	p.addMidia(m1);
	 	p.addMidia(m2);
	 	p.addMidia(m3);
	 	p.addMidia(v1);
	 	p.addMidia(v2);

	 	System.out.println(p);

	 }
}