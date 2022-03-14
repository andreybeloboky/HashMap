package com.company;

import java.util.Arrays;
import java.util.Objects;

public class HashMap1 implements Map1 {
    private Node[] array = new Node[16];
    private int countOfElements;
    private int lock;
    private float loadFactory = 0.75f;

    public HashMap1() {
    }

    public HashMap1(int capacity, int loadFactory) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    capacity);
        } else {
            this.array = new Node[capacity];
        }
        if (loadFactory < 0) {
            throw new IllegalArgumentException("Illegal initial load factor: " +
                    capacity);
        } else {
            this.loadFactory = loadFactory;
        }
    }

    private static class Node {
        private final int hash;
        private Object value;
        private Object key;
        private Node next;

        private Node(Object key, Object value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = null;
        }
    }

    @Override
    public int size() {
        return countOfElements;
    }

    /**
     * @param key - unique element;
     * @return true or false. If the element key is found - true, if no - false;
     */
    @Override
    public boolean containsKey(String key) {
        lock = 0;
        int i = 0;
        boolean containKey = false;
        while (lock != 1 && i < array.length - 1) {
            Node current = array[i];
            try {
                while (current.next != null) {
                    containKey = checkContainKey(current, containKey, key);
                    current = current.next;
                }
                containKey = checkContainKey(current, containKey, key);
                i++;
            } catch (NullPointerException e) {
                i++;
            }
        }
        return containKey;
    }

    /**
     * @param value - associated element with key;
     * @return true or false. If the element is found - true, if no - false;
     */
    @Override
    public boolean containsValue(Object value) {
        lock = 0;
        int i = 0;
        boolean containValue = false;
        while (lock != 1 && i < array.length - 1) {
            Node current = array[i];
            try {
                while (current.next != null) {
                    containValue = checkContainValue(current, containValue, value);
                    current = current.next;
                }
                containValue = checkContainValue(current, containValue, value);
                i++;
            } catch (NullPointerException e) {
                i++;
            }
        }
        return containValue;
    }

    /**
     * @param key - unique element;
     * @return - str (string) who features, whether or not there is data;
     */
    @Override
    public Object get(String key) {
        lock = 0;
        int i = 0;
        String str = "This key isn't exist";
        while (lock != 1 && i < array.length - 1) {
            Node current = array[i];
            try {
                while (current.next != null) {
                    str = checkGet(current, key, str);
                    current = current.next;
                }
                str = checkGet(current, key, str);
                i++;
            } catch (NullPointerException e) {
                i++;
            }
        }
        return str;
    }

    /**
     * @param key   - unique element;
     * @param value - associated element with key;
     */
    @Override
    public void put(String key, Object value) {
        if (loadFactory * array.length > countOfElements) {
            int index = key.hashCode() % array.length;
            Node newNode = new Node(key, value, key.hashCode());
            Node current = array[index];
            if (array[index] == null) {
                array[index] = newNode;
                countOfElements++;
            } else {
                while (current.next != null) {
                    if (current.hash == key.hashCode()) {
                        if (key.equals(current.key)) {
                            current.value = value;
                            return;
                        }
                    }
                    current = current.next;
                }
                // last element, where element is equal null;
                checkLastElement(current, key, value, newNode);
            }
        } else {
            Node[] reserveArray = this.array;
            this.array = new Node[reserveArray.length * 2];
            System.arraycopy(reserveArray, 0, this.array, 0, reserveArray.length);
            put(key, value);
        }
    }

    /**
     * @param key - unique element;
     */
    @Override
    public void remove(String key) {
        int lock = 0;
        int i = 0;
        while (lock != 1 && i < array.length - 1) {
            Node current = array[i];
            Node previous = null;
            try {
                while (current.next != null) {
                    if (current.hash == key.hashCode()) {
                        if (current == array[i]) {
                            array[i] = current.next;
                            lock++;
                        } else {
                            if (previous != null) {
                                previous.next = current.next;
                                countOfElements--;
                                lock++;
                            }
                        }
                    }
                    previous = current;
                    current = current.next;
                }
                if (current.hash == key.hashCode()) {
                    if (previous != null) {
                        previous.next = null;
                    }
                    countOfElements--;
                    lock++;
                }
                i++;
            } catch (NullPointerException e) {
                i++;
            }
        }
    }

    /**
     * @param current - current Node; LinkedList
     * @param key     - unique element;
     * @param value   - associated element with key;
     * @param newNode - new Node; LinkedList
     */
    private void checkLastElement(Node current, String key, Object value, Node newNode) {
        if (current.hash == key.hashCode()) {
            if (key.equals(current.key)) {
                current.value = value;
            }
        } else {
            current.next = newNode;
            countOfElements++;
        }
    }

    /**
     * @param current - current Node;
     * @param key     - key of data;
     * @param str     - string default;
     * @return string, default - if data don't find, key and value - if data is true;
     */
    private String checkGet(Node current, String key, String str) {
        if (current.hash == key.hashCode()) {
            str = current.key + " " + current.value;
            lock++;
        }
        return str;
    }

    /**
     * @param current      - current Node;
     * @param containValue - false (default);
     * @param value        - value of data;
     * @return contain true or false
     */
    private boolean checkContainValue(Node current, boolean containValue, Object value) {
        if (current.value == value) {
            containValue = true;
            lock++;
        }
        return containValue;
    }

    /**
     * @param current    - current Node;
     * @param containKey - false;
     * @param key        - key of data;
     * @return contain true or false
     */
    private boolean checkContainKey(Node current, boolean containKey, String key) {
        if (current.hash == key.hashCode()) {
            containKey = true;
            lock++;
        }
        return containKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashMap1 hashMap1 = (HashMap1) o;
        return countOfElements == hashMap1.countOfElements && lock == hashMap1.lock && Float.compare(hashMap1.loadFactory, loadFactory) == 0 && Arrays.equals(array, hashMap1.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(countOfElements, lock, loadFactory);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
