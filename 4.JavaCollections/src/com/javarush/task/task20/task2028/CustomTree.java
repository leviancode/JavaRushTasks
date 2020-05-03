package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;

/*
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    private Queue<Entry<String>> entryQueue;

    public CustomTree() {
        root = new Entry<>("0");
        entryQueue = new LinkedList<>();
        entryQueue.add(root);
    }


    @Override
    public boolean add(String s) {
        Entry<String> newEntry = new Entry<>(s);
        if (tryAdd(newEntry)) {
            entryQueue.add(newEntry);
        }else {
            restoreAbilityToAdd();
            add(s);
        }
        return true;
    }

    private boolean tryAdd(Entry<String> newEntry){
        for (Entry<String> parent : entryQueue){
            if (parent.isAvailableToAddChildren()){
                linkEntry(parent, newEntry);
                return true;
            }
        }
        return false;
    }

    private void linkEntry(Entry<String> parent, Entry<String> newEntry) {
        newEntry.parent = parent;
        if (parent.availableToAddLeftChildren){
            parent.leftChild = newEntry;
            parent.availableToAddLeftChildren = false;
        }else{
            parent.rightChild = newEntry;
            parent.availableToAddRightChildren = false;
        }
    }

    private void restoreAbilityToAdd(){
        for (Entry<String> parent : entryQueue){
            if (parent.leftChild == null)
                parent.availableToAddLeftChildren = true;
            if (parent.rightChild == null)
                parent.availableToAddRightChildren = true;
        }
    }

    @Override
    public int size() {
        return entryQueue.size()-1;
    }

    public String getParent(String s) {
        Entry<String> element = findEntry(s);
        return element != null ? element.parent.elementName : null;
    }

    private Entry<String> findEntry(String name){
        Entry<String> entry = null;
        for (Entry<String> e : entryQueue){
            if (e.elementName.equals(name))
                entry = e;
        }
        return entry;
    }
    @Override
    public boolean remove(Object o) {
        if (!(o instanceof String)) throw new UnsupportedOperationException();

        Entry<String> element = findEntry((String) o);
        if (element !=null) {
            unlinkEntry(element);
            restoreEntryQueue();
            return true;
        }
        return false;
    }

    private void unlinkEntry(Entry<String> element){
        if (element.parent.leftChild != null && element.parent.leftChild.elementName.equals(element.elementName))
            element.parent.leftChild = null;
        else element.parent.rightChild = null;
    }

    private void restoreEntryQueue(){
        Queue<Entry<String>> tmpQueue = new LinkedList<>();
        entryQueue.clear();
        tmpQueue.add(root);
        entryQueue.add(root);

        Entry<String> parent;
        while ((parent = tmpQueue.poll()) != null){
            if (parent.leftChild != null){
                entryQueue.add(parent.leftChild);
                tmpQueue.add(parent.leftChild);
            }
            if (parent.rightChild != null) {
                entryQueue.add(parent.rightChild);
                tmpQueue.add(parent.rightChild);
            }
        }
    }

    @Override
    public void add(int index, String element) {throw new UnsupportedOperationException();}

    @Override
    public boolean addAll(int index, Collection<? extends String> c){throw new UnsupportedOperationException();}

    @Override
    public List<String> subList(int fromIndex, int toIndex) {throw new UnsupportedOperationException();}

    @Override
    public String get(int index) {throw new UnsupportedOperationException();}

    @Override
    protected void removeRange(int fromIndex, int toIndex){ throw new UnsupportedOperationException();}

    @Override
    public String remove(int index) { throw new UnsupportedOperationException();}

    @Override
    public String set(int index, String element){throw new UnsupportedOperationException();}


    static class Entry<T> implements Serializable{
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
