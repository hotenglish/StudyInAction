package io.github.viscent.mtpattern.ch4.gs;

import java.util.concurrent.Callable;

public abstract class GuardedAction<V> implements Callable<V> {

    protected final Predicate guard;

    public GuardedAction(Predicate guard){
        this.guard=guard;
    }

}
