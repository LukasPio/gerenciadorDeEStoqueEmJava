package GerenciadorDeEstoque.dominio;

import java.util.Random;

public class Jogo extends Produto {
    private int id;

    @Override
    public String toString() {
        return "Jogo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                ", quantidadeEmEstoque=" + quantidadeEmEstoque +
                '}';
    }

    public Jogo(String nome, double preco, String descricao) {
        super(nome, preco, descricao);
        Random random = new Random();
        this.id = random.nextInt();
    }


}



