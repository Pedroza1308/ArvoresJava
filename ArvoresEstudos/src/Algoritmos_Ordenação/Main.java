package Algoritmos_Ordenação;
import Algoritmos_Ordenação.A_HeapSort.HeapSort;
import Algoritmos_Ordenação.A_QuickSort.QuickSort;
import Algoritmos_Ordenação.ClasseALuno.Aluno;

public class Main <T extends Comparable<T>>{
    public static void main(String[] args){
        sortInteiros();
        sortString();
        sortNotas();
    }
    
    public static void sortInteiros(){ //questao 1
        QuickSort qSort = new QuickSort<>('D');

        Integer[] vetor = {23, 45, 72, 37, 120, 61, 8, 15, 87};
        qSort.quicksort(vetor, 0, vetor.length-1);

        for(Integer i : vetor){
            System.out.println(i+" ");
        }
    }

    public static void sortString(){ //questao 2
        QuickSort qSort = new QuickSort<>('C');

        String[] vetor = {"Pedro", "Bruna", "Felipe", "Pablo", "Catarina", "Wilmer", "Vinicius", "Diego"};
        qSort.quicksort(vetor, 0, vetor.length-1);

        for(String i : vetor){
            System.out.println(i+ ", ");
        }
    }

    public static void sortNotas(){ //questao 3
        QuickSort qSort = new QuickSort<>('D');

        double[] vetor = {7.2, 8.5, 9.4, 4.1, 5.7, 2.5, 3.8, 9.7};
        Aluno[] alunos = new Aluno[vetor.length];
        for(int i=0; i < vetor.length; i++){
            Aluno a = new Aluno<>(null, null, vetor[i], 0);
            alunos[i] = a;
        }
        qSort.quicksort(alunos, 0, vetor.length-1);

        for(Aluno a : alunos){
            System.out.println(a.getNota()+" ");
        }
    }

    sortInteiros();
    sortString();
    sortNotas();
}