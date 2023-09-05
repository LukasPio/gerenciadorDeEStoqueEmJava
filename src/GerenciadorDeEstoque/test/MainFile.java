package GerenciadorDeEstoque.test;
import java.util.Scanner;
import GerenciadorDeEstoque.dominio.*;

public class MainFile {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Computador computador1 = new Computador("dell 5 inspiron", 5000, "Um belo notebook para te introduzir ao mundo da tecnologia");
        Computador computador2 = new Computador("Ryzen 5", 3400, "Um notebook básico, para tarefas simples");

        Jogo jogo1 = new Jogo("God of war 2018", 300, "Um jogo que mudou o rumo da franqui God of War");
        Jogo jogo2 = new Jogo("Elden Ring", 200, "Um dos melhores jogos no estilo souls já feito");

        Servico servico1 = new Servico("DEV front-end", 100, "Um serviço freelancer no qual o dev resolve um problema seu");
        Servico servico2 = new Servico("Cozinheiro de luxo", 740, "Contrato semanal para ter um cozinheiro particular na sua casa");
        Servico servico3 = new Servico("Cozinheiro de luxo", 740, "Contrato semanal para ter um cozinheiro particular na sua casa");


        Estoque estoque = new Estoque(computador1, "Estoque Atacadão");

        estoque.adicionarNovoProdutoNoEstoque(computador2);
        estoque.adicionarNovoProdutoNoEstoque(jogo1);
        estoque.adicionarNovoProdutoNoEstoque(jogo2);
        estoque.adicionarNovoProdutoNoEstoque(servico1);
        estoque.adicionarNovoProdutoNoEstoque(servico2);



        int option;

        do {

            option = scanner.nextInt();
            System.out.println("Escolha uma das opções a seguir: \n\n" +
                    "1- Incrementar um produto ao estoque" +
                    "2- Decrementar um produto no estoque" +
                    "3- Ver a lista de produtos pela classe" +
                    "4- Ver a lista de produtos ordenados pelo preço" +
                    "5- Consultar para saber se um produto está no estoque" +
                    "6- Imprimir um relatório geral do estoque" +
                    "7- Adicionar um produto no estoque" +
                    "8- Remover um produto no estoque" +
                    "9- Fechar aplicação");

            switch (option) {
                case 1:
                    estoque.incremetarAoEstoque();
                case 2:
                    estoque.decrementarProduto();
                case 3:
                    String nomeDaClasse = scanner.next();
                    System.out.println("Digite o nome da classe que busca");
                    estoque.buscarProdutoPelaClasse(nomeDaClasse);
                case 4:
                    String ordem = scanner.next();
                    System.out.println("Deseja ordenar em ordem rescente ou decrescente?");
                    estoque.ordenarEImprimirProdutos(ordem);
                case 5:
                    String nomeDoProduto = scanner.next();
                    System.out.println("Digite o nome do produto que procura");
                    estoque.consultarProduto(nomeDoProduto);
                case 6:
                    estoque.relatorioTotalDoEstoque();
                case 7:

            }

        } while (option != 9);

    }
}