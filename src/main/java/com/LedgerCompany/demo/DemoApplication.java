package com.LedgerCompany.demo;

import com.LedgerCompany.demo.commands.Command;
import com.LedgerCompany.demo.commands.CommandFinder;
import com.LedgerCompany.demo.service.LedgerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.File;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		LedgerService ledgerService = context.getBean(LedgerService.class);
		try {
			File file = new File("src/test1.txt");
			Scanner sc = new Scanner(file);
			State state = new State();

			while (sc.hasNextLine()) {
				String input = sc.nextLine();
				Command command = CommandFinder.findFromString(input);
				command.setLedgerService(ledgerService);
				command.apply(state);
				if(!state.getOutput().equals(State.NOOUTPUT))
					System.out.println(state.getOutput());
			}
		} catch (Exception e){
			System.out.println("Exception in reading file - " + e.getMessage());
		}

	}

}
