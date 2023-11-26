package dsa.contacts.ds;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayList<E> implements List<E> {
    private E[] arreglo;
    private int n, capacidad;

    public ArrayList() {
        capacidad = 10;
        arreglo = (E[]) new Object[capacidad];
        n = 0;
    }

    public ArrayList(int capacidad) {
        this.capacidad = capacidad;
        arreglo = (E[]) new Object[capacidad];
        n = 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public boolean contains(Object o) {
        int i = 0;
        while (i < n && !arreglo[i].equals(o)) {
            i++;
        }
        return i < n;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] arregloNuevo = new Object[n];
        for (int i = 0; i < n; i++) {
            arregloNuevo[i] = arreglo[i];
        }
        return arregloNuevo;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < n) {
            a = (T[]) new Object[n];
        }
        for (int i = 0; i < n; i++) {
            a[i] = (T) arreglo[i];
        }
        return a;
    }

    @Override
    public boolean add(E e) {
        add(n, e);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int i = 0;
        while (i < n && !arreglo[i].equals(o)) {
            i++;
        }
        if (i == n) return false;
        remove(i);
        return true;
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
        if (index < 0 || index >= n) throw new IndexOutOfBoundsException("Index está fuera de rango");
        return arreglo[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index >= n) throw new IndexOutOfBoundsException("Index está fuera de rango");
        E replacedElement = arreglo[index];
        arreglo[index] = element;
        return replacedElement;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index >= capacidad) throw new IndexOutOfBoundsException(index);
        // i esta entre 0 y indice final
        if (n == capacidad) {
            growArray();
        };
        // i esta entre 0 y capacidad - 1

        for (int i = n; i > index; i--) {
            arreglo[i] = arreglo[i-1];
        }
        arreglo[index] = (E) element;
        n++;
    }

    private void growArray() {
        capacidad = capacidad * 2;
        E[] arregloNuevo = (E[]) new Object[capacidad];
        for (int i = 0; i < n; i++) {
            arregloNuevo[i] = arreglo[i];
        }
        arreglo = arregloNuevo;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= n) throw new IndexOutOfBoundsException("Index está fuera de rango");

        E removedElement = arreglo[index];

        for (int i = index; i < n-1; i++) {
            arreglo[i] = arreglo[i+1];
        }

        arreglo[n - 1] = null;

        n--;

        return removedElement;
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
        for (E element: arreglo) {
            if (element != null) {
                s += element.toString();
            }
        }
        return s;
    }

    public ArrayList<Integer> indexOfAll(E obj) {
        ArrayList<Integer> indices = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (arreglo[i].equals(obj)) {
                indices.add(i);
            }
        }

        return indices;
    }

    private class ArrayListIterator implements Iterator<E> {
        private int i;

        public ArrayListIterator() {
            i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public E next() {
            E element = arreglo[i];
            i++;
            return element;
        }

    }
}
