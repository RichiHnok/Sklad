package org.sklad.repository;

import org.sklad.db.DB;

public class ProgrammerRepo {

    public static void saveData(){
        DB.saveToFile();
    }
}
