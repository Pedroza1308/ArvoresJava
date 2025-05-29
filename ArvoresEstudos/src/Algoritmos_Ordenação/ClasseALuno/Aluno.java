package Algoritmos_Ordenação.ClasseALuno;

public class Aluno <T extends Comparable<T>> {
    private String matr;
    private String nome;
    private double nota;
    private int faltas;

    public Aluno(String matr, String nome, double nota, int faltas){
        this.matr = matr;
        this.nome = nome;
        this.nota = nota;
        this.faltas = faltas;
    }

    public String getMatr(){
        return this.matr;
    }

    public void setMatr(String matr){
        this.matr = matr;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public double getNota(){
        return this.nota;
    }
    
    public void setNota(double nota){
        this.nota = nota;
    }

    public int getFaltas(){
        return this.faltas;
    }

    public void setFaltas(int faltas){
        this.faltas = faltas;
    }
}