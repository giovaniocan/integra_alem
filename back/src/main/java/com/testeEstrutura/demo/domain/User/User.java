package com.testeEstrutura.demo.domain.User;

import com.testeEstrutura.demo.domain.User.dto.DataAddCustomer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Entity(name = "User")
@Table(name = "users")
@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @Column(nullable = false, updatable = false, unique = true, length = 36)
    private String id;

    @Column(nullable = false, length = 255)
    private String customerName;

    @Column(nullable = false, length = 255)
    private String login;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 255)
    private String loginEntregaAlem;

    @Column(nullable = false, length = 255)
    private String passwordEntregaAlem;

    @Column(nullable = false)
    private Boolean isDeleted = false;

    @Column(nullable = false)
    private Boolean isAdmin = false;

    public User(DataAddCustomer dataAddCustomer) {
        this.id = java.util.UUID.randomUUID().toString();
        this.customerName = dataAddCustomer.customerName();
        this.login = dataAddCustomer.login();
        this.password = dataAddCustomer.password();
        this.loginEntregaAlem = dataAddCustomer.loginEntregaAlem();
        this.passwordEntregaAlem = dataAddCustomer.passwordEntregaAlem();
    }

    public User(){}

    public String getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getLoginEntregaAlem() {
        return loginEntregaAlem;
    }

    public String getPasswordEntregaAlem() {
        return passwordEntregaAlem;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }
}
