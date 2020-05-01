# Crystal
## The Idea
Crystal is an assignment tracking app that allows users to manage their upcoming assignments for school. The app provides a slick user interface that can display their assignments within a calendar or in a more traditional, day-by-day list-based format.

## The Premise
What separates Crystal from other assignment tracking apps is a feature that will suggest what assignment our users should be working on at that very moment. We can calculate this by taking into account the assignment’s due date, how much a user is struggling in a certain subject, and how difficult each assignment is. For example, Jimmy might log on to the app and click a button labeled _“What should I work on now?”_. Because we know that Jimmy struggles with math and he has a 10-page calculus worksheet due in 2 days, we will suggest he works on that instead of a book report due in a month. 

## The Final Product
<img src="https://raw.githubusercontent.com/eeshaan/crystal/master/screenshot_3.png" alt="Crystal screenshot">
<img src="https://raw.githubusercontent.com/eeshaan/crystal/master/screenshot_7.png" alt="Crystal screenshot">

## The Team
<a title="Eeshaan Pirani" href="https://github.com/eeshaan/"><img src="https://github.com/eeshaan.png?size=60" alt="Eeshaan Pirani"></a>
<a title="Ayaz Franz" href="https://github.com/acfranz2/"><img src="https://github.com/acfranz2.png" width="60px" alt="Ayaz Franz"></a>
<a title="Benjamin Tan" href="https://github.com/BenDanTan/"><img src="https://github.com/BenDanTan.png?size=60" alt="Benjamin  Tan"></a>
<a title="Devin Demirlika" href="https://github.com/ddem1221/"><img src="https://github.com/ddem1221.png" width="60px" alt="Devin Demirlika"></a>
<a title="Bryan Lin" href="https://github.com/MihayaHotsumiAMLN"><img src="https://github.com/MihayaHotsumiAMLN.png?size=60" alt="Bryan Lin"></a>

---

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
- Before doing anything, make sure git is configured properly and that everything is up-to-date by running `git status`. Then run `git pull`. If you accidentally made changes that cause a merge conflict, revert them by "checking-out" from the latest commmit by running `git checkout <filename>`. You can also stash them for later by running `git stash`.
- When working on [#1](https://github.com/eeshaan/crystal/issues/1]), I "checked-out" into a new branch by running `git checkout -b issue_1`.
- Once I was done adding my contributions, I ran `git commit -a -m "add css (#1)"`.
  - You should also reference the relevant issue number in your commit &mdash; it helps keeps things tidy on GitHub. You can find the issue number in the [_Issues_](https://github.com/eeshaan/crystal/issues) tab of this repo.
- After pushing all the commits related to your issue and everything is working as intended, create a pull request on GitHub that merges the changes from your branch into `master`.

> _**Note**: This issue-based approach should keep us organized for the most part, but if you feel you need to make other changes, feel free to make another branch and/or push commits that don't pertain to a certain issue. It's more important that we get the program done &mdash; don't wait around for someone else to assign you an issue._
