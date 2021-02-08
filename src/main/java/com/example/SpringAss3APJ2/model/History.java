package com.example.SpringAss3APJ2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity(name = "historyEntity")
@Table(name = "history1")
public class History implements Serializable {
    private Long id;
    private String card_id;
    private int amount;
    private String time;
    private String type;
    private int itogo;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "card_id")
    public String getCard_id() {
        return card_id;
    }
    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    @Column(name = "amount")
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column(name ="time")
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }



    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



    @Column(name = "itogo")
    public int getItogo() {
        return itogo;
    }

    public void setItogo(int itogo) {
        this.itogo = itogo;
    }


    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", card_id=" + card_id +
                ", amount=" + amount +
                ", time='" + time + '\'' +
                ", type='" + type + '\'' +
                ", itogo=" + itogo +
                '}';
    }
}
