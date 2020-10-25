package semisplay;

public class Node<E extends Comparable<E>> implements Comparable<Node>{

    public E key;
    public Node left, right;

    public Node(E key){
        this.key = key;
        this.left = null;
        this.right = null;
    }

    @Override
    public int compareTo(Node o) {
        return this.key.compareTo((E) o.key);
    }
}
