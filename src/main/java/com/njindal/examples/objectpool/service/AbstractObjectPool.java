package com.njindal.examples.objectpool.service;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractObjectPool<T> implements ObjectPool<T> {
    protected final int INITIAL_COUNT = 1;
    protected final int MAX_COUNT = 10;
    protected Set<T> initializedObjects = new HashSet<>();
    private int count = 1;

    protected void init() {
        initializedObjects.add(getInstance());
    }

    @Override
    public T get() {
        synchronized (this) {
            if (count > MAX_COUNT)
                throw new RuntimeException("Max limit reached");
            else if (initializedObjects.size() == count) {
                count++;
                return initializedObjects.stream().findFirst().get();
            }
            T newObj = getInstance();
            initializedObjects.add(newObj);
            count++;
            return newObj;

        }
    }

    @Override
    public void release(T obj) {
        synchronized (this) {
            if (!initializedObjects.contains(obj) && !initializedObjects.isEmpty())
                throw new RuntimeException("Unknown object found");
            initializedObjects.remove(obj);
            count--;
            if (initializedObjects.isEmpty()) {
                initializedObjects.add(getInstance());
            }

        }

    }

    @Override
    public int count() {
        return count-1;
    }

    protected abstract T getInstance();


}
