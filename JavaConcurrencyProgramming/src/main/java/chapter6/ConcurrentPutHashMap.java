package chapter6;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class ConcurrentPutHashMap {

    public static void main(String args[]) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    },"ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();

        Set<String> keySet=map.keySet();
        int i=0;
        for(String key:keySet){
            System.out.println(map.get(key));
            i++;
        }
    }

}
