import java.util.*;

public class WeightedGridPathfinding {

    public static void main(String[] args) {
        
        List<List<Integer>> weightedGrid = new ArrayList<>();

       
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 100; j++) {
                int weight = random.nextInt(10); 
                row.add(weight);
            }
            weightedGrid.add(row);
        }

        
        int numWeights = 50;
        for (int k = 0; k < numWeights; k++) {
            int x = random.nextInt(100);
            int y = random.nextInt(100);
            int weight = random.nextInt(10); 
            weightedGrid.get(x).set(y, weight);
        }

       
        System.out.println("Weighted Grid:");
        /*for (List<Integer> row : weightedGrid) {
            for (int weight : row) {
                System.out.print(weight + " ");
            }
            System.out.println();
        }*/

        // Dijkstra's algorithm
        int[] start = {0, 0}; // Start 
        int[] goal = {99, 99}; // Goal 
        long startTime = System.nanoTime();
        Map<String, Object> dijkstraResult = dijkstra(weightedGrid, start, goal);
        long endTime = System.nanoTime();
        System.out.println("\nDijkstra's Algorithm:");
        System.out.println("Time complexity: O((V+E)logV)");
        System.out.println("Space complexity: O(V)");
        System.out.println("Shortest path cost from start to goal: " + dijkstraResult.get("cost"));
        System.out.println("Path traveled: " + dijkstraResult.get("path"));
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");

        // A* algorithm
        startTime = System.nanoTime();
        Map<String, Object> aStarResult = aStar(weightedGrid, start, goal);
        endTime = System.nanoTime();
        System.out.println("\nA* Algorithm:");
        System.out.println("Time complexity: O((V+E)logV)");
        System.out.println("Space complexity: O(V)");
        System.out.println("Shortest path cost from start to goal: " + aStarResult.get("cost"));
        System.out.println("Path traveled: " + aStarResult.get("path"));
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");

        // Weighted Jump Point Search algorithm
        startTime = System.nanoTime();
        Map<String, Object> weightedJumpPointSearchResult = weightedJumpPointSearch(weightedGrid, start, goal);
        endTime = System.nanoTime();
        System.out.println("\nWeighted Jump Point Search Algorithm:");
        System.out.println("Time complexity: O(ElogV)");
        System.out.println("Space complexity: O(V)");
        System.out.println("Shortest path cost from start to goal: " + weightedJumpPointSearchResult.get("cost"));
        System.out.println("Path traveled: " + weightedJumpPointSearchResult.get("path"));
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
    }

    public static Map<String, Object> dijkstra(List<List<Integer>> grid, int[] start, int[] goal) {
        int width = grid.size();
        int height = grid.get(0).size();

        PriorityQueue<int[]> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        frontier.add(new int[]{start[0], start[1], grid.get(start[0]).get(start[1])});

        Map<String, Integer> costSoFar = new HashMap<>();
        costSoFar.put(start[0] + "," + start[1], grid.get(start[0]).get(start[1]));

        Map<String, String> cameFrom = new HashMap<>();
        cameFrom.put(start[0] + "," + start[1], null);

        while (!frontier.isEmpty()) {
            int[] current = frontier.poll();
            int currentX = current[0];
            int currentY = current[1];
            int currentCost = current[2];

            if (currentX == goal[0] && currentY == goal[1]) {
                List<String> path = reconstructPath(cameFrom, start, goal);
                Map<String, Object> result = new HashMap<>();
                result.put("cost", currentCost);
                result.put("path", path);
                return result;
            }

            List<int[]> neighbors = getNeighbors(currentX, currentY, width, height);
            for (int[] neighbor : neighbors) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                int newCost = currentCost + grid.get(nextX).get(nextY);

                String nextKey = nextX + "," + nextY;
                if (!costSoFar.containsKey(nextKey) || newCost < costSoFar.get(nextKey)) {
                    costSoFar.put(nextKey, newCost);
                    frontier.add(new int[]{nextX, nextY, newCost});
                    cameFrom.put(nextKey, currentX + "," + currentY);
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("cost", -1); // Indicates that no path was found
        return result;
    }

    public static Map<String, Object> aStar(List<List<Integer>> grid, int[] start, int[] goal) {
        int width = grid.size();
        int height = grid.get(0).size();

        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        frontier.add(new Node(start[0], start[1], 0, heuristic(start, goal)));

        Map<String, Integer> costSoFar = new HashMap<>();
        costSoFar.put(start[0] + "," + start[1], 0);

        Map<String, String> cameFrom = new HashMap<>();
        cameFrom.put(start[0] + "," + start[1], null);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.x == goal[0] && current.y == goal[1]) {
                List<String> path = reconstructPath(cameFrom, start, goal);
                Map<String, Object> result = new HashMap<>();
                result.put("cost", current.cost);
                result.put("path", path);
                return result;
            }

            List<int[]> neighbors = getNeighbors(current.x, current.y, width, height);
            for (int[] neighbor : neighbors) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                int newCost = current.cost + grid.get(nextX).get(nextY);
                String nextKey = nextX + "," + nextY;
                if (!costSoFar.containsKey(nextKey) || newCost < costSoFar.get(nextKey)) {
                    costSoFar.put(nextKey, newCost);
                    int priority = newCost + heuristic(new int[]{nextX, nextY}, goal);
                    frontier.add(new Node(nextX, nextY, newCost, priority));
                    cameFrom.put(nextKey, current.x + "," + current.y);
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("cost", -1); 
        return result;
    }

    public static Map<String, Object> weightedJumpPointSearch(List<List<Integer>> grid, int[] start, int[] goal) {
        int width = grid.size();
        int height = grid.get(0).size();

        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        frontier.add(new Node(start[0], start[1], 0, heuristic(start, goal)));

        Map<String, Integer> costSoFar = new HashMap<>();
        costSoFar.put(start[0] + "," + start[1], 0);

        Map<String, String> cameFrom = new HashMap<>();
        cameFrom.put(start[0] + "," + start[1], null);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.x == goal[0] && current.y == goal[1]) {
                List<String> path = reconstructPath(cameFrom, start, goal);
                Map<String, Object> result = new HashMap<>();
                result.put("cost", current.cost);
                result.put("path", path);
                return result;
            }

            List<int[]> neighbors = getNeighbors(current.x, current.y, width, height);
            for (int[] neighbor : neighbors) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                int newCost = current.cost + grid.get(nextX).get(nextY);

                String nextKey = nextX + "," + nextY;
                if (!costSoFar.containsKey(nextKey) || newCost < costSoFar.get(nextKey)) {
                    costSoFar.put(nextKey, newCost);
                    int priority = newCost + heuristic(new int[]{nextX, nextY}, goal);
                    frontier.add(new Node(nextX, nextY, newCost, priority));
                    cameFrom.put(nextKey, current.x + "," + current.y);
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("cost", -1);
        return result;
    }

    public static List<int[]> getNeighbors(int x, int y, int width, int height) {
        List<int[]> neighbors = new ArrayList<>();
        if (x > 0) neighbors.add(new int[]{x - 1, y});
        if (x < width - 1) neighbors.add(new int[]{x + 1, y});
        if (y > 0) neighbors.add(new int[]{x, y - 1});
        if (y < height - 1) neighbors.add(new int[]{x, y + 1});
        return neighbors;
    }

    public static List<String> reconstructPath(Map<String, String> cameFrom, int[] start, int[] goal) {
        List<String> path = new ArrayList<>();
        int[] current = {goal[0], goal[1]};
        while (!Arrays.equals(current, start)) {
            path.add(current[0] + "," + current[1]);
            String previous = cameFrom.get(current[0] + "," + current[1]);
            current[0] = Integer.parseInt(previous.split(",")[0]);
            current[1] = Integer.parseInt(previous.split(",")[1]);
        }
        path.add(start[0] + "," + start[1]);
        Collections.reverse(path);
        return path;
    }

    public static int heuristic(int[] current, int[] goal) {
        int dx = current[0] - goal[0];
        int dy = current[1] - goal[1];
        return (int) Math.sqrt(dx * dx + dy * dy);
    }

    static class Node {
        int x, y, cost, priority;

        Node(int x, int y, int cost, int priority) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.priority = priority;
        }
    }
}
