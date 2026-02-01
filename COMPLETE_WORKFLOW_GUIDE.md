# Complete Java Development Workflow Guide

## Part 1: Introduction

### Who Am I?
I'm **GitHub Copilot**, your AI programming assistant powered by Claude. I can help you with:
- Writing and debugging Java code
- Understanding git and version control
- Setting up development environments
- Creating comprehensive documentation
- Testing and validating your code

### This Project: java-copilot-demo
A learning project demonstrating:
- ✅ Java program structure and execution
- ✅ Git version control fundamentals
- ✅ GitHub cloud integration
- ✅ Java debugging techniques
- ✅ Development workflows with branching and merging

---

## Part 2: Demo - Run the Program

### What the Program Does
The `Main.java` program demonstrates:
1. **Factorial Calculation**: Computes 6! = 720
2. **Loop Logic**: Finds multiples of 4 (12, 24)

### Build the Program
```bash
cd /Users/benjamin/java-copilot-demo
mvn clean compile
```

**Output:**
```
[INFO] Compiling 1 source file to /Users/benjamin/java-copilot-demo/target/classes
[INFO] BUILD SUCCESS
```

### Run the Program
```bash
java -cp target/classes com.example.Main
```

**Expected Output:**
```
factorial(6) = 720
multiple of 4: 12
multiple of 4: 24
```

### Debug the Program
1. **Open in VS Code**: `code .`
2. **Set Breakpoint**: Click on line 5 (in factorial method)
3. **Press F5**: Start debugging
4. **Inspect Variables**: Watch `n`, `result`, `i` values
5. **Step Through**: Use F10 (step over) or F11 (step into)
6. **Continue**: Press F5 to jump to next breakpoint

---

## Part 3: Create Git Repository & Push to GitHub

### Status: ✅ Already Done!

You already have:
```bash
# Repository created
git init
✓ .git directory created

# Commits created
git log --oneline
✓ b15b543 Add comprehensive learning summary document
✓ 23cc87e Add comprehensive guides for debugging, git, and GitHub setup
✓ 6a9bbdc Initial commit: Java project setup with debugging guide and git integration

# Pushed to GitHub
git push -u origin main
✓ Code now at: https://github.com/bye8014-prog/java-copilot-demo
```

---

## Part 4: Make Changes & Create Commits

### Workflow Pattern
```
1. Create feature branch
2. Make changes
3. Commit with meaningful message
4. Repeat steps 2-3
5. Merge back to main
```

### Example 1: Add Javadoc Comments

**Create feature branch:**
```bash
git checkout -b feature/add-javadoc
```

**Edit Main.java to add Javadoc:**
```java
/**
 * Calculates the factorial of a number.
 * Example: factorial(6) = 1 × 2 × 3 × 4 × 5 × 6 = 720
 * 
 * @param n the number to calculate factorial for
 * @return the factorial of n
 */
public static int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}

/**
 * Main entry point demonstrating factorial calculation
 * and finding multiples of 4.
 * 
 * @param args command line arguments (unused)
 */
public static void main(String[] args) {
    // ... rest of code
}
```

**Commit the changes:**
```bash
git add src/main/java/com/example/Main.java
git commit -m "Add Javadoc comments to Main.java

- Document factorial() method with parameters and return
- Document main() method and its purpose
- Improves code documentation for developers"
```

**Merge back to main:**
```bash
git checkout main
git merge feature/add-javadoc
git branch -d feature/add-javadoc
```

**Push to GitHub:**
```bash
git push origin main
```

### Example 2: Add a New Method

**Create new feature branch:**
```bash
git checkout -b feature/add-division
```

**Add division method to Main.java:**
```java
/**
 * Divides two numbers with error handling.
 * 
 * @param dividend the number to divide
 * @param divisor the number to divide by
 * @return the result of division, or -1 if divisor is 0
 */
public static int divide(int dividend, int divisor) {
    if (divisor == 0) {
        System.err.println("Error: Cannot divide by zero");
        return -1;
    }
    return dividend / divisor;
}
```

**Call it from main:**
```java
public static void main(String[] args) {
    // ... existing code ...
    
    // Test division
    int quotient = divide(20, 4);
    System.out.println("20 / 4 = " + quotient);
}
```

**Commit:**
```bash
git add src/main/java/com/example/Main.java
git commit -m "Add divide() method with zero-check

- Implements division with error handling
- Returns -1 when divisor is 0
- Prints error message to stderr
- Called from main() for demonstration"
```

**Test it:**
```bash
mvn clean compile
java -cp target/classes com.example.Main
# Output includes: 20 / 4 = 5
```

