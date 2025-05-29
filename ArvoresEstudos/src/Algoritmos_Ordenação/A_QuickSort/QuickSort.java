package Algoritmos_Ordenação.A_QuickSort;

public class QuickSort <T extends Comparable<T>>{
    private char ordem; //D - decrescente | C - crescente

    public QuickSort(char c){
        this.ordem = c;
    }

    public void quicksort(T[] vetor, int i, int f){
        int k;
        if(f>i){
            k = particao(vetor, i, f);
            quicksort(vetor, i, k-1); //seguimento s1
            quicksort(vetor,k+1, f); //seguimento s3
        }
    }

    private int particao(T[] vetor, int i, int f){
        T aux = vetor[i];
        char lado = 'E';
        while(i<f){
            if(lado == 'E'){
                if(this.ordem == 'C' && vetor[f].compareTo(aux) < 0 || this.ordem == 'D' && vetor[f].compareTo(aux) > 0){
                    vetor[i] = vetor[f];
                    lado = 'D';
                    i++;
                }
                else{
                    f--;
                }
            }
            else{
                if(this.ordem == 'C' && vetor[i].compareTo(aux) < 0 || this.ordem = 'D' && vetor[i].compareTo(aux > 0)){
                    i++;
                }
                else{
                    vetor[f] = vetor[i];
                    lado = 'E';
                    f--;
                }
            }
        }
        vetor[i] = aux;
        return i;
    }
}