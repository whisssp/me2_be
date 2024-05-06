package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "addresses", schema = "public", catalog = "me2_db")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity extends AbstractAuditEntity implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "street_address", nullable = true, length = 255)
    private String streetAddress;
    @Basic
    @Column(name = "apartment", nullable = true, length = 100)
    private String apartment;
    @Basic
    @Column(name = "city", nullable = true, length = 100)
    private String city;
    @Basic
    @Column(name = "zip_code", nullable = true, length = 100)
    private String zipCode;
    @Basic
    @Column(name = "country", nullable = false, length = 100)
    private String country;
    @Basic
    @Column(name = "state", nullable = true, length = 100)
    private String state;
    @Basic
    @Column(name = "is_default", nullable = true)
    private Boolean isDefault;
    @Basic
    @Column(name = "user_id", nullable = true)
    private Long userId;

    @OneToMany(mappedBy = "addressId", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<RecipientEntity> recipentList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressEntity that = (AddressEntity) o;

        if (id != that.id) return false;
        if (streetAddress != null ? !streetAddress.equals(that.streetAddress) : that.streetAddress != null)
            return false;
        if (apartment != null ? !apartment.equals(that.apartment) : that.apartment != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (isDefault != null ? !isDefault.equals(that.isDefault) : that.isDefault != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (streetAddress != null ? streetAddress.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (isDefault != null ? isDefault.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}