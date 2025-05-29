package AVL;
import ED_auxiliar.Fila.Queue;
import stackGenerica.pilha.Stack;

public class AVL<T extends Comparable<T>> {
    private AVLNode<T> root;
    private boolean status;
    
    public boolean isEmpty(){
        return this.root==null;
    }

    public void insert(T valor){
        if(this.isEmpty()){
            this.root = new AVLNode<T>(valor);
        }
        else{
            this.root = insertNode(this.root, valor);
            this.status = false;
        }
    }

    private AVLNode<T> insertNode(AVLNode<T> r, T valor){
        if(r==null){
            r = new AVLNode<T>(valor);
            this.status = true;
        }
        else{
            int resultado = valor.compareTo(r.getInfo());
            if(resultado >= 0){
                r.setRight(insertNode(r.getRight(), valor));
                if(this.status){
                    switch(r.getFatBal()){
                        case -1->{
                            r.setFatBal(0);
                            this.status=false;
                        }
                        case 0-> r.setFatBal(1);
                        case 1-> r = this.rotateLeft(r);
                    }
                }
            }
            else{
                r.setLeft(insertNode(r.getLeft(), valor));
                if(this.status){
                    switch(r.getFatBal()){
                        case 1->{
                            r.setFatBal(0);
                            this.status=false;
                        }
                        case 0->r.setFatBal(-1);
                        case -1->r = this.rotateRight(r);
                    }
                }
            }
        }
        return r;
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
            a.setRight(c.setLeft());
            c.setLeft=a;
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
        else{
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
            StackNode<AVLNode<T>> pilha = new StackNode<AVLNode<T>>();
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
                aux = fila.deQueue();
                if(aux!=null){ //&& aux.getLeft()!=null && aux.getRight()!=null){
                    fila.enQueue(aux.getLeft());
                    fila.enQueue(aux.getRight());
                }
                else if(aux==null){
                    System.out.println("null ");
                }
                else{
                    System.out.println(aux.getinfo()+" ");
                }
            }
            System.out.println();
        }
    }

    

    public void remove(T valor){
        if(this.isEmpty()){
            System.out.println("arvore vazia");
        }
        else{
            this.root = removeNode(this.root, T valor);
            this.staus = false;
        }
    }

    private AVLNode<T> removeNode(AVLNode<T> r, T valor){
        if(node!=null){
            int resultado = valor.compareTo(r.getinfo());
            if(resultado==0){
                if(r.getLeft()==null && r.getRight()==null){
                    r=null;
                }
                else if(r.getleft()==null){
                    r=r.getRight();
                }
                else if(r.getRight()==null){
                    r=r.getLeft();
                }
                else{
                    AVLNode<T> pai, filho;
                    pai=r;
                    filho=pai.getleft();
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
                    r.setInfo(filho.getInfo());
                }
            }
            else if(resultado<0){
                r.setLeft(removeNode(r.getLeft(), valor));
            }
            else{
                r.setRight(removeNode(r.getRight(), valor));
            }


        }
    }
}