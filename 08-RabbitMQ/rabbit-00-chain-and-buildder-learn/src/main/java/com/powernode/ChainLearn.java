package com.powernode;

public class ChainLearn {
    public static void main(String[] args) {
        Student student = new Student();
        student.setId(10).setName("thomas").setAge(20).setAddress("beijing");
        System.out.println(student);

        //使用lombok生成链式编程
        Teacher teacher = new Teacher();
        teacher.setId(20).setName("aobama").setAge(30).setAddress("henan");
        System.out.println(teacher);

        //建造者模式
        Teacher teacher1 = Teacher.builder().id(20).name("obama").age(30).address("america").build();
        System.out.println(teacher1);
    }
}
