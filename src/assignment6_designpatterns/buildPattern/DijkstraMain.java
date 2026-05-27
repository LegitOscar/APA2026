package assignment6_designpatterns.buildPattern;

import java.util.*;

public class DijkstraMain {

    // ANSI farve-koder til terminal output
    static final String RESET  = "\033[0m";
    static final String BOLD   = "\033[1m";
    static final String GREEN  = "\033[32m";
    static final String YELLOW = "\033[33m";
    static final String CYAN   = "\033[36m";
    static final String RED    = "\033[31m";
    static final String DIM    = "\033[2m";

    // ── Udskriv én linje i distancetabellen ──────────────────────────────────
    private static void printRow(City city, int dist, City prev,
                                 Set<City> visited, PriorityQueue<City> queue) {
        String distStr = (dist == Integer.MAX_VALUE) ? "∞" : dist + " km";
        String prevStr = (prev == null) ? "—" : prev.name;

        String status;
        if (visited.contains(city)) {
            status = GREEN + "✓ Visited" + RESET;
        } else if (queue.contains(city)) {
            status = YELLOW + "📬 I kø" + RESET;
        } else {
            status = DIM + "⬜ Ukendt" + RESET;
        }

        System.out.printf("│ %-15s │ %-8s │ %-15s │ %-20s │%n",
                city.name, distStr, prevStr, status);
    }

    // ── Udskriv hele tilstandstabellen ───────────────────────────────────────
    private static void printTable(Map<City, Integer> dist,
                                   Map<City, City> previous,
                                   Set<City> visited,
                                   PriorityQueue<City> queue,
                                   List<City> allCities) {
        System.out.println("┌─────────────────┬──────────┬─────────────────┬──────────────────────┐");
        System.out.println("│ By              │ Afstand  │ Via             │ Status               │");
        System.out.println("├─────────────────┼──────────┼─────────────────┼──────────────────────┤");
        for (City city : allCities) {
            printRow(city, dist.get(city), previous.get(city), visited, queue);
        }
        System.out.println("└─────────────────┴──────────┴─────────────────┴──────────────────────┘");
    }

    // ── Udskriv prioritetskøens indhold sorteret ──────────────────────────────
    private static void printQueue(PriorityQueue<City> queue, Map<City, Integer> dist) {
        List<City> sorted = new ArrayList<>(queue);
        sorted.sort(Comparator.comparingInt(dist::get));

        StringBuilder sb = new StringBuilder(CYAN + "Prioritetskø: [" + RESET);
        for (int i = 0; i < sorted.size(); i++) {
            City c = sorted.get(i);
            sb.append(CYAN).append(c.name).append(":").append(dist.get(c)).append(" km").append(RESET);
            if (i < sorted.size() - 1) sb.append(", ");
        }
        sb.append(CYAN + "]" + RESET);
        System.out.println(sb);
    }

    // ── Vent på at brugeren trykker ENTER ────────────────────────────────────
    private static void waitForEnter(Scanner scanner) {
        System.out.print(DIM + "\n  Tryk ENTER for næste trin..." + RESET);
        scanner.nextLine();
    }

    // ── Dijkstra med step-by-step forklaring ─────────────────────────────────
    public static Map<City, Integer> dijkstraTeaching(Map<City, List<Edge>> graph,
                                                      Map<City, City> previous,
                                                      City start,
                                                      City goal,
                                                      List<City> allCities) {
        Scanner scanner = new Scanner(System.in);

        Map<City, Integer> dist = new HashMap<>();
        for (City city : graph.keySet()) {
            dist.put(city, Integer.MAX_VALUE);
        }
        dist.put(start, 0);

        Set<City> visited = new LinkedHashSet<>();

        PriorityQueue<City> queue = new PriorityQueue<>(
                Comparator.comparingInt(dist::get)
        );
        queue.add(start);

        // ── Intro ─────────────────────────────────────────────────────────────
        System.out.println("\n" + BOLD + "╔══════════════════════════════════════════════════════════╗" + RESET);
        System.out.println(BOLD + "║          DIJKSTRAS ALGORITME — STEP-BY-STEP              ║" + RESET);
        System.out.println(BOLD + "╚══════════════════════════════════════════════════════════╝" + RESET);
        System.out.println("  Start : " + BOLD + start.name + RESET);
        System.out.println("  Mål   : " + BOLD + goal.name + RESET);
        System.out.println("\n  Alle afstande sættes til ∞. Startby sættes til 0.");
        System.out.println("  Startby tilføjes til prioritetskøen.\n");
        printTable(dist, previous, visited, queue, allCities);
        printQueue(queue, dist);
        waitForEnter(scanner);

        int stepNumber = 1;

        while (!queue.isEmpty()) {
            City current = queue.poll();

            System.out.println("\n" + BOLD
                    + "╔══════════════════════════════════════════════════════════╗");
            System.out.printf("║  TRIN %-2d — Behandler: %-20s (%-6s)  ║%n",
                    stepNumber, current.name,
                    dist.get(current) == Integer.MAX_VALUE ? "∞" : dist.get(current) + " km");
            System.out.println("╚══════════════════════════════════════════════════════════╝" + RESET);
            System.out.println(DIM + "  (Denne by har den laveste afstand i køen og behandles nu)" + RESET);

            List<Edge> neighbors = graph.get(current);
            System.out.println("\n  Naboer undersøges:");

            if (neighbors.isEmpty()) {
                System.out.println(DIM + "    (ingen udgående veje)" + RESET);
            }

            for (Edge edge : neighbors) {
                int currentDist  = dist.get(current);
                int newDist      = currentDist + edge.weight;
                int existingDist = dist.get(edge.destination);

                if (visited.contains(edge.destination)) {
                    System.out.printf("    → %-12s %3d + %3d = %3d km  %s%n",
                            edge.destination.name + ":",
                            currentDist, edge.weight, newDist,
                            DIM + "↷ Allerede visited — ignoreres" + RESET);
                    continue;
                }

                String existingStr = (existingDist == Integer.MAX_VALUE) ? "∞" : existingDist + " km";

                if (newDist < existingDist) {
                    System.out.printf("    → %-12s %3d + %3d = %3d km  %s  (var %s)%n",
                            edge.destination.name + ":",
                            currentDist, edge.weight, newDist,
                            GREEN + "✓ Forbedring!" + RESET,
                            existingStr);
                    dist.put(edge.destination, newDist);
                    previous.put(edge.destination, current);
                    queue.remove(edge.destination);
                    queue.add(edge.destination);
                } else {
                    System.out.printf("    → %-12s %3d + %3d = %3d km  %s  (har allerede %s)%n",
                            edge.destination.name + ":",
                            currentDist, edge.weight, newDist,
                            RED + "✗ Ingen forbedring" + RESET,
                            existingStr);
                }
            }

            visited.add(current);
            System.out.println("\n  " + GREEN + BOLD + current.name
                    + " markeres som visited." + RESET);

            if (current.equals(goal)) {
                System.out.println("\n  " + GREEN + BOLD
                        + "🏁 Målet (" + goal.name + ") er nået! Algoritmen stopper." + RESET);
                printTable(dist, previous, visited, queue, allCities);
                break;
            }

            System.out.println();
            printTable(dist, previous, visited, queue, allCities);
            printQueue(queue, dist);

            stepNumber++;
            waitForEnter(scanner);
        }

        return dist;
    }

