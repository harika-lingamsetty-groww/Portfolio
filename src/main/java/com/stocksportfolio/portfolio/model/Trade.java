package com.stocksportfolio.portfolio.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "user_trade",
            joinColumns = @JoinColumn(name = "trade_id"),
            inverseJoinColumns = @JoinColumn(name = "user_account_id")
    )
    private Set<User> users;

    @Column(name = "user_account_id")
    private Long userAccountId;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_type")
    private TradeType tradeType;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    private UpdateStocks stock;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Long userAccountId) {
        this.userAccountId = userAccountId;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public void setTradeType(TradeType tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public UpdateStocks getStock() {
        return stock;
    }

    public void setStock(UpdateStocks stock) {
        this.stock = stock;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
