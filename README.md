# pokertraining - a video poker calculation tool

This repo started out as a video poker training system, but has since evolved into a command line video poker calculation tool instead.  I'm currently working on cleaning up this repo, so please bear with me.  

## Necessary software
1.  Java.  Most currently tested with version 1.7.0_71.
2.  Maven.

## Running the App

Please note I'm skipping tests here because there is a massive one that takes forever.  I need to refactor that and remove it from the standard test suite.  However, feel free to run tests - it will just take a while.
```
mvn -DskipTests clean install
java -jar target/pokertraining-1.0-SNAPSHOT.jar
```

## How it works

You will be prompted to enter a 5 card combination.

```
Please enter the 5 card combination.
Please note the format is D1S1D2S2...D5S5, where D is the denomination of the card and S is the suit.
Example : Ah9c5c3sTh  (note - case matters for now.  First character is always uppercase, second always lowercase.)
Enter 'q' to quit.
>>
```

From there, this tool will calculate the odds for each possible combination and generate a report, like this one:

```
*** VP DECISION REPORT ***
* Ace of clubs
* Ace of spades
* Jack of spades
* Queen of spades
* Ten of diamonds
1 :  Ac As : Average value : 10.000925069380203
2 :  Ac As Js : Average value : 6.988899167437558
3 :  Ac As Qs : Average value : 6.988899167437558
4 :  Ac As Td : Average value : 6.988899167437558
5 :  As Js Qs : Average value : 6.586493987049029
6 :  Ac As Js Qs : Average value : 5.425531914893617
7 :  Ac As Js Td : Average value : 5.425531914893617
8 :  Ac As Qs Td : Average value : 5.425531914893617
9 :  Ac As Js Qs Td : Average value : 5.0
10 :  Ac Js Qs Td : Average value : 2.5531914893617023
11 :  As Js Qs Td : Average value : 2.5531914893617023
12 :  Js Qs : Average value : 2.406722170829479
13 :  Js Qs Td : Average value : 2.076780758556892
14 :  As Js : Average value : 1.9691643539932162
15 :  As Qs : Average value : 1.9691643539932162
16 :  Js : Average value : 1.95357833655706
17 :  Qs : Average value : 1.9309001205393435
18 :  Ac Js Qs : Average value : 1.887141535615171
19 :  Ac Js : Average value : 1.784150477952513
20 :  Ac Qs : Average value : 1.784150477952513
21 :  Js Td : Average value : 1.6056120875732347
22 :  Qs Td : Average value : 1.526672833795868
23 :  Ac : Average value : 1.5068258907296834
24 :  As : Average value : 1.4447060802287444
25 :  Ac Js Td : Average value : 1.3598519888991674
26 :  Ac Qs Td : Average value : 1.3598519888991674
27 :  As Js Td : Average value : 1.3598519888991674
28 :  As Qs Td : Average value : 1.3598519888991674
29 :  : Average value : 1.2931479022307928
30 :  Td : Average value : 1.2072155411655874
31 :  Ac Td : Average value : 1.0792476102374344
32 :  As Td : Average value : 1.0792476102374344
```

It will then prompt you to enter another card combination, or exit the tool.

## How are the values calculated?

The values are relative values based on the data in Paytable.java.  If you modify those values and rebuild/rerun the program, you can test out various paytables.  

## Have you vetted out that these numbers are 100% accurate?

Nope.  Buyer beware.  I have tests in place that give me some confidence, but I'm not a mathematician and would love someone else to take a look and validate whether the data generated is accurate.

## Running tests

```
mvn clean test
```
