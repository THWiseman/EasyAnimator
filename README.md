# EasyAnimator
Easy Animator Project for Object-Oriented Design.

# Description
Easy Animator is a simple to use Java program that can load, save, edit, and diplay fun animations in various formats. The full GUI not only displays the animation, but also allows users to add, remove, or change the motions of shapes in the animation. Animations can be exported as .svg or .txt files, the former of which can be played by any modern browser.  

![example](https://user-images.githubusercontent.com/77803506/147881971-f48499c9-4e56-4e5b-8ef8-390e8cc7a710.gif)

# Running the program
If you would like to compile the program on your own system, clone this repo and build it with your favorite IDE or Java tool. However, a precompiled .jar file is available for your convienence. Assuming Java is already installed on your system, simply download EasyAnimator.zip, extract the folder, and run it through the command line with the following format:

**Example:**
> java -jar ./EasyAnimator.jar -view playback -in ./buildings.txt -speed 50

**Arguments:**

'-view' arguments are mandatory and can be 'playback', 'visual', 'svg', or 'text'. Playback view launches the full GUI, Visual view just plays the animation once before closing, SVG exports the animation as a file that can be opened in a browser, and text view exports the animation as a .txt.

'-in' arguments are mandatory and specify the input filepath. 

'-speed' arguments are optional and specify the number of frams per second. 

'-out' arguemnts are mandatory for text and svg views and specify the output filepath. 

# Brief Code Walkthrough
This program uses the well-known Model, View, Controller design pattern.

The Model includes the 'Canvas' class that ultimatley contains all data necessary for storing and manipulating the animation. The canvas class mostly contains Shapes and Patterns. The 'Shape' interface provides ways for shapes to be defined and displayed, while the 'Pattern' interface provides ways for shapes to be manipulated over time (e.g. moved, resized, recolored). 

The View includes several ways that the animation can be presented to the user. PlaybackView uses the Java Swing library to create the fully functional GUI. VisualView displays the animation in a Java Swing window, SVGView exports the animation as an SVG file, and TextView exports the animation as a text file. 

The Controller handles program flow and processes commands sent from the View through various callbacks functions. Functions like play(), pause(), addShape(), getInput(), and saveFile() can be found in the controller and mediate interaction between the Model and View to produce the desired outcome. 

# Attributions
This project was created as an assignment for professor Clark Frefield at Northeastern University for CS 5004 Object-Oriented Design, Spring 2021. Some of the included animation files were included as part of the assignment. Tom Wiseman and Noah Beliveau were the sole authors of the source code. 

