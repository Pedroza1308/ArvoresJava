package stackGenerica;

class StackNode<T> {
    private T info;
    private StackNode<T> prox;

    StackNode(T info){
        this.info = info;
    }

    T getInfo(){
        return info;
    }

    void setInfo(T info){
        this.info = info;
    }

    StackNode<T> getprox(){
        return prox;
    }

    void setprox(StackNode<T> prox){
        this.prox = prox;
    }
}