package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CODE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CODE_PARAMETER_LANGUAGE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CODE_SNIPPET_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CODE_SNIPPET_C2;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CODE_SNIPPET_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@JMockFunctionRegister(descI18nKey = DOC_CODE_SNIPPET_DESC, categoryI18nKey = {
    DOC_CATEGORY_CODE}, order = 3001)
public class MCodeSnippet extends AbstractMockFunction {

  public static final SecureRandom random = new SecureRandom();

  public enum Language {
    PYTHON, JAVA, JAVASCRIPT, TYPESCRIPT, CPP, CSHARP, PHP, RUBY, SWIFT, GO;

    public static Language of(String name) {
      try {
        return valueOf(name.toUpperCase());
      } catch (Exception e) {
        return JAVA;
      }
    }
  }

  public static final Map<Language, String> LANGUAGE_NAMES = Map.of(
      Language.PYTHON, "Python",
      Language.JAVA, "Java",
      Language.JAVASCRIPT, "JavaScript",
      Language.TYPESCRIPT, "TypeScript",
      Language.CPP, "C++",
      Language.CSHARP, "C#",
      Language.PHP, "PHP",
      Language.RUBY, "Ruby",
      Language.SWIFT, "Swift",
      Language.GO, "Go"
  );

  public static final Map<Language, Boolean> LANGUAGE_FEATURES = Map.of(
      Language.PYTHON, true,
      Language.JAVA, true,
      Language.JAVASCRIPT, true,
      Language.TYPESCRIPT, true,
      Language.CPP, true,
      Language.CSHARP, true,
      Language.PHP, true,
      Language.RUBY, true,
      Language.SWIFT, true,
      Language.GO, true
  );

  @JMockParameter(descI18nKey = DOC_CODE_PARAMETER_LANGUAGE)
  private final String language;

  @JMockConstructor(descI18nKey = DOC_CODE_SNIPPET_C1,
      example = "@CodeSnippet()",
      exampleValues = {"""
          public class MathUtils {
              /**
               * Calculate the square of a number
               */
              public static int updateResult(int userCount) {
                  int newCount = userCount * userCount;
                  return newCount;
              }

              public static void main(String[] args) {
                  int result = updateResult(3);
                  System.out.println("Square of " + 3 + " is " + result);
              }
          }"""})
  public MCodeSnippet() {
    this(Language.JAVA.name());
  }

  @JMockConstructor(descI18nKey = DOC_CODE_SNIPPET_C2,
      example = "@CodeSnippet(PYTHON)",
      exampleValues = {"""
          def calculateResult(currentTotal):
              ""\"Calculate the square of a number""\"
              data = currentTotal ** 2
              return data

          # Function call
          result = calculateResult(94)
          print(f"Square of 94 is {result}")"""})
  public MCodeSnippet(String language) {
    this.language = language;
  }

  @Override
  public String mock() {
    return generateSnippet(language);
  }

  public static String generateSnippet(String language0) {
    Language language = Language.of(language0);

    int snippetType = random.nextInt(7);
    return switch (snippetType) {
      case 0 -> generateFunction(language);
      case 1 -> generateClass(language);
      case 2 -> generateLoop(language);
      case 3 -> generateConditional(language);
      case 4 -> generateDataStructure(language);
      case 5 -> generateErrorHandling(language);
      default -> generateFunction(language);
    };
  }

