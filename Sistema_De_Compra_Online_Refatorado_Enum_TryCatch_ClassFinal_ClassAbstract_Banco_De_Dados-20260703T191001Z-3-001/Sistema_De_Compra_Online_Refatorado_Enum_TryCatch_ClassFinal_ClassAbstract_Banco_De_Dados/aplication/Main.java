package Sistema_De_Compra_Online_Refatorado_Enum_TryCatch_ClassFinal_ClassAbstract_Banco_De_Dados.aplication;

import Sistema_De_Compra_Online_Refatorado_Enum_TryCatch_ClassFinal_ClassAbstract.entities.Cliente;
import Sistema_De_Compra_Online_Refatorado_Enum_TryCatch_ClassFinal_ClassAbstract_Banco_De_Dados.Class_Connection.ClassConnectionCompra;
import Sistema_De_Compra_Online_Refatorado_Enum_TryCatch_ClassFinal_ClassAbstract_Banco_De_Dados.Repository.produtoDAO;
import Sistema_De_Compra_Online_Refatorado_Enum_TryCatch_ClassFinal_ClassAbstract_Banco_De_Dados.entities.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        try {
            // Cria objeto para ler dados do usuário

            System.out.println("sistema de compra online");
            System.out.println("-----cadastro-----");
            System.out.println("");
            System.out.println("digite seu nome:");
            String nome = sc.nextLine();
            System.out.println("digite seu cpf:");
            String cpf = sc.nextLine();
            System.out.println("digite seu endereço:");
            String endereco = sc.nextLine();

            // Conexão com o banco de dados
            Connection conn = (Connection) ClassConnectionCompra.getConnection();

            // Cria objeto cliente com os dados informados
            Cliente cliente = new Cliente(nome, cpf, endereco);

            // SQL para inserir cliente no banco
            String sql = "INSERT INTO pessoa (nome, cpf, endereco) VALUES (?, ?, ?)";

            // PreparedStatement evita SQL Injection
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Substitui os ? pelos valores do cliente
            stmt.setString(1, cliente.getNome());// primeiro ?
            stmt.setString(2, cliente.getCpf());// segundo ?
            stmt.setString(3, cliente.getEndereco());// terceiro ?

            stmt.executeUpdate();// Executa o comando INSERT no banco de dados.

            // Agora sim: pega o ID gerado DEPOIS do INSERT executado
            int idCliente = -1;
            try (ResultSet rsGerado = stmt.getGeneratedKeys()) {
                if (rsGerado.next()) {
                    idCliente = rsGerado.getInt(1);
                }
            }

            System.out.println("cadastrado com sucesso");

            int opcaoMenu;

            do {
                System.out.println("deseja atualizar o cadastro ou continuar compra:");
                System.out.println("");
                System.out.println("1 - Comprar");
                System.out.println("2 - Atualizar cliente");
                System.out.println("3 - Deletar cliente");
                System.out.println("4 - Buscar cliente");

                opcaoMenu = sc.nextInt();
                sc.nextLine();

                switch (opcaoMenu) {

                    case 1:
                        //a parte do sistem de carrinho entra em ação
                        System.out.println("vc escolheu compra");

                        break;

                    case 2:
                        // UPDATE

                        // Pergunta o CPF do cliente que será atualizado
                        System.out.println("Digite o CPF do cliente:");
                        String cpfUpdate = sc.nextLine();

                        // Pede o novo endereço
                        System.out.println("Novo endereço:");
                        String novoEndereco = sc.nextLine();

                        // Comando SQL para atualizar o endereço baseado no CPF
                        String sqlUpdate = "UPDATE pessoa SET endereco = ? WHERE cpf = ?";

                        // Cria o PreparedStatement (evita SQL Injection)
                        PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);

                        // Substitui os ? pelos valores
                        stmtUpdate.setString(1, novoEndereco); // primeiro ? → novo endereço
                        stmtUpdate.setString(2, cpfUpdate);    // segundo ? → cpf

                        // Executa o UPDATE e retorna quantas linhas foram afetadas
                        int linhas = stmtUpdate.executeUpdate();
                        // Verifica se atualizou alguém
                        if (linhas > 0) {
                            System.out.println("Cliente atualizado com sucesso!");
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;

                    case 3:
                        // DELETE

                        // Pergunta qual CPF será deletado
                        System.out.println("Digite o CPF para deletar:");
                        String cpfDelete = sc.nextLine();

                        // Comando SQL para deletar
                        String sqlDelete = "DELETE FROM pessoa WHERE cpf = ?";

                        // Cria o PreparedStatement
                        PreparedStatement stmtDelete = conn.prepareStatement(sqlDelete);

                        // Substitui o ? pelo CPF
                        stmtDelete.setString(1, cpfDelete);

                        // Executa o DELETE
                        linhas = stmtDelete.executeUpdate();

                        // Verifica se deletou
                        if (linhas > 0) {
                            System.out.println("Cliente deletado com sucesso!");
                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;

                    case 4:
                        // SELECT

                        // Pergunta o CPF que será buscado
                        System.out.println("Digite o CPF para buscar:");
                        String cpfBusca = sc.nextLine();

                        // Comando SQL para buscar cliente
                        String sqlSelect = "SELECT * FROM pessoa WHERE cpf = ?";

                        // Cria o PreparedStatement
                        PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);

                        // Substitui o ? pelo CPF
                        stmtSelect.setString(1, cpfBusca);

                        // Executa a consulta (SELECT usa executeQuery)
                        ResultSet rs = stmtSelect.executeQuery();

                        // Verifica se encontrou resultado
                        if (rs.next()) {

                            // Pega os dados do banco pelo nome das colunas
                            System.out.println("Nome: " + rs.getString("nome"));
                            System.out.println("CPF: " + rs.getString("cpf"));
                            System.out.println("Endereço: " + rs.getString("endereco"));

                        } else {
                            System.out.println("Cliente não encontrado!");
                        }
                        break;
                }
            } while (opcaoMenu != 1);

            // Cria DAO para acessar produtos no banco
            produtoDAO produtoDAO = new produtoDAO(conn);

            // Cria carrinho de compras
            carrinho carrinho = new carrinho();

            String continuar;

            do {
                System.out.println("digite o ID do produto");
                int iDproduto = sc.nextInt();

                Produto produto = produtoDAO.buscarPorID(iDproduto);

                // Verifica se o produto existe
                if (produto != null) {

                    // Adiciona no carrinho
                    carrinho.adicionarProduto(produto);

                    System.out.println("produto adicionadado: " + produto.nomeProduto());
                } else {
                    System.out.println("produto não encontrado");
                }
                // Pergunta se quer continuar
                System.out.println("deseja adicionar mais produto?");
                continuar = sc.next();

            } while (continuar.equalsIgnoreCase("S"));

            // Mostra resumo da compra
            System.out.println("Resumo da compra");

            // Percorre lista de produtos do carrinho
            for (Produto p : carrinho.getProdutos()) {
                System.out.println(p.nomeProduto() + " -$" + p.precoProdutoo());
            }
            // Mostra total da compra
            System.out.println("total: " + carrinho.calcularTotal());

            // Escolha de pagamento 
            System.out.println("Escolha a forma de pagamento:");
            System.out.println("1 - CARTAO");
            System.out.println("2 - PIX");
            System.out.println("3 - BOLETO");
            System.out.println("4 - DINHEIRO");
            int opcao = sc.nextInt();
            sc.nextLine();

            //para puxar as class herdadas
            Pagamento pagamento;

            // tipo de pagamento
            switch (opcao) {
                case 1:
                    pagamento = new PagamentoCartao(carrinho.calcularTotal());
                    break;
                case 2:
                    pagamento = new PagamentoPix(carrinho.calcularTotal());
                    break;
                case 3:
                    pagamento = new PagamentoBoleto(carrinho.calcularTotal());
                    break;
                case 4:
                    pagamento = new PagamentoDinheiro(carrinho.calcularTotal());
                    break;
                default:
                    // Caso escolha inválida
                    throw new InputMismatchException();
            }
            pagamento.processarPagamento();

            System.out.println("Pagamento escolhido: " + pagamento.getClass().getSimpleName().replace("Pagamento", "").toUpperCase());
            System.out.println("pagamento realizado com sucesso");

            // SQL para mandar as informaçoes da compra no banco
            String sqlCompra = "INSERT INTO compra (id_cliente, valor, frete, FormaDePagamento) VALUES (?, ?, ?, ?)";

            try (java.sql.Connection conexao = ClassConnectionCompra.getConnection()) {

                // Cria o statement para fazer o INSERT
                PreparedStatement stmtCompra = conexao.prepareStatement(sqlCompra);

                stmtCompra.setInt(1, idCliente); // e e ID do cliente
                stmtCompra.setDouble(2, carrinho.calcularTotal());//  valor total que vai dar a conta
                stmtCompra.setDouble(3, 10.0);// esse aqui e pro frete fixo
                stmtCompra.setString(4, pagamento.getClass().getSimpleName().replace("Pagamento", "").toUpperCase());// forma de pagamento que pessoa vai fazer (ex: PagamentoCartao -> CARTAO)

                stmtCompra.executeUpdate();

                System.out.println("Compra salva com sucesso!");

            } catch (Exception e) {

                e.printStackTrace(); //isso aqui MOStra o error
            }
        } catch (InputMismatchException e) {

            // Erro quando usuário digita tipo errado de entrada
            System.out.println("entrada invalida");
        } catch (MySQLIntegrityConstraintViolationException e ){
            System.out.println("dados ja existentes");
        }
        finally {
            sc.close();

        }
    }
}