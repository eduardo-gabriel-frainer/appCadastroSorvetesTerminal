import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<Pedido> pedidos = new ArrayList<>();
    private static ArrayList<Pagamento> pagamentos = new ArrayList<>();

    public static void main(String[] args) {
        try {
            menu();
        } catch (IOException e) {
            System.err.println("Erro ao executar o sistema: " + e.getMessage());
        }
    }

    private static void menu() throws IOException {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("------------- MENU -------------");
            System.out.println("1 - Cadastrar Pedidos");
            System.out.println("2 - Listar Pedidos");
            System.out.println("3 - Alterar Pedidos");
            System.out.println("4 - Apagar Pedidos");
            System.out.println("5 - Deletar Pedidos (Verificação de Exclusão)");
            System.out.println("6 - Deletar Pagamentos");
            System.out.println("7 - Recuperar Pagamentos");
            System.out.println("8 - Recuperar Pedidos");
            System.out.println("9 - Sair");
            System.out.println("--------------------------------");
            System.out.print("Escolha uma opção: ");

            String opcao = input.nextLine();
            switch (opcao) {
                case "1":
                    cadastrarPedido();
                    break;
                case "2":
                    listarPedidos();
                    break;
                case "3":
                    alterarPedido();
                    break;
                case "4":
                    apagarPedido();
                    break;
                case "5":
                    deletarTodosPedidos();
                    break;
                case "6":
                    deletarTodosPagamentos();
                    break;
                case "7":
                    recuperarPagamentos();
                    break;
                case "8":
                    recuperarPedidos();
                    break;
                case "9":
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void cadastrarPedido() {
        Scanner input = new Scanner(System.in);

        System.out.print("Qual sabor de sorvete você deseja? ");
        String sabor = input.nextLine();

        System.out.print("Deseja adicionais? ");
        String adicionais = input.nextLine();

        System.out.print("Deseja cobertura? ");
        String cobertura = input.nextLine();

        System.out.print("Quer canudo ou colher? ");
        String item = input.nextLine();

        System.out.print("Forma de pagamento: ");
        String pagamento = input.nextLine();

        System.out.print("Vai ser à vista? ");
        String avista = input.nextLine();

        System.out.print("Deseja comer aqui? ");
        String local = input.nextLine();

        System.out.print("Dê uma nota pelo nosso atendimento (0 a 10): ");
        int atendimento;
        try {
            atendimento = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nota inválida! Atribuindo valor padrão: 0");
            atendimento = 0;
        }

        pedidos.add(new Pedido(sabor, adicionais, cobertura, item));
        pagamentos.add(new Pagamento(pagamento, avista, local, atendimento));

        System.out.println("Pedido cadastrado com sucesso!");
    }

    private static void listarPedidos() throws IOException {
        if (pedidos.isEmpty() && pagamentos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado.");
            return;
        }

        try (PrintWriter pedidoWriter = new PrintWriter(new FileWriter("registro_sorvete_pedido.txt", true));
                PrintWriter pagamentoWriter = new PrintWriter(new FileWriter("registro_sorvete_pagamento.txt", true))) {

            System.out.println("--- Lista de Pedidos ---");
            pedidoWriter.println("--- Lista de Pedidos ---");

            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
                pedidoWriter.println(pedido);
            }

            System.out.println("--- Lista de Pagamentos ---");
            pagamentoWriter.println("--- Lista de Pagamentos ---");

            for (Pagamento pagamento : pagamentos) {
                System.out.println(pagamento);
                pagamentoWriter.println(pagamento);
            }
        }
    }

    private static void alterarPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado para alterar.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Informe o sabor do pedido que deseja alterar: ");
        String saborAntigo = input.nextLine();

        for (Pedido pedido : pedidos) {
            if (pedido.getPedido().equalsIgnoreCase(saborAntigo)) {
                System.out.print("Informe o novo sabor: ");
                String novoSabor = input.nextLine();
                pedido.setPedido(novoSabor);
                System.out.println("Pedido alterado com sucesso!");
                return;
            }
        }
        System.out.println("Pedido não encontrado.");
    }

    private static void apagarPedido() {
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido cadastrado para apagar.");
            return;
        }

        Scanner input = new Scanner(System.in);
        System.out.print("Informe o sabor do pedido que deseja apagar: ");
        String sabor = input.nextLine();

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getPedido().equalsIgnoreCase(sabor)) {
                pedidos.remove(i);
                pagamentos.remove(i);
                System.out.println("Pedido apagado com sucesso!");
                return;
            }
        }
        System.out.println("Pedido não encontrado.");
    }

    private static void deletarTodosPedidos() {
        if (!pagamentos.isEmpty()) {
            System.out.println("Você não pode deletar os pedidos antes de deletar os pagamentos.");
            return;
        }

        pedidos.clear();
        System.out.println("Todos os pedidos foram apagados com sucesso!");
    }

    private static void deletarTodosPagamentos() {
        pagamentos.clear();
        System.out.println("Todos os pagamentos foram apagados com sucesso!");
    }

    private static void recuperarPedidos() {
        recuperarDados("registro_sorvete_pedido.txt");
    }

    private static void recuperarPagamentos() {
        recuperarDados("registro_sorvete_pagamento.txt");
    }

    private static void recuperarDados(String arquivo) {
        File file = new File(arquivo);
        if (!file.exists()) {
            System.out.println("Arquivo " + arquivo + " não encontrado.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linha;
            System.out.println("--- Recuperando dados de " + arquivo + " ---");
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + arquivo + ": " + e.getMessage());
        }
    }
}
