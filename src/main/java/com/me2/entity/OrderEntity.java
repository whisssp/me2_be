package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.me2.global.enums.EnumOrderStatus;
import com.me2.global.enums.EnumPaymentMethod;
import com.me2.global.enums.EnumPaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders", schema = "public", catalog = "me2_db")
public class OrderEntity extends AbstractAuditEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "recipient_id", nullable = false)
    private Long recipientId;
    @Basic
    @Column(name = "user_id", nullable = false)
    private Long userId;
    @Basic
    @Column(name = "promotion_id", nullable = true)
    private Long promotionId;
    @Basic
    @Column(name = "payment_method", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private EnumPaymentMethod paymentMethod;
    @Basic
    @Column(name = "payment_status", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private EnumPaymentStatus paymentStatus;
    @Basic
    @Column(name = "status", nullable = false, length = 100)
    @Enumerated(EnumType.STRING)
    private EnumOrderStatus status;

    @OneToMany(mappedBy = "orderId", fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private List<OrderDetailEntity> orderDetailList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public EnumPaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(EnumPaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public EnumPaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(EnumPaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public void setStatus(EnumOrderStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (id != that.id) return false;
        if (recipientId != that.recipientId) return false;
        if (userId != that.userId) return false;
        if (promotionId != null ? !promotionId.equals(that.promotionId) : that.promotionId != null) return false;
        if (paymentMethod != null ? !paymentMethod.equals(that.paymentMethod) : that.paymentMethod != null)
            return false;
        if (paymentStatus != null ? !paymentStatus.equals(that.paymentStatus) : that.paymentStatus != null)
            return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (recipientId ^ (recipientId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (promotionId != null ? promotionId.hashCode() : 0);
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (paymentStatus != null ? paymentStatus.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}