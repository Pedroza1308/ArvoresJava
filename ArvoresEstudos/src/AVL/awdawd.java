package AVL;

public @interface awdawd {
    private AVLNode<T> removeNode(AVLNode<T> r, T valor) {
        if (r != null) {
            int resultado = valor.compareTo(r.getInfo());
    
            if (resultado == 0) {
                // Node to be removed found
                if (r.getLeft() == null && r.getRight() == null) {
                    r = null; // Leaf node
                } else if (r.getLeft() == null) {
                    r = r.getRight(); // Only right child
                } else if (r.getRight() == null) {
                    r = r.getLeft(); // Only left child
                } else {
                    // Node with two children
                    AVLNode<T> pai = r;
                    AVLNode<T> filho = pai.getLeft();
    
                    // Find the in-order predecessor
                    while (filho.getRight() != null) {
                        pai = filho;
                        filho = filho.getRight();
                    }
    
                    // Replace node's value with predecessor's value
                    r.setInfo(filho.getInfo());
    
                    // Remove the in-order predecessor
                    if (pai == r) {
                        pai.setLeft(filho.getLeft());
                    } else {
                        pai.setRight(filho.getLeft());
                    }
                }
            } else if (resultado < 0) {
                // Go to the left subtree
                r.setLeft(removeNode(r.getLeft(), valor));
            } else {
                // Go to the right subtree
                r.setRight(removeNode(r.getRight(), valor));
            }
    
            if (r == null) {
                return null;
            }
    
            // Update the height of the current node
            r.setHeight(1 + Math.max(height(r.getLeft()), height(r.getRight())));
    
            // Get the balance factor
            int balance = getBalance(r);
    
            // Rebalance if needed
            if (balance > 1 && getBalance(r.getLeft()) >= 0) {
                // Left-Left (LL) case
                return rightRotate(r);
            }
    
            if (balance > 1 && getBalance(r.getLeft()) < 0) {
                // Left-Right (LR) case
                r.setLeft(leftRotate(r.getLeft()));
                return rightRotate(r);
            }
    
            if (balance < -1 && getBalance(r.getRight()) <= 0) {
                // Right-Right (RR) case
                return leftRotate(r);
            }
    
            if (balance < -1 && getBalance(r.getRight()) > 0) {
                // Right-Left (RL) case
                r.setRight(rightRotate(r.getRight()));
                return leftRotate(r);
            }
        }
    
        return r;
    }
    
}
