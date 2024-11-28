# Cellular Automata Project

## Overview

This project implements a general framework for cellular automata in n-dimensional spaces. The cellular automata rules are designed to evolve grids of cells based on their neighborhood relationships and other predefined conditions. The project uses several Java classes to define the grid, cells, and various operators used for the evolution of the cellular automaton.

## Project Structure

Below is a detailed description of the main files and classes involved in this project:

### 1. **Neighbors.java**

- This class is responsible for defining the neighborhood relationships of cells in the grid. It calculates which cells are considered neighbors based on specific neighborhood types, such as Moore or von Neumann neighborhoods. Each neighborhood type may have different rules that determine the interaction between cells.

### 2. **InvalidNeighborException.java**

- This class is a custom exception used to handle scenarios where an invalid neighborhood is defined. This helps prevent errors when specifying neighborhoods that do not comply with the rules of the automaton.

### 3. **G26.java**

- Represents a specific type of neighborhood, called G26. This class is used to define neighbors of a cell in a 3D grid where all 26 surrounding cells are considered as neighbors. This is essential for the automata that require interaction in three-dimensional environments.

### 4. **SUPEQ.java**

- This class defines a custom operator called **SUPEQ**, used to evaluate certain conditions between cells, such as whether a value is greater than or equal to another. It is part of the arithmetic and logical operations used by the automaton rules.

### 5. **COMPTER.java**

- Represents a function for counting specific types of neighboring cells based on certain conditions. The class uses neighborhood information and evaluates the presence of certain values or states in the neighboring cells.

### 6. **ColorGrid.java**

- This class is used for displaying the grid with cells colored according to their states. It provides a visual representation of the evolution of the automaton, making it easier to understand and debug the process of cellular transformation.

### 7. **CellPanel.java**

- A graphical component used in the UI to represent individual cells within the grid. It allows each cell to be displayed as a colored element, which can change dynamically as the automaton evolves.

### 8. **TableauDynamiqueND.java**

- Implements an n-dimensional dynamic array, which serves as the backbone data structure for representing the cellular grid. This allows the automaton to function in any number of dimensions, providing a high level of flexibility for different types of simulations.

### 9. **Cellule.java**

- Represents an individual cell in the grid. The class stores the state of the cell and provides methods for manipulating its state based on the rules of the automaton.

### 10. **TraitRegle.java**

- Contains methods for parsing and evaluating rules of the automaton. It includes functionality for splitting rules into tokens and evaluating them recursively. The class supports operations like `ADD`, `SI`, `ET`, `OU`, `SUP`, `MUL`, and more. These operations are used to apply complex rules to cells, allowing for dynamic evolution of the grid.

### 11. **parcoursTab.java**

- This class provides methods for traversing and interacting with the grid. It helps iterate through the cells, applying rules, and gathering information about cell states.

### 12. **Grillev2.java**

- Represents an advanced version of the grid, providing enhanced methods for managing cells and their states. It allows for more sophisticated manipulation of the grid structure and cell interactions.

### 13. **SUP.java**

- Defines the **SUP** operator, which evaluates whether one value is greater than another. It is used in the logical conditions that dictate the evolution of cell states.

### 14. **MUL.java**

- Implements the **MUL** operator, which multiplies values together. This operator is used in the calculation of new cell states based on predefined rules.

### 15. **RegleGen.java**

- Generalizes the rules that can be applied to the cellular automaton. It provides a flexible way to define and manage various types of rules, making the system adaptable to different kinds of automata.

### 16. **SI.java**

- Implements conditional logic (`if-then`) for the automaton's rules. It allows for branching logic where different actions can be taken depending on the state of a cell or its neighbors.

### 17. **Testmain.java**

- This is the main class used to run the cellular automaton. It reads configuration parameters from an XML file (`automate.xml`), including dimensions, rules, and initial states. It then initializes the grid, applies the rules, and displays the evolution of the automaton in a graphical window. The class supports 1D, 2D, and 3D visualizations of the automaton's evolution【488†source】.

### 18. **automate.xml**

- This XML file defines the initial configuration for the cellular automaton. It includes parameters such as the dimensionality of the grid, initial states of cells, the rules for cell evolution, and the neighborhood definitions. For example, it can specify the dimensions as 1D, 2D, or 3D, and includes rules like the Sierpiński triangle rule for cell behavior【489†source】.

## Key Features

- **N-Dimensional Grids**: The automaton works on grids with any number of dimensions, allowing complex spatial simulations.
- **Custom Neighborhoods**: Different neighborhood types such as G26 enable versatile interaction models.
- **Custom Operators**: Operators like **SUPEQ** and functions like **COMPTER** allow intricate rules to govern cell states.
- **Graphical Representation**: The `ColorGrid` and `CellPanel` classes provide a clear, visual representation of the automaton's evolution.

## How to Run

1. Compile all Java files using the following command:
   ```sh
   javac *.java
   ```
2. Run the main class (assuming there's a main class called `Testmain`):
   ```sh
   java Testmain
   ```
3. The automaton will display a graphical representation of the cells, updating as they evolve based on the rules defined.

## Usage

- **Define Rules**: Use `TraitRegle` to define and customize the rules governing the automaton.
- **Customize Neighborhoods**: Modify classes like `G26` to experiment with different neighborhood definitions.
- **Visualize**: Run the program and observe the evolution of the cellular automaton using the visual grid provided.

## Dependencies

- This project requires Java 8 or above.

## Contributing

Feel free to submit pull requests for new features, bug fixes, or improvements to the documentation.

## License

This project is licensed under the MIT License.

