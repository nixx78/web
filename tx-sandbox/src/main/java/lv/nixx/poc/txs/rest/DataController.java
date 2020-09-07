package lv.nixx.poc.txs.rest;


import lv.nixx.poc.txs.BalanceService;
import lv.nixx.poc.txs.data.BalanceRepository;
import lv.nixx.poc.txs.data.TransactionRepository;
import lv.nixx.poc.txs.data.model.AccountBalance;
import lv.nixx.poc.txs.data.model.Container;
import lv.nixx.poc.txs.data.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private TransactionRepository txnRepo;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private BalanceService balanceService;

    @GetMapping("/clearAll")
    public void clearAll() {
        txnRepo.deleteAll();
        balanceRepository.deleteAll();
    }


    @GetMapping("/transaction")
    public List<Transaction> getAllTransactions() {
        return txnRepo.findAll();
    }

    @GetMapping("/balance")
    public List<AccountBalance> getAllBalance() {
        return balanceRepository.findAll();
    }


    @PostMapping("/transaction")
    public Transaction add(@RequestBody Transaction txn) {
        return txnRepo.save(txn);
    }

    @PostMapping("/bulk")
    public Container saveAll(@RequestBody Container c) {
        return balanceService.saveAll(c);
    }


}
