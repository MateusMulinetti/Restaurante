package objetos;

public class Funcionario {

    public String nome;
    public int id;
    public String cargo;
    public double totalVendas;
    public int totalPedidos;

    public Funcionario(String nome, int id, String cargo) {
        this.nome = nome;
        this.id = id;
        this.cargo = cargo;
        this.totalVendas = 0;
        this.totalPedidos = 0;
    }

    public void registrarVenda(double valor) {
        if (this.cargo.equals("Garçom")) {
            this.totalVendas += valor;
            this.totalPedidos++;
        }
    }

    @Override
    public String toString() {
        return "Nome: " + nome + "\nId: " + id + "\nCargo: " + cargo + "\nTotal de vendas: " + totalVendas;
    }

}
