# Grid Pathfinding Algorithms

## Overview

This project implements and evaluates several classical pathfinding algorithms on both conventional and weighted grid environments. The goal is to compare algorithm performance in terms of shortest-path cost, execution time, time complexity, and space complexity.

The project was developed as part of a comparative study on grid-based pathfinding techniques commonly used in game development, robotics, autonomous navigation, and route optimization systems.

---

## Algorithms Implemented

### Conventional Grid

* Breadth-First Search (BFS)
* Dijkstra's Algorithm
* A* Search Algorithm

### Weighted Grid

* Dijkstra's Algorithm
* A* Search Algorithm
* Weighted Grid Pathfinding Approach

---

## Project Objectives

* Implement pathfinding algorithms on a 100 × 100 grid.
* Compare algorithm efficiency and accuracy.
* Measure execution time and path cost.
* Analyze time and space complexity.
* Study the behavior of algorithms in weighted and unweighted environments.

---

## Grid Configurations

### Conventional Grid

* Grid Size: 100 × 100
* Randomly generated obstacles
* Uniform movement cost

### Weighted Grid

* Grid Size: 100 × 100
* Randomly generated cell weights
* Different traversal costs for each cell

---

## Performance Metrics

The algorithms are evaluated using the following metrics:

* Shortest Path Cost
* Execution Time
* Time Complexity
* Space Complexity

---

## Algorithm Analysis

### Breadth-First Search (BFS)

BFS explores neighboring nodes level by level and guarantees the shortest path in an unweighted environment.

**Time Complexity**

O(V + E)

**Space Complexity**

O(V)

---

### Dijkstra's Algorithm

Dijkstra's algorithm computes the shortest path by considering cumulative traversal costs.

**Time Complexity**

O((V + E) log V)

**Space Complexity**

O(V)

---

### A* Search Algorithm

A* improves search efficiency using heuristic functions such as Manhattan Distance.

**Time Complexity**

O((V + E) log V)

**Space Complexity**

O(V)

---

## Experimental Results

### Conventional Grid

| Algorithm | Cost | Time Complexity | Space Complexity |
| --------- | ---- | --------------- | ---------------- |
| BFS       | 198  | O(V + E)        | O(V)             |
| Dijkstra  | 198  | O((V+E)logV)    | O(V)             |
| A*        | 198  | O((V+E)logV)    | O(V)             |

### Weighted Grid

| Algorithm                 | Cost | Time Complexity | Space Complexity |
| ------------------------- | ---- | --------------- | ---------------- |
| Dijkstra                  | 429  | O((V+E)logV)    | O(V)             |
| A*                        | 423  | O((V+E)logV)    | O(V)             |
| Weighted Grid Pathfinding | 423  | O(ElogV)        | O(V)             |

---

## Applications

* Video Game AI
* Robotics Navigation
* Autonomous Vehicles
* Geographic Information Systems
* Route Optimization
* Logistics and Delivery Planning

---

## How to Run

### Prerequisites

* Java JDK 17 or later
* Visual Studio Code (optional)

### Compile

```bash
javac NormalGridPathfinding.java
javac WeightedGridPathfinding.java
```

### Run

```bash
java NormalGridPathfinding
java WeightedGridPathfinding
```

---

## Repository Structure

```text
Grid-Pathfinding-Algorithms/
│
├── README.md
├── LICENSE
├── .gitignore
│
├── src/
│   ├── NormalGridPathfinding.java
│   └── WeightedGridPathfinding.java
│
└── docs/
    ├── PROJECT_DOCUMENTATION.md
    └── Project_Report.doc
```

---

## Future Scope

* Dynamic obstacle handling
* Real-world game map integration
* Interactive grid visualization
* JavaFX-based graphical interface
* Advanced heuristic algorithms
* Bidirectional A* implementation

---

## Authors

Yasasree Lasya A
Gayatri Yerukola
Asi Kuushalie
Aiswariya Milan K

---

## License

This project is intended for academic and educational purposes.