  public static String generateFunction(Language language) {
    String functionName = generateIdentifier("function");
    String argName = generateIdentifier("arg");
    String varName = generateIdentifier("var");
    int randomNum = random.nextInt(100) + 1;

    Map<Language, String> templates = new EnumMap<>(Language.class);
    templates.put(Language.PYTHON,
        """
            def {0}({1}):
                ""\"Calculate the square of a number""\"
                {2} = {1} ** 2
                return {2}

            # Function call
            result = {0}({3})
            print(f"Square of {3} is {result}")""");

    templates.put(Language.JAVA,
        """
            public class MathUtils {
                /**
                 * Calculate the square of a number
                 */
                public static int {0}(int {1}) {
                    int {2} = {1} * {1};
                    return {2};
                }

                public static void main(String[] args) {
                    int result = {0}({3});
                    System.out.println("Square of " + {3} + " is " + result);
                }
            }""");

    templates.put(Language.JAVASCRIPT,
        """
            /**
             * Calculate the square of a number
             */
            function {0}({1}) {
                const {2} = Math.pow({1}, 2);
                return {2};
            }

            // Function call
            const result = {0}({3});
            console.log(`Square of {3} is ${result}`);""");

    templates.put(Language.TYPESCRIPT,
        """
            /**
             * Calculate the square of a number
             */
            const {0} = ({1}: number): number => {
                const {2}: number = Math.pow({1}, 2);
                return {2};
            };

            // Function call
            const result: number = {0}({3});
            console.log(`Square of {3} is ${result}`);""");

    templates.put(Language.CPP,
        """
            #include <iostream>
            #include <cmath>

            // Calculate the square of a number
            int {0}(int {1}) {
                int {2} = pow({1}, 2);
                return {2};
            }

            int main() {
                int result = {0}({3});
                std::cout << "Square of " << {3} << " is " << result << std::endl;
                return 0;
            }""");

    templates.put(Language.CSHARP,
        """
            using System;

            public class MathUtils {
                /// <summary>
                /// Calculate the square of a number
                /// </summary>
                public static int {0}(int {1}) {
                    int {2} = (int)Math.Pow({1}, 2);
                    return {2};
                }

                public static void Main(string[] args) {
                    int result = {0}({3});
                    Console.WriteLine($"Square of {3} is {result}");
                }
            }""");

    templates.put(Language.PHP,
        """
            <?php

            /**
             * Calculate the square of a number
             */
            function {0}(${1}) {
                ${2} = pow(${1}, 2);
                return ${2};
            }

            // Function call
            $result = {0}({3});
            echo "Square of {3} is " . $result;""");

    templates.put(Language.RUBY,
        """
            # Calculate the square of a number
            def {0}({1})
              {2} = {1} ** 2
              return {2}
            end

            # Function call
            result = {0}({3})
            puts "Square of {3} is #{result}\"""");

    templates.put(Language.SWIFT,
        "// Calculate the square of a number\n" +
            "func {0}(_{1}: Int) -> Int {\n" +
            "    let {2} = {1} * {1}\n" +
            "    return {2}\n" +
            "}\n\n" +
            "// Function call\n" +
            "let result = {0}({3})\n" +
            "print(\"Square of {3} is \\(result)\")");

    templates.put(Language.GO,
        """
            package main

            import (
            \t"fmt"
            \t"math"
            )

            // Calculate the square of a number
            func {0}({1} int) int {
            \t{2} := int(math.Pow(float64({1}), 2))
            \treturn {2}
            }

            func main() {
            \tresult := {0}({3})
            \tfmt.Printf("Square of %d is %d\\n", {3}, result)
            }""");

    return Formatter.format(
        templates.getOrDefault(language, templates.get(Language.PYTHON)),
        functionName, argName, varName, String.valueOf(randomNum)
    );
  }

  public static String generateClass(Language language) {
    String className = generateIdentifier("Class");
    String propertyName = generateIdentifier("data");
    String methodName = generateIdentifier("process");
    String instanceName = generateIdentifier("obj");

    Map<Language, String> templates = new EnumMap<>(Language.class);
    templates.put(Language.PYTHON,
        """
            class {0}:
                ""\"A sample class with basic functionality""\"
               \s
                def __init__(self, {1}):
                    self.{1} = {1}
               \s
                def {2}(self):
                    ""\"Process the stored data""\"
                    return self.{1} * 2

            # Create an instance
            {3} = {0}('test')
            print(f"Processed: {3}.{2}()}")""");

    templates.put(Language.JAVA,
        """
            public class {0} {
                private String {1};

                public {0}(String {1}) {
                    this.{1} = {1};
                }

                /** Process the stored data */
                public String {2}() {
                    return this.{1}.toUpperCase();
                }

                public static void main(String[] args) {
                    {0} {3} = new {0}("example");
                    System.out.println("Processed: " + {3}.{2}());
                }
            }""");

    templates.put(Language.TYPESCRIPT,
        """
            class {0} {
                private {1}: string;

                constructor({1}: string) {
                    this.{1} = {1};
                }

                /** Process the stored data */
                public {2}(): string {
                    return this.{1}.toUpperCase();
                }
            }

            // Create an instance
            const {3} = new {0}('test');
            console.log(`Processed: ${3}.{2}()}`);""");

    templates.put(Language.CSHARP,
        """
            public class {0} {
                private string {1};

                public {0}(string {1}) {
                    this.{1} = {1};
                }

                /// <summary>Process the stored data</summary>
                public string {2}() {
                    return this.{1}.ToUpper();
                }
            }

            class Program {
                static void Main() {
                    {0} {3} = new {0}("example");
                    Console.WriteLine($"Processed: {3}.{2}()}");
                }
            }""");

    templates.put(Language.SWIFT,
        """
            class {0} {
                private var {1}: String
               \s
                init({1}: String) {
                    self.{1} = {1}
                }
               \s
                /// Process the stored data
                func {2}() -> String {
                    return self.{1}.uppercased()
                }
            }

            // Create an instance
            let {3} = {0}({1}: "test")
            print("Processed: \\({3}.{2}())")""");

    templates.put(Language.GO,
        """
            type {0} struct {
                {1} string
            }

            func New{0}({1} string) *{0} {
                return &{0}{ {1}: {1} }
            }

            // Process the stored data
            func (o *{0}) {2}() string {
                return strings.ToUpper(o.{1})
            }

            func main() {
                {3} := New{0}("example")
                fmt.Println("Processed:", {3}.{2}())
            }""");

    return Formatter.format(
        templates.getOrDefault(language, templates.get(Language.PYTHON)),
        className, propertyName, methodName, instanceName
    );
  }

