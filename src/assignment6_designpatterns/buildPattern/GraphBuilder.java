package assignment6_designpatterns.buildPattern;

import java.util.*;

// Design Pattern: Builder
// Problem: Graf-konstruktionen (byer + kanter) var blandet direkte ind i main(),
// hvilket gjorde koden svær at læse og vedligeholde. main() havde ansvaret for
// både at oprette City-objekter, registrere dem i grafen OG tilføje kanter —
// tre separate opgaver rodede sammen ét sted.
// Løsning: GraphBuilder samler al konstruktionslogik i én dedikeret klasse og
// tilbyder en flydende API så grafen kan beskrives trin for trin på en læsbar måde.
// Pattern passer her fordi grafen kræver mange konfigurationstrin i en bestemt
// rækkefølge (byer før kanter) — præcis det problem Builder er designet til at løse.
public class GraphBuilder {

    // Intern opbevaring af byer — LinkedHashMap bevarer indsætningsrækkefølgen
    // så tabellen udskrives i samme rækkefølge som vi tilføjede byerne
    private final Map<String, City> cities = new LinkedHashMap<>();

    // Selve grafen — City mapper til en liste af udgående kanter
    private final Map<City, List<Edge>> graph = new LinkedHashMap<>();

    // Opretter en by og registrerer den internt
    // Returnerer 'this' så kald kan kædes sammen (method chaining)
    public GraphBuilder addCity(String name) {
        City city = new City(name);
        cities.put(name, city);
        graph.put(city, new ArrayList<>());
        return this;
    }

    // Slår begge byer op ved navn og tilføjer en rettet kant mellem dem
    // Kaster en fejl hvis en by ikke er oprettet endnu — byer skal tilføjes før kanter
    public GraphBuilder addEdge(String from, String to, int weight) {
        City fromCity = cities.get(from);
        City toCity   = cities.get(to);

        if (fromCity == null || toCity == null) {
            throw new IllegalArgumentException(
                    "By ikke fundet — tilføj byer med addCity() før du tilføjer kanter"
            );
        }

        graph.get(fromCity).add(new Edge(toCity, weight));
        return this;
    }

    // Slår en enkelt by op ved navn — bruges i main() til at hente start og mål
    public City getCity(String name) {
        City city = cities.get(name);
        if (city == null) {
            throw new IllegalArgumentException("By ikke fundet: " + name);
        }
        return city;
    }

    // Returnerer alle byer i indsætningsrækkefølge — bruges til at printe tabellen
    public List<City> getCities() {
        return new ArrayList<>(cities.values());
    }

    // Returnerer den færdige graf — kaldes sidst når alt er konfigureret
    public Map<City, List<Edge>> build() {
        return graph;
    }
}