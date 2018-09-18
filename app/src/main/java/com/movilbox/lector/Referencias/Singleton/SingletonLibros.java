package com.movilbox.lector.Referencias.Singleton;

/**
 * Created by cathe on 7/11/2017.
 */

class SingletonLibros {
    private static final SingletonLibros ourInstance = new SingletonLibros();

    static SingletonLibros getInstance() {
        return ourInstance;
    }

    private SingletonLibros() {
    }
}
