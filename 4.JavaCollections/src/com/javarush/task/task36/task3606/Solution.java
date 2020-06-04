package com.javarush.task.task36.task3606;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException{
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException{
        if (!packageName.endsWith("/")) packageName += File.separator;
        // packageName = packageName.replace("%20", " ");

        try {
            Files.list(Paths.get(packageName))
                    .filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith(".class"))
                    .map(this::loadClass).filter(Objects::nonNull)
                    .forEach(this::checkAndAddClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Class<?> loadClass(Path path){
        try {
            return new HiddenClassLoader().loadHiddenClass(path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void checkAndAddClass(Class<?> aClass) {
        try {
            Constructor<?> constructor = aClass.getDeclaredConstructor();
            if (constructor.getParameterCount() == 0 && HiddenClass.class.isAssignableFrom(aClass))
                hiddenClasses.add(aClass);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for (Class<?> clazz : hiddenClasses){
            if (clazz.getSimpleName().toLowerCase().contains(key.toLowerCase())) {
                try {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return (HiddenClass) constructor.newInstance();
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    static class HiddenClassLoader extends ClassLoader{
        public Class<?> loadHiddenClass (Path path) throws ClassNotFoundException {
            try {
                byte[] classBytes = Files.readAllBytes(path);
                return defineClass(null, classBytes, 0, classBytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return findClass(path.getFileName().toString());
        }
    }
}

