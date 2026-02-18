package com.ducanh.coffee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Nationalized;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @Column(name = "Id", length = 10)
    @NotBlank(message = "ID is required!") //validation từ form đưa lên
    @Size(min = 4, max = 4, message = "ID length must be 4 characters")
    private String id;

    @Column(name = "Name", length = 50, nullable = false)
    @NotBlank(message = "Name is required!") //validation từ form đưa lên
    @Size(min = 5, max = 50, message = "Name length must be between 5---50 characters")
    @Pattern(
            regexp = "^(\\p{Lu}\\p{Ll}+)(\\s\\p{Lu}\\p{Ll}+)*$",
            message = "Mỗi từ phải bắt đầu hoa, chỉ chứa chữ (Unicode), không số/ký tự đặc biệt, không khoảng trắng thừa"
    )
    private String name;

    @Column(name = "Quantity", nullable = false)
    @NotNull(message = "Quantity is required!")
    @Min(value = 5, message = "Quantity must be greater than 5...")
    @Max(value = 10_000, message = "Quantity must be smaller than 10_000...")
    private int quantity;

    @Column(name = "Price", nullable = false)
    @Min(value = 5, message = "Price must be greater than 5...")
    @Max(value = 6_000_000, message = "Price must be smaller than 6_000_000...")
    private double price;

    @ManyToOne
    @JoinColumn(name = "CateId")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product() {
    }

    public Product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
