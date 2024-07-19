public class BinaryTree {
    private Node root;

    public Node getRoot() {
        return root;
    }
    private void setRoot(Node root){
        this.root = root;
    }

    private void printInOrder(Node node){
        if(node.getLeft() != null)
            printInOrder(node.getLeft());
        System.out.print(node.getKey()+", ");
        if(node.getRight() != null)
            printInOrder(node.getRight());
    }

    public void printTree(Node node){
        System.out.print("[");
        printInOrder(node);
        System.out.println("]");
    }

    /*public Node successor(int key){
        if(node.getRight() != null){
            System.out.println("yes:)");
            if(node.getRight().getLeft() == null)
                return node.getRight();
            else
                return minimum(node.getRight());
        }
        else
            return null;
    }*/
    public void add(Node node){
        if(getRoot() == null)
            setRoot(node);
        else {
            Node current = getRoot();
            Node parent = null;
            while (current != null){
                parent = current;
                if(current.getKey() >= node.getKey())
                    current = current.getLeft();
                else
                    current = current.getRight();
            }
            current = new Node(node.getKey());
            current.setParent(parent);
            assert parent != null;
            if(node.getKey() < parent.getKey())
                parent.setLeft(current);
            else
                parent.setRight(current);
        }
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public Node search(int key){
        Node current = this.root;
        while (current != null){
            if(current.getKey() == key)
                break;
            else if(current.getKey() > key)
                current = current.getLeft();
            else
                current = current.getRight();
        }
        return current;
    }

    public Node minimum(Node root){
        Node current = root;
        while(current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

    public Node maximum(Node root){
        Node current = root;
        while (current.getRight() != null)
            current = current.getRight();
        return current;
    }


     static class Node{
        Node(int key){
            parent = null;
            right = null;
            left = null;
            this.key = key;
        }
        private int key;
        private Node parent;
        private Node right;
        private Node left;

        public Node getParent() {
            return parent;
        }

        public Node getRight() {
            return right;
        }

        public Node getLeft() {
            return left;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }
    }
}
