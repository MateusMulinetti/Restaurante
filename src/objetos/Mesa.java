package objetos;

public class Mesa {

    public int numero;
    public int capacidade;
    public boolean disponivel;
    public double valorVendido;

    public Mesa(int numero, int capacidade, boolean disponivel) {
        this.numero = numero;
        this.capacidade = capacidade;
        this.disponivel = disponivel;
        this.valorVendido = 0;
    }

    @Override
    public String toString() {
        return "Número: " + numero + "\nCapacidade: " + capacidade + "\nDisponibilidade: " + disponivel + "\nValor Vendido: " + valorVendido + "\n";
    }

}
