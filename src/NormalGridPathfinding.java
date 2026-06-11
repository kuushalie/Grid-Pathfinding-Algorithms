import java.util.*;
public class NormalGridPathfinding {

	    public static void main(String[] args) {
	        
	        int[][] grid = new int[100][100];
	        Random random = new Random();

	        
	        for (int i = 0; i < 1000; i++) {
	            int x = random.nextInt(100);
	            int y = random.nextInt(100);
	            grid[x][y] = 1; // Set obstacle
	        }

	        // Print the grid 
	        System.out.println("Grid:");
	        /*for (int[] row : grid) {
	            for (int cell : row) {
	                System.out.print(cell + " ");
	            }
	            System.out.println();
	        }*/

	        // BFS algorithm
	        int[] start = {0, 0}; // Start
	        int[] goal = {99, 99}; // Goal 
	        long startTime = System.nanoTime();
	        Map<String, Object> bfsResult = bfs(grid, start, goal);
	        long endTime = System.nanoTime();
	        System.out.println("\nBFS Algorithm:");
	        System.out.println("Shortest path cost from start to goal: " + bfsResult.get("cost"));
	        System.out.println("Path traveled: " + bfsResult.get("path"));
	        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
	        System.out.println("Time complexity: O(V + E)");
	        System.out.println("Space complexity: O(V)");

	        // Test A* algorithm
	        startTime = System.nanoTime();
	        Map<String, Object> aStarResult = aStar(grid, start, goal);
	        endTime = System.nanoTime();
	        System.out.println("\nA* Algorithm:");
	        System.out.println("Shortest path cost from start to goal: " + aStarResult.get("cost"));
	        System.out.println("Path traveled: " + aStarResult.get("path"));
	        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
	        System.out.println("Time complexity: O((V + E) log V)");
	        System.out.println("Space complexity: O(V)");

	        // Dijkstra's algorithm
	        startTime = System.nanoTime();
	        Map<String, Object> dijkstraResult = dijkstra(grid, start, goal);
	        endTime = System.nanoTime();
	        System.out.println("\nDijkstra's Algorithm:");
	        System.out.println("Shortest path cost from start to goal: " + dijkstraResult.get("cost"));
	        System.out.println("Path traveled: " + dijkstraResult.get("path"));
	        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
	        System.out.println("Time complexity: O((V + E) log V)");
	        System.out.println("Space complexity: O(V)");
	    }

	    public static Map<String, Object> bfs(int[][] grid, int[] start, int[] goal) {
	        int width = grid.length;
	        int height = grid[0].length;

	        Queue<int[]> frontier = new LinkedList<>();
	        frontier.add(new int[]{start[0], start[1]});

	        Map<String, Integer> costSoFar = new HashMap<>();
	        costSoFar.put(start[0] + "," + start[1], 0);

	        Map<String, String> cameFrom = new HashMap<>();
	        cameFrom.put(start[0] + "," + start[1], null);

	        while (!frontier.isEmpty()) {
	            int[] current = frontier.poll();
	            int currentX = current[0];
	            int currentY = current[1];
	            int currentCost = costSoFar.get(currentX + "," + currentY);

	            if (currentX == goal[0] && currentY == goal[1]) {
	                List<String> path = reconstructPath(cameFrom, start, goal);
	                Map<String, Object> result = new HashMap<>();
	                result.put("cost", currentCost);
	                result.put("path", path);
	                return result;
	            }

	            for (int[] neighbor : getNeighbors(currentX, currentY, width, height)) {
	                int nextX = neighbor[0];
	                int nextY = neighbor[1];
	                if (grid[nextX][nextY] == 1) continue; // Skip obstacles

	                String nextKey = nextX + "," + nextY;
	                if (!costSoFar.containsKey(nextKey)) {
	                    costSoFar.put(nextKey, currentCost + 1);
	                    frontier.add(new int[]{nextX, nextY});
	                    cameFrom.put(nextKey, currentX + "," + currentY);
	                }
	            }
	        }

	        Map<String, Object> result = new HashMap<>();
	        result.put("cost", -1); // no path was found
	        return result;
	    }

	    public static Map<String, Object> dijkstra(int[][] grid, int[] start, int[] goal) {
	        int width = grid.length;
	        int height = grid[0].length;

	        PriorityQueue<int[]> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
	        frontier.add(new int[]{start[0], start[1], 0});

	        Map<String, Integer> costSoFar = new HashMap<>();
	        costSoFar.put(start[0] + "," + start[1], 0);

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

	            for (int[] neighbor : getNeighbors(currentX, currentY, width, height)) {
	                int nextX = neighbor[0];
	                int nextY = neighbor[1];
	                if (grid[nextX][nextY] == 1) continue; // Skip obstacles
	                int newCost = currentCost + 1;

	                String nextKey = nextX + "," + nextY;
	                if (!costSoFar.containsKey(nextKey) || newCost < costSoFar.get(nextKey)) {
	                    costSoFar.put(nextKey, newCost);
	                    frontier.add(new int[]{nextX, nextY, newCost});
	                    cameFrom.put(nextKey, currentX + "," + currentY);
	                }
	            }
	        }

	        Map<String, Object> result = new HashMap<>();
	        result.put("cost", -1); // no path was found
	        return result;
	    }

	    public static Map<String, Object> aStar(int[][] grid, int[] start, int[] goal) {
	        int width = grid.length;
	        int height = grid[0].length;

	        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(a -> a.priority));
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

	            for (int[] neighbor : getNeighbors(current.x, current.y, width, height)) {
	                int nextX = neighbor[0];
	                int nextY = neighbor[1];
	                if (grid[nextX][nextY] == 1) continue; // Skip obstacles
	                int newCost = current.cost + 1;

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
	        result.put("cost", -1); //no path was found
	        return result;
	    }

	    private static List<int[]> getNeighbors(int x, int y, int width, int height) {
	        List<int[]> neighbors = new ArrayList<>();
	        if (x > 0) neighbors.add(new int[]{x - 1, y});
	        if (x < width - 1) neighbors.add(new int[]{x + 1, y});
	        if (y > 0) neighbors.add(new int[]{x, y - 1});
	        if (y < height - 1) neighbors.add(new int[]{x, y + 1});
	        return neighbors;
	    }

	    private static List<String> reconstructPath(Map<String, String> cameFrom, int[] start, int[] goal) {
	        List<String> path = new ArrayList<>();
	        String current = goal[0] + "," + goal[1];
	        while (!current.equals(start[0] + "," + start[1])) {
	            path.add(current);
	            current = cameFrom.get(current);
	        }
	        path.add(start[0] + "," + start[1]);
	        Collections.reverse(path);
	        return path;
	    }

	    private static int heuristic(int[] a, int[] b) {
	        return Math.abs(a[0] - b[0]) + Math.abs(a[1] - b[1]);
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
