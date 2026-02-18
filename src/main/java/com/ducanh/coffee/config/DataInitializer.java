package com.ducanh.coffee.config;

import com.ducanh.coffee.entity.Account;
import com.ducanh.coffee.entity.Category;
import com.ducanh.coffee.entity.Product;
import com.ducanh.coffee.service.AccountService;
import com.ducanh.coffee.service.CategoryService;
import com.ducanh.coffee.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//TỰ CHẠY 1 LẦN DUY NHẤT KHI TOMCAT ĐC CHẠY, LÀ IOC CONTAINER CHẠY
//DÙNG ĐỂ TẠO TABLE, TẠO SẴN DATA TRONG TABLE
@Component
public class DataInitializer implements CommandLineRunner {
    //nhờ vả 2 service giúp tạo table, chèn sẵn data
    @Autowired
    private CategoryService cateService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        accountService.saveAccount(new Account("anh@gmail.com", "1", 1));
        accountService.saveAccount(new Account("nguyenduyducanh19102005@gmail.com", "1", 2));
        accountService.saveAccount(new Account("ducanh@gmail.com", "1", 3));

        //TẠO OBJ NHỜ SERVICE ĐẨY XUỐNG
        //TẠO TABLE 1 TRƯỚC, N SAU (DO KHOÁ NGOẠI THAM CHIẾU KHOÁ CHÍNH)
        Category cat1 = new Category("Trà sữa", "Trà sữa nhà làm");
        Category cat2 = new Category("Cà phê", "Uống cà phê top server");
        Category cat3 = new Category("Beer", "Bia lúa mạch lên men");
        Category cat4 = new Category("Bánh kẹo", "Bánh kẹo socola");

        //tạo dữ liệu table product
        Product pro1cat1 = new Product("TS01", "Trà Sữa Viên Viên", 30, 39_000);
        Product pro2cat1 = new Product("TS02", "Trà Sữa Ngô Gia", 30, 68_000);
        cat1.addProduct(pro1cat1);
        cat1.addProduct(pro2cat1);

        Product pro1cat2 = new Product("CF01", "Cà Phê Đen", 450, 2_350_000);
        Product pro2cat2 = new Product("CF02", "Cà Phê Java", 6969, 5_700_000);
        cat2.addProduct(pro1cat2);
        cat2.addProduct(pro2cat2);

        //Xuống table ngay khi tomcat chạy, CASCADE ALL
        //Nghĩa là table 1 đi xuống thì table N cũng xuống theo
        cateService.saveCategory(cat1);
        cateService.saveCategory(cat2);
        cateService.saveCategory(cat3);
        cateService.saveCategory(cat4);
    }

}
