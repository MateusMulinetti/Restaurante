package objetos;

public class ItemCardapio {

    public String nome;
    public int codigo;
    public double preco;
    public boolean disponivel;
    public int quantidade;

    public ItemCardapio(String nome, int codigo, double preco, boolean disponivel, int quantidade) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.disponivel = disponivel;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\nNome: " + nome + "\nPreço: " + preco + "\nDisponibilidade: " + disponivel + "\n Quantidade: " + quantidade;
    }

}
