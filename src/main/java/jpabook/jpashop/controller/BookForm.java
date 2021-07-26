package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class BookForm {
    private Long id;
    //공통
    private String name;
    private int price;
    private int stockQuantity;
    //특별속성
    private String author;
    private String isbn;
}