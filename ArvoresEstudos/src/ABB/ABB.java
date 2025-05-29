package ABB;
import ED_auxiliar.Fila.Queue;
import ED_auxiliar.pilha.Stack;

public class ABB <T extends Comparable<T>> {
    private ABBNode<T> root;

    public boolean isEmpty(){
        if(this.root==null){
            return true;
        }
        else{
            return false;
        }
    }

    public void inserirValor(T valor){
        if(this.isEmpty()==true){
            this.root=new ABBNode<T>(valor);
        }   
        else{
            inserir(this.root, valor);
        }
    }

    private void inserir(ABBNode<T> node, T valor){
        int retorno;

        if(node!=null){
            retorno = valor.compareTo(node.getInfo());
            if(retorno==0){
                System.out.println("valor repetido!\n");
                return;
            }

            else if(retorno < 0){
                if(node.getLeft()!=null){
                    inserir(node.getLeft(), valor);
                }
                else{
                    ABBNode<T> novo = new ABBNode<T>(valor);
                    node.setLeft(novo);
                }
            }

            else{
                if(node.getRight()!=null){
                    inserir(node.getRight(), valor);
                }
                else{
                    ABBNode<T> novo = new ABBNode<T>(valor);
                    node.setRight(novo);
                }
            }
        }
    }

    public T find(T valor){
        if(this.isEmpty()==true){
            return null;
        }
        else{
            return findNode(this.root, valor);
        }
    }

    private T findNode(ABBNode<T> r, T valor){
        int resultado = valor.compareTo(r.getInfo());
        if(resultado==0){
            return r.getInfo();
        }
        else if(resultado < 0){
            if(r.getLeft()==null){
                return null;
            }
            else{
                return findNode(r.getLeft(), valor);
            }
        }
        else{
            if(r.getRight()==null){
                return null;
            }
            else{
                return findNode(r.getRight(), valor);
            }
        }
    }

    public void emOrdem(){
        if(this.isEmpty()==true){
            System.out.println("arvore vazia\n");
        }
        else{
            this.percorrerEmOrdem(this.root);
        }
    }

    private void percorrerEmOrdem(ABBNode<T> r){
        if(r!=null){
            percorrerEmOrdem(r.getLeft());
            System.out.println(r.getInfo());
            percorrerEmOrdem(r.getRight());
        }
    }

    public void preOrdem(){
        if(this.isEmpty()==true){
            System.out.println("arvore vazia\n");
        }
        else{
            this.percorrerPreOrdem(this.root);
        }
    }
    private void percorrerPreOrdem(ABBNode<T> r){
        if(r!=null){
            System.out.println(r.getInfo());
            percorrerPreOrdem(r.getLeft());
            percorrerPreOrdem(r.getRight());
        }
    }

    public void posOrdem(){
        if(this.isEmpty()==true){
            System.out.println("arvore vazia\n");
        }
        else{
            this.percorrerPosOrdem(this.root);
        }
    }
    private void percorrerPosOrdem(ABBNode<T> r){
        if(r!=null){
            percorrerPreOrdem(r.getLeft());
            percorrerPreOrdem(r.getRight());
            System.out.println(r.getInfo());
        }
    }

    public void remove(T valor){
        if(this.isEmpty()==true){
            System.out.println("arvore vazia!\n");
        }
        else{
            this.root=this.removeNo(this.root, valor);
        }
    }

    private ABBNode<T> removeNo(ABBNode<T> r, T valor){
        if(r!=null){
            int resultado = valor.compareTo(r.getInfo());
            if(resultado==0){   
                if(r.getLeft()==null && r.getRight()==null){
                    r = null;
                }
                else if(r.getLeft()==null){
                    r=r.getRight();       
                }
                else if(r.getRight()==null){
                    r=r.getLeft();
                }
                else{
                    ABBNode<T> pai, filho;
                    pai = r;
                    filho = pai.getLeft();
                    if(filho.getRight()!=null){
                        while(filho.getRight()!=null){
                            pai = filho;
                            filho = filho.getRight();
                        }
                    }
                    pai.setRight(filho.getLeft());
                    else{
                        pai.setLeft(filho.getLeft());
                    }
                    r.setInfo(filho.getInfo());
                }
            }
            else if(resultado < 0){
                r.setLeft(removeNo(r.getLeft(), valor)); //pilha de recursao
            }
            else{
                r.setRight(removeNo(r.getRight(), valor)); //pilha de recursao
            }
            return r;
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

    public void EmOrdemNaoRecursivo(){ //usando stack
        ABBNode<T> aux;
        if(this.isEmpty()){
            System.out.println("arvore vazia\n");
        }
        else{
            Stack<ABBNode<T>> pilha = new Stack<ABBNode<T>>();
            aux = this.root;

            while(pilha.isEmpty()==false || aux!=null){
                while(aux!=null){
                    pilha.push(aux);
                    aux = aux.getLeft();
                }
                aux = pilha.pop();
                System.out.println(aux.getInfo());
                aux = aux.getRight();
            }
        }
    }

    public int contarRecursivo(){
        if(this.isEmpty()==true){
            return 0;
        }
        else{
            return 1 + this.countNodeRecursivo(this.root.getLeft()) + countNodeRecursivo(this.root.getRight());
        }
    }

    private int countNodeRecursivo(ABBNode<T> r){
        if(r==null){
            return 0;
        }
        else{
            return 1 + countNodeRecursivo(r.getLeft()) + countNodeRecursivo(r.getRight()); //pilha de recursividade
        }
    }

    public ABBNode<T> NodeMenorValor(){
        if(this.isEmpty()){
            return null;
        }
        else{
            return acharNodeMenorValor(this.root);
        }
    }

    private ABBNode<T> acharNodeMenorValor(ABBNode<T> r){
        ABBNode<T> aux = r;
        while(aux.getLeft()!=null){
            aux = aux.getLeft();
        }
        return aux;
    }

    public ABBNode<T> nodeMaiorValor(){
        if(this.isEmpty()){
            return null;
        }
        else{
            return acharNodeMaiorValor(this.root);
        }
    }

    private ABBNode<T> acharNodeMaiorValor(ABBNode<T> r){
        ABBNode<T> aux = r;
        while(aux!=null){
            aux=aux.getRight();
        }
        return aux;
    }

    public void countNodeNaoRecursivo(){
        if(this.isEmpty()){
            System.out.println("arvore vazia!\n");
        }
        else{
            int count = 0;
            Stack<ABBNode<T>> pilha = new Stack<ABBNode<T>>();
            ABBNode<T> aux = this.root;
            while(!pilha.isEmpty() || aux!=null){
                while(aux!=null){
                    pilha.push(aux);
                    aux=aux.getLeft();
                }
                aux = pilha.pop();
                count++;
                aux = aux.getRight();
            }
        }
        System.out.println(count);
    }
}