  public static String generateLoop(Language language) {
    String varName = generateIdentifier("i");
    String arrayName = generateIdentifier("items");
    int maxValue = random.nextInt(5) + 3;

    Map<Language, String> templates = new EnumMap<>(Language.class);
    templates.put(Language.PYTHON,
        """
            {1} = ["apple", "banana", "cherry"]
            print("For loop:")
            for {0} in range({2}):
                print(f"Iteration {0}")

            print("While loop:")
            count = 0
            while count < {2}:
                print(f"Count: {count}")
                count += 1""");

    templates.put(Language.JAVA,
        """
            public class LoopExample {
                public static void main(String[] args) {
                    String[] {1} = {"apple", "banana", "cherry"};

                    System.out.println("For loop:");
                    for (int {0} = 0; {0} < {2}; {0}++) {
                        System.out.println("Iteration " + {0});
                    }

                    System.out.println("Enhanced for loop:");
                    for (String item : {1}) {
                        System.out.println("Fruit: " + item);
                    }
                }
            }""");

    templates.put(Language.JAVASCRIPT,
        "const {1} = [\"apple\", \"banana\", \"cherry\"];\n\n" +
            "console.log(\"For loop:\");\n" +
            "for (let {0} = 0; {0} < {2}; {0}++) {\n" +
            "    console.log(`Iteration ${0}`);\n" +
            "}\n\n" +
            "console.log(\"For-of loop:\");\n" +
            "for (const item of {1}) {\n" +
            "    console.log(`Fruit: ${item}`);\n" +
            "}");

    templates.put(Language.CPP,
        """
            #include <iostream>
            #include <vector>
            #include <string>

            int main() {
                std::vector<std::string> {1} = {"apple", "banana", "cherry"};

                std::cout << "For loop:" << std::endl;
                for (int {0} = 0; {0} < {2}; {0}++) {
                    std::cout << "Iteration " << {0} << std::endl;
                }

                std::cout << "Range-based for loop:" << std::endl;
                for (const std::string& item : {1}) {
                    std::cout << "Fruit: " << item << std::endl;
                }
                return 0;
            }""");

    templates.put(Language.GO,
        """
            package main

            import "fmt"

            func main() {
                {1} := []string{"apple", "banana", "cherry"}

                fmt.Println("For loop:")
                for {0} := 0; {0} < {2}; {0}++ {
                    fmt.Printf("Iteration %d\\n", {0})
                }

                fmt.Println("Range loop:")
                for _, item := range {1} {
                    fmt.Printf("Fruit: %s\\n", item)
                }
            }""");

    return Formatter.format(
        templates.getOrDefault(language, templates.get(Language.PYTHON)),
        varName, arrayName, String.valueOf(maxValue)
    );
  }