**Merge and push:**
```bash
git checkout main
git merge feature/add-division
git push origin main
```

### Example 3: Intentional Bug & Recovery

**Create bug branch:**
```bash
git checkout -b bugfix/break-factorial
```

**Introduce a bug (on purpose):**
```java
public static int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;
        result = result / 2;  // BUG: dividing by 2!
    }
    return result;
}
```

**Commit the buggy code:**
```bash
git add src/main/java/com/example/Main.java
git commit -m "BROKEN: Divide factorial by 2 (testing)"
```

**Build and test:**
```bash
mvn clean compile
java -cp target/classes com.example.Main
# Output: factorial(6) = 360 (WRONG! Should be 720)
```

**Fix the bug:**
```java
public static int factorial(int n) {
    int result = 1;
    for (int i = 2; i <= n; i++) {
        result *= i;  // Remove the division
    }
    return result;
}
```

**Commit the fix:**
```bash
git add src/main/java/com/example/Main.java
git commit -m "Fix: Remove erroneous division in factorial

- Removed 'result = result / 2' line
- factorial(6) now correctly returns 720"
```

**Or recover from previous version:**
```bash
# View history
git log --oneline

# Go back to before the bug
git revert HEAD
# This creates a NEW commit that undoes the changes

# Or hard reset (destructive)
git reset --hard HEAD~1
# This removes the buggy commit entirely
```

**Merge and push:**
```bash
git checkout main
git merge bugfix/break-factorial
git push origin main
```

---

## Part 5: Debugging Operations

### Breakpoints

#### Simple Breakpoint
```java
public static int factorial(int n) {
    int result = 1;  // ← Click here to set breakpoint
    for (int i = 2; i <= n; i++) {
        result *= i;
    }
    return result;
}
```

Then press **F5** to debug - execution pauses at this line.

#### Conditional Breakpoint
```java
for (int i = 1; i <= 10; i++) {
    int v = i * 3;
    if (v % 4 == 0) {
        System.out.println("multiple of 4: " + v);  // ← Right-click → Add Conditional Breakpoint
        // Condition: v == 12
    }
}
```

Pauses only when `v` equals 12 (first time through loop where `v % 4 == 0`).

### Stepping Operations

**While paused in debugger:**

| Operation | Keyboard | When to Use |
|-----------|----------|------------|
| Step Over | F10 | Execute current line, skip function calls |
| Step Into | F11 | Enter function to see internal logic |
| Step Out | Shift+F11 | Exit current function, back to caller |
| Continue | F5 | Run until next breakpoint |
| Pause | Ctrl+Alt+P | Pause running program |

### Example Debug Session

1. **Set breakpoint at line 5** (start of factorial)
2. **Press F5** to start debugging
3. **Inspector panel shows:**
   - `n = 6` (parameter)
   - `result = 1` (local variable)
4. **Press F10** (step over) to go through loop:
   - Iteration 1: `i = 2`, `result = 2` (1 × 2)
   - Iteration 2: `i = 3`, `result = 6` (2 × 3)
   - Iteration 3: `i = 4`, `result = 24` (6 × 4)
   - Iteration 4: `i = 5`, `result = 120` (24 × 5)
   - Iteration 5: `i = 6`, `result = 720` (120 × 6)
5. **Verify: result = 720** ✓

### Inspecting Variables

**Right-click variable → Add to Watch**

Watch expressions you can monitor:
- `result` - current factorial accumulation
- `i` - current loop iteration
- `n * 2` - mathematical expressions
- `result > 100` - boolean conditions

### Viewing Call Stack

Shows how you got to current location:
```
Stack:
main()  ← Program started here
  └─ factorial(6)  ← Currently executing here
```

Each level shows file and line number.

---

## Part 6: Using AI Tools (GitHub Copilot)

### How Copilot Helps

#### 1. Code Completion
Start typing and Copilot suggests:
```java
public static int // Copilot suggests: multiply(int a, int b)
```

Press **Tab** to accept suggestion.

#### 2. Generate Javadoc
```java
/**
 * // Copilot auto-generates:
 * Calculates the factorial of a number.
 * ...
 */
public static int factorial(int n) {
```

#### 3. Generate Tests
```java
// Copilot can generate test cases:
@Test
public void testFactorial() {
    assertEquals(1, factorial(1));
    assertEquals(720, factorial(6));
    assertEquals(40320, factorial(8));
}
```

#### 4. Refactor Code
```java
// Original:
for (int i = 2; i <= n; i++) {
    result *= i;
}

// Copilot suggests cleaner approach using streams:
result = IntStream.rangeClosed(2, n).reduce(1, (a, b) -> a * b);
```

