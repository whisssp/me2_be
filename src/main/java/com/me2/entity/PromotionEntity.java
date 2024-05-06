package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.me2.global.enums.EnumPromotionStatus;
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
public class PromotionEntity extends AbstractAuditEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "type", nullable = false, length = 255)
    @Enumerated(EnumType.STRING)
    private EnumPromotionType type;
    @Basic
    @Column(name = "value", nullable = false, precision = 1)
    private BigDecimal value;
    @Basic
    @Column(name = "from_date", nullable = true)
    private Instant fromDate;
    @Basic
    @Column(name = "to_date", nullable = true)
    private Instant toDate;
    @Basic
    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private EnumPromotionStatus status;
    @Basic
    @Column(name = "is_deleted", nullable = true)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "promotionId", fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private List<OrderEntity> orderList;

    @OneToMany(mappedBy = "promotionId", fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private List<ProductEntity> productList;

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

    public Instant getFromDate() {
        return fromDate;
    }

    public void setFromDate(Instant fromDate) {
        this.fromDate = fromDate;
    }

    public Instant getToDate() {
        return toDate;
    }

    public void setToDate(Instant toDate) {
        this.toDate = toDate;
    }

    public EnumPromotionStatus getStatus() {
        return status;
    }

    public void setStatus(EnumPromotionStatus status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionEntity that = (PromotionEntity) o;

        if (id != that.id) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        if (fromDate != null ? !fromDate.equals(that.fromDate) : that.fromDate != null) return false;
        if (toDate != null ? !toDate.equals(that.toDate) : that.toDate != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (isDeleted != null ? !isDeleted.equals(that.isDeleted) : that.isDeleted != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (toDate != null ? toDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (isDeleted != null ? isDeleted.hashCode() : 0);
        return result;
    }
}