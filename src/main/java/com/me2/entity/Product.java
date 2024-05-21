package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.me2.global.enums.ActionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products", schema = "public", catalog = "me2_db")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractAuditEntity<Long> implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties(value = {"children", "parent"}, allowSetters = true)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    @JsonIgnoreProperties(value = {"orders", "products"}, allowSetters = true)
    private Promotion promotion;

   
    @Column(name = "name", nullable = true, length = 255)
    private String name;

   
    @Column(name = "summary", nullable = true, length = 255)
    private String summary;

    @Column(name = "description", nullable = true, length = 255)
    private String description;

   
    @Column(name = "price", nullable = true, precision = 2)
    private BigDecimal price;

   
    @Column(name = "poster", nullable = true, length = 255)
    private String poster;

   
    @Column(name = "status", nullable = false, length = 255)
    @Enumerated(EnumType.STRING)
    private ActionStatus status;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"product", "cartItems", "productGallery", "orderDetails"}, allowSetters = true)
    private List<ProductVariant> productVariants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public ActionStatus getStatus() {
        return status;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        if (id != that.id) return false;
        if (category != that.category) return false;
        if (promotion != null ? !promotion.equals(that.promotion) : that.promotion != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (summary != null ? !summary.equals(that.summary) : that.summary != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (poster != null ? !poster.equals(that.poster) : that.poster != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (promotion != null ? promotion.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);

        return result;
    }
}