package com.company;

public class HashMap1 implements Map1 {
    private Node[] array;
    private int countOfElements;

    public HashMap1() {
        this.array = new Node[16];
        this.countOfElements = 0;
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
        int lock = 0;
        int i = 0;
        boolean str = false;
        while (lock != 1 && i < array.length - 1) {
            Node current = array[i];
            try {
                while (current.next != null) {
                    if (current.hash == key.hashCode()) {
                        str = true;
                        lock++;
                    }
                    current = current.next;
                }
                if (current.hash == key.hashCode()) {
                    str = true;
                    lock++;
                }
                i++;
            } catch (NullPointerException e) {
                i++;
            }
        }
        return str;
    }

    /**
     * @param value - associated element with key;
     * @return true or false. If the element is found - true, if no - false;
     */
    @Override
    public boolean containsValue(Object value) {
        int lock = 0;
        int i = 0;
        boolean str = false;
        while (lock != 1 && i < array.length - 1) {
            Node current = array[i];
            try {
                while (current.next != null) {
                    if (current.value == value) {
                        str = true;
                        lock++;
                    }
                    current = current.next;
                }
                if (current.value == value) {
                    str = true;
                    lock++;
                }
                i++;
            } catch (NullPointerException e) {
                i++;
            }
        }
        return str;
    }

    /**
     * @param key - unique element;
     * @return -
     */
    @Override
    public Object get(String key) {
        int lock = 0;
        int i = 0;
        String str = "This key isn't exist";
        while (lock != 1 && i < array.length - 1) {
            Node current = array[i];
            try {
                while (current.next != null) {
                    if (current.hash == key.hashCode()) {
                        str = current.key + " " + current.value;
                        lock++;
                    }
                    current = current.next;
                }
                if (current.hash == key.hashCode()) {
                    str = current.key + " " + current.value;
                    lock++;
                }
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
        int index = key.hashCode() % array.length;
        System.out.println(index);
        Node newNode = new Node(key, value, key.hashCode());
        Node current = array[index];
        if (array[index] == null) {
            array[index] = newNode;
            countOfElements++;
        } else {
            while (current.next != null) {
                if (current.hash == key.hashCode()) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            // last element, where element is equal null;
            checkLastElement(current, key, value, newNode);
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
     * @param current -
     * @param key     - unique element;
     * @param value   - associated element with key;
     * @param newNode -
     */
    private void checkLastElement(Node current, String key, Object value, Node newNode) {
        if (current.hash == key.hashCode()) {
            current.value = value;
        } else {
            current.next = newNode;
            countOfElements++;
        }
    }
}
