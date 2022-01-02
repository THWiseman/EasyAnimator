# EasyAnimator
Noah &amp; Tom's Easy Animator Project for Object Oriented Design

# Description
Easy Animator is a simple to use Java program that can load, save, edit, and diplay fun animations in various formats. The full GUI not only displays the animation, but also allows users to add, remove, or change the motions of shapes in the animation. Animations can be exported as .svg or .txt files, the former of which can be played by any modern browser.  


https://user-images.githubusercontent.com/77803506/147881921-d43584a2-86e1-4744-a991-9aaa033ab891.mov


# Running the program
If you would like to compile the program on your own system, clone this repo and build it with your favorite IDE or Java tool. However, a precompiled .jar file is available for your convienence. Assuming Java is already installed on your system, simply download EasyAnimator.zip, extract the folder, and run it through the command line with the following format:

**Example:**
> java -jar ./EasyAnimator.jar -view playback -in ./buildings.txt -speed 50

**Arguments:**

'-view' arguments are mandatory and can be 'playback', 'visual', 'svg', or 'text'. Playback view launches the full GUI, Visual view just plays the animation once before closing, SVG exports the animation as a file that can be opened in a browser, and text view exports the animation as a .txt.

'-in' arguments are mandatory and specify the input filepath. 

'-speed' arguments are optional and specify the number of frams per second. 

'-out' arguemnts are mandatory for text and svg views and specify the output filepath. 


