package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.List;

/*
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
        for (Class<?> clazz : declaredClasses){
            if (checkModifiers(clazz) && checkInterface(clazz)) {
                try {
                    Method method = clazz.getDeclaredMethod("get", int.class);
                    method.setAccessible(true);

                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);

                    if (constructor.getParameterCount() == 0)
                        method.invoke(constructor.newInstance(), 1);

                }catch (InvocationTargetException e){
                    if (e.getCause().toString().contains("IndexOutOfBoundsException"))
                        return clazz;
                }
                catch(NoSuchMethodException | InstantiationException | IllegalAccessException ignored){}
            }
        }
        return null;
    }
    private static boolean checkModifiers(Class<?> clazz){
        return Modifier.isPrivate(clazz.getModifiers()) && Modifier.isStatic(clazz.getModifiers());
    }
    private static boolean checkInterface(Class<?> clazz){
        return List.class.isAssignableFrom(clazz) || List.class.isAssignableFrom(clazz.getSuperclass());
    }
}
