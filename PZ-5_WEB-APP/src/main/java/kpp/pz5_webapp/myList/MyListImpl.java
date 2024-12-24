package kpp.pz5_webapp.myList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyListImpl<T> implements IMyList<T> {
    private static class Node<T> {
        T data;
        Node<T> prev, next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head, tail;
    private int size;

    @Override
    public void add(T e) {
        Node<T> newNode = new Node<>(e);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean remove(T o) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean contains(T o) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(o)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        Node<T> current = head;
        while (current != null) {
            sb.append("[").append(current.data).append("]");
            if (current.next != null) {
                sb.append(",\n");
            }
            current = current.next;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<T> {
        private Node<T> current = head;
        private Node<T> lastReturned = null;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturned = current;
            current = current.next;
            return lastReturned.data;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            if (lastReturned.prev != null) {
                lastReturned.prev.next = lastReturned.next;
            } else {
                head = lastReturned.next;
            }
            if (lastReturned.next != null) {
                lastReturned.next.prev = lastReturned.prev;
            } else {
                tail = lastReturned.prev;
            }
            lastReturned = null;
            size--;
        }
    }
}
