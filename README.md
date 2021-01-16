# SimpleToDo


Simple ToDo is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: Chiwaya Benjamin Muyinda

Time spent: 6 hours spent in total

## User Stories

The following functionality is completed:


* [+] User can view a list of todo items
* [+] User can successfully add and remove items from the todo list
* [+] User's list of items persisted upon modification and and retrieved properly on app restart


Here's a walkthrough of implemented user stories:

<img src='' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with Imgur(https://imgur.com//).


## Notes
There were some challenges faced when building the app. For example, some dependencies had to be added in order to use classes from specific libraries. One example of such a scenario is when using FileUtils library. Android has a library like that too but the one needed for this project had to come from apache.common.io . In order to use that library, commons.io dependency. Furthermore, had trouble getting the items to show up on the list because the recyclerview binder was not properly implemented.
