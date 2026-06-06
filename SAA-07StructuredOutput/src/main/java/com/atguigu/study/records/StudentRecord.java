package com.atguigu.study.records;

/**
 * record和class的区别：
 * 1.record是不可变的，所有字段final修饰
 * 2.record不可继承父类也不可被继承，可以实现接口
 * 3.record自动实现了get、set、equals、hashCode、toString方法
 * 4.record只有一个构造方法，没有方法重载
 */
public record StudentRecord(String id, String sname, String major,String email) {

    public static void main(String[] args) {
        StudentRecord studentRecord = new StudentRecord("1", "John Smith", "计算机科学与技术","john@gmail.com");
        System.out.println(studentRecord);
    }
}

