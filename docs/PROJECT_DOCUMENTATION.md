# Project Documentation

# Grid Pathfinding Algorithms: Comparative Performance Analysis

## 1. Introduction

Pathfinding algorithms are fundamental in computer science and play a crucial role in applications such as video games, robotics, autonomous navigation, logistics, transportation systems, and Geographic Information Systems (GIS). These algorithms determine the most efficient route between a starting point and a destination while considering various constraints such as obstacles, terrain costs, and movement restrictions.

This project focuses on implementing and comparing several well-known pathfinding algorithms on both conventional and weighted grid environments. The study evaluates their performance in terms of execution time, path cost, time complexity, and space complexity.

---

## 2. Problem Statement

Different pathfinding algorithms perform differently depending on the nature of the environment. Algorithms that work efficiently in unweighted grids may not perform optimally in weighted grids where each cell has an associated traversal cost.

The objective of this project is to compare multiple pathfinding algorithms and determine their suitability for different grid-based environments.

---

## 3. Objectives

The main objectives of this project are:

* Implement pathfinding algorithms on conventional and weighted grids.
* Compare algorithm performance using benchmarking techniques.
* Analyze execution time and path cost.
* Study theoretical time and space complexities.
* Evaluate the suitability of each algorithm for practical applications.

---

## 4. Grid Configurations

### 4.1 Conventional Grid

A conventional grid is represented as a 100 × 100 two-dimensional array.

Characteristics:

* Cells are either empty or occupied by obstacles.
* Obstacles are generated randomly.
* Movement cost between cells is uniform.
* Algorithms focus on finding the shortest path in terms of distance.

### 4.2 Weighted Grid

A weighted grid assigns a traversal cost to each cell.

Characteristics:

* Every cell contains a weight value.
* Different paths have different traversal costs.
* Algorithms must consider both distance and cumulative cost.
* Provides a more realistic representation of real-world environments.

---

## 5. Algorithms Implemented

### 5.1 Breadth-First Search (BFS)

Breadth-First Search explores nodes level by level starting from the source node.

#### Working Principle

1. Start from the source node.
2. Explore all neighboring nodes.
3. Continue exploring nodes level by level.
4. Stop when the destination node is reached.

#### Advantages

* Simple implementation.
* Guarantees shortest path in unweighted grids.

#### Limitations

* Inefficient for weighted environments.
* Requires significant memory for large grids.

#### Complexity

Time Complexity:

```text
O(V + E)
```

Space Complexity:

```text
O(V)
```

---

### 5.2 Dijkstra's Algorithm

Dijkstra's Algorithm computes the shortest path by considering cumulative traversal costs.

#### Working Principle

1. Initialize the source node with cost 0.
2. Use a priority queue to select the node with minimum cost.
3. Update neighboring node costs.
4. Continue until the destination is reached.

#### Advantages

* Produces optimal paths.
* Works effectively on weighted grids.

#### Limitations

* Slower than heuristic-based approaches.
* Explores more nodes than necessary.

#### Complexity

Time Complexity:

```text
O((V + E) log V)
```

Space Complexity:

```text
O(V)
```

---

### 5.3 A* Search Algorithm

A* combines the advantages of Dijkstra's Algorithm with heuristic guidance.

#### Working Principle

A* uses:

```text
f(n) = g(n) + h(n)
```

where:

* g(n) = actual cost from start node
* h(n) = heuristic estimate to goal node

The heuristic used in this project is Manhattan Distance.

### Manhattan Distance

```text
|x1 - x2| + |y1 - y2|
```

#### Advantages

* Faster than Dijkstra in most cases.
* Produces optimal paths when using admissible heuristics.
* Suitable for large search spaces.

#### Complexity

Time Complexity:

```text
O((V + E) log V)
```

Space Complexity:

```text
O(V)
```

---

### 5.4 Weighted Grid Pathfinding

The weighted grid implementation extends traditional pathfinding by incorporating cell traversal costs.

#### Features

* Random weight assignment.
* Cost-aware route selection.
* Suitable for realistic navigation scenarios.

