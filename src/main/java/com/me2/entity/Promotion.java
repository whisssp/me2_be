package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.me2.global.enums.ActionStatus;
import com.me2.global.enums.EnumPromotionType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "promotions", schema = "public", catalog = "me2_db")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Promotion extends AbstractAuditEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "type", nullable = false, length = 255)
    @Enumerated(EnumType.STRING)
    private EnumPromotionType type;
    
    @Column(name = "value", nullable = false, precision = 1)
    private BigDecimal value;
    
    @Column(name = "start_date", nullable = true)
    private Instant startDate;
    
    @Column(name = "end_date", nullable = true)
    private Instant endDate;
    
    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ActionStatus status;
    
    @Column(name = "is_activated", nullable = true)
    private Boolean isActivated;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    @OneToMany(mappedBy = "promotion", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"product", "category", "productVariants"}, allowSetters = true)
    private List<Product> products;

    
    @Column(name = "code", nullable = false)
    private String code;

    
    @Column(name = "name")
    private String name;

    
    @Column(name = "content")
    private String content;

    
    @Column(name = "quantity")
    private Integer quantity;

    
    @Column(name = "quantity_used")
    private Integer quantityUsed;

    
    @Column(name = "image")
    private String image;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumPromotionType getType() {
        return type;
    }

    public void setType(EnumPromotionType type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public ActionStatus getStatus() {
        return status;
    }

    public void setStatus(ActionStatus status) {
        this.status = status;
    }

    public Boolean getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Boolean isActivated) {
        this.isActivated = isActivated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Promotion that = (Promotion) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (quantityUsed != null ? !quantityUsed.equals(that.quantityUsed) : that.quantityUsed != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (isActivated != null ? !isActivated.equals(that.isActivated) : that.isActivated != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (isActivated != null ? isActivated.hashCode() : 0);
        return result;
    }
}