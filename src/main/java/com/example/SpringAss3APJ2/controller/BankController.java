package com.example.SpringAss3APJ2.controller;


import com.example.SpringAss3APJ2.model.Card;
import com.example.SpringAss3APJ2.model.History;
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
import java.util.Date;
import java.util.List;

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
           /* Card card1=bankService.findById(card);
            card1.setAmount(card1.getAmount()-tg);
            card1.getAmount();
            System.out.println(card1);
            bankService.saveAmount(card1);

            History history=new History();
            history.setAmount(tg);
            history.setCard_id(card);
            java.util.Date date =new Date();
            history.setTime(date.toString());
            history.setType("-");
            bankService.saveHistory(history);
            System.out.println(history);*/

        }
        response.sendRedirect("/history/"+card);

    }
    @GetMapping("/transfers-boot")
    public String trans()
    {
        return "transfer-boot";
    }

    @PostMapping("/transfers-boot/")
    public void maketrans(HttpServletResponse response, @RequestParam("cardN1") Long card1, @RequestParam("cardN2") Long card2, @RequestParam("amount") int tg) throws IOException {
        if(bankService.findById(card1)!=null&&bankService.findById(card2)!=null){
            /*Card card=bankService.findById(card1);
            card.setAmount(card.getAmount()-tg);
            System.out.println(card);
            bankService.saveAmount(card);

            Card cardN2=bankService.findById(card2);
            cardN2.setAmount(cardN2.getAmount()+tg);
            System.out.println(cardN2);
            bankService.saveAmount(cardN2);

            History history=new History();
            history.setAmount(tg);
            history.setCard_id(card1);
            java.util.Date date =new Date();
            history.setTime(date.toString());
            history.setType("-");
            bankService.saveHistory(history);

            History history1=new History();
            history1.setAmount(tg);
            history1.setCard_id(card2);
            history1.setTime(date.toString());
            history1.setType("+");
            bankService.saveHistory(history1);

            System.out.println(history);
            System.out.println(history1);*/

        }
        response.sendRedirect("/history/"+card1);
    }

    @GetMapping("history/{id}")
    public String blogdetail(@PathVariable("id")Long id, Model model)
    {
        List<History> histories=bankService.showAll();
        model.addAttribute("historyList", histories);
        System.out.println(histories);
        return "history";
    }

}
