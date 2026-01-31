# Git Fundamentals Guide

## What is Git?

Git is a **version control system** that helps you:
- Track changes to your code over time
- Collaborate with other developers
- Undo mistakes by reverting to previous versions
- Maintain a complete history of your project

Think of it like a "save points" system for your code - you can take snapshots (commits) and go back to any previous state.

## Key Git Concepts

### Repository (.git directory)
- A folder containing your entire project history
- Created with `git init`
- Stored in `.git` subdirectory
- Can be uploaded to GitHub for backup and sharing

### Staging Area (Index)
- Intermediate step between working directory and repository
- You select which files to include in the next commit
- Think of it as a "shopping cart" for changes

### Working Directory
- Your actual files on disk that you edit
- Files here may be tracked or untracked

### Commit
- A "snapshot" of your code at a specific point in time
- Contains changes, author info, timestamp, and a message
- Creates a permanent record in the repository

### Branch
- An independent line of development
- Default branch is usually "main" or "master"
- Allows parallel work without affecting main code

## Git Workflow

```
1. Make changes to files (Working Directory)
        ↓
2. Stage changes with `git add` (Staging Area)
        ↓
3. Commit changes with `git commit` (Repository)
        ↓
4. Push to remote (GitHub) with `git push`
```

## Setup (What We Just Did)

### 1. Initialize Repository
```bash
git init
# Creates .git directory with version control system
```

### 2. Configure User Identity
```bash
git config --global user.name "Benjamin"
git config --global user.email "benjamin@example.com"
# Sets who you are - appears on all commits
# --global flag applies to all repositories on your computer
```

### 3. Create .gitignore File
```bash
# Tells Git which files to ignore
# Useful for:
# - Build artifacts (target/, build/)
# - IDE settings (.idea/, .vscode/)
# - Temporary files
# - Sensitive data (.env files)
```

The `.gitignore` file in this project ignores:
- `target/` - Maven build output
- `.vscode/` - VS Code settings
- `.idea/` - IntelliJ IDEA settings
- `.class`, `.jar` - Compiled Java files
- `.DS_Store`, `Thumbs.db` - OS files

**Why ignore files?**
- Keeps repository clean and small
- Avoids conflicts from IDE differences
- Prevents committing generated files
- Reduces repository size on GitHub

## Common Git Commands

### Checking Status
```bash
git status
# Shows:
# - Current branch
# - Staged files (ready to commit)
# - Unstaged files (modified but not staged)
# - Untracked files (new files not in git)

# Output example:
# On branch main
# Changes to be committed:
#   new file:   DEBUGGING_GUIDE.md
# Changes not staged for commit:
#   modified:   README.md
# Untracked files:
#   .DS_Store
```

### Staging Files
```bash
# Stage specific file
git add README.md

# Stage all changes
git add .

# Stage files matching pattern
git add src/**/*.java

# Remove file from staging (unstage)
git reset HEAD DEBUGGING_GUIDE.md
```

### Creating Commits
```bash
# Commit staged changes
git commit -m "Add debugging guide"

# Commit with detailed message
git commit -m "Add debugging guide

This guide covers:
- Breakpoint types
- Stepping operations
- Variable inspection"

# Skip staging and commit tracked files directly
git commit -am "Update Main.java"
```

### Viewing History
```bash
# Show commits in condensed format
git log --oneline
# Output: 6a9bbdc (HEAD -> main) Initial commit

# Show detailed commit history
git log

# Show changes in last commit
git show HEAD

# Show all commits affecting a file
git log -- src/main/java/com/example/Main.java

# Show commits by author
git log --author="Benjamin"
```

### Comparing Changes
```bash
# Show unstaged changes
git diff

# Show staged changes
git diff --cached

# Show changes in a file
git diff src/main/java/com/example/Main.java

# Show changes in last commit
git diff HEAD~1 HEAD

# Show changes between commits
git diff 6a9bbdc abc1234
```

### Undoing Changes
```bash
# Discard changes in working directory
git restore README.md

# Unstage a file
git restore --staged DEBUGGING_GUIDE.md

# Undo last commit (keep changes staged)
git reset --soft HEAD~1

# Undo last commit (discard changes)
git reset --hard HEAD~1

# Revert last commit (create new commit that undoes changes)
git revert HEAD
```

### Branch Management
```bash
# See all branches
git branch
# Output: * main (asterisk = current branch)

# Create new branch
git branch feature/add-tests

# Switch to branch
git checkout feature/add-tests

# Create and switch in one command
git checkout -b feature/add-tests

# Delete branch
git branch -d feature/add-tests

# Delete branch forcefully
git branch -D feature/add-tests

# Merge branch into current branch
git merge feature/add-tests
```

## Git Workflow Example

Let's say you want to add a new feature:

