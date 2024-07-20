package org.sklad.main;

import org.sklad.repository.ProgrammerRepo;

public class Main {
    public static void main(String[] args) {
        new Controller();

        // Code to execute before program termination
        Runtime.getRuntime().addShutdownHook(new Thread(ProgrammerRepo::saveData));
    }
}