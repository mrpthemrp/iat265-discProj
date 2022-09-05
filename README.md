# Record Player Simulation :dvd:

This repository contains a media-rich, interactive record player simulation using Java Swing.

:warning: ***Please DO NOT FORK this repository/project!*** :warning:

This project is not intended to be open-source, feel free to use it as a reference but DO NOT FORK!  
If used as reference, please cite by providing link to project and author name \([see section below](#citation-format)\).

  
Watch a quick demo of the project through the link below!  
:vhs: **[Video Demo Link](https://youtu.be/MswxSEbKJG8)**

## :bookmark_tabs: Table of Contents
1. [Project Description and Summary](#1-project-description-and-summary)
   1. [Project Takeaways](#sparkles-project-takeaways)
   2. [Project Shortcomings](#exclamation-project-shortcomings)
2. [Installation Guide](#2-installation-guide)
   1. [Software Requirements](#computer-software-requirements)
   2. [Processing and Minim Libraries](#books-processing-and-minim-libraries)
   3. [Steps](#memo-steps)
3. [Important Note](#3-important-note)
4. [References](#4-references)
    1. [Images](#art-images)
    2. [Audio](#headphones-audio)
5. [Citation Format](#5-citation-format)

## 1. Project Description and Summary

This project is the final assignment for IAT 265 \(Eric Yang\) at SFU.

This project runs a Java Swing program that allows the user to play a song on a simulated record player. The user is able to adjust the volume of the song from the record player (LOW, MEDIUM, HIGH) and also pause the song by toggling the on and off button. 

The simulation will show the passing of time by changing backgrounds and lighting, based on different times of day. 

After a song is finished playing, the user will have the option to play another song.  
If this option is selected, the program will refresh itself and allow the user to select another song.

If the user chooses to exit the program, then the program will terminate.

**See [video demo](#record-player-simulation-dvd) for more comprehensive walkthrough.**

### :sparkles: Project Takeaways

- Practiced [OOP/D](https://en.wikipedia.org/wiki/Object-oriented_programming) for the first time
    - Hence why this project is not the best example of OOP/D
- Utilized design and code skills to create an interactive simulation
- Worked with external image and sound files using Java for the first time
- Built my first ever Java Swing program

### :exclamation: Project Shortcomings

- File paths are hard-coded, in future code this will not be the case
    - Ran into a lot of debugging problems because of this.
- Buttons are not JButtons but are based off of where the mouse is clicking on the screen.
    - This is a deprecated method that is not easy to use.
- Sounds are not lined up properly with actions, there are lags.

## 2. Installation Guide
***This project was created through Eclipse but has been tested to run on both IntelliJ and Eclipse.***

### :computer: Software Requirements
- **Windows OS** 
  - *This project has not been tested on MacOS!*  
    It may run into errors due to backslash and indexing differences between Windows and MacOS.
  - Windows 7 and up is recommended
- **Latest version of [Eclipse](https://www.eclipse.org/downloads/) or [IntelliJ](https://www.jetbrains.com/idea/download/#section=windows)**
  - IntelliJ is preferred.
    - Either Ultimate or Community version works.
  - Eclipse can be tricky to navigate if the user is not familiar with it.

### :books: Processing and Minim Libraries
The Minim library from the Processing language is included in this project.  
They are already included in the project so it does not need to be downloaded externally.

However, these libraries do need to be added to the file path, so make sure that all JAR files in the [src folder](/main/src) are added to the library module.

### :memo: Steps
***The following steps are intended for use with IntelliJ IDE***

<details><summary><h4>Part I - Download Code and Open on IntelliJ</h4></summary><br>

1. Download this project as a **ZIP file**.
    - Click **'Code'** and then **'Download ZIP'**.
2. Unzip the main folder - **'record-player-sim-main'**.
    - This may take a few minutes, make sure your machine has ample space!
3. Open **IntelliJ** and select **'Open'** or 'Open Project'.
4. **Locate** where the **unzipped folder** from step 2 is on your machine. Click on the folder to open the project.
    - Click 'Trust Project' when the pop-up appears.
    - The project will now open.
</details>
<details><summary><h4>Part II - Configure Folders and Libraries</h4></summary><br>

5. Configure the SDK by **File > Project Structure > Project Settings > Project**
    - *We will set the JDK, Language Level, and Compiler output here.*
6. Select ***JDK 16***
    - It is HIGHLY important that JDK 16 is used! Project will not run otherwise.
    - JDK 16.0.2 is preferred.
7. Select **'Language Level' to be 16**
    - If language level is not 16, the project will not run.
8. Set **'Compiler output:'** to the out folder in the project folder
    - Path: **record-player-sim-main/out**
9. Go to **Libraries** which is **also under Project Settings**
10. **Click on the "+" button** to add a library
11. **Click "Java"** from the library options
12. **Add 'core.jar'** and only this one jar!
    - Path: **record-player-sim-main/src/core.jar**
13. Once the core.jar is added, **click on the '+' under the core library**
    - It should say 'Add Alt+Insert' on mouse hover
14. Now **add all the remaining jar files** under the src folder
    - Add all at the same time by holding down 'Shift' and selecting all.
15. Click **'Apply'** and then **'OK'**
</details>
<details><summary><h4>Part III - Set Sources Root and Configurations</h4></summary><br>

16. **Find the 'src' folder** in the project directory
17. Right-click and **go to 'Mark Directory as'**
18. **Click on 'Sources Root'** from the listed options
    - The folder colour should turn blue after clicking.
19. Let IntelliJ reconfigure things and once it is done, *go to click on 'Current File' to edit run configurations**
    - Located near the run button; top right bar.
20. Click **'Edit Configurations...'** and then **'Add new configuration...'**
21. Click **'Application'** on the pop-up
22. Under **'Build and run'** set SDK to 16 if not already, **type 'main.RoomApp' in the Main class bar**
    - The bar will be highlighted red if no main class is specified.
23. In **'Working directory:'** set the directory to **'src'**
    - It currently is just the record-player-sim-main folder which will not allow the program to run correctly.
24. **Change the name of the build** to something meaningful like 'RoomApp'
    - On default it is just 'Unnamed'
25. Click **'Apply'** and then **'OK'**
26. The project is now ready to run!
</details>

## 3. Important Note
Console will print the following warning message:

> ==== JavaSound Minim Error ====  
> ==== Don't know the ID3 code \<tag name\> ====

Please ignore these messages, they are only printed because Minim  
does not recognize certain custom tags from Adobe Audition.

## 4. References
All references to work that is not my own are in the **[references folder](/references)**.

### :art: Images
- Images are in the **[assets folder](/assets)**.
  - Images in the **[sleeves folder](/assets/sleeves)** have the same reference as the songs associated with them.

All graphics are of my own creation using Adobe Illustrator and Photoshop, except for the vinyl sleeves (see note above).  


### :headphones: Audio
- Sound effects are in the **[sounds folder](/assets/sounds)**.
  - **[Sounds Citations](/references/SoundsReferencesDoc.txt)**
- Music files are in the **[songs folder](/assets/songs)**.
  - **[Music Citations](/references/SongsReferencesDoc.txt)**

Adobe Audition was used to mix and edit sounds/songs.

## 5. Citation Format
Example of citing this project as a reference:
> Reference used for ImageBuffer: https://github.com/mrpthemrp/record-player-sim/blob/main/src/util/ImageLoader.java  
> Date Accessed: August 2022  
> Developer: [Deborah Wang](https://github.com/mrpthemrp)

If using this project as a reference please copy and paste the following into your references/citations:
```diff
Reference for <code referenced>: <file/folder URL>
Date Accessed: <date accessed>
Developer: Deborah Wang (https://github.com/mrpthemrp)
```

---
Last Code Update Date: July 2022

Copyright December 2021, Deborah Wang