### Step 1: Create a feature branch
```bash
git checkout -b feature/add-multiplication
```

### Step 2: Make changes
```bash
# Edit Main.java to add multiplication function
# (Files are now modified but not staged)
```

### Step 3: Check what changed
```bash
git status
# Shows: modified Main.java

git diff
# Shows exact changes made
```

### Step 4: Stage changes
```bash
git add src/main/java/com/example/Main.java
```

### Step 5: Commit changes
```bash
git commit -m "Add multiplication function to Main.java

- Creates multiply(a, b) method
- Returns product of two numbers
- Accessible from main method"
```

### Step 6: Switch back to main branch
```bash
git checkout main
```

### Step 7: Merge feature into main
```bash
git merge feature/add-multiplication
```

### Step 8: Delete feature branch
```bash
git branch -d feature/add-multiplication
```

## GitHub Integration

### What is GitHub?
- Cloud hosting service for Git repositories
- Provides backup of your code
- Enables collaboration
- Offers additional features (issues, pull requests, CI/CD)

### Setting Up SSH for GitHub
```bash
# Generate SSH key (do this once)
ssh-keygen -t ed25519 -C "benjamin@example.com"

# View your public key
cat ~/.ssh/id_ed25519.pub

# Add this key to GitHub:
# GitHub Settings → SSH and GPG keys → New SSH key
# Paste the key contents
```

### Connecting Your Repository to GitHub

1. **Create repository on GitHub**
   - Go to github.com
   - Click "+" → "New repository"
   - Name: `java-copilot-demo`
   - Description: "A demo Java project with debugging guide"
   - Click "Create repository"

2. **Add remote to your local repository**
   ```bash
   git remote add origin git@github.com:YOUR_USERNAME/java-copilot-demo.git
   ```
   - "origin" is the default name for the remote
   - This connects your local repo to GitHub

3. **Push your code to GitHub**
   ```bash
   git branch -M main
   git push -u origin main
   # -u sets origin/main as the default upstream branch
   ```

4. **Verify on GitHub**
   - Visit github.com/YOUR_USERNAME/java-copilot-demo
   - Your code is now backed up and visible!

## What We've Done in This Project

### Repository Status
```bash
# Current state after first commit
On branch main
# We have 1 commit
# All tracked files are clean

# Files tracked
- .github/copilot-instructions.md
- .gitignore
- DEBUGGING_GUIDE.md
- README.md
- pom.xml
- src/main/java/com/example/Main.java

# Files ignored (not tracked)
- target/           (Maven build output)
- .vscode/          (IDE settings)
- All .class files  (compiled Java)
```

### Git History
```
6a9bbdc (HEAD -> main) Initial commit: Java project setup...
```

## Best Practices

### Commit Messages
✅ **Good:**
- "Add factorial calculation to Main.java"
- "Fix null pointer in debug variable inspection"
- "Update .gitignore to ignore IDE files"

❌ **Bad:**
- "changes"
- "fix bug"
- "update"

### Commit Frequency
- Commit logical units of work
- Don't wait until you've made huge changes
- Make commits small enough to understand at a glance

### Branching Strategy
- Use `main` or `master` for production code
- Create branches for features: `feature/add-tests`
- Create branches for bug fixes: `bugfix/debug-issue`
- Create branches for experiments: `experiment/new-approach`

### Before Pushing
- Test your code
- Review changes: `git diff`
- Check commit message clarity
- Make sure nothing sensitive is included

## Helpful Git Commands Summary

| Command | Purpose |
|---------|---------|
| `git init` | Create new repository |
| `git clone <url>` | Download existing repository |
| `git status` | Check current state |
| `git add .` | Stage all changes |
| `git commit -m "msg"` | Create a snapshot |
| `git log` | View history |
| `git diff` | Show changes |
| `git restore <file>` | Discard changes |
| `git branch` | List branches |
| `git checkout -b <name>` | Create and switch branch |
| `git merge <branch>` | Combine branches |
| `git remote add origin <url>` | Connect to GitHub |
| `git push origin main` | Upload to GitHub |
| `git pull origin main` | Download from GitHub |

## Next Steps

1. **Create GitHub Account** (if you don't have one)
   - Go to github.com
   - Sign up with email

2. **Create SSH Key**
   - Run: `ssh-keygen -t ed25519 -C "your@email.com"`
   - Add public key to GitHub

3. **Connect This Repository to GitHub**
   - Create repository on GitHub
   - Add remote: `git remote add origin git@github.com:YOUR_USERNAME/java-copilot-demo.git`
   - Push code: `git push -u origin main`

4. **Practice Git Commands**
   - Make small changes
   - Stage and commit
   - View history
   - Create branches

## Resources

- Official Git Documentation: https://git-scm.com/doc
- GitHub Help: https://docs.github.com
- Interactive Git Learning: https://learngitbranching.js.org

