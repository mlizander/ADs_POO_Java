// Implementação simples de grafo representado por lista de adjacências

import java.util.List;
import java.util.ArrayList;
import java.io.*;

class Vertice {
    String nome;
    int idade;
    List<Aresta> adjac;

    Vertice(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
        this.adjac = new ArrayList<Aresta>();
    }

    void adicionaAdjac(Aresta a) {
        adjac.add(a);
    }

    //Método criado para auxiliar na questão 2...
    public String toString() {
        String s = "";
        s += this.nome + " " + Integer.toString(this.idade) + " -> ";
        for (Aresta a : this.adjac) {
            Vertice ve = a.destino;
            s += ve.nome + " " + Integer.toString(ve.idade) + ", ";
        }
        return s;
    }

}

class Aresta {
    Vertice origem;
    Vertice destino;

    Aresta(Vertice origem, Vertice destino) {
        this.origem = origem;
        this.destino = destino;
    }
}

class TGrafo {

    List<Vertice> vertices;
    List<Aresta> arestas;

    public TGrafo() {
        vertices = new ArrayList<Vertice>();
        arestas = new ArrayList<Aresta>();
    }

    Vertice adicionaVertice(String nome, int idade) {
        Vertice v = new Vertice(nome, idade);
        vertices.add(v);
        return v;
    }

    Aresta adicionaAresta(Vertice origem, Vertice destino) {
        Aresta a = new Aresta(origem, destino);
        origem.adicionaAdjac(a);
        arestas.add(a);
        return a;
    }

    // método apenas para mostrar o grafo e conferir se está OK
    public String toString() {
        String s = "";
        for (Vertice v : vertices) {
            s += v.toString();
            s += "\n";
        }
        return s;
    }

    public Vertice buscaVertice(String nome){
        for (Vertice v : this.vertices) {
            if (v.nome.equals(nome)) {
                return v;             
            }
        }        
        return null;
    }

}

public class AD2 {
    //QUESTÃO 1 - Quantas pessoas uma determinada pessoa segue?
    //Alterei o nome do método porque ele não retorna o número de seguidores, e sim quantos usuários o nome segue
    public static int numero_seguidos (TGrafo g, String nome){
        int num = 0;
        for (Vertice v : g.vertices) {
            if (v.nome.equals(nome)) {
                num = v.adjac.size();             
            }
        }
        return num;
    }

    //QUESTÃO 2 - Quem são os seguidores de uma determinada pessoa? (função imprime os nomes dos seguidores):
    public static void seguidores (TGrafo g, String nome){
        String s = "";
        for (Vertice v : g.vertices) {
            if (!(v.nome.equals(nome))) {
                for (Aresta a : v.adjac) {
                    Vertice vd = a.destino;
                    Vertice vo = a.origem;
                    if (vd.nome.equals(nome)){
                        s += vo.nome + ", ";
                    }
                }              
            }
        }
        System.out.println(s); 
    }

    //QUESTÃO 3 - Quem é a pessoa mais popular? (tem mais seguidores)
    //NÃO FOI TRATADO CRITÉRIO DE DESEMPATE ENTRE PESSOAS IGUALMENTE POPULARES, POIS NÃO HÁ CRITÉRIO EXPRESSO NA AD
    //Método Auxiliar que retorna o número de pessoas que seguem um determinado nome
    public static int numero_seguidores (TGrafo g, String nome){
        int num = 0;
        for (Vertice v : g.vertices) {
            if (!v.nome.equals(nome)) {
                for (Aresta a : v.adjac) {
                    Vertice ve = a.destino;
                    if (ve.nome.equals(nome)){
                        num++;
                    }
                }   
            }
        }
        return num;
    }

    public static String mais_popular (TGrafo g){
        String pop = "";
        int num_seguidores=-1;
        for (Vertice v : g.vertices) {
            int num = numero_seguidores(g, v.nome);
            if (num > num_seguidores) {
                num_seguidores = num;
                pop = v.nome;
            } else if (num == num_seguidores) {
                pop += "/"+ v.nome;
            }
        }
        return "O(s) mais popular(es) é(são) " + pop + " com " + Integer.toString(num_seguidores) + " seguidor(es).";
    }


    //QUESTÃO 4 - Quais são as pessoas que só seguem pessoas mais velhas do que ela própria? (função imprime os nomes das pessoas): 
    //Método Auxiliar que retorna TRUE se a pessoa só segue pessoas mais velhas que ela
    public static boolean so_segue_mais_velho (Vertice v){
        boolean resp = false;
        if (v.adjac.size() > 0) {
            resp = true;
            for (Aresta a : v.adjac) {
                Vertice ve = a.destino;
                if (ve.idade <= v.idade){
                    resp = false;
                }
            }               
        }
        return resp;
    }

    public static void segue_mais_velho(TGrafo g){
        String s = "Pessoas que só seguem mais velhos:";
        s+= "\n";
        for (Vertice v : g.vertices) {
            if (so_segue_mais_velho(v)) {
                s += v.nome + "\n";
            }
        }
        System.out.println(s);
    }


    public static void main(String[] args)  throws IOException  {
        
        //Abre o arquivo para leitura dos dados
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String linha;
        TGrafo g = new TGrafo();
        try{
            //Inicia a Leitura do arquivo
            linha = in.readLine();
            while (linha != null) {
                //transforma a linha em um vetor de 2 posições
                String vlinha[] = new String[2];
                vlinha = linha.split(" ");
                // verifica se a linha era de inserção de vértice ou de aresta
                if (vlinha[1].substring(0,1).matches("[0-9]*")) {
                    
                    int idade = Integer.parseInt(vlinha[1]);
                    //É um número, logo, vai inserir o vértice:
                    Vertice v = g.adicionaVertice(vlinha[0], idade);
                } else {
                    //Não é um número, logo é um par de vértices formando uma aresta
                    Vertice v1 = g.buscaVertice(vlinha[0]);
                    Vertice v2 = g.buscaVertice(vlinha[1]);
                    if ((v1 != null) && (v2 != null)) {
                        Aresta a = g.adicionaAresta(v1, v2);
                    }
                }
                linha = in.readLine();
            }
        }catch (Exception e){
            System.out.println("Exceção leitura do arquivo...\n");
        }finally{
            in.close();
        }

        System.out.println("Resultados:");
        //Essa impressão é apenas para conferir com o esquema da AD
        System.out.println("Grafo do Arquivo:");
        System.out.println(g);
        System.out.println("");
        System.out.println("Q1 - Número de Seguidos");
        for (Vertice v : g.vertices) {
            int num = numero_seguidos(g, v.nome);
            System.out.println("Número de Seguidos por "+ v.nome + ": " + Integer.toString(num));
        }
        System.out.println("");
        System.out.println("Q2 - Seguidores");
        for (Vertice v : g.vertices) {
            System.out.println("Seguidores de "+ v.nome + ": ");
            seguidores (g, v.nome);            
        }
        System.out.println("");
        System.out.println("Q3 - O Mais popular");
        System.out.println(mais_popular(g));

        System.out.println("");
        System.out.println("Q4 - Pessoas que só seguem mais velhos:");
        segue_mais_velho(g); 
    }   
}