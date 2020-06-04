package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.nio.file.*;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(
                Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath()
                        + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();
        String packageName = Solution.class.getPackage().getName() + ".data";
        SimpleCLassLoader classLoader = new SimpleCLassLoader(packageName);

        if (!pathToAnimals.endsWith("/")) pathToAnimals += File.separator;
        File dir = new File(pathToAnimals);
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".class"));

        for(File file : files){
            try {
                Class<?> aClass = classLoader.load(file.toPath());
                Constructor<?>[] constructors = aClass.getConstructors();
                if (constructors.length != 0) {
                    for (Constructor<?> constructor : constructors) {
                        if (constructor.getParameterCount() == 0 && Animal.class.isAssignableFrom(aClass)) {
                            set.add((Animal) aClass.newInstance());
                            break;
                        }
                    }
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }


        return set;
    }
    public static class SimpleCLassLoader extends ClassLoader{
        private String packageName;

        public SimpleCLassLoader (String packageName){
            this.packageName = packageName;
        }

        public Class<?> load (Path path) throws ClassNotFoundException{
            try {
                String className = packageName + "." + path.getFileName().toString().replace(".class", "");
                byte[] classBytes = Files.readAllBytes(path);
                return defineClass(className, classBytes, 0, classBytes.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return findClass(path.getFileName().toString());
        }
    }
}
