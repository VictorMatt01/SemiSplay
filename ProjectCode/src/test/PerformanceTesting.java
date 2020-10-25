package test;

import org.junit.Test;
import semisplay.SearchTree;
import semisplay.SemiSplayTree;

import javax.swing.event.DocumentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PerformanceTesting {

    @Test
    public void increasingListPerformanceTest(){
        System.out.println("increasingListPerformanceTest");
        int i;
        int count;

        Double[] times = new Double[5];
        for(i=0;i<5;i++){
            times[i] = 0.0;
        }

        for(count=0;count<50;count++){
            times[0] += this.hulpIncreasingTest(3);

            times[1] += this.hulpIncreasingTest(4);

            times[2] += this.hulpIncreasingTest(5);

            times[3] += this.hulpIncreasingTest(7);

            times[4] += this.hulpIncreasingTest(15);
        }

        System.out.println("Average SplaySize 3: " + times[0]/50);
        System.out.println("Average SplaySize 4: " + times[1]/50);
        System.out.println("Average SplaySize 5: " + times[2]/50);
        System.out.println("Average SplaySize 7: " + times[3]/50);
        System.out.println("Average SplaySize 15: " + times[4]/50);

    }

    public Double hulpIncreasingTest(int splaySize){
        SearchTree<Integer> splayTree = new SemiSplayTree(splaySize);
        Timer timer = new Timer();
        timer.start();
        int i;
        for(i=1;i<=10000;i++){
            splayTree.add(i);
        }
        for(i=10000;i>=1;i--){
            splayTree.remove(i);
        }
        timer.end();
        return timer.delta();
    }

    public Double hulpDecreasingTest(int splaySize){
        SearchTree<Integer> splayTree = new SemiSplayTree(splaySize);
        int i;
        Timer timer = new Timer();
        timer.start();
        for(i=10000;i>=1;i--){
            splayTree.add(i);
        }
        for(i=1;i<=10000;i++){
            splayTree.remove(i);
        }
        timer.end();
        return timer.delta();
    }

    @Test
    public void decreasingListPerformanceTest(){
        System.out.println("decreasingListPerformanceTest");
        int i;
        int count;

        Double[] times = new Double[5];
        for(i=0;i<5;i++){
            times[i] = 0.0;
        }

        for(count=0;count<50;count++){
            times[0] += this.hulpDecreasingTest(3);

            times[1] += this.hulpDecreasingTest(4);

            times[2] += this.hulpDecreasingTest(5);

            times[3] += this.hulpDecreasingTest(7);

            times[4] += this.hulpDecreasingTest(15);
        }

        System.out.println("Average SplaySize 3: " + times[0]/50);
        System.out.println("Average SplaySize 4: " + times[1]/50);
        System.out.println("Average SplaySize 5: " + times[2]/50);
        System.out.println("Average SplaySize 7: " + times[3]/50);
        System.out.println("Average SplaySize 15: " + times[4]/50);
    }

    public Double hulpRandomTest(int splaySize, List<Integer> randomList){
        SearchTree<Integer> splayTree = new SemiSplayTree(splaySize);

        Timer timer = new Timer();
        timer.start();
        int index;
        for(index=0;index<randomList.size();index++){
            splayTree.add(randomList.get(index));
        }
        for(index=0;index<randomList.size()-5;index+=5){
            splayTree.contains(randomList.get(index));
        }
        for(index=2;index<randomList.size()-7;index+=5){
            splayTree.remove(randomList.get(index));
        }
        timer.end();
        return timer.delta();
    }

    @Test
    public void randomNumberListPerformanceTest(){
        System.out.println("randomNumberListPerformanceTest");
        List<Integer> randomList = new ArrayList<Integer>();
        int i;
        Random random = new Random();
        for(i=1;i<=10000;i++){
            int randomInt = random.nextInt(20000);

            while(randomList.contains(randomInt)){
                randomInt = random.nextInt(20000);
            }

            randomList.add(randomInt);
        }

        System.out.println("size:" + randomList.size());

        Double[] times = new Double[5];
        for(i=0;i<5;i++){
            times[i] = 0.0;
        }

        int count;
        for(count=0;count<50;count++){
            times[0] += this.hulpRandomTest(3, randomList);

            times[1] += this.hulpRandomTest(4, randomList);

            times[2] += this.hulpRandomTest(5, randomList);

            times[3] += this.hulpRandomTest(7, randomList);

            times[4] += this.hulpRandomTest(15, randomList);
        }

        System.out.println("Average SplaySize 3: " + times[0]/50);
        System.out.println("Average SplaySize 4: " + times[1]/50);
        System.out.println("Average SplaySize 5: " + times[2]/50);
        System.out.println("Average SplaySize 7: " + times[3]/50);
        System.out.println("Average SplaySize 15: " + times[4]/50);
    }

    @Test
    public void randomNumberListPerformanceTest2(){
        System.out.println("randomNumberListPerformanceTest 2");

        List<Integer> randomList = new ArrayList<Integer>();
        int i;
        Random random = new Random();
        for(i=1;i<=10000;i++){
            int randomInt = random.nextInt(20000);

            while(randomList.contains(randomInt)){
                randomInt = random.nextInt(20000);
            }
            randomList.add(randomInt);
        }

        int[] indexArray = new int[250];
        for(i=0;i<250;i++){
            int randomIndex = random.nextInt(10000);
            indexArray[i] = randomIndex;
        }

        Double[] times = new Double[5];
        for(i=0;i<5;i++){
            times[i] = 0.0;
        }

        int count;
        for(count=0;count<50;count++){
            times[0] += this.hulpRandomTest2(3, randomList, indexArray);

            times[1] += this.hulpRandomTest2(4, randomList, indexArray);

            times[2] += this.hulpRandomTest2(5, randomList, indexArray);

            times[3] += this.hulpRandomTest2(7, randomList, indexArray);

            times[4] += this.hulpRandomTest2(15, randomList, indexArray);
        }

        System.out.println("Average SplaySize 3: " + times[0]/50);
        System.out.println("Average SplaySize 4: " + times[1]/50);
        System.out.println("Average SplaySize 5: " + times[2]/50);
        System.out.println("Average SplaySize 7: " + times[3]/50);
        System.out.println("Average SplaySize 15: " + times[4]/50);

    }


    public Double hulpRandomTest2(int splaySize, List<Integer> list, int[] indexArray){
        SearchTree<Integer> splayTree = new SemiSplayTree(splaySize);

        Timer timer = new Timer();
        timer.start();
        int index;
        for(index=0;index<list.size();index++){
            splayTree.add(list.get(index));
        }

        int i;
        Random random = new Random();
        for(i=0;i<1000;i++){
            int indexIn = random.nextInt(250);
            splayTree.contains(indexArray[indexIn]);
        }

        for(index=2;index<list.size()-7;index+=5){
            splayTree.remove(list.get(index));
        }
        timer.end();
        return timer.delta();
    }
}


