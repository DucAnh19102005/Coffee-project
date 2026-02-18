package com.ducanh.coffee.service;

import com.ducanh.coffee.entity.Product;
import com.ducanh.coffee.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    //GUI/CONTROLLER --- SERVICE --- REPOSITORY --- SPRING JPA/HIBERNATE --- JDBC DRIVER --- TABLE
    //CLASS TRUNG TÂM
    //CRUD TABLE PRODUCT, CẦN TIÊM REPO VÀO VÀ XÀI NHỮNG HÀM TỰ SINH

    @Autowired
    private ProductRepo productRepo;

    //phục vụ cho việc show toàn bộ sản phẩm trong trang products.html
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    //phục vụ cho nút save sản phẩm khi tạo mới vào edit
    //xài chung hàm: JPA nó check id nếu là mới là insert, còn nếu là cũ là update
    public String saveProduct(Product o) {
        return productRepo.save(o).getId();
    }

    //xoá product
    public void deleteProduct(Product o) {
        productRepo.delete(o);
    }

    //edit 1 product
    public Product getProductById(String id) {
        return productRepo.findById(id).orElse(null);
    }

    public boolean isExist(String id){
        return productRepo.existsById(id);
    }

    //Search product theo name
    public List<Product> searchProductByName(String keyword) {
        return productRepo.searchAllByNameContainingIgnoreCase(keyword);
    }

    public Product searchProductById(String id) {
        return productRepo.findById(id).orElse(null);
    }

}
