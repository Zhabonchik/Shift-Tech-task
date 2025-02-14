# Shift-Tech-task

This program reads data about **managers** and **employees** from a file, processes it, and outputs the results to either the console or a file.

---

## 📋 Input Format

The input data is expected to be in the following format:

Manager,1,Jane Smith,5000,HR Employee,101,John Doe,3000,1 Employee,102,Emily Johnson,abc,1 Manager,2,Michael Brown,6000,Sales Employee,103,Chris White,-2900,2 Employee,104,Anna Taylor,3100,2 Employee,105,Robert Black,,2


- **Manager**: Includes fields like ID, name, salary, and department name.
- **Employee**: Includes fields like ID, name, salary, and department ID.
- Erroneous or invalid lines (e.g., missing/invalid data) are logged and ignored during processing.

---

## 🚀 Features

- **Data Processing**: Detects and logs invalid rows in the input.
- **Sorting**: Employees can be sorted by `name` or `salary` in `ascending` or `descending` order.
- **Customizable Output**: Output can be sent to either the console or a file, depending on CLI arguments.
- **Logging**: All errors and additional logs are saved to the `app.log` file.

---

## 🔧 How to Use

The `Shift-TZ-1.0-SNAPSHOT-jar-with-dependencies.jar` file is located in the `target` folder. To execute the program, run the following command:

```bash
java -jar <path_to_jar_file> <parameters>

## 📥 CLI Parameters
Parameter	Description	Required	Example
-i / --input=<inputPath>	Path to the input file	✅ Yes	-i=src/resources/input.txt
-o / --output=<type>	Output type (console or file)	✅ Yes	-o=console
--path=<outputPath>	Path to the output file (if -o=file)	🚫 No	--path=src/resources/output.txt
-s / --sort=<criteria>	Sorting criteria (name or salary)	🚫 No	-s=name
--order=<order>	Sorting order (asc or desc)	🚫 No	--order=asc
## ⚠️ Rules

    If -o=file is used, --path=<outputPath> must also be set.
    If -s=<criteria> is used, --order=<order> must also be set.

## 🛠 Example Commands

    Output sorted data to a file:

java -jar Shift-TZ-1.0-SNAPSHOT-jar-with-dependencies.jar -i=input.txt -o=file --path=output.txt -s=name --order=desc

Output unsorted data to the console:

    java -jar Shift-TZ-1.0-SNAPSHOT-jar-with-dependencies.jar -i=input.txt -o=console

    Handle invalid parameters:
        All invalid or missing parameters will generate errors and log them in the app.log file.

## 📝 Logging

    Default Log File: app.log
    Location: Created in the current working directory where the program is executed.
    Contents: Contains details of invalid data, errors, and program execution logs.

## ⚙️ Technical Details

    Java Version: 17
    Build Tool: Maven (3.9.6)
    Dependencies:
        JUnit 5.11.4 (Testing)
        Apache Commons CLI 1.9.0 (CLI parsing)
        Lombok 1.18.30 (Boilerplate code reduction)
        Log4j 2.20.0 (Logging)

## 🔗 Maven Dependencies

Include the following in your pom.xml file:

<dependencies>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.11.4</version>
        <scope>test</scope>
    </dependency>

    <dependency>
        <groupId>commons-cli</groupId>
        <artifactId>commons-cli</artifactId>
        <version>1.9.0</version>
    </dependency>

    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>1.18.30</version>
        <scope>provided</scope>
    </dependency>

    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.20.0</version>
    </dependency>

    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.20.0</version>
    </dependency>
</dependencies>
