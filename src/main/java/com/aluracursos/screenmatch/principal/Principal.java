package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporadas;
import com.aluracursos.screenmatch.service.ConsumoApi;
import com.aluracursos.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoApi consumoApi = new ConsumoApi();
    private final String URL_BASE = "http://www.omdbapi.com/?t=%s";
    private final String APIKEY = "&apikey=35a94f24";
    private ConvierteDatos conversor = new ConvierteDatos();

    public void muestraElMenu(){
        System.out.println("Por favot escribe el nombre de la serie que deseas buscar: ");

       // Busca los datos generales de las series
        var nombreSerie = teclado.nextLine().replace(" ", "+");
        var urlFinal = String.format(URL_BASE + APIKEY , nombreSerie);
        var json = consumoApi.obtenerDatos(urlFinal);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        // Busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalDeTemporadas(); i++) {
            var stringSeason = "&Season=" + i;
            urlFinal = String.format(URL_BASE + APIKEY + stringSeason, nombreSerie);
            json = consumoApi.obtenerDatos(urlFinal);
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println);
    }
}
