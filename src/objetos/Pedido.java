package objetos;

import java.util.List;

public class Pedido {

    public int id;
    public Funcionario garcom;
    public Mesa mesa;
    public List<ItemCardapio> itens;
    public double valorTotal;
    public boolean aberto;

    public Pedido(int id, Funcionario garcom, Mesa mesa, List<ItemCardapio> itens, double valorTotal) {
        this.id = id;
        this.garcom = garcom;
        this.mesa = mesa;
        this.itens = itens;
        this.valorTotal = valorTotal;
        this.aberto = true;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nGarçom: " + garcom.nome + "\nMesa: " + mesa.numero + "\nValor Total: R$ " + valorTotal;
    }

}
