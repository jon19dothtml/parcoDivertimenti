package it.its.modulo.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        PojoExample pojoExample = new PojoExample();
//        pojoExample.nome = "Juan";
//        pojoExample.cognome = "Lungomare";
//        pojoExample.CodiceFiscale = "JGBNPO12A662X";

//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("pojo_out.txt"))) {
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            String json = gson.toJson(example);
//            writer.write(json);
//        } catch(IOException e) {
//            System.out.println("Errore io");
//            e.printStackTrace();
//        }
        try (BufferedReader reader = new BufferedReader(new FileReader("pojo_out.txt"))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = reader.readLine();
            PojoExample pojoExample= gson.fromJson(json, PojoExample.class);
            System.out.println("Hai letto questo file JSON: \n" +json);
            System.out.println("L'oggetto letto è : \n" +pojoExample);
        } catch(IOException e) {
            System.out.println("Errore io");
            e.printStackTrace();
        }
    }
}