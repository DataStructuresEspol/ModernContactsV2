package dsa.contacts.ds;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class DoublyCircular<E> implements List<E>, Iterable<E> {
    Node<E> head;
    Node<E> tail;
    int n;

    private class Node<E> {
        E content;
        Node<E> next;
        Node<E> prev;

        public Node(E e) {
            content = e;
            next = null;
            prev = null;
        }
    }

    public DoublyCircular() {
        head = null;
        tail = null;
    }

    @Override
    public Iterator<E> iterator() {
        return new CDLLIterator();
    }

    public Iterator<E> reverseIterator() {
        return new ReverseIterator();
    }

    private class CDLLIterator implements Iterator<E> {
        int cursor = 0;
        Node<E> ref = head;

        @Override
        public boolean hasNext() {
            return !(cursor == n);
        }

        @Override
        public E next() {
            E val = ref.content;
            ref = ref.next;
            cursor += 1;
            return val;
        }
    }

    private class ReverseIterator implements Iterator<E> {
        int cursor = n;
        Node<E> ref = tail;

        @Override
        public boolean hasNext() {
            return !(cursor == 0);
        }

        @Override
        public E next() {
            E val = ref.content;
            ref = ref.prev;
            cursor -= 1;
            return val;
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean add(E e) {
        Node<E> newNode = new Node(e);

        if (!isEmpty()) {
            newNode.next = head;
            newNode.prev = tail;
            tail.next = newNode;
        } else {
            head = newNode;
            tail = newNode;
            head.next = tail;
            tail.prev = head;
        }
        tail = newNode;
        n++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E get(int index) {
        checkIndexRemove(index);
        int i = 0;
        Node<E> ref = head;
        while (i != index) {
            ref = ref.next;
            i++;
        }
        return ref.content;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(int index, E element) {
        checkIndexAdd(index);

        if ((index == 0 && isEmpty()) || index == n) {
            add(element); // add end or no elements
        } else if (index == 0) { // add at beginnig
            Node<E> newNode = new Node(element);
            newNode.next = head;
            newNode.prev = tail;
            head.prev = newNode;
            tail.next = newNode;
            head = newNode;
            n++;
        } else {
            Node<E> newNode = new Node(element); // add between
            int i = 0;
            Node<E> ref = head;
            while (i < index - 1) {
                ref = ref.next;
                i++;
            }
            newNode.next = ref.next;
            newNode.prev = ref;
            ref.next.prev = newNode;
            ref.next = newNode;
            n++;
        }
    }

    @Override
    public E remove(int index) {
        checkIndexRemove(index);

        Node<E> toBeRemoved;

        if (index == 0) {// remove head
            toBeRemoved = head;
            head = head.next;
            head.prev = tail;
            tail.next = head;
        } else if (index == n - 1) { // remove tail
            toBeRemoved = tail;
            tail = tail.prev;
            tail.next = head;
            head.prev = tail;
        } else { // remove between
            int i = 0;
            Node<E> ref = head;
            while (i < index) {
                ref = ref.next;
                i++;
            }
            toBeRemoved = ref;
            ref.next.prev = ref.prev;
            ref.prev.next = ref.next;
        }
        n--;
        return toBeRemoved.content;
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        String s = "";

        Node<E> ref = head;
        int i = 0;
        while (i < n) {
            s += ref.content.toString() + " ";
            ref = ref.next;
            i++;
        }
        return s;
    }

    public void checkIndexAdd(int index) {
        if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException(n);
        }
    }

    public void checkIndexRemove(int index) {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException(n);
        }
    }

    public E find(Comparator<E> cmp, E obj) {
        for (E element: this) {
            if (cmp.compare(element, obj) == 0) {
                return element;
            }
        }
        return null;
    }

    public DoublyCircular<E> findAll(Comparator<E> cmp, E obj) {
        DoublyCircular<E> found = new DoublyCircular<>();

        for (E element: this) {
            if (cmp.compare(element, obj) == 0) {
                found.add(element);
            }
        }

        return found;
    }

    public DoublyCircular<E> reverse() {
        if (isEmpty()) {
            return null;
        }

        DoublyCircular<E> reversed = new DoublyCircular<>();

        Node<E> last = tail;
        int i = 0;
        while (i < n) {
            reversed.add(last.content);
            last = last.next;
            i++;
        }

        return reversed;
    }
}
