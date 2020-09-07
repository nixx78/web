package lv.nixx.poc.txs;

import lv.nixx.poc.txs.data.BalanceRepository;
import lv.nixx.poc.txs.data.TransactionRepository;
import lv.nixx.poc.txs.data.model.AccountBalance;
import lv.nixx.poc.txs.data.model.Container;
import lv.nixx.poc.txs.data.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private TransactionRepository txnRepo;

    @Autowired
    private BalanceRepository balanceRepository;

    @Transactional
    public Container saveAll(Container c) {

        final AccountBalance balance = c.getBalance();

        final List<Transaction> savedTxn = txnRepo.saveAll(c.getTxn());
        final AccountBalance savedBalance = balanceRepository.save(balance);

        if (balance.getAccountId().equals("Error")) {
            throw new IllegalArgumentException("Wrong account");
        }

        return new Container(savedTxn, savedBalance);
    }


}
