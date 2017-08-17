# Email Contact List Updater

As a part of my internship, I was matched with [Convessa](http://convessa.com/) - an enterprise-grade voice platform that enables the development of extensive, robust and scalable voice applications. One of my first tasks was to dive into the capabilities offered by email marketing platforms such as [Mailjet](https://dev.mailjet.com/). This small program constructs JSON Objects with contact information, custom segmentation properties, and unique identifiers to programmatically update user information in bulk.

## Automatic updates using crontab (Linux)
An effective testing technique would be to test the [Updater](https://github.com/jf2978/mailjet-updater/blob/master/ConvessaMailjet/src/mailjet/UpdaterTester.java) by exporting the project as a Runnable JAR file  (e.g. in Eclipse: _File_ > _Export_ > _Java_ > _Runnable Jar File_ > ...). From there, we can automate the execution of this using [crontab](http://crontab.org/) following the instructions below.

## Instructions

1. Once the program is exported as a Runnable JAR, test that it can be executed:

```
java -jar <PATH_TO_JAR_FILE>
```

2. Edit the crontabs for the current user:
```
crontab -e
```
_Edits the current crontab using your editor of choice (as defined by the EDITOR environment variable)_

3. Schedule the execution command according to the following format:
```
<minute> <hour> <day_of_the_month> <month> <day_of_the_week> <command_be_executed>
```
Thus, if we wanted to update our contacts every day at midnight, our command would be:
```
0 0 * * * java -jar /updater.jar
```
__NOTE:__ The path to your .jar file must be an _absolute_ path
