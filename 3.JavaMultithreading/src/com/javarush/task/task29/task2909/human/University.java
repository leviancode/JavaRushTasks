package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        for (Student student : students ){
            if (student.getAverageGrade() == averageGrade)
                return student;
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student bestStudent = students.get(0);
        for (int i = 1; i < students.size(); i++){
            if (students.get(i).getAverageGrade() > bestStudent.getAverageGrade())
                bestStudent = students.get(i);
        }
        return bestStudent;
    }

    public Student getStudentWithMinAverageGrade() {
        Student worseStudent = students.get(0);
        for (int i = 1; i < students.size(); i++){
            if (students.get(i).getAverageGrade() < worseStudent.getAverageGrade())
                worseStudent = students.get(i);
        }
        return worseStudent;
    }

    public void expel(Student student){
        students.remove(student);
    }
}