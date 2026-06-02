package assignment6_designpatterns.buildPattern;

import java.util.*;

// Design Pattern: Builder
// Problem: graph construction was mixed directly into main() — cities, registration and edges all in one place
// Solution: GraphBuilder collects all construction logic in one class with a fluent API
// Cities must be added before edges — builder enforces this order
public class GraphBuilder {

    // LinkedHashMap preserves insertion order so cities print in the order they were added
    private final Map<String, City> cities = new LinkedHashMap<>();
    private final Map<City, List<Edge>> graph = new LinkedHashMap<>();

    // returns 'this' to allow method chaining
    public GraphBuilder addCity(String name) {
        City city = new City(name);
        cities.put(name, city);
        graph.put(city, new ArrayList<>());
        return this;
    }

    // throws if either city hasn't been added yet
    public GraphBuilder addEdge(String from, String to, int weight) {
        City fromCity = cities.get(from);
        City toCity   = cities.get(to);

        if (fromCity == null || toCity == null) {
            throw new IllegalArgumentException(
                    "City not found — add cities with addCity() before adding edges"
            );
        }

        graph.get(fromCity).add(new Edge(toCity, weight));
        return this;
    }

    public City getCity(String name) {
        City city = cities.get(name);
        if (city == null) throw new IllegalArgumentException("City not found: " + name);
        return city;
    }

    // returns cities in insertion order
    public List<City> getCities() {
        return new ArrayList<>(cities.values());
    }

    // call last — returns the completed graph
    public Map<City, List<Edge>> build() {
        return graph;
    }
}