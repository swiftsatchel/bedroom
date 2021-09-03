# Bedroom
Due to the pandemic people have started working remotely, _working from the bedroom couldn't get any easier!_
Or... maybe it just did. Bedroom is an open source Java application developed in order to aid call center 
agents in keeping track of their orders and orders per hour to meet their quotas effectively.

This is my first Java program as a self taught high school student, so it has grown and improved (and will hopefully 
continue to) as i improve or learn more about the language. It was originally created for me and my friends working 
at a call center to keep track of our orders/hr without doing the math ourselves, enabling us to share it quicker.

## Table of contents
* [Prerequisites](https://github.com/swiftsatchel/bedroom#prerequisites)
* [How to set up](https://github.com/swiftsatchel/bedroom#how-to-set-up)
* [How to use](https://github.com/swiftsatchel/bedroom#how-to-use)
* [Shortcuts](https://github.com/swiftsatchel/bedroom#shortcuts)
* [Compiling from source](https://github.com/swiftsatchel/bedroom#compiling-from-source)

## Prerequisites
**For running and/or compiling the program** the newest version of Java is recommended, while the minimum required
version is stated under the release you are trying to run. Ex: For Bedroom 3 Java 16+ is required.

### _How to set up_
Download the .jar file from the Releases section, and double click it like any other application.

**A start script** could be used to reduce Bedroom's memory usage if you are into that stuff. For Windows, 
a .bat file can be made in Bedroom's location with the text ```start javaw -jar -Xmx32M -Xms16M filename.jar``` 
(replacing _filename_ with the name of the jar you are trying to run) and then making a shortcut to it wherever 
you like. If you want to keep the command prompt opened for errors, etc. change ```javaw```  to ```java```. The 
argument "-Xmx32M" is the maximum amount of memory Bedroom is allowed to allocate to itself (In this case 32MB) 
while "-Xms16M" is the initial allocation. Actual memory usage may be more due to the JVM, more information can 
be found [online](https://plumbr.io/blog/memory-leaks/why-does-my-java-process-consume-more-memory-than-xmx).)

## How to use
After opening Bedroom you can input your clock in and out times, if you every mess up on these select time dialogs you
may close them to go to the previous one (although closing the clock in time dialog will close Bedroom.) On the right will
be information about your current shift, on the center will be your Add Order button, and on the left will be the Set
Break button. These buttons can be substituted by shortcuts, which you can see below. To remove orders you have to use
a shortcut, which is currently the down arrow on your keyboard. Bedroom also contains a Settings dialog for customizing
your theme and other things to make your experience better, this can be opened through either Backspace or Delete.

### _Shortcuts:_
* **Adding/removing orders:** _Up Arrow_ & _Down Arrow_ respectively.
* **Open Settings:** _Backspace_ or _Delete_
   * Holding _Shift_ while dragging the color sliders will make them all the same value.
* **Opening Set Break dialog:** _Number 0_
* **Exiting select time dialogs:** _Escape_
* **Selecting time in select time dialogs, without pressing Select button:** _Enter_
* Any default Swing shortcuts.

_These shortcuts are meant to be unobtrusive to work applications,
hence their seemingly random keyboard placements._

## Compiling from source
_This is not supported; there could be loss of data or other bugs with things currently being experimented on._

After downloading the source code, extract the folder inside and delete the original zipped file. Then, open the 
extracted folder with your Terminal/Command Prompt (on Windows this can simply be done by selecting the address bar on 
top of File Explorer, typing ```cmd```, then pressing Enter) and run ```gradlew build```. In macOS, you may need to 
run ```chmod +x gradlew``` before this works, open the Terminal in the location of the extracted folder and run 
```./gradlew build```. Once finished, the resulting files will be in the ```build``` folder. The .jar will be in 
```build > libs``` and gradle's default build scripts will be in ```build > bin```.
