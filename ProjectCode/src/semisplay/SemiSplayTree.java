package semisplay;

import java.util.*;

public class SemiSplayTree<E extends Comparable<E>> implements SearchTree<E>{

    private int splaySize;
    private Node<E> root;
    private int size;

    /**
     * The constructor
     *
     * @param splaySize, The amount of nodes that will be used in every splay step during the process
     */
    public SemiSplayTree(int splaySize){
        this.splaySize = splaySize;
        this.root = null;
        this.size = 0;
    }

    /**
     * a simple function that will return the root of the tree
     *
     * @return a function that returns the field root
     */
    public Node<E> getRoot(){
        return this.root;
    }


    /**
     *  This function has the implementation to execute the semisplay on a specific path
     *
     * @param nodeStack this is a stack that contains all nodes on the path, this path is used to execute the semisplay on
     */
    public void semiSplayStep(Stack<Node<E>> nodeStack){

        if(nodeStack.size() >= this.splaySize){ //this is a quick check to verify if there are enough nodes to do a semi-splay step

            //checking if there are enough nodes to execute a semi-splay step
            while(nodeStack.size() >= this.splaySize){
                int splaySizeCount;
                List<Node<E>> splayStepList = new ArrayList<Node<E>>();

                //construct a shorter list for, with the elements we need to execute one semi splay step on the path
                for(splaySizeCount=0;splaySizeCount<this.splaySize;splaySizeCount++){
                    splayStepList.add(nodeStack.pop());
                }

                //we need the elements in reversed order to build the correct subtree
                Collections.reverse(splayStepList);

                //get all the outer trees of these nodes in order
                Node[] outerTrees = this.getAllOuterTrees(splayStepList);

                Collections.sort(splayStepList);
                //call the recursive function to build a perfect balanced subtree
                Node<E> newNodeAfterSemiSplay = recSemiSplay(splayStepList);

                //here we will add the outer trees back to the subtree on the correct place
                this.addAllOuterTrees(splayStepList, outerTrees);

                //adding the new subtree back in the right place in the tree
                if(!nodeStack.empty()){
                    Node<E> parentOfSubTree = nodeStack.peek();

                    int compare = parentOfSubTree.compareTo(newNodeAfterSemiSplay);
                    if(compare>0){
                        parentOfSubTree.left = newNodeAfterSemiSplay;
                    }else{
                        parentOfSubTree.right = newNodeAfterSemiSplay;
                    }
                }
                //push the root of the new subtree back on the stack
                nodeStack.push(newNodeAfterSemiSplay);
            }

            //there aren't enough nodes available to do a semisplay step
            if(nodeStack.size() == 1){
                //this becomes the new root
                this.root = nodeStack.pop();
            }else{
                //there are at least two nodes left, recreate the tree
                Node<E> lastRootFromSemiSplayStep = nodeStack.pop();
                Node<E> parentOfSemiSplay = nodeStack.pop();
                int compare = parentOfSemiSplay.compareTo(lastRootFromSemiSplayStep);
                if(compare > 0){
                    parentOfSemiSplay.left = lastRootFromSemiSplayStep;
                }else{
                    parentOfSemiSplay.right = lastRootFromSemiSplayStep;
                }
            }
        }
    }

    /**
     * This function will assign the outertrees to the correct position of nodes in the path
     *
     * @param path this is the path with the same size as the semisplay size
     * @param outerTrees these are all the trees we want to add on the rigth place on the nodes from the path
     */
    public void addAllOuterTrees(List<Node<E>> path, Node[] outerTrees){
            //last step adding the outer trees back on it, we will use a depth first search approach to find all the null pointers
            // we ad the outertrees in ascending order
            Collections.sort(path);

            int indexOuterTrees = 0;
            for(Node<E> node:path){
                Node<E> leftNode = node.left;
                Node<E> rightNode = node.right;
                if(!path.contains(leftNode) && !path.contains(rightNode)){
                    node.left = outerTrees[indexOuterTrees];
                    indexOuterTrees++;
                    node.right = outerTrees[indexOuterTrees];
                    indexOuterTrees++;
                }else if(!path.contains(leftNode)){
                    //this will normally not be used becasue of the way the subtree gets ordered
                    node.left = outerTrees[indexOuterTrees];
                    indexOuterTrees++;
                }else if(!path.contains(rightNode)){
                    node.right = outerTrees[indexOuterTrees];
                    indexOuterTrees++;
                }
            }

    }


