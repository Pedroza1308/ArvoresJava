package Aplicacao;
import AVL.AVL;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AVL<Integer> arvore = new AVL<>();
        int op;
        do {
            System.out.println("-------------- Selecione uma Opção --------------");
            System.out.println("1 - Inserir valor");
            System.out.println("2 - Passeio por ordem");
            System.out.println("3 - Passeio por nivel");
            System.out.println("4 - Remover valor");
            System.out.println("0 - Encerrar programa");
            System.out.println();
            op = scan.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Insira um valor:");
                    int valorInserir = scan.nextInt();
                    arvore.insert(valorInserir);
                    break;
                case 2:
                    arvore.emOrdem();
                    break;
                case 3:
                    arvore.porNivel();
                    break;
                case 4: 
                    System.out.println("Insira um valor para ser removido: ");
                    int valorRemover= scan.nextInt();
                    arvore.remove(valorRemover);
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (op != 0);
        scan.close();
    }
}
