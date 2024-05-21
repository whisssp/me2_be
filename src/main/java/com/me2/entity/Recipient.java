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
@Table(name = "recipients", schema = "public", catalog = "me2_db")
public class Recipient extends AbstractAuditEntity<Long> implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "rec_first_name", nullable = false, length = 100)
    private String recFirstName;
    
    @Column(name = "rec_last_name", nullable = false, length = 100)
    private String recLastName;
    
    @Column(name = "rec_last_phone", nullable = false, length = 10)
    private String recLastPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"user", "recipients"}, allowSetters = true)
    private Address address;

    @OneToMany(mappedBy = "recipient", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"orderDetails"}, allowSetters = true)
    private List<Order> orders;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recipient that = (Recipient) o;

        if (id != that.id) return false;
        if (address != that.address) return false;
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
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}