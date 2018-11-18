## Number Therapy

Number Therapy is an application that make numbers say their names in English

## Motivation
An exercise to display problem solving and coding style to whom it may concern.  The problem as originally stated was as follows:

```
Write some software using the technology of your choice that will transform numbers into English word equivalents.
```
The following is some examples 

| Sample Input | Sample Output                               |
| ------------ | ------------------------------------------- | 
| 0            | Zero                                        |
| 13           | Thriteen                                    |
| 85           | Eighty five                                 |
| 5237         | Five thousand two hundred and thirty seven  |


## Tech used

<b>Built with</b>
- [Maven](https://maven.apache.org/)
- [Guava](https://github.com/google/guava/)
- [JUnit](https://junit.org/junit5/)
- [Spotbugs](https://spotbugs.github.io/)
- [PMD](https://pmd.github.io/)


## Features
Converts all numbers from Long.MIN_VALUE to Long.MAX_VALUE into their english word equivalents

## Output Example

```
Welcome to Number Therapy
==========================

In this session we will help the integers between 
-9223372036854775808 and 9223372036854775807 say their names in english

Please note that spaces and commas are ignored in these sessions
You can end the session at any time by entering the safe words 'pineapple' or 'exit'

Please enter an integer: 98752304673957
Your number says: Ninety eight trillion seven hundred fifty two billion three hundred four million six hundred seventy three thousand nine hundred and fifty seven
```


## How to use?

To run NumberTherapy you can either use maven 

```
mvn clean package
mvn exec:java
```

or build it and run the executable jar

```
mvn clean package
cd target
java -jar numbertherapy-1.0.1.jar 
```

The application will display instructions for the therapy session and then prompt the user to enter a number to get the numbers talking.  The session can be ended at any time by typing the safe words 'pineapple' or 'exit'


## License
A short and simple permissive license with conditions only requiring preservation of copyright and license notices. Licensed works, modifications, and larger works may be distributed under different terms and without source code.

MIT © [Kyle Girard]()
