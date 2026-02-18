package com.ducanh.coffee.service;

import com.ducanh.coffee.entity.Category;
import com.ducanh.coffee.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    //GUI/CONTROLLER --- SERVICE --- REPO --- SPRING JPA/HIBERNATE -- JDBC DRIVER --- TABLE
    //SERVICE LO CRUD TABLE TRONG RAM, LO XỬ LÍ VỚI ENTITY/DTO, VÍ DỤ TÍNH VOUCHER, KHUYẾN MÃI, GỌI CỔNG THANH TOÁN
    //NẾU LO VIỆC CRUD TABLE THÌ PHẢI NHỜ VÀO REPO
    //BẢN CHẤT LÀ PHẢI KHAI BÁO REPO, NEW REPO(), GỌI HÀM REPO
    //NHƯNG REPO HIỆN NAY ĐANG LÀ INTERFACE, VÀ LÀ BEAN, CHO NÊN TA SẼ NHỜ IOC CONTAINER CHÍCH TIÊM VÀO SERVICE
    //VẬY SERVICE ĐC TIÊM REPO VÀO, VẬY SERVICE CX PHẢI LÀ BEAN
    //@Component, @Service, @Repository, @Controller, @RestController

    //tiêm repo, có 3 cách
    private CategoryRepo cateRepo;

    //tiêm qua constructor
    //ko cần @Autowired nếu class chỉ có 1 constructor này!!! vì chỉ có 1 đường new
    @Autowired //chích nhanh (ưu tiên chích)
    public CategoryService(CategoryRepo cateRepo) {
        this.cateRepo = cateRepo;
    }

    //CRUD TRUYỀN THỐNG, GỌI HÀM DERIVED QUERY METHODS TỰ SINH CỦA THẰNG REPO ĐC TIÊM VÀO TỰ ĐỘNG
    public void saveCategory(Category o) {
        cateRepo.save(o); //hàm tự sinh bên repo
    }

    public List<Category> getAllCategories() {
        return cateRepo.findAll(); //hàm tự sinh bên repo
        //JPQL: SELECT c FROM CATEGORY c;
        //SQL: SELECT * FROM CATEGORY
    }
}
