# Hollow Exploration
You are tasked to create a team of two and explore the Mii hollow that has ravaged the new district. Fight against enemies in a turn based game and survive for as long as you can.

## How To Run
1. Download ZIP file
2. Unzip, paste into your preferred IDE. (Codespace, VScode, intelliJ)
3. Right click Main.java, found in game folder, click run java file

## MVC Structure
### Model
Contains files of the agent and enemy abstract class, with several subclasses of different agents and enemies. agentFactory and enemyFactory classes are utilized to create agent and enemy objects from one place.

### View
gameView class contains many method to output structures for information of agents, menu, end game, battle interface, and a template for showcasing a user's choices. 

### Controller
Contains the game flow of the project and interactions between agent classes.

## Factory Design Pattern
With so many subclasses of agents and enemies that behave differently, creating a factory pattern was the best choice. It centralizes the creation of subclasses, in this case agents and enemies, to one class and based on input, will create that type of object. the agent and enemy abstract parent classes have set values that each subclass needs, then allows for methods to be overided based on the needs of the agent and enemy. This can be seen especially for agents with different normal, skill, and ultimate attack effects. 

## Mediator Design Pattern
An agent subclass called agentSunna increases the dmg of agents in their party. In order for her dmg bonus to be added to her partner, she communicates with the gameController to send new information of the buffs duration, dmg, and max duration. Same goes to agentAria which increases energy for her other agent and agentNangong that stuns the enemy, needing information of the enemy to be stunned and vice versa.

## Running Test Suite
1. Download ZIP file.
2. Unzip and open file in Eclipse.
3. Run the test suites

## AI Usage
1. code structure ideas
2. Bug finder and fixer
3. Design pattern ideas and structure


