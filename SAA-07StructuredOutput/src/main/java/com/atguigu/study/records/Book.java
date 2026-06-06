package com.atguigu.study.records;

import lombok.Data;

import java.util.Objects;

/**
 * @Description: TODO
 * @Author: wanxuhao
 * @Date: 2026/6/6
 **/
@Data
public class Book {
    int id;
    String bookName;

    public Book() {
    }

    public Book(int id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && Objects.equals(bookName, book.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName);
    }


}
