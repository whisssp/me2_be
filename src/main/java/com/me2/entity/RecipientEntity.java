package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "recipients", schema = "public", catalog = "me2_db")
public class RecipientEntity extends AbstractAuditEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "rec_first_name", nullable = false, length = 100)
    private String recFirstName;
    @Basic
    @Column(name = "rec_last_name", nullable = false, length = 100)
    private String recLastName;
    @Basic
    @Column(name = "rec_last_phone", nullable = false, length = 10)
    private String recLastPhone;
    @Basic
    @Column(name = "address_id", nullable = false)
    private Long addressId;

    @OneToMany(mappedBy = "recipientId", fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private List<OrderEntity> orderList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecFirstName() {
        return recFirstName;
    }

    public void setRecFirstName(String recFirstName) {
        this.recFirstName = recFirstName;
    }

    public String getRecLastName() {
        return recLastName;
    }

    public void setRecLastName(String recLastName) {
        this.recLastName = recLastName;
    }

    public String getRecLastPhone() {
        return recLastPhone;
    }

    public void setRecLastPhone(String recLastPhone) {
        this.recLastPhone = recLastPhone;
    }

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipientEntity that = (RecipientEntity) o;

        if (id != that.id) return false;
        if (addressId != that.addressId) return false;
        if (recFirstName != null ? !recFirstName.equals(that.recFirstName) : that.recFirstName != null) return false;
        if (recLastName != null ? !recLastName.equals(that.recLastName) : that.recLastName != null) return false;
        if (recLastPhone != null ? !recLastPhone.equals(that.recLastPhone) : that.recLastPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (recFirstName != null ? recFirstName.hashCode() : 0);
        result = 31 * result + (recLastName != null ? recLastName.hashCode() : 0);
        result = 31 * result + (recLastPhone != null ? recLastPhone.hashCode() : 0);
        result = 31 * result + (int) (addressId ^ (addressId >>> 32));
        return result;
    }
}