  public static String generateConditional(Language language) {
    String varName = generateIdentifier("num");
    int value = random.nextInt(10) + 1;

    Map<Language, String> templates = new EnumMap<>(Language.class);
    templates.put(Language.PYTHON,
        """
            {0} = {1}
            if {0} > 5:
                print("Greater than 5")
            elif {0} < 5:
                print("Less than 5")
            else:
                print("Equal to 5")

            # Ternary operator
            result = "Even" if {0} % 2 == 0 else "Odd"
            print(f"Number is {result}")""");

    templates.put(Language.JAVA,
        """
            public class ConditionalExample {
                public static void main(String[] args) {
                    int {0} = {1};

                    if ({0} > 5) {
                        System.out.println("Greater than 5");
                    } else if ({0} < 5) {
                        System.out.println("Less than 5");
                    } else {
                        System.out.println("Equal to 5");
                    }

                    // Ternary operator
                    String result = ({0} % 2 == 0) ? "Even" : "Odd";
                    System.out.println("Number is " + result);
                }
            }""");

    templates.put(Language.JAVASCRIPT,
        """
            const {0} = {1};

            if ({0} > 5) {
                console.log("Greater than 5");
            } else if ({0} < 5) {
                console.log("Less than 5");
            } else {
                console.log("Equal to 5");
            }

            // Ternary operator
            const result = ({0} % 2 === 0) ? "Even" : "Odd";
            console.log(`Number is ${result}`);""");

    templates.put(Language.CPP,
        """
            #include <iostream>

            int main() {
                int {0} = {1};

                if ({0} > 5) {
                    std::cout << "Greater than 5" << std::endl;
                } else if ({0} < 5) {
                    std::cout << "Less than 5" << std::endl;
                } else {
                    std::cout << "Equal to 5" << std::endl;
                }

                // Ternary operator
                const char* result = ({0} % 2 == 0) ? "Even" : "Odd";
                std::cout << "Number is " << result << std::endl;
                return 0;
            }""");

    templates.put(Language.SWIFT,
        """
            let {0} = {1}

            if {0} > 5 {
                print("Greater than 5")
            } else if {0} < 5 {
                print("Less than 5")
            } else {
                print("Equal to 5")
            }

            // Ternary operator
            let result = {0} % 2 == 0 ? "Even" : "Odd"
            print("Number is \\(result)")""");

    return Formatter.format(
        templates.getOrDefault(language, templates.get(Language.PYTHON)),
        varName, String.valueOf(value)
    );
  }

  public static String generateDataStructure(Language language) {
    String mapName = generateIdentifier("ages");
    String listName = generateIdentifier("names");

    Map<Language, String> templates = new EnumMap<>(Language.class);
    templates.put(Language.PYTHON,
        """
            # Dictionary
            {0} = {"Alice": 28, "Bob": 32, "Charlie": 25}

            # List
            {1} = ["Alice", "Bob", "Charlie"]

            print("Dictionary operations:")
            print(f"Bob's age: {0}['Bob']}")
            print("Keys:", {0}.keys())

            print("\\nList operations:")
            print("First name:", {1}[0])
            print("Slice:", {1}[1:])""");

    templates.put(Language.JAVA,
        """
            import java.util.*;

            public class DataStructures {
                public static void main(String[] args) {
                    // HashMap
                    Map<String, Integer> {0} = new HashMap<>();
                    {0}.put("Alice", 28);
                    {0}.put("Bob", 32);
                    {0}.put("Charlie", 25);

                    // ArrayList
                    List<String> {1} = new ArrayList<>();
                    {1}.add("Alice");
                    {1}.add("Bob");
                    {1}.add("Charlie");

                    System.out.println("Map operations:");
                    System.out.println("Bob's age: " + {0}.get("Bob"));
                    System.out.println("Keys: " + {0}.keySet());

                    System.out.println("\\nList operations:");
                    System.out.println("First name: " + {1}.get(0));
                    System.out.println("Sublist: " + {1}.subList(1, {1}.size()));
                }
            }""");

    templates.put(Language.JAVASCRIPT,
        """
            // Object as map
            const {0} = {
                Alice: 28,
                Bob: 32,
                Charlie: 25
            };

            // Array
            const {1} = ["Alice", "Bob", "Charlie"];

            console.log("Map operations:");
            console.log(`Bob's age: ${0}.Bob}`);
            console.log("Keys:", Object.keys({0}));

            console.log("\\nArray operations:");
            console.log(`First name: ${1}[0]}`);
            console.log("Slice:", {1}.slice(1));""");

    templates.put(Language.GO,
        """
            package main

            import (
            \t"fmt"
            )

            func main() {
                // Map
                {0} := map[string]int{
                    "Alice": 28,
                    "Bob": 32,
                    "Charlie": 25,
                }

                // Slice
                {1} := []string{"Alice", "Bob", "Charlie"}

                fmt.Println("Map operations:")
                fmt.Println("Bob's age:", {0}["Bob"])
                keys := make([]string, 0, len({0}))
                for k := range {0} {
                    keys = append(keys, k)
                }
                fmt.Println("Keys:", keys)

                fmt.Println("\\nSlice operations:")
                fmt.Println("First name:", {1}[0])
                fmt.Println("Sublice:", {1}[1:])
            }""");

    return Formatter.format(
        templates.getOrDefault(language, templates.get(Language.PYTHON)), mapName, listName
    );
  }

