# JMock

[English](README.md) | [中文](README_zh.md)

## Introduction

JMock is a high-performance data generation and simulation library implemented in Java. It generates data that more closely resembles real business data characteristics compared to random data generation.

## Core Features

- **High-performance data generation**:  
  Generates 2M+ user records per second (single thread). Each record contains 10+ attributes totaling 200+ bytes.
- **Business-realistic data with i18n support**:  
  Produces data that accurately simulates real-world business scenarios with internationalization capabilities.
- **Dual definition approaches**:
    - *Annotation-based*: Applied to class fields
    - *Function-based*: Used in script files (txt, json, yml, etc.)
- **Multiple storage options**:  
  Supports JDBC, in-memory, and local filesystem persistence for batch data.
- **Extensible architecture**:  
  Plugin-based system for custom mock data functions.

## Function Expressions

### Expression Formats

| Type              | Parameterless Form | Parameterized Form                     |
|-------------------|-------------------|----------------------------------------|
| **Annotation**    | `@Annotation`     | `@Annotation(param1=value1, param2=value2)` |
| **Function**      | `@Function()`     | `@Function(param1, param2)` or `@Function(param1,,param3)` |

### Syntax Rules

1. **Core Symbols**
    - `@` - Marks expression start
    - `()` - Encloses parameters
    - `,` - Separates parameters
    - `|` - Delimits array elements (array parameters only)
    - `\` - Escapes special characters (`@`, `,`, `|`)

2. **Naming Conventions**
    - **Annotation/Function Names**: UpperCamelCase  
      *Example: `EmailValidator`, `RandomNumber`*
    - **Parameter Names**: lowerCamelCase  
      *Example: `minValue`, `maxLength`*

3. **Parameter Rules**
    - Parameterless calls: Omit parentheses (annotations) or use empty `()` (functions)
    - Parameterized calls:
        - Annotation: `param=value` key-value pairs
        - Function: Positional values
    - Omitted parameters: Preserve commas  
      *Example: `@Generate(1,,3)` indicates empty second parameter*

4. **Special Character Handling**  
   Escape `@`, `,`, `|` in parameter values:  
   *Example: `@String("tom\@domain.com\|nick\@domain.com")`*

## Usage Example

1. **Add Maven Dependency**
```xml
<dependency>
   <groupId>cloud.xcan.jmock</groupId>
   <artifactId>xcan-jmock.core</artifactId>
   <version>1.0.0</version>
</dependency>
```

2. **Generate Sample Data**
```java
// Define template
String content = """
    {
      "name": "@Name()",
      "email": "@Email()",
      "phone": "@Mobile()",
      "address": "@Address()",
      "hobbies": [ "reading", "hiking",  "cooking" ]
    }""";
    
// Process mock functions
String result = new DefaultMockTextReplacer().replace(content);

// Output result
System.out.println(result);
```

***Output:***
```json
{
  "name": "Durfee Jacob",
  "email": "9alJWYsUGJuJZtGuXT@yahoo.com.cn",
  "phone": "15292153757",
  "address": "ul. Akademika Pavlova, 12к3, Moskva",
  "hobbies": [ "reading", "hiking",  "cooking" ]
}
```