    // ── Rekonstruer stien ─────────────────────────────────────────────────────
    public static List<City> getPath(Map<City, City> previous, City start, City end) {
        List<City> path = new LinkedList<>();
        City current = end;
        while (current != null && !current.equals(start)) {
            path.add(0, current);
            current = previous.get(current);
        }
        if (current == null) return Collections.emptyList();
        path.add(0, start);
        return path;
    }

    public static void main(String[] args) {

        // ── Builder pattern: graf-konstruktion ───────────────────────────────
        // Tidligere blev byer oprettet som individuelle variabler og kanter
        // tilføjet manuelt via addEdge() direkte i main(). Det betød at main()
        // havde ansvaret for BÅDE at bygge grafen OG at køre algoritmen.
        // GraphBuilder samler al konstruktionslogik ét sted og giver en
        // flydende API — main() beskriver bare hvad den vil have, og builderen
        // håndterer resten.
        GraphBuilder builder = new GraphBuilder()
                .addCity("København")
                .addCity("Helsingør")
                .addCity("Hillerød")
                .addCity("Roskilde")
                .addCity("Køge")
                .addCity("Næstved")
                .addCity("Odense")
                .addCity("Vejle")
                .addCity("Aarhus")
                .addEdge("København", "Roskilde",   32)
                .addEdge("København", "Helsingør",  46)
                .addEdge("Helsingør", "Hillerød",   25)
                .addEdge("Hillerød",  "Roskilde",   40)
                .addEdge("Hillerød",  "Odense",    160)
                .addEdge("Roskilde",  "Køge",       28)
                .addEdge("Roskilde",  "Odense",    130)
                .addEdge("Køge",      "Næstved",    42)
                .addEdge("Køge",      "Vejle",     120)
                .addEdge("Næstved",   "Odense",     95)
                .addEdge("Odense",    "Vejle",      80)
                .addEdge("Vejle",     "Aarhus",     72);

        Map<City, List<Edge>> graph = builder.build();
        List<City> allCities        = builder.getCities();

        // ── Start og mål hentes fra builderen — ingen City-variabler i main ──
        City start = builder.getCity("København");
        City goal  = builder.getCity("Aarhus");

        // ── Kør teaching-mode ─────────────────────────────────────────────────
        Map<City, City> previous = new HashMap<>();
        Map<City, Integer> distances =
                dijkstraTeaching(graph, previous, start, goal, allCities);

        // ── Slutresultat ──────────────────────────────────────────────────────
        System.out.println("\n" + BOLD
                + "╔══════════════════════════════════════════════════════════╗");
        System.out.println("║                    SLUTRESULTAT                         ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝" + RESET);

        List<City> path = getPath(previous, start, goal);
        if (path.isEmpty()) {
            System.out.println("  Ingen sti fundet fra " + start + " til " + goal);
        } else {
            System.out.println("  Korteste rute : "
                    + BOLD + String.join(" → ", path.stream().map(c -> c.name).toList()) + RESET);
            System.out.println("  Total afstand : "
                    + BOLD + distances.get(goal) + " km" + RESET);
        }
        System.out.println();
    }
}