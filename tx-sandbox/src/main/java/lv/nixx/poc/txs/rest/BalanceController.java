package lv.nixx.poc.txs.rest;


import lv.nixx.poc.txs.BalanceService;
import lv.nixx.poc.txs.data.model.Container;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @Autowired
    private BalanceService balanceService;

    @PostMapping("/bulk")
    public Container saveAllInTransaction(@RequestBody Container c) {
        return balanceService.saveAllInTransaction(c);
    }

    @PostMapping("/bulkSaveInInternalMethod")
    public Container saveAllInTransactionInternalMethod(@RequestBody Container c) {
        return balanceService.saveInTxnInternalMethodCall(c);
    }


}
