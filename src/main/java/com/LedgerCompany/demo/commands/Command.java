package com.LedgerCompany.demo.commands;

import com.LedgerCompany.demo.State;
import com.LedgerCompany.demo.service.LedgerService;

public abstract class Command {
    String args[];
    LedgerService ledgerService;

    public Command(String[] args) {
        this.args = args;
    }

    public final void setLedgerService(LedgerService ledgerService){
        this.ledgerService = ledgerService;
    }

    public abstract void apply(State state);
}
