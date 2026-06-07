package com.atguigu.study.records;

/**
 * record和class的区别：
 * 1.record是不可变的，所有字段final修饰
 * 2.record不可继承父类也不可被继承，可以实现接口
 * 3.record自动实现了get、equals、hashCode、toString方法
 * 4.record只有一个构造方法，没有方法重载
 * 一般用于简单的数据场景使用
 */
public record StudentRecord(String id, String sname, String major,String email) {

    // 静态字段
    public static int MinAge = 0;

    // 静态方法
    public static boolean IsValidAge(int age) {
        return age >= MinAge && age <= 1;
    }
    // 静态内部类
    public static class PersonBuilder {
        private int id;
        private String name;

        public PersonBuilder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Book build() {
            return new Book(id, name);
        }
    }

    public static void main(String[] args) {
        StudentRecord studentRecord = new StudentRecord("1", "John Smith", "计算机科学与技术","john@gmail.com");
        System.out.println(studentRecord);
    }
}

