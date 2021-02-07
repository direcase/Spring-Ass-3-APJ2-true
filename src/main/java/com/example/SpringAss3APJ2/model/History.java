package com.example.SpringAss3APJ2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Entity(name = "historyEntity")
@Table(name = "history")
public class History implements Serializable {
    private Long id;
    private Long card_id;
    private int amount;
    private String time;
    private String type;


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
    public Long getCard_id() {
        return card_id;
    }
    public void setCard_id(Long card_id) {
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

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", card_id=" + card_id +
                ", amount=" + amount +
                ", time=" + time +
                '}';
    }
}
