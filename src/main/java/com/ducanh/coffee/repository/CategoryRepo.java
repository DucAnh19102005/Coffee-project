package com.ducanh.coffee.repository;

import com.ducanh.coffee.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    //JpaRepository interface này cần 2 thông số:
    //Tên entity class, tơng lai là table nào
    //Cột key có kiểu data type nào đó
    //nhờ 2 thông số này, Jpa/Hibernate phía hậu trường nó tự generate ra các hàm CRUD table Category, và 1 loạt các hàm ứng với các câu SQL hay dùng trên 1 table
    //ứng với where riêng của bạn, Spring JPA lo đc luôn các hàm này
    //-> để tận dụng cơ chế tự sinh hàm, bạn cần viết tên hàm theo chuẩn Spring JPA quy ước trước, tự Spring lo nốt câu JPQL/SQL
    //chuẩn kĩ thuật gọi là: DERIVED QUERY METHODS, QUERY METHODS
    //KEYWORD: "QUERY METHODS IN SPRING DATA JPA"

    public List<Category> findCategoriesByNameContainingIgnoreCase(String name);
    //where Name like '%ng%'

    //NHƯNG BẠN VẪN CÓ TH ĐỘ CÂU QUERY RIÊNG (JPQL, SQL NATIVE) ĐC LUÔN
}
