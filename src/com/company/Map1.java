package com.company;

public interface Map1 {
    int size();

    boolean containsKey(String key);

    boolean containsValue(Object value);

    Object get(String key);

    void put(String key, Object value);

    void remove(String key);

}