    /**
     *  this function will return all outertrees of a specific path in a bigger tree
     *
     * @param semiPath this is the path we want all the outertrees from
     * @return we return an array with all the outertrees in the correct order, this makes it easier to append them on the rigth place afterwards
     */
    public Node[] getAllOuterTrees(List<Node<E>> semiPath){
        // there is always one n+1 outer trees on a path of n nodes
        Node[] outerTrees = new Node[semiPath.size()+1];

        int index;
        int indexOuter = 0;
        int right = 0;
        for(index = 0;index<semiPath.size()-1;index++){ //we don't include the last element, because we always add the 2 children of the last element
            if(!semiPath.contains(semiPath.get(index).left)){
                outerTrees[indexOuter] = semiPath.get(index).left;
                indexOuter++;
            }else{
                outerTrees[semiPath.size()-right] = semiPath.get(index).right;
                right++;
            }
        }
        outerTrees[index+1-right] = semiPath.get(index).right;
        right++;
        outerTrees[index+1-right] = semiPath.get(index).left;

        return outerTrees;
    }

    /**
     * This function will return a perfect balanced tree, starting form a list with nodes
     *
     * @param list this is the path with all the nodes we want to use in our new balanced tree
     * @return we only return the new root of the balanced tree, this root will have the correct children assigned
     */
    public Node<E> recSemiSplay(List<Node<E>> list){
        int half = list.size()/2;

        Node<E> rootSemiSplay = list.get(half);

        // all the outer trees will be added at the end of the semi splay step
        if(list.size() >= 3){
            rootSemiSplay.left = recSemiSplay(list.subList(0, half));
            rootSemiSplay.right = recSemiSplay(list.subList(half+1, list.size()));
        }else if(list.size() >= 2){
            rootSemiSplay.left = recSemiSplay(list.subList(0, half));
            rootSemiSplay.right = null;
        }else if(list.size() == 1){
            rootSemiSplay.left = null;
            rootSemiSplay.right = null;
        }

        return rootSemiSplay;
    }