#### 5. Explain Code
- Select code
- Ask Copilot: "Explain this code"
- Get explanation of what it does

#### 6. Fix Bugs
- Describe the problem
- Copilot suggests fixes
- Review and apply changes

---

## Complete Workflow Summary

### Day 1: Initial Setup ✅
```bash
git init                           # Initialize local repo
git add .                          # Stage files
git commit -m "Initial commit"     # Create snapshot
git remote add origin <url>        # Connect to GitHub
git push -u origin main            # Upload to GitHub
```

### Day 2: Feature Development
```bash
git checkout -b feature/my-feature # Create branch
# Make changes
git add .
git commit -m "Add my feature"     # Multiple commits
git commit -m "Refine feature"
git checkout main                  # Switch to main
git merge feature/my-feature       # Merge changes
git push origin main               # Upload to GitHub
git branch -d feature/my-feature   # Clean up
```

### Day 3: Bug Fixes
```bash
git checkout -b bugfix/issue       # Create fix branch
# Debug using breakpoints and stepping
# Make fixes
git add .
git commit -m "Fix: describe issue"
git checkout main
git merge bugfix/issue
git push origin main
```

### Debugging Loop
```
1. Set breakpoint
2. Press F5 (debug)
3. Program pauses at breakpoint
4. Inspect variables (watch panel)
5. Step through code (F10/F11)
6. Verify state is correct
7. Continue to next breakpoint (F5)
8. Fix issues found
9. Commit fix with git
```

---

## Commands Reference

### Git Commands
```bash
# Checking status
git status
git log --oneline
git diff

# Creating branches
git checkout -b feature/name
git branch -D name

# Committing
git add .
git commit -m "message"

# Merging
git merge feature/name
git rebase main

# Pushing/Pulling
git push origin main
git pull origin main

# Undoing
git revert HEAD
git reset --hard HEAD~1
```

### Build & Run
```bash
mvn clean compile     # Build
java -cp target/classes com.example.Main  # Run
```

### Debug
```bash
F5          # Start/continue debugging
F10         # Step over
F11         # Step into
Shift+F11   # Step out
Ctrl+Alt+P  # Pause
```

---

## Best Practices

### Commits
- ✅ Commit frequently (after each feature/fix)
- ✅ Use clear messages ("Add factorial method" not "update")
- ✅ One logical change per commit

### Branches
- ✅ Create branch for each feature
- ✅ Name clearly: `feature/`, `bugfix/`, `experiment/`
- ✅ Delete after merging

### Debugging
- ✅ Use breakpoints to pause at suspected issues
- ✅ Inspect variables before and after operations
- ✅ Step through loops iteration by iteration
- ✅ Watch expressions for repeated checks

### GitHub
- ✅ Push daily (backup)
- ✅ Write meaningful commit messages
- ✅ Keep README up to date
- ✅ Add issues for bugs and features

---

## Example: Full Development Cycle

### Scenario: Add power() method

**1. Create branch**
```bash
git checkout -b feature/add-power
```

**2. Write code with Copilot help**
```java
/**
 * Calculates base raised to the power of exponent.
 * @param base the base number
 * @param exponent the power
 * @return base^exponent
 */
public static long power(int base, int exponent) {
    long result = 1;
    for (int i = 0; i < exponent; i++) {
        result *= base;
    }
    return result;
}
```

**3. Test by running**
```bash
# Add to main():
System.out.println("2^8 = " + power(2, 8));

mvn clean compile
java -cp target/classes com.example.Main
# Output: 2^8 = 256
```

**4. Debug to verify**
- Set breakpoint in power() method
- Press F5
- Watch `result` accumulate: 1 → 2 → 4 → 8 → 16 → 32 → 64 → 128 → 256
- Verify correct output

**5. Commit**
```bash
git add src/main/java/com/example/Main.java
git commit -m "Add power() method for exponentiation

- Calculates base^exponent efficiently
- Tested with 2^8 = 256
- Ready for production use"
```

**6. Merge to main**
```bash
git checkout main
git merge feature/add-power
```

**7. Push to GitHub**
```bash
git push origin main
```

**8. View on GitHub**
```
https://github.com/bye8014-prog/java-copilot-demo
```

---

## What's Next?

1. **Practice the workflow**: Make small changes, commit, push
2. **Try advanced git**: Rebasing, stashing, cherry-picking
3. **Write tests**: Add unit tests using JUnit
4. **Use Copilot more**: Ask it to explain, refactor, generate docs
5. **Debug complex issues**: Use breakpoints on tricky code
6. **Collaborate**: Invite others to your GitHub repo

You now have all the tools to become a professional Java developer! 🚀

