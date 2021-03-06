# ShrineSurvival

## About this Project

### 1.1 Introduction

This Project is a 2D top down game written in Java and OpenJFX.  
For me, the purpose of this Project is: to learn coding in general.  
Just a warning beforehand: This code is surely badly optimised and can easily be improved.  
But since this is my playground, i can do whatever i want!

### 1.2 Technologies

 - CODE: Java JDK 14  
 - GUI: OpenJFX14  
 - Build Tool: Maven  
 - JDBC Connector: MYSQLConnector version 2.8.6  
 - OpenSource MIT Licence

Most of the code is pure Java.  
Bacause i think Swing is oldschool, i used OpenJFX.
The GUI is builded in pure code and doesn't use FXML files.  
The Player Sprite and Scores are saved in a MYSQL Database and can easily be accessed from a website or (in this case) the game itself.
For now, the Database has to be set up locally via XAMPP or similar.  
The Project is published in Open Source with the MIT Licence, so feel free to use it yourself!
  
## 2 The Game

The game itself is a 2D top down style pixelart survival game, where the player hast to survive as long as possible.
Enemies spawn in waves at the outer edge of the screen and wander towards the player.
When the player gets hit by one of the enemies, there is a certain cooldown before he can be hit again.
The same applies in the other direction aswell.  
At the time your HP arrive at zero, your character dies and your score pops up.
This score will be saved in a database, so you can compare to other players.

## 3 Controls

For controls you user the arrowkeys to move and WASD to attack in the direction, this key is binded to its direction.  
That means:  
 - W is attack UP
 - S is attack Down
 - A is attack Left
 - D is attack Right
  
## 4 The Score

Like said before, the score is generated as the game ends and is saved in a local database.  
This score has following attributes:  

 - Name of the player (chosen at the start of the game)  
 - Score (Point generated by defeating enemies)  
 - Time survived (should be self explanatory)  
 - Defeated enemies (should be self explanatory as well)  
 - Waves (at what wave your character died)  

## 5 Sprites

First of all, all assets are provided by [**Kenney.nl**](https://kenney.nl).  
You will find awesome assets there, so check it out!!!  

In this project's case, sprites are subimaged drawn (layer by layer) on Canvas.  
The Canvas is 16x16 and has a rescale factor of 2.  
I am using 2 classes to split this subimages:  
  - SpriteSheet class takes the resource path, tilesize and margin, and specifies this parameters for the useage in the Sprite class  
  - Sprite class takes the SpritSheet object, x and y counter and generates a Canvas with the specified subimage.  
    - Sprite.addSprite method adds a sprite to this canvas  
    - Sprite.getCanvas returns it to add to your Pane  

For now, enemies are only generated with a single sprite and dont add any layers.  

The Map is generated on the same principle, except the generated Sprite has the same Resolution as the ViewManager (In my case 1600 x 900).  

Here is an example Spritesheet used in the game:  
![Character Sheet](/usedAssets/SpriteSheets/roguelikeChar_transparent.png)

