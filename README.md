# Dante's Inferno RPG

A desktop JavaFX application implementing a narrative role-playing 
adventure inspired by Dante's Nine Circles of Hell. The project combines 
an interactive 2D coordinate-based graphical interface with data 
persistence (XML level loading and JSON game-state saving via Google Gson), 
structured with modular packages (`it.unicam.cs.mpgc.rpg132193`) and 
object-oriented design.

## How to run the project
### Prerequisites
- **Java25 (LTS)**
- **Gradle** (or the Gradle Wrapper included in the repository)


### Instructions
```bash
git clone https://github.com/VittoDD/dantes-inferno-rpg.git
cd dantes-inferno-rpg
```

## Build the project
To compile the project and execute the JUnit tests: 
- *Linux/macOS*: 
```bash
  ./gradlew build
```
- *Windows*: 
```bash
  ./gradlew.bat build
```

## Execution
To launch the JavaFX desktop application:
- *Linux/macOS*: 
```bash
./gradlew run
```
- *Windows*: 
```bash
./gradlew.bat run
```

## Use of AI Tools
AI tools were used with the purpose of supporting learning, speeding up
routine tasks and brainstorming creative/gameplay elements while maintaining
full personal control over the implementation.

*Use of Gemini* for generating background images for 
the various game screens, brainstorming Virgil's 
hints/quotes, and selecting hidden objects for the 
background gameplay.

*Use of Copilot* for generating repetitive methods 
(such as getters and setters) and utility methods 
(like toString).

*Use of ChatGPT* for clarifying JavaFX core concepts, 
UI components, and the layout commands needed for the 
desktop frontend interface.

## Level of personal intervention 
All generated code, assets, and content were personally 
reviewed, integrated, tested, and adapted to fit 
the project architecture.
The AI was employed strictly as a supportive tool to 
accelerate development and deepen technical understanding 
of JavaFX, ensuring full personal comprehension and 
control over the codebase.

*For full architectural details, class responsibilities, data persistence, 
extensibility mechanisms, and a detailed AI usage report, please refer to the Wiki
in this repository.*

