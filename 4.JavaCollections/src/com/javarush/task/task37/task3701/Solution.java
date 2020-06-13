package com.javarush.task.task37.task3701;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;

/* 
Круговой итератор
*/
public class Solution<T> extends ArrayList<T> {
    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public static void main(String[] args) {
        Solution<Integer> list = new Solution<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);


        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }

    }

    public class RoundIterator implements Iterator<T>{
        private int cursor = -1;     // index of next element to return
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return Solution.this.toArray().length != 0;
        }

        @Override
        public T next() {
            checkForComodification();
            Object[] data = Solution.this.toArray();
            cursor += 1;
            if (cursor >= data.length) {
                cursor = 0;
            }
            return (T) data[cursor];
        }

        @Override
        public void remove() {
            checkForComodification();
            Solution.this.remove(cursor);
            expectedModCount = modCount;
        }
        void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