    /**
     * This function is used to add an element to the existing tree, we wrap the comparable element into the class node
     *
     * @param comparable This is the element we want to add to the tree
     * @return This method returns true only if the operation for adding the element succeeded, if the element already
     * exists inside the tree, than we return false
     */
    @Override
    public boolean add(E comparable) {
        // We hold a list where we can add all the nodes that we travers on the path
        Stack<Node<E>> path = new Stack<Node<E>>();

        // this is the new node we want to add to the tree
        Node<E> newNode = new Node(comparable);
        if(this.root == null){
            //if the root is null, we add the new element
            this.root = newNode;
            this.size++;
            return true;
        }else{
            //from here on we will descend in the tree to find the correct spot to add the new element
            Node<E> node = this.root;
            while (node != null){
                //we always update the path so we can call the function semi splay
                path.add(node);
                Comparable keyNode = node.key;
                int compare = keyNode.compareTo(comparable);
                if(compare > 0){
                    if(node.left != null){
                        node = node.left;
                    }else{
                        //we found an empty spot where we can add the new element
                        node.left = newNode;
                        this.size++;
                        //adding the node to the semisplay path
                        path.add(newNode);
                        this.semiSplayStep(path);
                        return true;
                    }
                }else if(compare < 0){
                    if(node.right != null){
                        node = node.right;
                    }else{
                        //we found an empty spot where we can add the new element
                        node.right = newNode;
                        this.size++;
                        //adding the node to the semisplay path
                        path.add(newNode);
                        this.semiSplayStep(path);
                        return true;
                    }
                }else{
                    //element is already part of the tree, so we still call the semisplay function
                    this.semiSplayStep(path);
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * This function is used to delete an element from the tree
     *
     * @param comparable, this value is the node that we want to delete from the tree
     * @return we return true only when the value is found inside the tree and is deleted
     * otherwise we return false
     */
    @Override
    public boolean remove(E comparable) {
        Stack<Node<E>> path = new Stack<Node<E>>();

        // We will first check if the root isn't null, if so we return false
        if(this.root == null){
            return false;
        }
        //this node is the current node, which we will update while moving in the tree
        Node<E> current = this.root;
        Comparable comp = current.key;
        Node<E> parent = null;
        boolean isLeftChild = false;
        int compare = comp.compareTo(comparable);

        // we continue as long as there are nodes to compare to
        // if there is no node left then the remove operation can't continue en we return false
        while(compare != 0){
            if(compare < 0){
                parent = current;
                path.add(parent);
                current = current.right;
                isLeftChild = false;
            } else if(compare > 0){
               //move to the left
                parent = current;
                path.add(parent);
                current = current.left;
                isLeftChild = true;
            }
            if(current == null){
                //we call the semi splay function with the path until now.
                this.semiSplayStep(path);
                return false;
            }
            comp = current.key;
            compare = comp.compareTo(comparable);
        }
        //the node is found
        //if the node is a leafnode
        if(current.left == null && current.right == null){
            //the semi splay path is the path until the parent of the removed node
            if(current == this.root){
                this.root = null;
            }else if(isLeftChild){
                parent.left = null;
            }else{
                parent.right = null;
            }
            this.semiSplayStep(path);
        }else if(current.left == null){
            if(current == this.root){
                this.root = current.right;
            }else if(isLeftChild){
                parent.left = current.right;
                path.add(parent.left);
            }else{
                parent.right = current.right;
                path.add(parent.right);
            }
            this.semiSplayStep(path);
        }else if(current.right == null){
            if(current == this.root){
                this.root = current.left;
            }else if(isLeftChild){
                parent.left = current.left;
                path.add(parent.left);
            }else{
                parent.right = current.left;
                path.add(parent.right);
            }
            this.semiSplayStep(path);
        }
        else{
            Node<E> nextNode = getMinNode(current, path);
            if(current == this.root){
                this.root = nextNode;
            }else if(isLeftChild){
                parent.left = nextNode;
            }else {
                parent.right = nextNode;
            }
            nextNode.left = current.left;
            this.semiSplayStep(path);
        }
        this.size --;
        return true;
    }

    /**
     * This function is used to get the smallest node out of a tree
     *
     * @param node this node is the start node from where we start searching
     * @return we return the smallest node in the subtree
     */
    public Node<E> getMinNode(Node node, List<Node<E>> path){
        int sizeOfPath = path.size();
        path.add(sizeOfPath, null);

        Node<E> successor = node;
        Node<E> successorParent = node;
        Node<E> current = node.right;

        // we use the fact that the smallest node is always in the most left position in a tree
        while(current != null){
            successorParent = successor;
            successor = current;
            path.add(successor);
            current = current.left;
        }

        if(successor != node.right){
            successorParent.left = successor.right;
            successor.right = node.right;
        }

        //remove the last element out of the list and place it at the position with null as value
        int endSizeOfPath = path.size();
        Node<E> endNode = path.remove(endSizeOfPath-1);
        path.set(sizeOfPath, endNode);

        return successor;
    }


    /**
     * This method gives the size of the tree, so the count of all nodes present in the current tree
     *
     * @return we return an integer to specify how many nodes this tree contains.
     */
    @Override
    public int size() {
        return this.size;

        // this piece of code can be used when we aren't allowed to use a field size in the constructor
        /*
        int size = 0;
        Stack<Node> stack = new Stack<Node>();
        if(this.root == null){
            return 0;
        }else{
            stack.push(this.root);
            while(!stack.isEmpty()){
                Node node = stack.pop();
                size ++;
                if(node.right!=null){
                    stack.push(node.right);
                }
                if(node.left != null){
                    stack.push(node.left);
                }
            }
        }
        return size;
        */

    }

    /**
     * This function is used to check whether an element is part of a tree or not
     *
     * @param comparable this argument is the element we want to search in the tree
     * @return We return true if the element indeed is a part of tree
     * otherwise we return false
     */
    @Override
    public boolean contains(E comparable) {
        //we use this list to construct a path which will be used in the semisplay step
        Stack<Node<E>> path = new Stack<Node<E>>();
        if(this.root == null){
            //if there is no root, return false!
            return false;
        }else{
            Node node = this.root;
            while(node != null){
                //update the path correctly while we descent in the tree
                path.add(node);
                Comparable comp = node.key;
                int compare = comp.compareTo(comparable);
                if(compare < 0){
                    node = node.right;
                }else if(compare > 0){
                    node = node.left;
                }else{
                    //we found the node we where looking for, so return true
                    this.semiSplayStep(path);
                    return true;
                }
            }
            //if we leave the while loop and the node with the element we are looking for is still not found
            // then this means that the element isn't in the tree so we return false
        }
        this.semiSplayStep(path);
        return false;
    }

    /**
     * The function will build an iterator that runs over the nodes in ascending order
     * This function doesn't use the semiesplay function, so also no semisplay step.
     * @return This function returns an iterator
     */
    @Override
    public Iterator iterator() {
        return new Iterator(this.root);
    }

    /**
     *  a class that overrides all the functions necessary to implement an iterator
     */
    static class Iterator implements java.util.Iterator{

        // we use a stack to implement all necessary functions
        Stack<Node> stack = new Stack<Node>();

        public Iterator(Node root){
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Comparable next() {
            Node result = stack.pop();
            if (result.right != null) {
                Node node = result.right;
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
            return result.key;
        }

        @Override
        public void remove() {
            new NoSuchMethodException();
        }
    }

    /**
     * This function will return the depth of the tree
     * we work with an iterative solution, this function doesn't use the semisplay function, so no splaystep is used
     *
     * @return we return an integer to specify the depth of the tree
     */
    @Override
    public int depth() {
        // the root has depth 0
        return maxDepth(this.root) - 1;
    }

    /**
     * This function is a recursive function to find the maximum depth of a tree
     * @param node this is the starting node for the recursive function
     * @return we return an integer, that show how deep the subtree is from the start node
     */
    public int maxDepth(Node node){
        if(node == null) return 0;

        int leftDepth = maxDepth(node.left);
        int rigthDepth = maxDepth(node.right);

        // take the biggest depth and return it + 1 for the current node itself
        int bigger = Math.max(leftDepth, rigthDepth);
        return bigger+1;
    }
}
