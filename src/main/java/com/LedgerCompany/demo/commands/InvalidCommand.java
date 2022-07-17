package com.LedgerCompany.demo.commands;

import com.LedgerCompany.demo.State;
import org.springframework.stereotype.Component;

public class InvalidCommand extends Command {
    public InvalidCommand(String[] args) {
        super(args);
    }

    @Override
    public void apply(State state) {
        state.setOutput("Failure: InvalidCommand");
    }
}
