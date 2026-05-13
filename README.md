# 🚀 Catapulter

A text-based sci-fi roguelike adventure game with a sophisticated natural language parser and JavaFX-powered interface.

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21.0.1-blue.svg)](https://openjfx.io/)
[![Gradle](https://img.shields.io/badge/Gradle-Build-green.svg)](https://gradle.org/)

## 📖 About

Catapulter is an ASCII-based sci-fi semi-roguelike game that combines classic text adventure gameplay with modern UI elements. The project features a custom natural language parser that interprets casual user input and translates it into game commands, creating an intuitive and immersive experience.

### Key Features

- 🎮 **Natural Language Parser** - Type commands naturally; the game understands your intent
- 🗺️ **Dynamic World System** - Explore procedurally connected rooms and environments
- ⚔️ **Combat System** - Multiple damage types (acid, fire, lightning, poison, etc.)
- 🎭 **Character System** - Classes (Fighter, Wizard, Cleric, Rogue), races, and stats
- 📦 **Interactive Objects** - Containers, furniture, weapons, and consumables
- 🎨 **JavaFX Interface** - Modern graphical interface with CSS styling
- 🛠️ **Map Builder** - Built-in tools for creating custom game maps

## 🏗️ Architecture

The project is organized into several key modules:

- **Actor System** - Player and NPC management with stats and behaviors
- **World System** - Maps, rooms, tiles, and environmental updates
- **Input Processing** - Natural language parser and command interpretation
- **Combat System** - Weapons, damage types, and fighting mechanics
- **UI Layer** - Console, text map, and graphical interface components
- **Game Objects** - Items, containers, furniture, and interactive elements

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Gradle (wrapper included)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/XcovertX/Catapulter.git
   cd Catapulter
   ```

2. **Build the project**
   ```bash
   ./gradlew build
   ```

3. **Run the game**
   ```bash
   ./gradlew run
   ```

### Alternative: Using Gradle Wrapper (Windows)

```cmd
gradlew.bat build
gradlew.bat run
```

## 🎮 How to Play

The game uses a natural language parser, so you can type commands as you would naturally speak:

- **Movement**: `go north`, `walk to the door`, `enter room`
- **Interaction**: `look at table`, `open chest`, `take key`
- **Combat**: `attack enemy`, `use sword`, `fight`
- **Inventory**: `list inventory`, `drop item`, `wear armor`

## 🛠️ Development

### Project Structure

```
Catapulter/
├── src/main/java/
│   ├── actor/           # Character and NPC classes
│   ├── characteristics/ # Stats, classes, and races
│   ├── damage/          # Damage type system
│   ├── gameObjects/     # Items and interactive objects
│   ├── inputProcessor/  # Natural language parser
│   ├── mapBuilder/      # Map creation tools
│   ├── UserInterface/   # UI components
│   ├── verbs/           # Action implementations
│   ├── weapons/         # Weapon system
│   └── world/           # Game world and updates
├── src/main/resources/  # FXML, CSS, and assets
└── files/               # Game data and graphics
```

### Building from Source

```bash
# Clean build
./gradlew clean build

# Run tests
./gradlew test

# Create JAR
./gradlew jar
```

### Technologies Used

- **Java 17** - Core language
- **JavaFX 21.0.1** - GUI framework
- **Gson 2.8.9** - JSON serialization
- **Gradle** - Build automation

## 🎯 Roadmap

- [ ] Expand world content and storylines
- [ ] Implement save/load system
- [ ] Add more character classes and races
- [ ] Enhance AI for NPCs
- [ ] Multiplayer support
- [ ] Sound and music integration
- [ ] Mobile platform support

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is open source and available under the MIT License.

## 👤 Author

**XcovertX**

- GitHub: [@XcovertX](https://github.com/XcovertX)

## 🙏 Acknowledgments

- Inspired by classic text adventures and MUD games
- Built with a passion for sci-fi and roguelike gameplay
- Special thanks to the JavaFX and open-source communities

---

<div align="center">
Made with ❤️ and ☕ | Bringing sci-fi to the roguelike genre
</div>
