package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_variants", schema = "public", catalog = "me2_db")
public class ProductVariant extends AbstractAuditEntity<Long> implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = {"category", "promotion", "productVariants"}, allowSetters = true)
    private Product product;
    
    @Column(name = "quantity", nullable = true)
    private Integer quantity;
    
    @Column(name = "size", nullable = false, length = 5)
    private String size;
    
    @Column(name = "color", nullable = false, length = 50)
    private String color;
    
    @Column(name = "sku", nullable = true, length = 30)
    private String sku;
    
    @Column(name = "name", nullable = true, length = 250)
    private String name;

    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "productVariantId", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<ProductGallery> productGalleries;

    @OneToMany(mappedBy = "productVariant", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderDetail> orderDetails;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductVariant that = (ProductVariant) o;

        if (id != that.id) return false;
        if (product != that.product) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (sku != null ? !sku.equals(that.sku) : that.sku != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (quantity != that.quantity) return false;

        return true;
    }
}