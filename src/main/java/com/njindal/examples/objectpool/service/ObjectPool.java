package com.njindal.examples.objectpool.service;

public interface ObjectPool<E> {
    E get();
    void release(E obj);
    int count();
}
