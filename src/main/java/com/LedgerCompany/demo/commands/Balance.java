package com.LedgerCompany.demo.commands;

import com.LedgerCompany.demo.State;
import com.LedgerCompany.demo.helpers.BalanceCommandResponse;

public class Balance extends Command {
    public Balance(String[] args) {
        super(args);
    }

    @Override
    public void apply(State state) {
        try {
            String bankName = args[0];
            String customerName = args[1];
            Integer emiNo = Integer.valueOf(args[2]);

            BalanceCommandResponse commandResponse = ledgerService.getClosingBalanceForLoanAfterEmiNo(bankName, customerName, emiNo);
            state.setOutput(formatResponse(commandResponse));
        } catch (Exception e){
            System.out.println("Exception in Balance.apply - " + e.getMessage());
        }
    }

    private String formatResponse(BalanceCommandResponse commandResponse){
        StringBuilder builder = new StringBuilder();
        builder.append(commandResponse.getBankName()).append(" ");
        builder.append(commandResponse.getCustomerName()).append(" ");
        builder.append(commandResponse.getAmountPaid()).append(" ");
        builder.append(commandResponse.getEmiLeft());
        return builder.toString();
    }
}
