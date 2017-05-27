package com.netcracker.internetshop.entity.account;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

    @Entity
    @Table(name = "accounts")
    public class Account implements Serializable {

        private static final long serialVersionUID = -2054386655979281969L;


        public static final String ROLE_MANAGER = "MANAGER";
        public static final String ROLE_EMPLOYEE = "EMPLOYEE";

        private String userName;
        private String password;
        private String email;
        private String phone;
        private String address;
        private boolean active;
        private String userRole;

        @Id
        @Column(name = "User_Name", length = 20, nullable = false)
        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Column(name = "Password", length = 20, nullable = false)
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Column(name = "Active", length = 1, nullable = false)
        public boolean isActive() {
            return active;
        }

        public void setActive(boolean active) {
            this.active = active;
        }

        @Column(name = "User_Role", length = 20, nullable = false)
        public String getUserRole() {
            return userRole;
        }

        public void setUserRole(String userRole) {
            this.userRole = userRole;
        }

        @Column(name = "Email", length = 30)
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        @Column(name = "Phone", length = 15)
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Column(name = "Address", length = 50)
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        @Override
        public String toString()  {
            return "["+ this.userName+","+ this.password+","+ this.userRole+"]";
        }

    }

