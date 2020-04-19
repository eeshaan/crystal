# Crystal
"Weighted" assignment tracker using a PriorityQueue — built with JavaFX.

## Getting Started
- Create new Eclipse project. Call it `crystal`.
  - Go to `Libraries` tab. Add your local `JavaFX11` user library to the class path.
- Create a new package within your project. Call it `application`.
- Create a new class within the new `application` package. Call it `Main`.
  - In the superclass field, type `javafx.application.Application`.
  
- Now clone this git repo somewhere else.
  - `git clone https://github.com/eeshaan/crystal.git`
- Copy the contents from this repo into your Eclipse project.
  - Be sure to include the hidden files from this repo (a folder called `.git` and a file called `.gitignore`) in your copy!
  - Select some form of the "yes" option when your computer prompts you if you want to replace the files.
 - You're all set up. Run a `git status` inside your Eclipse project directory and make sure everything is up-to-date.
 
## Building and Running
### From the command line.
Edit the path variables in either `Makefile` (Linux/macOS) or `make_fx.bat` (Windows). In most cases, you should only need to edit the `MP` (JavaFX module path) variable.
 
Now you can run `make` or `make_fx.bat` to compile the application. Look through the file for other commands like `run` and `jar`.

### From Eclipse
When trying to run from Eclipse normally, you'll encounter this error:  
`Error: JavaFX runtime components are missing, and are required to run this application`

To fix this, edit your run configuration for the `Main` class. Add the following for the VM arguments:  
**Linux/macOS**  
`--module-path /path/to/javafx-sdk-11.0.2/lib --add-modules javafx.controls,javafx.fxml`  
UNCHECK THIS OPTION  
`Use the -XstartOnFirstThread argument when launching with SWT`

**Windows**  
`--module-path "\path\to\javafx-sdk-11.0.2\lib" --add-modules javafx.controls,javafx.fxml`

Now you can "Run as" Java Application from the Eclipse menu.

## Contributing
For the most part, tasks will be added as issues into the _To-do_ column of the [project board](https://github.com/eeshaan/crystal/projects/1). When working on a certain issue, create a new branch. This will help prevent merge conflicts. 
> **Never push code directly to `master`.** [@AtaGowani](https://github.com/AtaGowani) will write "NO" on your forehead in Sharpie while you're sleeping if you do.   
- When working on [#1](https://github.com/eeshaan/crystal/issues/1]), I "checked-out" into a new branch by running `git checkout -b issue_1`.
- Once I was done adding my contributions, I ran `git commit -a -m "add css (#1)"`.
  - You should also reference the relevant issue number in your commit &mdash; it helps keeps things tidy on GitHub. You can find the issue number in the [_Issues_](https://github.com/eeshaan/crystal/issues) tab of this repo.
- After pushing all the commits related to your issue and everything is working as intended, create a pull request on GitHub that merges the changes from your branch into `master`.
