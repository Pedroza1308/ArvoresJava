package queueGenerica;

class QueueNode<T> {
    private T info;
    private QueueNode<T> prox;

    QueueNode(T info){
        this.info = info;
    }

    T getInfo(){
        return info;
    }

    void setInfo(T info){
        this.info = info;
    }

    QueueNode<T> getProx(){
        return prox;
    }

    void setProx(QueueNode<T> prox){
        this.prox=prox;
    }
}