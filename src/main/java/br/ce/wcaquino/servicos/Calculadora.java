package br.ce.wcaquino.servicos;

public class Calculadora {

    private int a;
    private int b;

    public Calculadora(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getB() {
        return b;
    }

    public int somar(int a, int b){
        return a + b;
    }

    public int subtrair(int a, int b){
        return a-b;
    }

    public int multiplicar(int a, int b){
        return a * b;
    }

    public double dividir(int a, int b){

        if(b==0){
            throw new ArithmeticException("Não é possível dividir por zero");
        }

        return (double) a /b;
    }
}
