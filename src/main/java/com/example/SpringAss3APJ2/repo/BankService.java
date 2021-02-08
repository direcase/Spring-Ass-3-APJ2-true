package com.example.SpringAss3APJ2.repo;

import com.example.SpringAss3APJ2.model.Card;
import com.example.SpringAss3APJ2.model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final BankRepo bankRepo;
    private final HistoryRepo historyRepo;


    @Autowired
    public BankService(BankRepo bankRepo, HistoryRepo historyRepo) {
        this.bankRepo = bankRepo;
        this.historyRepo = historyRepo;
    }

    public Card findById(Long id){
        return bankRepo.findById(id).orElse(null);
    }

    public Card saveAmount(Card card){
        return bankRepo.save(card);
    }

    public History saveHistory(History history){
        return historyRepo.save(history);
    }

    public List<History> showAll(String id){
        return historyRepo.findAll();
    }
}
