## os-clans
#### The open source alternative to adding Clans to your server.

- The aim of this project is to create an open source plugin of which you can either commit your requesteed changes/improvements or create a new fork and create your own plugin.

## Features
- Class based command creation
- BoostedYaml for Yaml processing

## FAQ
#### How do I make a new command (no args)?
 - Ensure you have branched off from the master branch using: ```git checkout -b branchname``` and ensure that all your changes are being pushed to this branch to open up a pull request.
 - To begin adding a new command, add the command to the plugin.yml located in: ```src/main/resources```. Following the same format as either /clan or /helloworld
 - Then create a new file following the format: ```CommandnameCmd.java``` which extends the class ```GenericCmd```.
 - Within this file, add a constructor (See HelloWorld.java example) and update the command name, description and usage here.
 - Add an @Override for: boolean onCommand event, and begin coding your command here.
 - Inside the constructor in: ```src/main/java/cc/yllo/events/OnCommand.java``` add your command to the list 'cmds', as this is where cmds are loaded from. Without this, your command will fail.

#### How do arguments work?
 - All arguments are completely optional. You do not need any arguments to work with this.
 - First arguments are referenced as GenericTopic.
 - Second arguments are referenced as GenericPost.
 - Additional arguments are handled by the GenericPost.
 - For example: /clan ```topic:```kick ```post:``` playername ```post:```args[2]

##### How can I create an argument (no additional args)
 - Please use this guide, to create a command with only one argument such as: /clan disband. See below for creating additional args.
 - Please have created a command, before completing the steps below for full understanding of how the system works.
 - Inside the folder: /topics/ create a Java file for your topic.
 - Extend : ```GenericTopic``` and create constructor for this class, edit the variable: ```arg``` to match your argument name.
 - Add an @Override for: boolean executeArgument, and provide the code to execute your argument here. See topics/HelloWorld.java for reference.
 - In your created command class, within your constructor, add your 'Topic' class to the argsList extended from the GenericCmd class. ``argsList.add(new ClassCmd())```
 - Done. You should be able to use your command, and this one argument successfully.

#### How can I process additional arguments?
 - Ensure you've read the above steps on setting up your first argument, then proceed.
 - Within the folder: ```/topics/posts/ ``` create a class for your Post arg.
 - Remember, this file will be the file processing any additional arguments, player name, ect...
 - Extend: ```GenericPost```, create your constructor, edit the title (& optionally the body) strings. The title here is used as the args[1].
 - Add an @Overrid method for: boolean executePost
 - In your first Topic argument, under the constructor, add your created Post class to the post list.