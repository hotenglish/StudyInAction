package scjp_book;

import java.util.*;

public class Example_9_80 {

    public static void main(String[] args) {
        Queue<String> q = new LinkedList<String>();
        q.add("Veronica");
        q.add("Wallace");
        q.add("Duncan");
        showAll(q);
    }

    public static void showAll(Queue q) {
        q.add(new Integer(42));
        while (!q.isEmpty()) {
            System.out.print(q.remove() + " ");
        }
    }
}