#### Advantages

* More realistic than conventional grids.
* Supports varying terrain difficulties.

#### Complexity

Time Complexity:

```text
O(E log V)
```

Space Complexity:

```text
O(V)
```

---

## 6. Performance Metrics

The following metrics are used for evaluation.

### 6.1 Path Cost

Represents the total cost incurred while traveling from source to destination.

### 6.2 Execution Time

Measures the actual runtime of the algorithm in nanoseconds.

### 6.3 Time Complexity

Represents theoretical growth in runtime as input size increases.

### 6.4 Space Complexity

Represents memory usage during execution.

---

## 7. Experimental Setup

### Grid Size

```text
100 × 100
```

### Obstacle Generation

```text
1000 random obstacles
```

### Weight Generation

```text
Random weights between 0 and 9
```

### Start Position

```text
(0,0)
```

### Goal Position

```text
(99,99)
```

---

## 8. Experimental Results

### Conventional Grid Results

| Algorithm | Cost | Time Complexity | Space Complexity |
| --------- | ---- | --------------- | ---------------- |
| BFS       | 198  | O(V+E)          | O(V)             |
| Dijkstra  | 198  | O((V+E)logV)    | O(V)             |
| A*        | 198  | O((V+E)logV)    | O(V)             |

### Execution Time (Report)

| Algorithm | Execution Time (ns) |
| --------- | ------------------- |
| BFS       | 20,366,200          |
| Dijkstra  | 12,812,000          |
| A*        | 8,498,100           |

---

### Weighted Grid Results

| Algorithm                 | Cost | Time Complexity | Space Complexity |
| ------------------------- | ---- | --------------- | ---------------- |
| Dijkstra                  | 429  | O((V+E)logV)    | O(V)             |
| A*                        | 423  | O((V+E)logV)    | O(V)             |
| Weighted Grid Pathfinding | 423  | O(ElogV)        | O(V)             |

### Execution Time (Report)

| Algorithm                 | Execution Time (ns) |
| ------------------------- | ------------------- |
| Dijkstra                  | 28,496,100          |
| A*                        | 22,751,600          |
| Weighted Grid Pathfinding | 17,623,900          |

---

## 9. Analysis

The results indicate that:

* BFS performs effectively in unweighted environments.
* Dijkstra guarantees optimal paths but explores more nodes.
* A* consistently provides faster execution while maintaining optimality.
* Weighted pathfinding techniques perform better in weighted environments because traversal costs are considered.

Among all algorithms tested, A* demonstrated the best balance between efficiency and accuracy.

---

## 10. Applications

These algorithms can be used in:

* Game AI Navigation
* Robotics
* Autonomous Vehicles
* Route Planning
* Geographic Information Systems
* Warehouse Automation
* Delivery Optimization Systems

---

## 11. Conclusion

This project successfully implemented and compared multiple pathfinding algorithms on conventional and weighted grids.

The analysis demonstrates that:

* BFS is suitable for simple unweighted environments.
* Dijkstra is reliable for weighted pathfinding.
* A* offers superior performance due to heuristic guidance.
* Weighted pathfinding techniques are more realistic for practical navigation systems.

The choice of algorithm depends on the environment and performance requirements of the application.

---

## 12. Future Scope

Future improvements may include:

* Dynamic obstacle handling
* Real-time path replanning
* Interactive grid visualization using JavaFX
* Bidirectional A* implementation
* Integration with real-world game maps
* Multi-agent pathfinding
* 3D environment pathfinding
* Advanced heuristic optimization techniques

---

## 13. References

1. Moghadam, Sajjad Kardani, Morteza Ebrahimi, and Daniel D. Harabor. "GUARDS: Benchmarks for weighted grid-based pathfinding."
2. Wenzheng Li et al. "Improved Dijkstra's Algorithm for Shortest Path Planning on 2D Grid Maps."
3. Wei and Lu. "Comprehensive Study on Pathfinding Algorithms for Static 2D Square Grids."
4. Foead et al. "A Systematic Literature Review of A* Pathfinding."
5. Additional references included in the project report.
