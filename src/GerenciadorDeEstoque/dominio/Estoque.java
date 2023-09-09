package GerenciadorDeEstoque.dominio;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Estoque {
    private Produto[] produtos;

    public void setNomeDoEstoque(String nomeDoEstoque) {
        this.nomeDoEstoque = nomeDoEstoque;
    }

    private String nomeDoEstoque;

    public Estoque(Produto produto, String nomeDoEstoque) {
        this.produtos = new Produto[]{produto};
        this.nomeDoEstoque = nomeDoEstoque;
    }

    public Estoque(Produto produto) {
        this.produtos = new Produto[]{produto};
    }

    public static void iniciarAplicacao() {
        Scanner scanner = new Scanner(System.in);

        Computador computador1 = new Computador("dell 5 inspiron", 5000, "Um belo notebook para te introduzir ao mundo da tecnologia");
        Computador computador2 = new Computador("Ryzen 5", 3400, "Um notebook básico, para tarefas simples");

        Jogo jogo1 = new Jogo("God of war 2018", 300, "Um jogo que mudou o rumo da franqui God of War");
        Jogo jogo2 = new Jogo("Elden Ring", 200, "Um dos melhores jogos no estilo souls já feito");

        Servico servico1 = new Servico("DEV front-end", 100, "Um serviço freelancer no qual o dev resolve um problema seu");
        Servico servico2 = new Servico("Cozinheiro de luxo", 740, "Contrato semanal para ter um cozinheiro particular na sua casa");

        Estoque estoque = new Estoque(computador1, "Estoque Atacadão");

        estoque.adicionarNovoProdutoNoEstoque(computador2);
        estoque.adicionarNovoProdutoNoEstoque(jogo1);
        estoque.adicionarNovoProdutoNoEstoque(jogo2);
        estoque.adicionarNovoProdutoNoEstoque(servico1);
        estoque.adicionarNovoProdutoNoEstoque(servico2);

        int option;

        do {

            System.out.println("Escolha uma das opções a seguir: \n\n" +
                    "1- Incrementar um produto ao estoque\n" +
                    "2- Decrementar um produto no estoque\n" +
                    "3- Ver a lista de produtos pela classe\n" +
                    "4- Ver a lista de produtos ordenados pelo preço\n" +
                    "5- Consultar para saber se um produto está no estoque\n" +
                    "6- Imprimir um relatório geral do estoque\n" +
                    "7- Adicionar um produto no estoque\n" +
                    "8- Remover um produto no estoque\n" +
                    "9- Fechar aplicação");

            option = scanner.nextInt();

            switch (option) {
                case 1:
                    estoque.incremetarAoEstoque();
                    break;
                case 2:
                    estoque.decrementarProduto();
                    break;
                case 3:
                    System.out.println("Digite o nome da classe que busca");
                    String nomeDaClasse = scanner.nextLine();
                    estoque.buscarProdutoPelaClasse(nomeDaClasse);
                    break;
                case 4:
                    System.out.println("Deseja ordenar em ordem rescente ou decrescente?");
                    String ordem = scanner.nextLine();
                    estoque.ordenarEImprimirProdutos(ordem);
                    break;
                case 5:
                    System.out.println("Digite o nome do produto que procura");
                    String nomeDoProduto = scanner.nextLine();
                    estoque.consultarProduto(nomeDoProduto);
                    break;
                case 6:
                    estoque.relatorioTotalDoEstoque(estoque);
                    break;
                case 7:
                    estoque.adicionarNovoProdutoNoEstoque();
                    break;
                case 8:
                    System.out.println("Digite o id do produto a ser removido");
                    int idProduto = scanner.nextInt();
                    estoque.removerUmProdutoDoEstoque(idProduto);
                    break;
                case 9:
                    System.out.println("Fechando aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida!");

            }

        } while (option != 9);
    }

    public boolean verificarProdutoValido(Produto produto) {
        for (Produto umProduto : this.produtos) {
            if (umProduto == produto) {
                return true;
            }
        }
        return false;
    }

    public void consultarProduto(String nomeDoProduto) {
        for (Produto umProduto: this.produtos) {
            if (nomeDoProduto.equals(umProduto.getNome())) {
                System.out.println("Esse produto está em estoque! detalhes abaixo");
                System.out.println(umProduto);
            }
        }
        Produto umProduto = buscarPeloId();
        Scanner scanner = new Scanner(System.in);
    }

    public Produto buscarPeloId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a id do produto");
        int id = scanner.nextInt();
        Produto produto;

        for (Produto umProduto : this.produtos) {
            if (umProduto.getId() == id) {
                return umProduto;
            }
        }
        Computador min = new Computador("x", 0, "x");
        return min;
    }



    public void incremetarAoEstoque() {
        Produto produto = buscarPeloId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade a ser adicionada");
        int quantidadeParaAdicao = scanner.nextInt();

        if (verificarProdutoValido(produto)) {
            int novaQuantidade = produto.getQuantidadeEmEstoque() + quantidadeParaAdicao;
            produto.setQuantidadeEmEstoque(novaQuantidade);
            System.out.println("Foram adicionados " + quantidadeParaAdicao + " de " + produto.getNome() + " no estoque");
        } else {
            System.out.println("Esse produto não está incluso no estoque, primeiro adicione-o");
        }
    }

    public void decrementarProduto() {
        Produto produto = buscarPeloId();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a quantidade a ser removida");
        int quantidadeParaRemocao = scanner.nextInt();

        if (verificarProdutoValido(produto)) {
            int novaQuantidade = produto.getQuantidadeEmEstoque() - quantidadeParaRemocao;
            if (novaQuantidade >= 0) {
                produto.setQuantidadeEmEstoque(novaQuantidade);
                System.out.println("Foram removidos " + quantidadeParaRemocao + " de " + produto.getNome() + " no estoque");
            } else {
                System.out.println("Quantidade insuficiente em estoque para remover.");
            }
        } else {
            System.out.println("Esse produto não está incluso no estoque, primeiro adicione-o");
        }
    }

    public void adicionarNovoProdutoNoEstoque() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o tipo do produto:\n" +
                "Servico, " +
                "Jogo, " +
                "Computador");
        String tipoProduto = scanner.nextLine();
        System.out.println("Digite o nome do produto");
        String nomeProduto = scanner.nextLine();
        System.out.println("Digite a descricao do produto");
        String descricaoProduto = scanner.nextLine();
        System.out.println("Digite o preço do produto");
        double precoProduto = scanner.nextDouble();

        Produto produto;

        if (tipoProduto.equals("Servico") || tipoProduto.equals("servico")) {
            Servico servico = new Servico(nomeProduto, precoProduto, descricaoProduto);
            produto = servico;
        }
        else if (tipoProduto.equals("Computador") || tipoProduto.equals("computador")) {
            Computador computador = new Computador(nomeProduto, precoProduto, descricaoProduto);
            produto = computador;
        }
        else if (tipoProduto.equals("Jogo") || tipoProduto.equals("Jogo")) {
            Jogo jogo = new Jogo(nomeProduto, precoProduto, descricaoProduto);
            produto = jogo;
        }
        else {
            System.out.println("Tipo de protudo inválido!");
            produto = null;
        }

        boolean produtoValido = !verificarProdutoValido(produto);

        Produto[] produtosEmEstoqueAtualizado = new Produto[this.produtos.length + 1];


        if (produtoValido) {
            for (int i = 0; i < this.produtos.length; i++) {
                produtosEmEstoqueAtualizado[i] = this.produtos[i];
            }

            produtosEmEstoqueAtualizado[this.produtos.length] = produto;
            this.produtos = produtosEmEstoqueAtualizado;
        }
    }

    public void adicionarNovoProdutoNoEstoque(Produto produto) {
        boolean produtoValido = !verificarProdutoValido(produto);

        Produto[] produtosEmEstoqueAtualizado = new Produto[this.produtos.length + 1];

        if (produtoValido) {
            produto.setQuantidadeEmEstoque(1); // Configure a quantidade inicial para 1 ou o valor desejado.
            for (int i = 0; i < this.produtos.length; i++) {
                produtosEmEstoqueAtualizado[i] = this.produtos[i];
            }

            produtosEmEstoqueAtualizado[this.produtos.length] = produto;
            this.produtos = produtosEmEstoqueAtualizado;
        }
    }

    public void removerUmProdutoDoEstoque(Produto produto) {
        boolean produtoValido = verificarProdutoValido(produto);

        if (produtoValido) {
            Produto[] novoArray = new Produto[this.produtos.length - 1];
            int indexNovoArray = 0;

            for (int i = 0; i < this.produtos.length; i++) {
                if (this.produtos[i] != produto) {
                    novoArray[indexNovoArray] = this.produtos[i];
                    indexNovoArray++;
                }
            }

            this.produtos = novoArray;
            System.out.println("O produto foi removido");
            listarProdutos();
        } else {
            System.out.println("O produto não existe ou não está presente no estoque");
        }
    }

    public void removerUmProdutoDoEstoque(int idProduto) {

        boolean produtoValido = false;

        for (Produto produto: this.produtos) {
            if (produto.getId() == idProduto) {
                produtoValido = true;
            }
            if (produtoValido) {
                Produto[] novoArray = new Produto[this.produtos.length - 1];
                int indexNovoArray = 0;

                for (int i = 0; i < this.produtos.length; i++) {
                    if (this.produtos[i] != produto) {
                        novoArray[indexNovoArray] = this.produtos[i];
                        indexNovoArray++;
                    }
                }

                this.produtos = novoArray;
                System.out.println("O produto foi removido");
                listarProdutos();
            } else {
                System.out.println("O produto não existe ou não está presente no estoque");
            }
        }
    }

    public void listarProdutos() {
        System.out.println("Lista de produtos: ");
        for (Produto produto: this.produtos) {
            if (produto != null) {
                System.out.println(produto);
            }
        }
    }


    public void ordenarEImprimirProdutos(String ordem) {
        List<Produto> listaProdutos = Arrays.asList(this.produtos);

        if (ordem.equalsIgnoreCase("crescente")) {
            Collections.sort(listaProdutos, Comparator.comparing(Produto::getPreco));
            System.out.println("Lista de produtos ordenada de forma crescente:");

        } else if (ordem.equalsIgnoreCase("decrescente")) {
            Collections.sort(listaProdutos, Comparator.comparing(Produto::getPreco).reversed());
            System.out.println("Lista de produtos ordenada de forma decrescente:");

        } else {
            System.out.println("Opção de ordenação inválida.");
            return;
        }

        for (Produto produto : listaProdutos) {
            System.out.println(produto);
        }
    }


    public void relatorioTotalDoEstoque(Estoque estoque) {
        int produtosDiferentesNoEstoque = estoque.produtos.length;
        int quantidadeDeProdutosNoEstoque = 0;
        double valorTotalDoEstoque = 0;

        for (Produto produto : estoque.produtos) {
            if (produto != null) {
                quantidadeDeProdutosNoEstoque += produto.getQuantidadeEmEstoque();
                valorTotalDoEstoque += (produto.getPreco() * produto.getQuantidadeEmEstoque());
            }
        }

        System.out.println("Quantidade de produtos diferentes no estoque: " + produtosDiferentesNoEstoque);
        System.out.println("Quantidade total de produtos no estoque: " + quantidadeDeProdutosNoEstoque);
        System.out.println("Valor total de todos os produtos do estoque: " + valorTotalDoEstoque);
    }

    public void buscarProdutoPorNome(String nome) {
        for (Produto umProduto: this.produtos) {
            if (umProduto.getNome().equals(nome)) {
                System.out.println("O produto está contido no estoque: ");
                System.out.println(umProduto);
            }
        }
        System.out.println("O produto não está incluso no estoque");
    }

    public void buscarProdutoPelaClasse(String nomeDaClasse) {

        int tamanhoDoArrayDeProdutosFiltrados = 0;
        int auxiliar = 0;
        Produto[] listaDeProdutosFiltrados = new Produto[0];
        Produto[] novaListaFiltrada;

        for (Produto produto: this.produtos) {
            String nomeDaClasseDoObjeto = produto.getClass().getSimpleName();

            if (nomeDaClasse.equals(nomeDaClasseDoObjeto)) {
                tamanhoDoArrayDeProdutosFiltrados++;
                novaListaFiltrada = listaDeProdutosFiltrados;
                listaDeProdutosFiltrados = new Produto[tamanhoDoArrayDeProdutosFiltrados];
                for(int i = 0; i < novaListaFiltrada.length; i++) {
                    listaDeProdutosFiltrados[i] = novaListaFiltrada[i];
                }
                listaDeProdutosFiltrados[auxiliar] = produto;
                auxiliar++;
            }
        }
        if (listaDeProdutosFiltrados.length > 0) {
            System.out.println("Lista dos produtos tipo: " + nomeDaClasse + ", abaixo");
            for (Produto produto: listaDeProdutosFiltrados) {
                System.out.println(produto);
            }
        }
        else {
            System.out.println("Esse tipo de produto não existe ou não está contido no estoque");
        }
    }
    public Produto[] getProduto() {
        return produtos;
    }

    public String getNomeDoEstoque() {
        return nomeDoEstoque;
    }
}
