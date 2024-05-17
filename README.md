# Summer-Project - Overview

For my first year summer project, I chose project number 2 - Social Networks.

My project contains 5 classes;

  Analysis.java - Uses methods from Graph.java to solve each task and print the output. The constructor creates the graph.
  
  Graph.java - Contains methods to build a graph, and some methods to abstract away the logic of each task.

  Node.java - Class to model a node in a graph. Each node is an Array List of edges and has name, followers, following and reach attributes.

  Edge.java - Class to model an directed edge in a graph. Each edge has a start and end node.

  InputReader.java - Parses input text and turns each line into an array of strings.

# Usage
```text
java Analysis <filename>
```