  public static String generateErrorHandling(Language language) {
    String fileName = "\"data.txt\"";

    Map<Language, String> templates = new EnumMap<>(Language.class);
    templates.put(Language.PYTHON,
        """
            print("File operation with error handling")
            try:
                # Attempt to open a file
                with open({0}, 'r') as f:
                    content = f.read()
                    print("File content:", content)
            except FileNotFoundError as e:
                print(f"File not found: {e}")
            except Exception as e:
                print(f"Unexpected error: {e}")
            else:
                print("File read successfully")
            finally:
                print("Cleanup completed")""");

    templates.put(Language.JAVA,
        """
            import java.io.*;

            public class ErrorHandlingExample {
                public static void main(String[] args) {
                    System.out.println("File operation with error handling");
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader({0}));
                        String line = reader.readLine();
                        System.out.println("First line: " + line);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("I/O error: " + e.getMessage());
                    } finally {
                        try {
                            if (reader != null) {
                                reader.close();
                            }
                            System.out.println("Cleanup completed");
                        } catch (IOException e) {
                            System.out.println("Error closing file: " + e.getMessage());
                        }
                    }
                }
            }""");

    templates.put(Language.JAVASCRIPT,
        """
            console.log("File operation with error handling");
            const fs = require('fs');

            fs.readFile({0}, 'utf8', (err, data) => {
                if (err) {
                    if (err.code === 'ENOENT') {
                        console.log(`File not found: ${err.message}`);
                    } else {
                        console.log(`Unexpected error: ${err.message}`);
                    }
                    return;
                }
                console.log("File content:", data);
            });

            // Promise-based with async/await
            (async () => {
                try {
                    const data = await fs.promises.readFile({0}, 'utf8');
                    console.log("File content:", data);
                } catch (err) {
                    if (err.code === 'ENOENT') {
                        console.log(`File not found: ${err.message}`);
                    } else {
                        console.log(`Unexpected error: ${err.message}`);
                    }
                }
            })();""");

    templates.put(Language.CPP,
        """
            #include <iostream>
            #include <fstream>
            #include <stdexcept>

            int main() {
                std::cout << "File operation with error handling" << std::endl;
                std::ifstream file;
               \s
                try {
                    file.open({0});
                    if (!file) {
                        throw std::runtime_error("File not found");
                    }
                   \s
                    std::string line;
                    std::getline(file, line);
                    std::cout << "First line: " << line << std::endl;
                } catch (const std::exception& e) {
                    std::cerr << "Error: " << e.what() << std::endl;
                }
               \s
                if (file.is_open()) {
                    file.close();
                }
                std::cout << "Cleanup completed" << std::endl;
                return 0;
            }""");

    return Formatter.format(
        templates.getOrDefault(language, templates.get(Language.PYTHON)),
        fileName
    );
  }

  public static String generateIdentifier(String type) {
    String[] prefixes = {};
    String[] suffixes = {};

    switch (type.toLowerCase()) {
      case "class":
        prefixes = new String[]{"User", "Data", "Account", "System", "Network"};
        suffixes = new String[]{"Manager", "Service", "Handler", "Controller", "Util", "Helper"};
        break;
      case "function":
        prefixes = new String[]{"get", "set", "create", "update", "delete", "find", "calculate"};
        suffixes = new String[]{"User", "Data", "Value", "Item", "Result"};
        break;
      default: // variable
        prefixes = new String[]{"user", "data", "result", "temp", "current", "new"};
        suffixes = new String[]{"Id", "Name", "Count", "Total", "Status"};
    }

    String prefix = prefixes[random.nextInt(prefixes.length)];
    String suffix = suffixes[random.nextInt(suffixes.length)];

    if (type.equals("class")) {
      return prefix + suffix;
    }

    if (random.nextDouble() < 0.7) {
      return prefix + suffix;
    }
    return prefix;
  }

  public static List<Language> getSupportedLanguages() {
    return new ArrayList<>(LANGUAGE_FEATURES.keySet());
  }

  public static String getLanguageName(Language language) {
    return LANGUAGE_NAMES.getOrDefault(language, "Unknown");
  }
}
