package GerenciadorDeEstoque.dominio;

import java.util.Random;

public class Computador extends Produto{
    private int id;
    public Computador(String nome, double preco, String descricao) {
        super(nome, preco, descricao);
        Random random = new Random();
        this.id = random.nextInt();
    }

    @Override
    public String toString() {
        return "Computador{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\'' +
                ", quantidadeEmEstoque=" + quantidadeEmEstoque +
                '}';
    }


}
