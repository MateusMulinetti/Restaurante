import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

import objetos.Funcionario;
import objetos.Mesa;
import objetos.ItemCardapio;
import objetos.Pedido;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static List<Funcionario> funcionarios = new ArrayList<>();
    public static List<ItemCardapio> itens = new ArrayList<>();
    public static List<Mesa> mesas = new ArrayList<>();
    public static List<Pedido> pedidos = new ArrayList<>();

    public static int contadorIdFuncionario = 1;
    public static int contadorCodigoCardapio = 1;
    public static int contadorNumeroMesa = 1;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        System.out.println("\n--- Menu ---\n"
                + "1. Cadastrar funcionário\n"
                + "2. Cadastrar prato/Bebida no cardápio\n"
                + "3. Cadastrar mesa\n"
                + "4. Registrar pedido\n"
                + "5. Acompanhar vendas\n"
                + "6. Fechar contas e faturar\n"
                + "7. Relatório de faturamento\n"
                + "8. Sair\n"
                + "------------\n"
        );
        executar();
    }

    public static void executar() {
        System.out.print("Digite a opção desejada: ");
        Scanner scanner = new Scanner(System.in);

        int entrada = scanner.nextInt();
        scanner.nextLine();

        switch (entrada) {
            case 1: {
                cadastrarFuncionario();
                menu();
                break;
            }
            case 2: {
                cadastrarItemCardapio();
                menu();
                break;
            }
            case 3: {
                cadastrarMesa();
                menu();
                break;
            }
            case 4: {
                registrarPedido();
                menu();
                break;
            }
            case 5: {
                acompanharVendas();
                menu();
                break;
            }
            case 6: {
                fechamento();
                menu();
                break;
            }
            case 7: {
                relatorioFaturamento();
                menu();
                break;
            }
            case 8: {
                System.out.println("Saindo...");
                break;
            }
            
            default:
                System.out.println("Opção inválida!\n");
                menu();
                break;
        }

    }

    public static void cadastrarFuncionario() {
        System.out.println("--- Cadastro de Funcionário ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        String cargo = "";

        while (cargo.equals("")) {
            System.out.print("Cargo (1 - Garçom, 2 - Cozinheiro, 3 - Gerente): ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cargo = "Garçom";
                    break;
                case 2:
                    cargo = "Cozinheiro";
                    break;
                case 3:
                    cargo = "Gerente";
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida (1, 2, ou 3).");
                    break;
            }
        }

        int id = contadorIdFuncionario;
        contadorIdFuncionario++;

        Funcionario funcionario = new Funcionario(nome, id, cargo);
        funcionarios.add(funcionario);

        System.out.println("Funcionário cadastrado com sucesso!");
    }

    public static void cadastrarItemCardapio() {
        System.out.println("--- Cadastrar Item ---");

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        boolean disponivel;

        if (quantidade > 0) {
            disponivel = true;
        } else {
            disponivel = false;
        }

        int codigo = contadorCodigoCardapio;
        contadorCodigoCardapio++;

        ItemCardapio itemCardapio = new ItemCardapio(nome, codigo, preco, disponivel, quantidade);
        itens.add(itemCardapio);

        System.out.println("Item cadastrado com sucesso!");

    }

    public static void cadastrarMesa() {
        System.out.println("--- Cadastrar Mesa ---");

        System.out.print("Digite a capacidade da mesa " + contadorNumeroMesa +": ");
        int capacidade = scanner.nextInt();

        int numero = contadorNumeroMesa;
        contadorNumeroMesa++;

        Mesa mesa = new Mesa(numero, capacidade, true);
        mesas.add(mesa);

        System.out.println("Mesa cadastrada com sucesso!");

    }

    public static void registrarPedido() {
        System.out.println("\n--- Registro de Pedido ---");

        listarGarcons();
        Funcionario garcom = null;
        while (garcom == null) {
            System.out.print("Digite o ID do garçom responsável: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            for (Funcionario funcionario : funcionarios) {
                if (funcionario.id == id && funcionario.cargo.equals("Garçom")) {
                    garcom = funcionario;
                    break;
                }
            }
            if (garcom == null) {
                System.out.println("Garçom inválido! Por favor, tente novamente.");
            }
        }

        listarMesas();
        Mesa mesa = null;
        while (mesa == null) {
            System.out.print("Digite o número da mesa: ");
            int numero = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite a quantidade de pessoas que utilizarão: ");
            int quantidadePessoas = scanner.nextInt();
            scanner.nextLine();

            for (Mesa m : mesas) {
                if (m.numero == numero && m.disponivel && m.capacidade >= quantidadePessoas) {
                    mesa = m;
                    m.disponivel = false;
                    break;
                }
            }
            if (mesa == null) {
                System.out.println("Mesa inválida, ocupada ou sem capacidade! Por favor, tente novamente.");
            }
        }

        cardapio();
        List<ItemCardapio> itensPedido = new ArrayList<>();
        double valorTotal = 0;
        while (true) {
            System.out.print("Digite o código do item do cardápio (ou 0 para finalizar): ");
            int codigoItem = scanner.nextInt();
            scanner.nextLine();

            if (codigoItem == 0) {
                break;
            }

            System.out.print("Digite a quantidade desejada: ");
            int quantidade = scanner.nextInt();
            scanner.nextLine();

            ItemCardapio itemEscolhido = null;
            for (ItemCardapio item : itens) {
                if (item.codigo == codigoItem && item.disponivel && item.quantidade >= quantidade) {
                    itemEscolhido = item;
                    break;
                }
            }

            if (itemEscolhido != null) {
                itensPedido.add(itemEscolhido);
                itemEscolhido.quantidade -= quantidade;
                valorTotal += itemEscolhido.preco * quantidade;
                if(itemEscolhido.quantidade == 0) {
                    itemEscolhido.disponivel = false;
                }
                System.out.println("\nItem adicionado: " + itemEscolhido.nome + "\n");
            } else {
                System.out.println("\nItem inválido, não disponível ou sem estoque!");
            }
        }

        if (!itensPedido.isEmpty()) {
            Pedido novoPedido = new Pedido(pedidos.size() + 1, garcom, mesa, itensPedido, valorTotal);
            pedidos.add(novoPedido);

            System.out.println("\nPedido registrado com sucesso!");
            System.out.println(novoPedido);

        } else {
            System.out.println("\nNenhum item foi adicionado. Pedido não registrado.");
        }
    }

    public static void acompanharVendas() {
        System.out.println("\n--- Relatório de Vendas ---");
        for (Funcionario garcom : funcionarios) {
            if(garcom.cargo.equals("Garçom")) {
                System.out.println("\nNome: " + garcom.nome);
                System.out.println("Total de pedidos realizados: " + garcom.totalPedidos);
                System.out.println("Valor total vendido: " + garcom.totalVendas);
            }
        }
    }

    public static void fechamento() {
        System.out.println("\n--- Fechamento de Conta ---");

        System.out.println("Mesas ocupadas:");
        boolean mesaOcupadaEncontrada = false;
        for (Mesa mesa : mesas) {
            if (!mesa.disponivel) {
                System.out.println("\nNúmero da mesa: " + mesa.numero);
                mesaOcupadaEncontrada = true;
            }
        }

        if (!mesaOcupadaEncontrada) {
            System.out.println("Não há mesas ocupadas no momento.\n");
            return;
        }

        System.out.print("Digite o número da mesa para fechar a conta: ");
        int numeroMesa = scanner.nextInt();
        scanner.nextLine();

        Mesa mesaSelecionada = null;
        for (Mesa mesa : mesas) {
            if (mesa.numero == numeroMesa && !mesa.disponivel) {
                mesaSelecionada = mesa;
                break;
            }
        }

        if (mesaSelecionada == null) {
            System.out.println("Mesa inválida ou já disponível. Tente novamente.\n");
            return;
        }

        Pedido pedidoParaFechar = null;
        for (Pedido pedido : pedidos) {
            if (pedido.mesa.numero == numeroMesa) {
                pedidoParaFechar = pedido;
                break;
            }
        }

        if (pedidoParaFechar == null) {
            System.out.println("Nenhum pedido encontrado para essa mesa. Tente novamente.\n");
            return;
        }

        for (Funcionario garcom : funcionarios) {
            if (garcom.id == pedidoParaFechar.garcom.id) {
                garcom.registrarVenda(pedidoParaFechar.valorTotal);
                break;
            }
        }

        System.out.println("Valor total do pedido: R$ " + pedidoParaFechar.valorTotal);
        System.out.print("Deseja confirmar o pagamento? (1 - Sim, 2 - Não): ");
        int confirmacao = scanner.nextInt();
        scanner.nextLine();

        if (confirmacao == 1) {
            mesaSelecionada.valorVendido += pedidoParaFechar.valorTotal;

            mesaSelecionada.disponivel = true;
            pedidoParaFechar.aberto = false;

            System.out.println("Pagamento registrado. Mesa " + numeroMesa + " liberada com sucesso!\n");
        } else {
            System.out.println("Pagamento não confirmado. A mesa permanecerá ocupada.\n");
        }
    }

    public static void relatorioFaturamento() {
        System.out.println("\n--- Relatório de Faturamento do Restaurante ---");

        double totalVendasDia = 0;

        System.out.println("\nTotal de vendas por mesa:");
        for (Mesa mesa : mesas) {
            System.out.println("Mesa " + mesa.numero + ": R$ " + mesa.valorVendido);
            totalVendasDia += mesa.valorVendido;
        }

        System.out.println("\nTotal de vendas por funcionário:");
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.cargo.equals("Garçom")) {
                System.out.println("Garçom: " + funcionario.nome + " - Total vendido: R$ " + funcionario.totalVendas);
            }
        }

        System.out.println("\nTotal de vendas no dia: R$ " + totalVendasDia);
        System.out.println("----------------------------------------------\n");
    }

    public static void listarGarcons() {
        System.out.println("\n--- Lista de Garçons ---");
        for (Funcionario garcom : funcionarios) {
            if(garcom.cargo.equals("Garçom")) {
                System.out.println("ID: " + garcom.id + ", Nome: " + garcom.nome);
            }
        }
        System.out.println("------------------------\n");
    }

    public static void cardapio() {
        System.out.println("\n--- Cardápio ---");
        for (ItemCardapio item : itens) {
            if(item.disponivel) {
                System.out.println("Código: " + item.codigo + ", Nome: " + item.nome + ", Preço: " + item.preco);
            }
        }
        System.out.println("----------------\n");
    }

    public static void listarMesas() {
        System.out.println("\n--- Mesas ---");
        for (Mesa mesa : mesas) {
            if(mesa.disponivel) {
                System.out.println("Número: " + mesa.numero + ", Capacidade: " + mesa.capacidade);
            }
        }
        System.out.println("----------------\n");
    }

}