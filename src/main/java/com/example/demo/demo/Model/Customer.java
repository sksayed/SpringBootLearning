package com.example.demo.demo.Model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String _emailAddress ;
    private String _firstName;

    public Customer(Long id, String _emailAddress, String _firstName) {
        this.id = id;
        this._emailAddress = _emailAddress;
        this._firstName = _firstName;
    }

    public Customer(String _emailAddress, String _firstName) {
        this._emailAddress = _emailAddress;
        this._firstName = _firstName;
    }

    public Customer () {}


    public String get_firstName() {
        return _firstName;
    }

    public void set_firstName(String _firstName) {
        this._firstName = _firstName;
    }

    public String get_emailAddress() {
        return _emailAddress;
    }

    public void set_emailAddress(String _emailAddress) {
        this._emailAddress = _emailAddress;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }
}
