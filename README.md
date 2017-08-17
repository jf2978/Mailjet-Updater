# Email Contact List Updater

As a part of my internship, I was matched with [Convessa](http://convessa.com/) - an enterprise-grade voice platform that enables the development of extensive, robust and scalable voice applications. One of my first tasks was to dive into the capabilities offered by email marketing platforms such as [Mailjet](https://dev.mailjet.com/). This small program constructs JSON Objects with contact information, custom segmentation properties, and unique identifiers to programmatically update user information in bulk.

## Automatic updates using crontab (Linux)
An effective testing technique would be to test the [Updater](https://github.com/jf2978/mailjet-updater/blob/master/ConvessaMailjet/src/mailjet/UpdaterTester.java) by exporting the project as a Runnable JAR file  (e.g. in Eclipse: _File_ > _Export_ > _Java_ > _Runnable Jar File_ > ...). From there, we can automate the execution of this using [crontab](http://crontab.org/) using the instructions below.

### Prerequisites

Even though this integration was tested locally (Amazon Pay Sandbox Mode), there are a few things that have to be set up in order to effectively test each aspect of the checkout flow.

A useful 
```
crontab -e
```
