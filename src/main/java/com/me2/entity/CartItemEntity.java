package com.me2.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_items", schema = "public", catalog = "me2_db")
public class CartItemEntity extends AbstractAuditEntity<Long> implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "cart_id", nullable = false)
    private Long cartId;
    @Basic
    @Column(name = "product_variant_id", nullable = false)
    private Long productVariantId;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    private BigDecimal price;

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public long getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(Long productVariantId) {
        this.productVariantId = productVariantId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItemEntity that = (CartItemEntity) o;

        if (id != that.id) return false;
        if (cartId != that.cartId) return false;
        if (productVariantId != that.productVariantId) return false;
        if (quantity != that.quantity) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (cartId ^ (cartId >>> 32));
        result = 31 * result + (int) (productVariantId ^ (productVariantId >>> 32));
        result = 31 * result + quantity;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}