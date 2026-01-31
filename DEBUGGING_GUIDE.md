# Java Debugging Guide

## Project Setup Summary

### 1. Java Extensions Installed
- ✅ Extension Pack for Java (includes Language Server, Debugger, Test Runner)
- ✅ Maven for Java
- ✅ Gradle for Java

These extensions provide:
- IntelliSense and code completion
- Built-in debugging capabilities
- Maven and Gradle integration
- Unit testing support

### 2. Java Environment
- **JDK**: Java 25.0.1 (Eclipse Adoptium)
- **Build Tool**: Maven 3.9.12
- **Source Level**: Java 11 (configured in pom.xml)
- **Target Level**: Java 11

### 3. Project Structure
```
java-copilot-demo/
├── pom.xml                    # Maven configuration
├── src/
│   ├── main/java/com/example/Main.java   # Main Java class
│   └── test/java/com/example/             # Test classes
├── target/                    # Build output (compiled classes)
└── .vscode/                   # VS Code configuration
    └── launch.json            # Debug configurations
```

## Debugging Basics

### What is Debugging?
Debugging is the process of finding and fixing errors in code. It allows you to:
- Pause program execution at specific points (breakpoints)
- Step through code line-by-line
- Inspect variable values
- Evaluate expressions
- View the call stack (how you got to current location)

### Types of Breakpoints

#### 1. Simple Breakpoint
- Pauses execution unconditionally
- Click on the line number in the editor to set

```java
public static int factorial(int n) {
    int result = 1;        // Breakpoint set here
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}
```

#### 2. Conditional Breakpoint
- Only pauses when a condition is true
- Useful for debugging loops with specific values
- Example: `v == 12` (pause only when v equals 12)

#### 3. Logpoint
- Prints a message to the debug console without pausing
- Useful for monitoring values without stopping execution

### Stepping Operations

1. **Step Over (F10)**
   - Execute the current line and move to the next line
   - If the line calls a function, execute the entire function
   - Use when you want to skip over function details

2. **Step Into (F11)**
   - Execute the current line
   - If it calls a function, enter that function
   - Use when you want to see inside function calls

3. **Step Out (Shift+F11)**
   - Continue execution until the current function returns
   - Use when you've finished examining a function

4. **Continue (F5)**
   - Resume execution until the next breakpoint
   - Use when you want to jump ahead

### Inspecting Variables and Expressions

#### Variables Panel
Shows all accessible variables in current scope:
- Local variables (defined in current function)
- Parameters (function arguments)
- Class fields (instance variables)

#### Watch Expressions
Add custom expressions to evaluate:
- `count + 1` (arithmetic)
- `list.size()` (method calls)
- `user != null && user.age > 18` (complex conditions)

#### Evaluation Console
Evaluate any Java expression while paused:
- Type expressions directly
- Access variables and methods
- Call functions

### Reading the Call Stack

The call stack shows the "breadcrumb trail" of how you got to current location:

```
main()                    ← Program starts here
  main() → calls
    factorial(6)          ← Currently paused here
      factorial() uses
        for loop
```

Stack frames (from bottom to top):
- `main` - Started execution
- `factorial` - Called from main, currently executing

## Debugging the Example Program

The Main.java program does two things:

```java
public class Main {
    // Calculate factorial (1 × 2 × 3 × ... × n)
    public static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        // Test 1: Calculate factorial(6) = 720
        int n = 6;
        int f = factorial(n);
        System.out.println("factorial(" + n + ") = " + f);

        // Test 2: Find multiples of 4
        for (int i = 1; i <= 10; i++) {
            int v = i * 3;
            if (v % 4 == 0) {
                System.out.println("multiple of 4: " + v);
            }
        }
    }
}
```

### Breakpoints Set for Learning

1. **Line 5** (in factorial method)
   - Type: Simple breakpoint
   - Purpose: See the start of factorial calculation

2. **Line 19** (in the loop checking for multiples of 4)
   - Type: Conditional breakpoint with `v == 12`
   - Purpose: Pause only when v equals 12 (first multiple of 4)

### Debug Session Workflow

1. **Start Debugging**
   - Press F5 or use Debug menu
   - Program runs until first breakpoint

2. **At First Breakpoint (Line 5)**
   - You're entering the factorial function
   - Inspect parameters: `n` should be 6
   - Use Step Over to go through the loop
   - Watch `result` variable change: 1 → 2 → 6 → 24 → 120 → 720

3. **Continue to Next Breakpoint**
   - Press F5 (Continue)
   - Program runs the first calculation
   - Stops at conditional breakpoint when `v == 12`

4. **At Conditional Breakpoint (Line 19)**
   - You're in the loop at `i = 4`
   - `v = 12` (4 × 3)
   - Inspect loop variables: `i`, `v`
   - Step through the if statement

5. **Observe Output**
   - After debugging finishes, see:
     ```
     factorial(6) = 720
     multiple of 4: 12
     multiple of 4: 24
     ```

## Advanced Debugging Techniques

### Expression Evaluation
While paused, evaluate expressions like:
- `n * 2` (double the parameter)
- `Math.pow(result, 2)` (square the result)
- `i > 5 && v % 8 == 0` (complex conditions)

### Conditional Logic
Conditional breakpoints use Java syntax:
- `i > 5` - trigger when i is greater than 5
- `name.equals("target")` - trigger when name matches
- `list.size() > 0` - trigger when list is not empty
- `x % 2 == 0` - trigger when x is even

### Variable Inspection
Check what's stored in memory:
- Primitive types: int, double, boolean, etc.
- Objects: inspect fields and call methods
- Collections: view array/list contents

## Building and Running the Program

### Build from Command Line
```bash
cd /Users/benjamin/java-copilot-demo
/Users/benjamin/.maven/maven-3.9.12/bin/mvn clean compile
```

### Run the Program
```bash
/Library/Java/JavaVirtualMachines/temurin-25.jdk/Contents/Home/bin/java -cp target/classes com.example.Main
```

Output:
```
factorial(6) = 720
multiple of 4: 12
multiple of 4: 24
```

## Key Concepts Summary

| Concept | Definition | Use Case |
|---------|-----------|----------|
| Breakpoint | Pause execution at a line | Investigate program state |
| Conditional Breakpoint | Pause only when condition is true | Debug specific loop iterations |
| Step Over | Execute line without entering functions | Skip function internals |
| Step Into | Enter function calls | Trace function execution |
| Variable Watch | Monitor specific variables | Track value changes |
| Expression Eval | Evaluate Java expressions | Test hypotheses |
| Call Stack | Show execution path | Understand program flow |

