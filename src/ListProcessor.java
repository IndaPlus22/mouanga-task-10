import org.junit.Test;
import org.junit.Before;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.*;

public class ListProcessor {
    
    /** 
    * Returns an array with integers from "from" to "to".
    * <p>
    * If "from" and "to" are the same, return an empty list.
    * @param from The number we start with
    * @param to The number we end at (and do not include)
    * @return An array in with the numbers from..to.
    */
    public int[] arraySequence(int from, int to) {
        // check special cases first
        if(from > to) {
            throw new IllegalArgumentException("Start number cannot be lower than end number.");
        } else if (from == to) {
            return new int[0];
        }

        int length = to - from;
        int[] list = new int[length];
        for(int i = 0; i < length; i++) {
            list[i] = from + i;
        }
        return list;
    }

    /** 
    * Returns a list with integers from "from" to "to".
    * <p>
    * If "from" and "to" are the same, return an empty list.
    * @param from The number we start with
    * @param to The number we end at (and do not include)
    * @return A list in with the numbers from..to.
    */
    public List<Integer> listSequence(int from, int to) {
        // check special cases first
        List<Integer> list = new ArrayList<Integer>();
        if(from > to) {
            throw new IllegalArgumentException("Start number cannot be lower than end number.");
        } else if (from == to) {
            return list;
        }

        int length = to - from;
        for(int i = 0; i < length; i++) {
            list.add(from + i);
        }
        return list;
    }

    /** 
    * Returns a shuffled version of the list.
    * @param list The list we're making a shuffled version of.
    * @return A shuffled list.
    */
    public int[] shuffled(int[] numbers) {
        int length = numbers.length;
        int[] shuffledNumbers = Arrays.copyOfRange(numbers, 0, length);

        // the actual shuffling part
        Random rand = new Random();

        for(int i = 0; i < length; i++) {
            swap(
                shuffledNumbers,
                rand.nextInt(length),
                rand.nextInt(length)
            );
        }
        return shuffledNumbers;
    }

    private static void swap(int[] list, int a, int b) {
        int temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }

/** 
    * Returns a shuffled version of the list.
    * @param list The list we're making a shuffled version of.
    * @return A shuffled list.
    */
    public List<Integer> shuffled(List<Integer> numbers) {
        int length = numbers.size();
        List<Integer> shuffledNumbers = new ArrayList<Integer>();
        shuffledNumbers.addAll(numbers);

        // the actual shuffling part
        Random rand = new Random();

        for(int i = 0; i < length; i++) {
            swapList(
                shuffledNumbers,
                shuffledNumbers.get(rand.nextInt(length)),
                shuffledNumbers.get(rand.nextInt(length))
            );
        }
        return shuffledNumbers;

    }

    private static void swapList(List<Integer> list, int a, int b) {
        int aPos = list.indexOf(a);
        int bPos = list.indexOf(b);
        list.set(aPos, b);
        list.set(bPos, a);
    }

    /*
    public static void main(String[] args) {
        ListProcessor listProcessor = new ListProcessor();
        int[] b = listProcessor.arraySequence(0, 4);
        System.out.println(b[2]);
        List<Integer> c = listProcessor.listSequence(0, 4);
        System.out.println("zyx");
        c.forEach(System.out::println);
        System.out.println("zyx");
        List<Integer> d = listProcessor.shuffled(c);
        d.forEach(System.out::println);
        int[] e = listProcessor.shuffled(b);
        System.out.println("zyx: Now doing the array");
        System.out.println(b[2]);
        System.out.println(listProcessor.sumRecursive(b));
    }
    */

/** 
    * Returns the sum of the list.
    * @param numbers The list we're summating.
    * @return The sum of the list.
    */
    public int sumIterative (int[] numbers) {
        int sum = 0;
        int length = numbers.length;
        if(length == 0) {
            return 0;
        }
        for(int i = 0; i < length; i++) {
            sum += numbers[i];
        }
        return sum;
    }


/** 
    * Returns the sum of the list.
    * @param numbers The list we're summating.
    * @return The sum of the list.
    */
    public int sumIterative (List<Integer> numbers) {
        int sum = 0;
        int length = numbers.size();
        if(length == 0) {
            return 0;
        }
        for(int i = 0; i < length; i++) {
            sum += numbers.get(i);
        }
        return sum;
    }

    /**
    * Takes the sum of an array list, but calculates it recursively instead of iteratively.
    */
    public int sumRecursive (int[] numbers) {
        
        if (numbers.length == 0) {
            return 0; // we are done
        } else if (numbers.length == 1){
            return numbers[0];
        } else {
            int[] a = SumRecursive2(numbers, 0);
            return a[0];
        }
    }


    public static int[] SumRecursive2(int[] numbers, int cumSum) {
         if(numbers.length <= 1) {
            int[] cumList = new int[1];
            cumSum += numbers[0];
            cumList[0] = cumSum;
            return cumList;
         } else {
            cumSum += numbers[0];
            int[] a = SumRecursive2(Arrays.copyOfRange(numbers, 1, numbers.length), cumSum);
            return a;
         }
    }

    /**
    * Takes the sum of a list, but calculates it recursively instead of iteratively.
    */
    public int sumRecursive (List<Integer> numbers) {
        List<Integer> numCopy = new ArrayList<Integer>();
        numCopy.addAll(numbers);
        if (numCopy.size() == 0) {
            return 0; // we are done
        } else if (numCopy.size() == 1){
            return numCopy.get(0);
        } else {
        return SumRecursive3(numCopy, 0).get(0);
        }
    }


    public static List<Integer> SumRecursive3(List<Integer> numbers, int cumSum) {
        List<Integer> copy = new ArrayList<Integer>();
        copy.addAll(numbers);
        if (copy.size() == 0) {
            List<Integer> cumList = new ArrayList<Integer>();
            return cumList;
        }
         else if(copy.size() == 1) {
            List<Integer> cumList = new ArrayList<Integer>(1);
            cumSum += copy.get(0);
            cumList.add(cumSum);
            return cumList;
         } else {
            cumSum += copy.get(0);
            List<Integer> nextList = new ArrayList<Integer>();
            nextList.addAll(copy);
            if(copy.size() > 1) {
                nextList.remove(0);
            }
            return SumRecursive3(nextList, cumSum);
         }
         
          
    }
    
}




