# Email Contact List Updater

As a part of my internship with [Convessa](http://convessa.com/), one of my first tasks was to dive into the capabilities offered by email marketing platforms such as [Mailjet](https://dev.mailjet.com/). This small program constructs JSON Objects with contact information, custom segmentation properties, and unique identifiers to programmatically update user information in bulk.

## Project Structure

This was my first exposure to accessing API endpoints through a Java network application (so there is plenty of room for improvement of course). Below are short descriptions of the two classes (Updater and UpdaterTester) as well as the context of the task itself (to bettter understand my reasoning behind the project structure used).

### Updater
The *Updater* class was meant to define the HTTP POST to the Mailjet's API endpoint with Convessa's Authorization Key (_omitted and set to null for security reasons_). Initially, the __getContacts__ method accesses a pre-defined *UserInfoManager* object to turn the internal users into the expected JSON format. This could also have been pre-loaded with "dummy" contact information for testing using a *UserInfoProvider* object in the constructor. The __postToMailjet__ method opened this API endpoint's output stream, read the given JSONObject for contacts and returned the endpoint response as a String. Note, the two versions of this method capture the two possible types of operations:

1. Adding and/or updating Mailjet "Contacts" (tracks user history - master list of sorts)
2. Adding and/or updating to specific Mailjet "Contact List" (groups used for email segmentation)

### UpdaterTester
The *UpdaterTester* defines a simple test suite that randomly selects scenarios (e.g. "BETA_DAY_3" providing users that have had the beta for 3 days) and updating those contacts to their corresponding contact list for appropriate email segmentation.

## Automatic updates using crontab (Linux)
Additionally I tested [Updater](https://github.com/jf2978/mailjet-updater/blob/master/ConvessaMailjet/src/mailjet/UpdaterTester.java) in an automated environment by exporting the project to a Runnable JAR file  (e.g. in Eclipse: _File_ > _Export_ > _Java_ > _Runnable Jar File_ > ...). and running a cron job. Automatic updating was executed using [crontab](http://crontab.org/) and the following instructions outline some of the steps taken.

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
If we wanted to update our contacts every day at midnight, our command would be:
```
0 0 * * * java -jar /updater.jar
```
__NOTE:__ The path to your .jar file must be an _absolute_ path

## Author ##
* **Jeffrey Fabian** - [jf2978](https://github.com/jf2978)
* **Dan McCafferty** - _UserInfo* Singleton classes_ - [Convessa](convessa.com) Co-Founder & CTO
