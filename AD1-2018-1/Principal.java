//AD1 DE PROGRAMAÇÃO ORIENTADA A OBJETOS
//ALUNO: MARCUS LIZANDER DOS SANTOS LUGÃO - MATR. 15213050325
//PÓLO DE NOVA IGUAÇU
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

class Midia {
	String titulo;
	//Esse atributo foi considerado como pertencente também à classe Ebook, uma vez
	//que o mesmo é usado no enunciado da letra d, na instanciação dos objetos.
	//Vide tópico aberto por mim na sala de tutoria.
	String url;
	LocalDate dataPublicacao;
	String autor;
	public Midia(String titulo, String url, LocalDate dataPublicacao, String autor){
		this.titulo = titulo;
		this.url = url;
		this.autor = autor;
		//Tratamento para impedir data futura.
		//Caso se tente instanciar uma data posterior à de hoje, é considerada a data de hoje
		//Evitando cálculos de tempo de publicação negativos
		if (dataPublicacao.isAfter(LocalDate.now())){
			this.dataPublicacao = LocalDate.now();
		} else {
			this.dataPublicacao = dataPublicacao;	
		}
		
	}
}

class Video extends Midia {
	int duracao;
	String descricao;
	int curtidas;
	public Video(String titulo, String url, LocalDate dataPublicacao, String autor, String descricao, int duracao, int curtidas){
		super(titulo, url, dataPublicacao, autor);
		this.descricao = descricao;
		this.duracao = duracao;
		this.curtidas = curtidas;
	}
	public String getTempoPublicado(){
		String ret = "";
		LocalDate hoje = LocalDate.now();
		Period periodo = Period.between(this.dataPublicacao, hoje);
		int anos = periodo.getYears();
		int meses = periodo.getMonths();
		int dias = periodo.getDays();
		//Verifica a quantidade de anos
		if (anos > 0) {
			ret += anos + (anos > 1 ? " anos" : " ano");
			if (meses > 0 && dias > 0) {
				ret += ", ";
			}
			else {
				if ((meses == 0) ^ (dias == 0)) {
					ret += " e ";
				}
			}
		}
		//De meses
		if (meses > 0) {
			ret += meses + (meses > 1 ? " meses" : " mês");
			if (dias > 0){
				ret += " e ";
			}
		}
		//De dias
		if (dias > 0) {
			ret += dias + (dias > 1 ? " dias" : " dia");
		}
		//Se os três forem zerados, a string ret vai estar ainda vazia
		if (ret == ""){
			ret = "Publicado hoje";
		}
		return ret;
	}
	public String toString() {
        return "(Vídeo) - Autor: " + this.autor + " - Titulo: " + this.titulo + " - Tempo de publicação: " + this.getTempoPublicado();
	}
}

class Ebook extends Midia {
	int paginas;
	public Ebook(String titulo, String url, LocalDate dataPublicacao, String autor, int paginas){
		super(titulo, url, dataPublicacao, autor);
		this.paginas = paginas;
	}
	public String toString() {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "(Ebook) - Autor: " + this.autor + " - Titulo: " + this.titulo + " - Data de Publicação: " + this.dataPublicacao.format(formato);
	}}

public class Principal {
	public static void main(String[] args){
		Video v1 = new Video("Por quê ainda estudar Java?",
			                 "https://www.youtube.com/watch?v=bdpSqjTZJcg",
			                  LocalDate.of(2017, 8, 22),
			                  "Carlos Bazilio","Razões para ainda se estudar a linguagem Java",
			                  998, 11);

		Ebook e1 = new Ebook("Programando na Cozinha",
		                     "https://carlosbazilio.gitbooks.io/programando-nacozinha/content/pt-br/",
		                      LocalDate.of(2017, 9, 23), "Carlos Bazilio", 57);

		Ebook e2 = new Ebook("Eloquent JavaScript",
		                     "http://eloquentjavascript.net/3rd_edition/",
		                      LocalDate.of(2017, 3, 6), "Marijn Haverbeke", 472);

		//Criando o vetor de Midia
		Midia[] vetMidia = new Midia[3];
		vetMidia[0] = v1;
		vetMidia[1] = e1;
		vetMidia[2] = e2;
		//optei por usar esse for, e não o específico para percorrer arrays pois o mesmo não foi dado ainda em aula.
		for (int i = 0; i < 3; i++){
 		    System.out.println(vetMidia[i].toString());
		}
	}
}