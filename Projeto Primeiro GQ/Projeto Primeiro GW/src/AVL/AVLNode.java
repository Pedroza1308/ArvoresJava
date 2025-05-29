package AVL;

class AVLNode<T extends Comparable<T>> {
    private AVLNode<T> right;
    private AVLNode<T> left;
    private int fatbal;
    private T info;
    
    AVLNode(T info){
        this.info=info;
    }

    AVLNode<T> getRight(){
        return right;
    }
    void setRight(AVLNode<T> right){
        this.right = right;
    }

    AVLNode<T> getLeft(){
        return left;
    }
    void setLeft(AVLNode<T> left){
        this.left=left;
    }

    int getFatBal(){
        return fatbal;
    }
    void setFatBal(int fatbal){
        this.fatbal=fatbal;
    }

    T getInfo(){
        return info;
    }
    void setInfo(T info){
        this.info=info;
    }
}