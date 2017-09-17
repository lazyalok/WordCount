# WordCount

## What this project is about?

This project aims to analyse a file with a large body of text and to count number of occurance of the words.
This Application counts the words in a file and saves the counts to a MongoDB server. 


## How we can use it?

I have attcahed the jar (wordcount.jar) in challenge.rar. We can execute this jar by below command.
###### java -jar [jar-path] [file-path] [mongodb-host] [port]
For example on windows-
###### java -jar F:\challenge\wordcount.jar F:\challenge\names.txt localhost 27017


## How can we generate the jar from application?
Run the maven command to create the jar. Check pom.xml


## How application flow works ?

Flow Diagram of the appication.

![alt text](https://github.com/lazyalok/WordCount/blob/master/images/DFD.png)


## How report looks like ?
![alt text](https://github.com/lazyalok/WordCount/blob/master/images/report.PNG)


## How can we test the application code ?

There are two major tests are written.
1)DBConnectionAndRecordStoreTest
2)WordCountHandlerTest

As I am newbie on mongodb, I have developed mongodb code with test driven approach.
As of now tests are still connecting to real db and perform testing. I will mocked it later to not make it flaky.

## Pending Works
1) Some refactoring still required. ( Extracting report logic to specific report class)
2) Major thing is still need to do is Fault tolerance. (if server goes down how we will handle it. I have some ideas will implement it later)
3) Few more validations
4) Multiple vm testing.


