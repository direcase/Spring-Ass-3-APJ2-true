package com.example.SpringAss3APJ2.controller;


import com.example.SpringAss3APJ2.model.Card;
import com.example.SpringAss3APJ2.model.History;
import com.example.SpringAss3APJ2.model.Sort;
import com.example.SpringAss3APJ2.repo.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.sql.Time;
import java.util.*;

@Controller

public class BankController {

    private final BankService bankService;

    @Autowired
    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/payments")
    public String payments()
    {
        return "payment";
    }

    @PostMapping("/payments/")
    public void payment(HttpServletResponse response,@RequestParam("numberTel") String telNum, @RequestParam("Amount") int tg, @RequestParam("CardNumber") Long card) throws IOException {
        System.out.println(telNum+" "+tg+" "+card);

        if(bankService.findById(card)!=null) {
            payMobile(tg, card.intValue());
        }
        response.sendRedirect("/history/"+card);

    }

    public int payMobile(int tg, int card){
        Card card1=bankService.findById(Long.valueOf(card));
        card1.setAmount(card1.getAmount()-tg);
        card1.getAmount();
        System.out.println(card1);
        bankService.saveAmount(card1);

        History history=new History();
        history.setAmount(tg);
        history.setCard_id(String.valueOf(card));
        Date date =new Date();
        history.setTime(date.toString());
        history.setType("-");
        history.setItogo(card1.getAmount());
        bankService.saveHistory(history);
        System.out.println(history);

        return card1.getAmount()-tg;
    }
    @GetMapping("/transfers-boot")
    public String trans()
    {
        return "transfer-boot";
    }

    @PostMapping("/transfers-boot/")
    public void maketrans(HttpServletResponse response, @RequestParam("a") String radio, @RequestParam("cardN1") Long card1, @RequestParam("cardN2") Long card2, @RequestParam("amount") int tg) throws IOException {
        if(bankService.findById(card1)!=null&&bankService.findById(card2)!=null){
            System.out.println(radio);
            Card card=bankService.findById(card1);
            card.setAmount(card.getAmount()-tg);
            System.out.println(card);
            bankService.saveAmount(card);

            Card cardN2=bankService.findById(card2);
            int a=0;

            if(radio.equals("another bank")){
                a=tg-(tg/100);

            }else if(radio.equals("this bank")){
                if(tg>100000){
                    a=tg-(tg/100);

                }else {
                    a=tg;
                }
            }else if(radio.equals("between your accounts")){
                a=tg;
            }else System.out.println("h");

            System.out.println(a);
            cardN2.setAmount(cardN2.getAmount()+a);
            System.out.println(cardN2);
            bankService.saveAmount(cardN2);

            History history=new History();
            history.setAmount(tg);
            history.setCard_id(card1.toString());
            java.util.Date date =new Date();
            history.setTime(date.toString());
            history.setType("-");
            history.setItogo(card.getAmount());
            bankService.saveHistory(history);

            History history1=new History();
            history1.setAmount(a);
            history1.setCard_id(card2.toString());
            history1.setTime(date.toString());
            history1.setType("+");
            history1.setItogo(cardN2.getAmount());
            bankService.saveHistory(history1);

            System.out.println(history);
            System.out.println(history1);

        }
        response.sendRedirect("/history/"+card1);
    }

    @GetMapping("history/{id}")
    public String blogdetail(@PathVariable("id")String id, Model model)
    {
        List<History> histories=bankService.showAll(id);
        Collections.sort(histories,new Sort());
        List<History> hisList = new ArrayList<>();
        for (int i=histories.size()-11; i>=0; i--) {
            System.out.println(histories.get(i));
            hisList.add(histories.get(i));
        }
        model.addAttribute("historyList", hisList);
        System.out.println(histories);
        return "history";
    }



}
