JMock
===

## Introduction

JMock is a high-performance data generation and simulation component library implemented in Java. It generates data that more closely resembles business data characteristics compared to random data.

## Features

- High-performance data generation: capable of generating 2 million+ user profiles per second on a single thread (each user profile includes 10 attribute fields, totaling 200+ bytes)
- Flexible data rules and internationalization support, producing data that more closely mimics business data characteristics
- Supports two methods for defining data generation rules and controlling data generation: annotations and functions. The "annotation method" is applied to class property fields, while the "function method" is used in script files (e.g., txt, json, yml)
- For batch data, supports persistent storage options including JDBC, memory, and local file systems
- Supports plugin-based extensions for Mock data functions

## Functions

### Expressions

- Annotation method: `@AnnotationTypeName` or `@AnnotationTypeName(param1=value1,param2=value2)`
- Function method: `@DataFunctionName()` or `@DataFunctionName(param1,param2)` or `@DataFunctionName(param1,param2,,param4,,param6)`

***Note:***

- In generation expressions, the "@" symbol identifies the expression, "()" is used to receive parameters, "," separates parameters, and "|" separates array elements when a parameter is an array.
- "DataFunctionName" and "AnnotationTypeName" must consist of letters and numbers. Both function names and annotation class names use PascalCase naming convention (i.e., first letter of each word capitalized), while parameter names use camelCase (i.e., first word all lowercase, subsequent words with first letter capitalized).
- `@AnnotationTypeName` and `@DataFunctionName()` represent no-argument constructors, using built-in default parameters to control data generation. `@AnnotationTypeName(param1=value1,param2=value2)` and `@DataFunctionName(param1,param2)` are parameterized constructors, with multiple parameters separated by ",". `@DataFunctionName(param1,param2,,param4,,param6)` is a full-parameter constructor where parameters can be omitted, but the separator "," must be retained.
- For the "data function" method, if the parameter string contains the above key characters, use "\" to escape them to avoid parsing errors, e.g., `@String(tom\@xcan.cloud|nick\@xcan.cloud)`.
- Compared to the "annotation method", the "function method" supports generating data for more scenario-based rules.

### Implementations

For detailed function implementations and descriptions, please refer to: [Function Documentation](docs/FUNCTION.md).

