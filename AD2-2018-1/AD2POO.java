
import java.io.*;

//Classe que implementa um nó da lista, com os campos de nome e se a pessoa está viva ou não
class no{
	private String nome;
	private boolean vivo;
	no prox;
	no(String nome){
		this.nome = nome;
		this.vivo = true;
	}
	void morre() {
		this.vivo = false;
	}
	void vive() {
		this.vivo = true;
	}

	boolean getVivo(){
		return this.vivo;
	}
	public String toString(){
		return this.nome;
	}
}

//Classe que implementa a lista encadeada circular
class lista{
	private no primeiro;
	private no ultimo;
	private int qtdLista;
	private int qtdVivos;
	
	lista(){ 
		this.primeiro = null;
		this.ultimo = null;
		this.qtdLista = 0;
		this.qtdVivos = 0;
	}

	//Método que insere um nome no final da lista, mantendo uma referência para o primeiro e para o último nome da lista
	void insere(String nome){
		no pessoa = new no(nome);
		pessoa.prox = null;
		if (this.qtdLista == 0){
			this.primeiro = pessoa;
			this.ultimo = pessoa;
		} else {
			this.ultimo.prox = pessoa;
			this.ultimo = pessoa;
		}
		this.qtdLista++;
		this.qtdVivos++;
	}

	//Método que faz um item da lista "morrer"
	void suicidar(no n){
		n.morre();
		this.qtdVivos--;
	}

	//Método que "reseta" os status de vivo de cada item da lista
	void reviverTodos(){
		no aux = this.primeiro;
		while (aux != null){
			aux.vive();
			aux = aux.prox;
		}
		this.qtdVivos = this.qtdLista;
	}

	//Método que retorna o nome do primeiro sobrevivente encontrado.
	//Será chamado apenas quando quantidade de vivos = 1
	String getSobrevivente(){
		no aux = primeiro;
		while (aux != null){
			if (aux.getVivo()) {
				return aux.toString();
			}
			aux = aux.prox;
		}
		return null;
	}

	//Método que trata a lista como circular, onde o próximo nó do último é o primeiro
	no proximo_no(no n){
		if (n == this.ultimo) {
			return this.primeiro;
		} else {
			return n.prox;
		}
	}

	//Método que roda o problema de Josephus para um dado k
	void probJosephus(int k) {
		no aux = this.ultimo;
		while (this.qtdVivos > 1) {
			int cont = 0;
			while (cont < k) {
				aux = this.proximo_no(aux);
				if (aux.getVivo()){
					cont++;
				}
			}
			this.suicidar(aux);
		}
	}
}


public class AD2POO{
	public static void main(String[] args) throws IOException {
		//Pega o nome do arquivo dado como parâmetro e gera o nome do arquivo de saída
		String arqSaida;
		String sep = File.separator;
        File f = new File (args[0]);  
		String dir = f.getParent();
		if (dir != null){
	 		arqSaida = dir + sep + "saida-" + f.getName();
		} else {
			arqSaida =  "saida-" + f.getName();
		}
		//Abre os arquivos para leitura e para escrita
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		BufferedWriter out = new BufferedWriter(new FileWriter(arqSaida));
		String linha;
		lista li = new lista();
		try{
			//Inicia a Leitura dos nomes
			linha = in.readLine();
			while ((! linha.equals("FIM")) && (linha != null)) {
				//Insere o nome lido na lista
				li.insere(linha);
				linha = in.readLine();
			}
			//Lê a linha que contém a quantidade de Ks no arquivo
			linha = in.readLine();
			if (linha != null) {
				int qtdK = Integer.parseInt(linha);
				int cont = 0;
				//Para cada um dos Ks...
				while (cont < qtdK) {
					//Lê o valor do K...
					linha = in.readLine();
					if (linha != null) {
						int k = Integer.parseInt(linha);
						if (k > 0) {
							//Roda o método para o k atual
							li.probJosephus(k);
							//Grava o valor de K e do sobrevivente no arquivo de saída
							out.write(k + " " + li.getSobrevivente()+"\r\n");
							//Revive todos para um novo valor de K, se necessário for
			                li.reviverTodos();
		            	}
	            	}
					cont++;
				}
			}
		}catch (Exception e){
			System.out.println("Exceção leitura ou escrita...\n");
		}finally{
			in.close();
			out.close();
		}
	}
}






