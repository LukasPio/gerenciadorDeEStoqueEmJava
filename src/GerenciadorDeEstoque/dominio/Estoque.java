package GerenciadorDeEstoque.dominio;

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
        int id = scanner.nextInt();
        System.out.println("Digite a id do produto");
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
        int quantidadeParaAdicao = scanner.nextInt();
        System.out.println("Digite a quantidade a ser adicionada");

        if (verificarProdutoValido(produto)) {
            produto.quantidadeEmEstoque += quantidadeParaAdicao;
            System.out.println("Foram adicionados " + quantidadeParaAdicao + " de " + produto.nome + " no estoque");
        }
        else {
            System.out.println("Esse produto não está incluso no estoque, primeiro adicione-o");
        }
    }

    public void decrementarProduto() {

        Produto produto = buscarPeloId();
        Scanner scanner = new Scanner(System.in);
        int quantidadeParaRemocao = scanner.nextInt();
        System.out.println("Digite a quantidade a ser removida");

        if (verificarProdutoValido(produto)) {
            produto.quantidadeEmEstoque -= quantidadeParaRemocao;
            System.out.println("Foram adicionados " + quantidadeParaRemocao + " de " + produto.nome + " no estoque");
        } else {
            System.out.println("Esse produto não está incluso no estoque, primeiro adicione-o");
        }
    }

    public void adicionarNovoProdutoNoEstoque(Produto produto) {

        boolean produtoValido = !verificarProdutoValido(produto);

        Produto[] produtosEmEstoqueAtualizado = new Produto[this.produtos.length + 1];


        if (produtoValido) {
            for (int i = 0; i < this.produtos.length; i++) {
                produtosEmEstoqueAtualizado[i] = this.produtos[i];
            }

            produtosEmEstoqueAtualizado[this.produtos.length] = produto;
            this.produtos = produtosEmEstoqueAtualizado;
        }
        else {
            System.out.println("Já existe um produto desse ou esse não é um produto válido");
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


    public void relatorioTotalDoEstoque () {
        int quantidadeDeProdutosNoEstoque = 0;
        int produtosDiferentesNoEstoque = this.produtos.length;
        double valorTotalDoEstoque = 0;

        for (Produto produto: this.produtos) {

                if (produto != null) {
                    quantidadeDeProdutosNoEstoque += produto.quantidadeEmEstoque;
                }

        }

        for (Produto produto: this.produtos) {
            if (produto != null) {
                valorTotalDoEstoque += (produto.getPreco() * produto.getQuantidadeEmEstoque());
            }
        }

        System.out.println("Quantidade de produtos diferentes no estoque: " + produtosDiferentesNoEstoque);
        System.out.println("Quantidade total de produtos no esotoque: " + quantidadeDeProdutosNoEstoque);
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
