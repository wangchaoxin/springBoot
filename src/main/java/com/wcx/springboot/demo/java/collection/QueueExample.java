package com.wcx.springboot.demo.java.collection;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class QueueExample {
    public static void printQ(Queue queue) {
        /*Both peek( ) and element( ) return the head of the queue without
        removing it, but peek( ) returns null if the queue is empty and element( ) throws
        NoSuchElementException. Both poll( ) and remove( ) remove and return the head of
        the queue, but poll( ) returns null if the queue is empty, while remove( ) throws
        NoSuchElementException.*/
        while(queue.peek() != null)
            System.out.print(queue.remove() + " ");
        System.out.println();
    }
    public static void main(String[] args) {
       /* LinkedList has methods to support queue behavior and it implements the Queue interface,
        so a LinkedList can be used as a Queue implementation. By upcasting a LinkedList to a
        Queue, this example uses the Queuespecific methods in the Queue interface:*/
        //转换成queue使用
        Queue<Integer> queue = new LinkedList<Integer>();
        Random rand = new Random(47);
        for (int i = 0; i < 10; i++) {
            //offer( ) is one of the Queue-specific methods; it inserts an element at the tail of the queue if
            //it can, or returns false
            queue.offer(rand.nextInt(i + 10));
        }
        printQ(queue);
        Queue<Character> qc = new LinkedList<Character>();
        for(char c : "Brontosaurus".toCharArray())
            qc.offer(c);
        printQ(qc);
    }
}
