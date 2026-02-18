package com.ducanh.coffee.repository;

import com.ducanh.coffee.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, String> {

    //SPRING BOOT, SPRING JPA, SPRING HIBERNATE CÓ 2 CƠ CHẾ SINH HÀM TỰ ĐỘNG CHO MỖI ENTITY/TABLE
    //NHỮNG HÀM PHỔ BIẾN, HAY DÙNG THƯỜNG XUYÊN

    //NHỮNG HÀM ĐẶC THÙ, ĐẶC BIỆT RIÊNG, ÍT KHI DÙNG
    //HÀM NÀY CÓ TÊN GỌI LÀ: DERIVED QUERY METHOD: HÀM DẪN XUẤT, PHÁT SINH TỪ CÂU QUERY GẮN TRÊN TÊN HÀM
    public List<Product> searchAllByNameContainingIgnoreCase(String keyword);

    //ko cần viết các haàm crud vì ta xài derived query method
}
