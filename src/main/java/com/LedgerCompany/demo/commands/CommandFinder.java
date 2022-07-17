package com.LedgerCompany.demo.commands;

import java.util.Arrays;

public class CommandFinder {
    private interface allCommands {
        String BALANCE = "BALANCE";
        String LOAN = "LOAN";
        String PAYMENT = "PAYMENT";
    }

    public static Command findFromString(String input){
        if(input == null || input.equals("")){
            return new InvalidCommand(null);
        }
        String[] tokens = input.split(" ");
        if (tokens.length < 1) {
            return new InvalidCommand(null);
        }
        String commandName = tokens[0];
        String[] args = Arrays.copyOfRange(tokens, 1, tokens.length);
        // Can add more validations on commands like 4th argument should be Integer etc
        switch (commandName) {
            case allCommands.BALANCE:
                if(tokens.length == 4) {
                    return new Balance(args);
                } else return new InvalidCommand(args);

            case allCommands.LOAN:
                if(tokens.length == 6) {
                    return new Loan(args);
                } else return new InvalidCommand(args);

            case allCommands.PAYMENT:
                if(tokens.length == 5) {
                    return new Payment(args);
                }
            default:
                return new InvalidCommand(args);
        }
    }
}
