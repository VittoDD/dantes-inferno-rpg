# Dante's Inferno RPG

An interactive Dante's Inferno themed desktop RPG application,
developed in **Java25** and **JavaFX 21**, guiding the player 
through the nine circle of Hell. The project is designed and 
implemented following principles like **Clean Architecture**, 
**Clean Code** practices, XML/DTD level configuration, JSON-based
data persistence via **Gson**, automated Unit Testing, and Git 
workflow, using isolated branches and atomic commits.

## Key Features
- **Clean Architecture & Clean Code**: Strict separation of concerns between
the UI presentation layer ('MainApp'), domain models ('model')
and the backend business logic ('GameManager', 'SaveManager', 'LevelLoader'),
combined with readable, maintenable, and self-documenting code standards;
- **Data-Driven Level Configuration (XML & DTD)**: Circle definitions, 
descriptions and hitbox boundaries are externalized in an XML file
validated against a custom DTD schema
- **Persistence System**: Automatic saving and loading of game
state in a structured JSON file ('save.json');
- **Dynamic Start/Resume flow**: Intelligent start screen detecting
existing save states, allowing players to start a fresh game or 
resume their journey;
- **UI (JavaFX)**: the style of the game implemented with gradient
buttons, dynamic gameplay header with level indicators, and a
quick exit button;
- **Unit Testing**: Comprehensive JUnit 5 test suite verifying 
level progression logic and 2D coordinate click hit-detection
('GameManagerTest') and XML parsing/loading integrity ('LevelLoaderTest').

## Tech Stack and Tooling
- **Language**: Java25
- **UI Framework**: JavaFX 21
- **Build and Dependency Management**: Gradle 8.14 (via Gradle Wrapper)
- **Data & Persistence**: XML/DTD, Google Gson
- **Testing**: JUnit 5

## Prerequisites
Ensure you have the following installed on your system:
- **JDK 25** (or compatible)
- *(No global Gradle installation required - the project includes the Gradle Wrapper)*

## Getting Started
Clone the repository, navigate to the project root directory,
and use the Gradle Wrapper commands for your terminal:

### On Windows:
1. **Build the project**: './gradlew.bat build'
2. **Run unit tests**: './gradlew.bat test'
3. **Run the game**: './gradlew.bat run'

### On macOS / Linux
1. **Build the project**: './gradlew build'
2. **Run unit tests**: './gradlew test'
3. **Run the game**: './gradlew run'

## Project Structure
Source code is structured according to the separation of
responsibilities:
- 'com.dantesinferno.rpg.model': defines the core domain entities
and level data structures;
- 'com.dantesinferno.rpg.manager': manages game state, circle progression
and core business logic ('GameManager');
- 'com.dantesinferno.rpg.ui': manages JavaFX graphical interfaces,
    scenes, styling and transitions ('MainApp');
- 'com.dantesinferno.rpg.utils': handles JSON serialization/deserialization
and file persistence via Gson ('SaveManager').
- 'src/main/recources': contains project assets, including the circle
background images, the XML level definitions, and the corresponding
DTD validation schema.

## Author
**Vittoria Di Domizio**
