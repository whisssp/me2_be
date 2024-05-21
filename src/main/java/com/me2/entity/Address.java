package com.me2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "addresses", schema = "public", catalog = "me2_db")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Address extends AbstractAuditEntity<Long> implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    
    @Column(name = "street_address", nullable = true, length = 255)
    private String streetAddress;
    
    @Column(name = "apartment", nullable = true, length = 100)
    private String apartment;
    
    @Column(name = "city", nullable = true, length = 100)
    private String city;
    
    @Column(name = "zip_code", nullable = true, length = 100)
    private String zipCode;
    
    @Column(name = "country", nullable = false, length = 100)
    private String country;
    
    @Column(name = "state", nullable = true, length = 100)
    private String state;
    
    @Column(name = "is_default", nullable = true)
    private Boolean isDefault;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"addresses", "carts", "orders"}, allowSetters = true)
    private User user;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"orders", "address"}, allowSetters = true)
    private Set<Recipient> recipients;

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

    public User getUserId() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address that = (Address) o;

        if (id != that.id) return false;
        if (streetAddress != null ? !streetAddress.equals(that.streetAddress) : that.streetAddress != null)
            return false;
        if (apartment != null ? !apartment.equals(that.apartment) : that.apartment != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (state != null ? !state.equals(that.state) : that.state != null) return false;
        if (isDefault != null ? !isDefault.equals(that.isDefault) : that.isDefault != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

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
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}