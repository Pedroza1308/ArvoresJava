package AVL;
import queueGenerica.Queue;
import stackGenerica.Stack;

public class AVL<T extends Comparable<T>> {
    private AVLNode<T> root;
    private boolean status;
    
    public boolean isEmpty(){
        return this.root==null;
    }

    public void insert(T valor){
        if(this.isEmpty()){
            this.root = new AVLNode<>(valor);
        }else{
            this.root = insertNode(this.root, valor);
            this.status = false;
        }
    }
    private AVLNode<T> insertNode(AVLNode<T>node, T valor){
        if(node == null){
            node = new AVLNode<>(valor);
            this.status = true;
        } else if(node.getInfo().compareTo(valor) > 0){
            node.setLeft(insertNode(node.getLeft(), valor));
            if(this.status){
                switch (node.getFatBal()) {
                    case 1 -> { 
                        node.setFatBal(0);
                        this.status = false;
                    }
                    case 0 -> node.setFatBal(-1);
                    case -1 -> node = this.rotateRight(node);
                }
            }
        } else {
            node.setRight(insertNode(node.getRight(), valor));
            if(this.status){
                switch (node.getFatBal()) {
                    case -1 -> { 
                        node.setFatBal(0);
                        this.status = false;
                    }
                    case 0 -> node.setFatBal(1);
                    case 1 -> node = this.rotateLeft(node);
                }
            }
        }
        return node;
    }

    private AVLNode<T> rotateLeft(AVLNode<T> a){
        AVLNode<T> b,c;
        b = a.getLeft();
        if(b.getFatBal()==1){ //rotacao simples
            a.setRight(b.getLeft());
            b.setLeft(a);
            a.setFatBal(0);
            a=b;
        }
        else{ //rotacao dupla
            c = b.getLeft();
            b.setLeft(c.getRight());
            c.setRight(b);
            a.setRight(c.getLeft());
            c.setLeft(a);
            if(c.getFatBal()==1){
                a.setFatBal(-1);
            }
            else{
                a.setFatBal(0);
            }
            a=c;
        }
        a.setFatBal(0);
        this.status=false;
        return a;
    }

    private AVLNode<T> rotateRight(AVLNode<T> a){
        AVLNode<T> b,c;
        b = a.getLeft();
        if(b.getFatBal()==-1){ //rotacao simples
            a.setLeft(b.getRight());
            b.setRight(a);
            a.setFatBal(0);
            a=b;
        }
        else{ //rotacao dupla
            c = b.getRight();
            b.setRight(c.getLeft());
            c.setLeft(b);
            a.setLeft(c.getRight());
            c.setRight(a);
            if(c.getFatBal()==-1){
                a.setFatBal(1);
            }
            else{
                a.setFatBal(0);
            }
            if(c.getFatBal()==1){
                b.setFatBal(0);
            }
            a=c;
        }
        a.setFatBal(0);
        this.status=false;
        return a;
    }

    public void emOrdem(){
        if(this.isEmpty()){
            System.out.println("arvore vazia!");
        }
        else{
            System.out.println("Em ordem-> ");
            Stack<AVLNode<T>> pilha = new Stack<AVLNode<T>>();
            AVLNode<T> aux = this.root;
            while(!pilha.isEmpty() || aux!=null){
                while(aux!=null){
                    pilha.push(aux);
                    aux = aux.getLeft();
                }
                aux = pilha.pop();
                System.out.println(aux.getInfo()+" ");
                aux = aux.getRight();
            }
            System.out.println();
        }        
    }

    public void porNivel(){
        if(this.isEmpty()){
            System.out.println("arvore vazia!\n");
        }
        else{
            System.out.println("por nivel-> ");
            Queue<AVLNode<T>> fila = new Queue<AVLNode<T>>();
            AVLNode<T> aux;
            fila.enQueue(this.root);
            while(!fila.isEmpty()){
                aux = fila.deQueue(); //primeiro nivel (root) ja é retirado da fila
                if(aux!=null && aux.getLeft()!=null && aux.getRight()!=null){
                    fila.enQueue(aux.getLeft());
                    fila.enQueue(aux.getRight());
                }
                else if(aux==null){
                    System.out.println("null ");
                }
                else{
                    System.out.println(aux.getInfo()+" ");
                }
            }
            System.out.println();
        }
    }

    private int getBalanceFactor(AVLNode<T> node) { //calcula a diferença de altura entre subarvores
        if (node == null) {
            return 0;
        }
        return height(node.getLeft()) - height(node.getRight());
    }
    
    private int height(AVLNode<T> node) { //calcula altura de arvores;subarvores a partir de node
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public void remove(T valor){ //metodo para remocao de nós
        if(this.isEmpty()){
            System.out.println("arvore vazia");
        }
        else{
            this.root = removeNode(this.root, valor); //chamada da funcao que retorna a arvore balanceada com T info removido
            this.status = false;
        }
    }

    private AVLNode<T> removeNode(AVLNode<T> r, T valor){
        if(r!=null){
            int resultado = valor.compareTo(r.getInfo());
            if(resultado==0){
                if(r.getLeft()==null && r.getRight()==null){ //verifica se o nó possui dois filhos
                    r=null;
                }
                else if(r.getLeft()==null){ //verifica se nao tem filho a esquerda
                    r=r.getRight();
                }
                else if(r.getRight()==null){ //verifica se nao tem filho a direita
                    r=r.getLeft();
                }
                else{ //possui dois filhos -> pegar o maior nó da subarvore a esquerda
                    AVLNode<T> pai, filho;
                    pai=r;
                    filho=pai.getLeft();
                    if(filho.getRight()!=null){
                        while(filho.getRight()!=null){
                            pai=filho;
                            filho=filho.getLeft();
                        }
                        pai.setRight(filho.getLeft());
                    }
                    else{
                        pai.setLeft(filho.getLeft());
                    }
                    r.setInfo(filho.getInfo()); //nós nao sao excluidos, há somente uma copia de T info
                }
            }
            else if(resultado<0){
                r.setLeft(removeNode(r.getLeft(), valor)); //procura o valor na subarvore a esquerda -> pilha de chamada
            }
            else{
                r.setRight(removeNode(r.getRight(), valor)); //procura o valor na subarvore a direita -> pilha de chamada
            }
        }
        
        if(r==null){
            return r;
        }

        r.setFatBal(getBalanceFactor(r)); //atualiza o fatbal

        if (r.getFatBal() > 1) { //subarvore a esquerda do nó esta maior que a direita
            if (getBalanceFactor(r.getLeft()) >= 0) {
                return rotateRight(r); //rotacao simples
            }
            else {
                r.setLeft(rotateLeft(r.getLeft()));
                return rotateRight(r); //rotacao dupla
            }
    }

        if (r.getFatBal() < -1) { //subarvore a direita do nó esta maior que a esquerda
            if (getBalanceFactor(r.getRight()) <= 0) {
                return rotateLeft(r); //rotacao simples
            }
            else {
                r.setRight(rotateRight(r.getRight()));
                return rotateLeft(r); //rotacao dupla
            }
        }
    return r;
    }
}