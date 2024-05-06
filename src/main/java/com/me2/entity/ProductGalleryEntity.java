package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_galleries", schema = "public", catalog = "me2_db")
public class ProductGalleryEntity extends AbstractAuditEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "product_variant_id", nullable = false)
    private Long productVariantId;

    @Column(name = "images", nullable = false, length = 255)
    private String images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getProductVariantId() {
        return productVariantId;
    }

    public void setProductVariantId(long productVariantId) {
        this.productVariantId = productVariantId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductGalleryEntity that = (ProductGalleryEntity) o;

        if (id != that.id) return false;
        if (productVariantId != that.productVariantId) return false;
        if (images != null ? !images.equals(that.images) : that.images != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (productVariantId ^ (productVariantId >>> 32));
        result = 31 * result + (images != null ? images.hashCode() : 0);
        return result;
    }
}