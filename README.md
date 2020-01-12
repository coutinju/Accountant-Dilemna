# Implementation

I decided not to implement factories because I wanted to 'Keep it simple' (Clean Code). Yet, abstract classes are already in place to facilitate new Parser, OutputGenerator, Strategy etc.

Concerning files' architecture of the project, I tried to use Domain Driven Development. Grouping elements by context (Accounting and IO).

The project was created using Visual Studio CODE, because I didn't find any good support of Java on Visual Studio. I decided to add my .vscode directory to share the 2 launch options I added to make some quick tests.

# How To Use

Run the following command in the root directory of project:

```shell
accountingdilemma> mvn clean install
```

That will generate a jar inside 'target' folder, you can now use this 'jar' with a path to a file as argument.

```shell
accountingdilemma/target> java -jar accountingdilemma-1.0-SNAPSHOT.jar input.txt
```

The solution will be generated in the 'output.txt' file.