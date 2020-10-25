package test;

import semisplay.Node;
import semisplay.SearchTree;
import semisplay.SemiSplayTree;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class TestJunit {



    //------------------------------------------------------------------------------
    /**
     * The following tests are for the splaying function
     */

    @Test
    public void testSplayGrootte3Groot(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(7);
        splayTree.add(9); //splaystap nodig want pad met 3 toppen

        Node newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);

        assertEquals(5, newRoot.left.key);
        assertEquals(9, newRoot.right.key);
        assertEquals(2, newRoot.left.left.key);

        splayTree.add(3); //splaystap
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(3, newRoot.left.key);
        assertEquals(9, newRoot.right.key);
        assertEquals(2, newRoot.left.left.key);
        assertEquals(5, newRoot.left.right.key);

        splayTree.add(6); //splaystap
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(3, newRoot.left.left.key);
        assertEquals(6, newRoot.left.right.key);
        assertEquals(9, newRoot.right.key);
        assertEquals(2, newRoot.left.left.left.key);
        assertEquals(null, newRoot.left.left.right);
        assertEquals(null, newRoot.left.right.left);
        assertEquals(null, newRoot.left.right.right);
        assertEquals(null, newRoot.right.left);
        assertEquals(null, newRoot.right.right);

        splayTree.add(1); // splaystap
        newRoot = splayTree.getRoot();
        assertEquals(5, newRoot.key);
        assertEquals(2, newRoot.left.key);
        assertEquals(7, newRoot.right.key);
        assertEquals(1, newRoot.left.left.key);
        assertEquals(3, newRoot.left.right.key);
        assertEquals(6, newRoot.right.left.key);
        assertEquals(9, newRoot.right.right.key);
        assertEquals(null, newRoot.left.left.left);
        assertEquals(null, newRoot.left.left.right);
        assertEquals(null, newRoot.left.right.left);
        assertEquals(null, newRoot.left.right.right);
        assertEquals(null, newRoot.right.left.left);
        assertEquals(null, newRoot.right.left.right);
        assertEquals(null, newRoot.right.right.left);
        assertEquals(null, newRoot.right.right.right);

        splayTree.add(4);
        splayTree.add(20);
        newRoot = splayTree.getRoot();
        assertEquals(5, newRoot.key);
        assertEquals(9, newRoot.right.key);
        assertEquals(20, newRoot.right.right.key);
        splayTree.add(30);
        splayTree.add(25);
        splayTree.add(35);
        splayTree.add(8);

        newRoot = splayTree.getRoot();
        assertEquals(20, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(30, newRoot.right.key);
        assertEquals(3, newRoot.left.left.key);
        assertEquals(8, newRoot.left.right.key);
        assertEquals(25, newRoot.right.left.key);
        assertEquals(35, newRoot.right.right.key);
        assertEquals(2, newRoot.left.left.left.key);
        assertEquals(4, newRoot.left.left.right.key);
        assertEquals(7, newRoot.left.right.left.key);
        assertEquals(9, newRoot.left.right.right.key);
        assertEquals(1, newRoot.left.left.left.left.key);
        assertEquals(6, newRoot.left.right.left.left.key);

    }

    @Test
    public void testSplayGrootte3Mogelijkheid1(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(10);
        splayTree.add(13);

        Node newRoot = splayTree.getRoot();
        assertEquals(10, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(13, newRoot.right.key);
        assertEquals(2, newRoot.left.left.key);
    }

    /**
     *  Een test voor het splayen van een pad met splaygrootte 3, mogelijkheid 1 -> pad met enkel linker kinderen
     */
    @Test
    public void testSplayGrootte3Mogelijkheid2(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(13);
        splayTree.add(6);
        splayTree.add(18);
        splayTree.add(1); // splaystap nodig want een pad met 3 toppen

        Node newRoot = splayTree.getRoot();
        assertEquals(6, newRoot.key);

        assertEquals(1, newRoot.left.key);
        assertEquals(13, newRoot.right.key);
        assertEquals(18, newRoot.right.right.key);
        assertEquals(null, newRoot.left.left);
        assertEquals(null, newRoot.left.right);
        assertEquals(null, newRoot.right.left);
        assertEquals(null, newRoot.right.right.left);
        assertEquals(null, newRoot.right.right.right);
    }

    /**
     *  Een test voor het splayen van een pad met splaygrootte 3, mogelijkheid 3
     */
    @Test
    public void testSplayGrootte3Mogelijkheid3(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(10);
        splayTree.add(5);
        splayTree.add(13);
        splayTree.add(7); // splaystap nodig, want een pad met 3 toppen

        Node newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);

        assertEquals(5, newRoot.left.key);
        assertEquals(10, newRoot.right.key);
        assertEquals(13, newRoot.right.right.key);
        assertEquals(null, newRoot.left.left);
        assertEquals(null, newRoot.left.right);
        assertEquals(null, newRoot.right.left);
        assertEquals(null, newRoot.right.right.left);
        assertEquals(null, newRoot.right.right.right);
    }

    /**
     *  Een test voor het splayen van een pad met splaygrootte 3, mogelijkheid 4
     */
    @Test
    public void testSplayGrootte3Mogelijkheid4(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(8);
        splayTree.add(16);
        splayTree.add(3);
        splayTree.add(10); //splaystap nodig, want een pad met 3 toppen

        Node newRoot = splayTree.getRoot();
        assertEquals(10, newRoot.key);

        assertEquals(8, newRoot.left.key);
        assertEquals(16, newRoot.right.key);
        assertEquals(3, newRoot.left.left.key);
    }

    @Test
    public void testSplayGrootte3BigTest(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(15);
        splayTree.add(10);
        splayTree.add(20);
        splayTree.add(5); // splaystap

        Node newRoot = splayTree.getRoot();
        assertEquals(10, newRoot.key);

        assertEquals(5, newRoot.left.key);
        assertEquals(15, newRoot.right.key);
        assertEquals(20, newRoot.right.right.key);
        assertEquals(null, newRoot.left.left);
        assertEquals(null, newRoot.left.right);
        assertEquals(null, newRoot.right.left);
        assertEquals(null, newRoot.right.right.right);
        assertEquals(null, newRoot.right.right.left);


        splayTree.add(25);//splaystap
        newRoot = splayTree.getRoot();
        assertEquals(10, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(20, newRoot.right.key);
        assertEquals(25, newRoot.right.right.key);
        assertEquals(15, newRoot.right.left.key);
        assertEquals(null, newRoot.left.left);
        assertEquals(null, newRoot.left.right);
        assertEquals(null, newRoot.right.left.right);
        assertEquals(null, newRoot.right.left.left);
        assertEquals(null, newRoot.right.right.right);
        assertEquals(null, newRoot.right.right.left);

        splayTree.add(18); //splaystap
        newRoot = splayTree.getRoot();
        assertEquals(10, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(18, newRoot.right.key);
        assertEquals(15, newRoot.right.left.key);
        assertEquals(20, newRoot.right.right.key);
        assertEquals(25, newRoot.right.right.right.key);
        assertEquals(null, newRoot.left.left);
        assertEquals(null, newRoot.left.right);
        assertEquals(null, newRoot.right.left.left);
        assertEquals(null, newRoot.right.left.right);
        assertEquals(null, newRoot.right.right.left);
        assertEquals(null, newRoot.right.right.right.right);
        assertEquals(null, newRoot.right.right.right.left);

        splayTree.add(30);
        newRoot = splayTree.getRoot();
        assertEquals(18, newRoot.key);
        assertEquals(10, newRoot.left.key);
        assertEquals(25, newRoot.right.key);
        assertEquals(5, newRoot.left.left.key);
        assertEquals(15, newRoot.left.right.key);
        assertEquals(20, newRoot.right.left.key);
        assertEquals(30, newRoot.right.right.key);
        assertEquals(null, newRoot.left.left.left);
        assertEquals(null, newRoot.left.left.right);
        assertEquals(null, newRoot.left.right.left);
        assertEquals(null, newRoot.left.right.right);
        assertEquals(null, newRoot.right.left.left);
        assertEquals(null, newRoot.right.left.right);
        assertEquals(null, newRoot.right.right.left);
        assertEquals(null, newRoot.right.right.right);

    }

    @Test
    public void testSPlayGrootte4(){
        SearchTree<Integer> splayTree = new SemiSplayTree(4);
        splayTree.add(10);
        splayTree.add(5);
        splayTree.add(16);
        splayTree.add(25);
        splayTree.add(1);
        splayTree.add(7);
        splayTree.add(12);
        splayTree.add(20); //splaystap nodig, want een pad met 4 toppen

        Node newRoot = splayTree.getRoot();

        assertEquals(20, newRoot.key);
        assertEquals(16, newRoot.left.key);
        assertEquals(25, newRoot.right.key);
        assertEquals(10, newRoot.left.left.key);
        assertEquals(12, newRoot.left.left.right.key);
        assertEquals(5, newRoot.left.left.left.key);
        assertEquals(1, newRoot.left.left.left.left.key);
        assertEquals(7, newRoot.left.left.left.right.key);
        assertEquals(null, newRoot.right.right);
        assertEquals(null, newRoot.right.left);
        assertEquals(null, newRoot.left.right);
        assertEquals(null, newRoot.left.left.right.left);
        assertEquals(null, newRoot.left.left.right.right);
        assertEquals(null, newRoot.left.left.left.left.left);
        assertEquals(null, newRoot.left.left.left.left.right);
        assertEquals(null, newRoot.left.left.left.right.left);
        assertEquals(null, newRoot.left.left.left.right.right);

        splayTree.add(6);
        newRoot = splayTree.getRoot();
        assertEquals(20, newRoot.key);
        assertEquals(16, newRoot.left.key);
        assertEquals(25, newRoot.right.key);
        assertEquals(7, newRoot.left.left.key);
        assertEquals(6, newRoot.left.left.left.key);
        assertEquals(5, newRoot.left.left.left.left.key);
        assertEquals(1, newRoot.left.left.left.left.left.key);
        assertEquals(10, newRoot.left.left.right.key);
        assertEquals(12, newRoot.left.left.right.right.key);
    }

    @Test
    public void testSplayGrootte5(){
        SearchTree<Integer> splayTree = new SemiSplayTree<Integer>(5);
        splayTree.add(18);
        splayTree.add(14);
        splayTree.add(50);
        splayTree.add(4);
        splayTree.add(70);
        splayTree.add(30);
        splayTree.add(60);
        splayTree.add(90);
        splayTree.add(55); //splaystap nodig want een pad met 5 toppen

        Node newRoot = splayTree.getRoot();
        assertEquals(55, newRoot.key);
        assertEquals(50, newRoot.left.key);
        assertEquals(70, newRoot.right.key);

        Node rigthChild = newRoot.right;
        Node leftChild = newRoot.left;
        assertEquals(18, leftChild.left.key);
        assertEquals(null, leftChild.right);
        assertEquals(14, leftChild.left.left.key);
        assertEquals(30, leftChild.left.right.key);
        assertEquals(60, rigthChild.left.key);
        assertEquals(90, rigthChild.right.key);
        assertEquals(null, rigthChild.right.right);
        assertEquals(null, rigthChild.right.left);
        assertEquals(null, rigthChild.left.left);
        assertEquals(null, rigthChild.left.right);

        splayTree.add(1);//splaystap
        newRoot = splayTree.getRoot();
        assertEquals(55, newRoot.key);
        assertEquals(70, newRoot.right.key);
        assertEquals(14, newRoot.left.key);
        assertEquals(4, newRoot.left.left.key);
        assertEquals(1, newRoot.left.left.left.key);
        assertEquals(18, newRoot.left.right.left.key);
        assertEquals(30, newRoot.left.right.left.right.key);

    }

    @Test
    public void testSplayGrootte6(){
        SearchTree<Integer> splayTree = new SemiSplayTree<Integer>(6);
        int i;
        for(i=1;i<=6;i++){
            splayTree.add(i);
        }

        Node newRoot = splayTree.getRoot();
        assertEquals(4, newRoot.key);
        assertEquals(2, newRoot.left.key);
        assertEquals(1, newRoot.left.left.key);
        assertEquals(3, newRoot.left.right.key);
        assertEquals(6, newRoot.right.key);
        assertEquals(5, newRoot.right.left.key);
    }

    @Test
    public void testSplayGrootte7(){
        SearchTree<Integer> splayTree = new SemiSplayTree(7);

        int start;
        for(start=1;start<=6;start++){
            splayTree.add(start);
        }
        assertEquals(1,splayTree.getRoot().key);
        assertEquals(4, splayTree.getRoot().right.right.right.key);

        splayTree.add(7); //splaystep
        Node newRoot = splayTree.getRoot();
        assertEquals(4, newRoot.key);
        assertEquals(2, newRoot.left.key);
        assertEquals(6, newRoot.right.key);
        assertEquals(1, newRoot.left.left.key);
        assertEquals(3, newRoot.left.right.key);
        assertEquals(5, newRoot.right.left.key);
        assertEquals(7, newRoot.right.right.key);

        
    }

    @Test
    public void testElementAlreadyInTree(){
        SearchTree<Integer> splayTree = new SemiSplayTree(4);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(7);

        assertFalse(splayTree.add(7));
        Node newRoot = splayTree.getRoot();
        assertEquals(5, newRoot.key);

        splayTree.add(15);
        splayTree.add(20);

        newRoot = splayTree.getRoot();
        assertEquals(15, newRoot.key);
        assertEquals(7, newRoot.left.key);
        assertEquals(20, newRoot.right.key);
        assertEquals(5, newRoot.left.left.key);
        assertEquals(2, newRoot.left.left.left.key);

        splayTree.add(2);
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(15, newRoot.right.key);
        assertEquals(2, newRoot.left.left.key);
        assertEquals(20, newRoot.right.right.key);
    }

    //------------------------------------------------------------------

    /**
     * The following tests are for checking the function remove node
     */
    @Test
    public void testRemove(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        //adding some elements to the tree
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(7);
        splayTree.add(6);
        Node newRoot = splayTree.getRoot();
        assertEquals(6, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(7,newRoot.right.key);
        splayTree.add(16);
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(6, newRoot.left.key);
        assertEquals(16, newRoot.right.key);

        splayTree.add(4);
        splayTree.add(3);

        newRoot = splayTree.getRoot();
        assertEquals(6, newRoot.key);
        assertEquals(3, newRoot.left.key);
        assertEquals(7, newRoot.right.key);
        assertEquals(2, newRoot.left.left.key);

        newRoot = splayTree.getRoot();
        assertTrue(splayTree.remove(3));
        assertEquals(4, newRoot.left.key);
        assertEquals(2, newRoot.left.left.key);
        assertEquals(5, newRoot.left.right.key);

        assertTrue(splayTree.remove(16));
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.right.key);
        assertEquals(null, newRoot.right.right);
        assertEquals(null, newRoot.right.left);


    }

    @Test
    public void testRemoveBig(){
        SearchTree<Integer> splayTree = new SemiSplayTree(4);
        splayTree.add(15);
        splayTree.add(5);
        splayTree.add(30);
        splayTree.add(18);
        splayTree.add(3);
        splayTree.add(40);
        splayTree.add(23); //semisplay step

        Node newRoot = splayTree.getRoot();
        assertEquals(23, newRoot.key);
        assertEquals(18, newRoot.left.key);
        assertEquals(30, newRoot.right.key);
        assertEquals(15, newRoot.left.left.key);
        assertEquals(5, newRoot.left.left.left.key);

        splayTree.add(28);
        splayTree.add(4); //semi splay step
        newRoot = splayTree.getRoot();
        assertEquals(5, newRoot.left.left.key);
        assertEquals(4, newRoot.left.left.left.key);
        assertEquals(15, newRoot.left.left.right.key);

        splayTree.add(45);
        splayTree.add(42);
        splayTree.add(44);

        assertTrue(splayTree.remove(3));
        newRoot = splayTree.getRoot();
        assertEquals(44, newRoot.key);
        assertEquals(40, newRoot.left.key);
        assertEquals(30, newRoot.left.left.key);
        assertEquals(18, newRoot.left.left.left.key);
        assertEquals(5, newRoot.left.left.left.left.key);
        assertEquals(4, newRoot.left.left.left.left.left.key);
        assertEquals(23, newRoot.left.left.left.right.key);
    }

    @Test
    public void testRemoveNullRoot(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        assertFalse(splayTree.remove(3));
    }

    @Test
    public void testRemoveOneNodeRoot(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(5);

        assertTrue(splayTree.contains(5));
        assertTrue(splayTree.remove(5));
        assertFalse(splayTree.contains(5));
        assertEquals(null, splayTree.getRoot());
    }

    @Test
    public void testRemoveRoot(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(5);
        splayTree.add(7);
        splayTree.add(1);

        assertTrue(splayTree.remove(5));

        Node newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(1, newRoot.left.key);
        assertEquals(null, newRoot.right);
        assertEquals(null, newRoot.left.left);
        assertEquals(null, newRoot.left.right);
    }

    @Test
    public void removeNonEx(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);

        splayTree.add(5);
        splayTree.add(3);
        splayTree.add(9);
        splayTree.add(13);
        splayTree.add(20);
        splayTree.add(30);

        Node newRoot = splayTree.getRoot();
        assertEquals(20, newRoot.key);

        assertFalse(splayTree.remove(1)); // this node is not part of the tree but there will be a semisplay step
        newRoot = splayTree.getRoot();
        assertEquals(13, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(20, newRoot.right.key);
        assertEquals(3, newRoot.left.left.key);
        assertEquals(9, newRoot.left.right.key);

    }

    @Test
    public void testRemoveRootLeftChild(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);

        splayTree.add(5);
        splayTree.add(2);

        assertTrue(splayTree.remove(5));

        Node newRoot = splayTree.getRoot();
        assertEquals(2, newRoot.key);
        assertEquals(null, newRoot.left);
        assertEquals(null, newRoot.right);

    }

    @Test
    public void testRemoveRootRightChild(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);

        splayTree.add(5);
        splayTree.add(7);

        assertTrue(splayTree.remove(5));
        assertTrue(splayTree.contains(7));
        assertFalse(splayTree.contains(5));

        Node newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(null, newRoot.left);
        assertEquals(null, newRoot.right);
    }

    @Test
    public void testRemoveNodeWithTwoChildren(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);

        splayTree.add(5);
        splayTree.add(7);
        splayTree.add(3);
        splayTree.add(1);
        splayTree.add(4);
        splayTree.add(6);
        splayTree.add(9);
        splayTree.add(8);

        assertTrue(splayTree.remove(8));
        Node newRoot = splayTree.getRoot();
        assertEquals(4, newRoot.key);
        assertEquals(9, newRoot.right.key);
        assertEquals(null, newRoot.right.right);
        assertEquals(7, newRoot.right.left.key);
    }

    @Test
    public void testRemoveAllOptionsChildren(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);

        splayTree.add(5);
        splayTree.add(7);
        splayTree.add(3);
        splayTree.add(1);
        splayTree.add(4);
        splayTree.add(6);
        splayTree.add(9);
        splayTree.add(8);

        // de boom na al de toevoeg bewerkingen
        Node newRoot = splayTree.getRoot();
        assertEquals(4, newRoot.key);
        //removing a leaf
        assertTrue(splayTree.remove(5));

        newRoot = splayTree.getRoot();
        assertEquals(4, newRoot.key);
        assertEquals(7, newRoot.right.key);
        assertEquals(6, newRoot.right.left.key);
        assertEquals(null, newRoot.right.left.left);
        assertEquals(null, newRoot.right.left.right);
        assertEquals(8, newRoot.right.right.key);

        //removing a node with one child on the right
        assertTrue(splayTree.remove(8));
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(4, newRoot.left.key);
        assertEquals(9, newRoot.right.key);
        assertEquals(6, newRoot.left.right.key);

        assertTrue(splayTree.remove(3));
        newRoot = splayTree.getRoot();
        assertEquals(4, newRoot.key);
        assertEquals(1, newRoot.left.key);
        assertEquals(7, newRoot.right.key);
        assertEquals(6, newRoot.right.left.key);
        assertEquals(9, newRoot.right.right.key);

        splayTree.add(30);
        splayTree.add(8);
        splayTree.add(22);
        assertTrue(splayTree.remove(4));
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(6, newRoot.left.key);
        assertEquals(8, newRoot.right.key);
        assertEquals(9, newRoot.right.right.left.key);
        assertEquals(22, newRoot.right.right.key);
        assertEquals(1, newRoot.left.left.key);
        assertEquals(30, newRoot.right.right.right.key);
    }

    @Test
    public void smallRemoveTest(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(7);
        splayTree.add(5);
        splayTree.add(10);
        splayTree.add(20);
        splayTree.add(9);
        splayTree.add(4);
        splayTree.remove(4);

        Node newRoot = splayTree.getRoot();
        assertEquals(9, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(7, newRoot.left.right.key);

        splayTree.add(30);
        splayTree.contains(10);
        assertTrue(splayTree.remove(5));

        newRoot = splayTree.getRoot();
        assertEquals(9, newRoot.key);
        assertEquals(7, newRoot.left.key);
        assertEquals(10, newRoot.right.key);
    }


    //------------------------------------------------------------------

    /**
     * The first small test for measuring the size of a tree
     */
    @Test
    public void testSize1(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(7);
        splayTree.add(6); //splay step, doesn't alter the size method
        splayTree.add(9); //splay step, doesn't alter the size method
        splayTree.add(10); //splay step, doesn't alter the size method

        assertEquals(6, splayTree.size());
        splayTree.remove(6);
        assertEquals(5, splayTree.size());
        splayTree.remove(5);
        assertEquals(4, splayTree.size());
        splayTree.remove(2);
        splayTree.remove(10);
        assertEquals(2, splayTree.size());

    }

    @Test
    public void testEmptyTree(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);

        assertEquals(0, splayTree.size());
    }

    @Test
    public void testOnlyRoot(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);

        splayTree.add(50);

        assertEquals(1, splayTree.size());

    }

    @Test
    public void biggerTestSizeOfTree(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        int index;
        for(index=0;index<=1000;index ++){
            splayTree.add(index);
        }

        assertEquals(1001, splayTree.size());
    }

    @Test
    public void nodeNotInTreeContainsTest(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(10);
        splayTree.add(7); //splay step becomes new root
        splayTree.add(6); //splay step
        Node newRoot = splayTree.getRoot();

        assertEquals(6, newRoot.key);
        splayTree.add(9);
        splayTree.add(8);

        assertEquals(8, newRoot.right.key);
        Boolean containsFalse = splayTree.contains(13); // !!! splay step

        assertEquals(containsFalse, false);
        assertEquals(9, newRoot.right.key);
        assertEquals(8, newRoot.right.left.key);
        assertEquals(10, newRoot.right.right.key);
        assertEquals(true, splayTree.contains(10)); //splay step

        splayTree.add(20);//splay step
        newRoot = splayTree.getRoot();
        assertEquals(10, newRoot.key);
        assertEquals(true, splayTree.contains(2)); // splay step
        newRoot = splayTree.getRoot();
        assertEquals(9, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(10, newRoot.right.key);

        assertEquals(true, splayTree.contains(7));
        assertEquals(7, splayTree.getRoot().key);
        assertEquals(5, splayTree.getRoot().left.key);
        assertEquals(9, splayTree.getRoot().right.key);
    }

    //------------------------------------------------------------------

    /**
     * The first small test for the function Contains, checks if an item is inside a tree
     */
    @Test
    public void testContains1(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(7);
        splayTree.add(6); //splaystep
        Node newRoot = splayTree.getRoot();
        assertEquals(6, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(7, newRoot.right.key);

        splayTree.add(9);//semisplay
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(6, newRoot.left.key);
        assertEquals(9, newRoot.right.key);

        assertTrue(splayTree.contains(2)); //splaystep
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(2, newRoot.left.left.key);

        assertTrue(splayTree.contains(2)); //splaystep
        newRoot = splayTree.getRoot();
        assertEquals(5, newRoot.key);
        assertEquals(2, newRoot.left.key);
        assertEquals(7, newRoot.right.key);

        assertFalse(splayTree.contains(8)); //element not in tree, but there is one semisplay step
        newRoot = splayTree.getRoot();
        assertEquals(7, newRoot.key);
        assertEquals(5, newRoot.left.key);
        assertEquals(9, newRoot.right.key);
        assertEquals(2, newRoot.left.left.key);
        assertEquals(6, newRoot.left.right.key);
    }

    //------------------------------------------------------------------

    /**
     *  the following tests are for the function Depth of a tree
     */
    @Test
    public void testDepth1(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3); // Voor het eerste deel van het project moet de splaystap nog niet werken
        //adding some elements to the tree
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(7);
        splayTree.add(6);
        splayTree.add(9);
        splayTree.add(10);

        assertEquals(4, splayTree.depth());
    }

    @Test
    public void testDepthLargeTree(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3); // Voor het eerste deel van het project moet de splaystap nog niet werken

        int i;
        for(i=1;i<1000;i++){
            splayTree.add(i);
        }

        assertEquals(999, splayTree.size());
    }

    @Test
    public void testEmptyTreeDepth(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3); // Voor het eerste deel van het project moet de splaystap nog niet werken

        assertEquals(-1, splayTree.depth());
    }

    @Test
    public void testRootTreeDepth(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3); // Voor het eerste deel van het project moet de splaystap nog niet werken

        splayTree.add(5);

        assertEquals(0, splayTree.depth());
    }


    @Test
    public void testDepth2(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3);

        splayTree.add(5);
        splayTree.add(9);

        assertEquals(1, splayTree.depth());

        splayTree.add(20); //splaystep
        splayTree.add(30); //splaystep
        splayTree.add(50); //splaystep

        assertEquals(3, splayTree.depth());

    }

    //------------------------------------------------------------------

    /**
     * A function to the test if the iterator works for a BST
     */
    @Test
    public void testIterator(){
        SearchTree<Integer> splayTree = new SemiSplayTree(3); // Voor het eerste deel van het project moet de splaystap nog niet werken
        //adding some elements to the tree
        splayTree.add(5);
        splayTree.add(2);
        splayTree.add(7);
        splayTree.add(6);
        splayTree.add(9);
        splayTree.add(10);

        Iterator it = splayTree.iterator();
        assertEquals(2, it.next());
        assertEquals(true, it.hasNext());
        assertEquals(5, it.next());
        assertEquals(6, it.next());
        assertEquals(true, it.hasNext());
        assertEquals(7, it.next());
        assertEquals(9, it.next());
        assertEquals(true, it.hasNext());
        assertEquals(10, it.next());
        assertEquals(false, it.hasNext());

    }
}
