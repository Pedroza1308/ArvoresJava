package ABB;

class ABBNode <T extends Comparable <T>> {
    private ABBNode<T> left;
    private ABBNode<T> right;
    private T info;

    ABBNode(T info){
        this.info=info;
    }

    T getInfo(){
        return info;
    }

    void setInfo(T info){
        this.info=info;
    }

    ABBNode<T> getLeft(){
        return left;
    }

    void setLeft(ABBNode<T> left){
        this.left=left;
    }

    ABBNode<T> getRight(){
        return right;
    }

    void setRight(ABBNode<T> right){
        this.right=right;
    }

}