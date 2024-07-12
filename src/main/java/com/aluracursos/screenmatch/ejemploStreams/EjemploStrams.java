package com.aluracursos.screenmatch.ejemploStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EjemploStrams {
    public void ejemploStrams (){
        List<String> nombres = Arrays.asList("Brenda", "Luis", "Maria", "Eric", "Genesys");

        // Strams -> permite realizar operaciones encadenadas desde java 8
        nombres.stream()
                .sorted()
                .limit(2)
                .filter(n-> n.startsWith("B"))
                .map(e-> e.toUpperCase())
                .forEach(System.out::println);
    }
}
