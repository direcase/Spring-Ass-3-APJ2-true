package com.example.SpringAss3APJ2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "CardEntity")
@Table(name = "cards")
public class Card implements Serializable {
    private Long id;
    private int user_id;
    private int amount;
    private Date date;
    private String type;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    //@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JoinColumn(name = "user_id")
    @Column(name = "user_id")
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column(name = "date")
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="type")
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", amount=" + amount +
                ", date=" + date +
                ", type='" + type + '\'' +
                '}';
    }
}
