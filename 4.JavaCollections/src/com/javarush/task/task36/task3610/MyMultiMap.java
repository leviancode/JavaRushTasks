package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return map.values().stream().mapToInt(List::size).sum();
    }

    @Override
    public V put(K key, V value) {
        V lastValue = null;
        if (map.containsKey(key)){
            List<V> list = map.get(key);
            list.add(value);
            lastValue = list.get(list.lastIndexOf(value)-1);
            if (list.size() > repeatCount) list.remove(0);
        }else{
            List<V> newList = new ArrayList<>();
            newList.add(value);
            map.put(key, newList);
        }
        return lastValue;
    }

    @Override
    public V remove(Object key) {
        V removedValue = null;
        if (map.containsKey(key)){
            List<V> list = map.get(key);
            removedValue = list.remove(0);
            if (list.size() == 0) map.remove(key);
        }
        return removedValue;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        List<V> values = new ArrayList<>();
        map.values().forEach(values::addAll);
        return values;
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.values().stream().anyMatch(list -> list.contains(value));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}