package com.LedgerCompany.demo.commands;

import com.LedgerCompany.demo.State;

public class Loan extends Command {
    public Loan(String[] args) {
        super(args);
    }

    @Override
    public void apply(State state) {
        try {
            String bankName = args[0];
            String customerName = args[1];
            Integer principalAmount = Integer.valueOf(args[2]);
            Integer years = Integer.valueOf(args[3]);
            Double rateOfInterest = Double.valueOf(args[4]);
            ledgerService.createLoanEntryForUser(bankName, customerName, principalAmount, years, rateOfInterest);
            state.setOutput(State.NOOUTPUT);
        } catch (Exception e){
            System.out.println("Exception in Loan.apply " + e.getMessage());
            state.setOutput("Failure");
        }
    }
}
