package com.javarush.task.task37.task3707;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Set<E>, Serializable, Cloneable {
    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }
    public AmigoSet(Collection<? extends E> collection){
        int capacity = 16 > (collection.size()/.75f) ? 16 : (int) Math.ceil(collection.size() / .75f);
        map = new HashMap<>(capacity);
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return map.put(e, PRESENT) == null;
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public boolean remove(Object o) {
        return map.keySet().remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Object clone() throws InternalError {
        try {
            AmigoSet<E> newSet = new AmigoSet<E>();
            newSet.map = (HashMap<E, Object>) map.clone();
            return newSet;
        }catch (Exception e){
            throw new InternalError(e);
        }
    }

    private void writeObject(ObjectOutputStream oos){
        try {
            oos.defaultWriteObject();
            oos.writeInt(map.size());
            oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
            for (E e : map.keySet()) {
                oos.writeObject(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream ois){
        try {
            ois.defaultReadObject();
            int size = ois.readInt();
            int capacity = ois.readInt();
            float loadFactor = ois.readFloat();
            map = new HashMap<>(capacity, loadFactor);
            for (int i = 0; i < size; i++){
                map.put((E) ois.readObject(), PRESENT);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
