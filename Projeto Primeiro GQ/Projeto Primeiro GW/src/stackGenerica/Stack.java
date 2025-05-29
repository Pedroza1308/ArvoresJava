package stackGenerica;

public class Stack<T> {
    private StackNode<T> topo;

    public boolean isEmpty(){
        return this.topo == null;
    }

    public void push(T info){
        StackNode<T> novo = new StackNode<T>(info);
        novo.setprox(this.topo);
        this.topo = novo;
    }

    public T pop(){
        StackNode<T> aux = this.topo;
        this.topo=this.topo.getprox();
        return aux.getInfo();
    }
}