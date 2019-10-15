package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/
public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

    public boolean equals(Object n) {

        if (n == this)
            return true;
        if (n == null || n.getClass() != getClass() || !(n instanceof Solution))
            return false;
        Solution sol = (Solution) n;

        return Objects.equals(first, sol.first) && Objects.equals(last, sol.last);
    }

    public int hashCode() {

        return Objects.hash(first, last);
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));
    }
}
