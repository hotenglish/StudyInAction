package lambdasinaction.chap11;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncShopClient {

    public static void main(String args[]) {
        AsyncShop asyncShop = new AsyncShop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = asyncShop.getPrice("myPhone");
        long invocationTime = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        try {
            System.out.println(futurePrice.get());
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        long retrivalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrivalTime + " msecs");
    }

}
