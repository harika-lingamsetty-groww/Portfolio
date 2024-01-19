//package com.stocksportfolio.portfolio.model;
//
//import jakarta.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "users")
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_account_id")
//    private Long userAccountId;
//
//    private String username;
//
//    @ManyToMany(mappedBy = "users")
//    private Set<Trade> trades;
//
//    // Getters and setters
//
//    public Long getUserAccountId() {
//        return userAccountId;
//    }
//
//    public void setUserAccountId(Long userAccountId) {
//        this.userAccountId = userAccountId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public Set<Trade> getTrades() {
//        return trades;
//    }
//
//    public void setTrades(Set<Trade> trades) {
//        this.trades = trades;
//    }
//}

package com.stocksportfolio.portfolio.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToMany(mappedBy = "users")
    private Set<Trade> trades;

    // Getters and setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Trade> getTrades() {
        return trades;
    }

    public void setTrades(Set<Trade> trades) {
        this.trades = trades;
    }
}
