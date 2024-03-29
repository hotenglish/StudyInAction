package io.github.viscent.mtpattern.ch4.gs;

import java.util.concurrent.Callable;

public interface Blocker  {

    <V> V callWithGuard(GuardedAction<V> guardedAction) throws Exception;

    void signalAfter(Callable<Boolean> stateOperation) throws Exception;

    void signal() throws InterruptedException;

    void broadcastAfter(Callable<Boolean> stateOperation) throws Exception;
}
