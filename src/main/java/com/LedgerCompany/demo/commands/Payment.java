package com.LedgerCompany.demo.commands;

import com.LedgerCompany.demo.State;
import org.springframework.stereotype.Component;

public class Payment extends Command {
    public Payment(String[] args) {
        super(args);
    }

    @Override
    public void apply(State state) {
        try{
            String bankName = args[0];
            String customerName = args[1];
            Integer lumpsumAmount = Integer.valueOf(args[2]);
            Integer emiNo = Integer.valueOf(args[3]);

            ledgerService.registerPaymentAndGenerateLedgers(bankName, customerName, lumpsumAmount, emiNo);
            state.setOutput(State.NOOUTPUT);
        } catch (Exception e){
            state.setOutput("Failure");
            System.out.println("Exception in Payment.apply " + e.getMessage());
        }
    }
}
