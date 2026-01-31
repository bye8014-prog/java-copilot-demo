# GitHub Setup Instructions

## Your SSH Key Has Been Generated ✅

Your public SSH key is:
```
ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIBnBUlSIiiuicqjTPKx+PZXOgtJnKx4VoW0Cby5VW9cA benjamin@example.com
```

## Step 1: Create a GitHub Account

If you don't already have one:

1. Go to https://github.com/signup
2. Enter your email address
3. Create a password
4. Choose a username (this will be YOUR_USERNAME in commands)
5. Verify your email address

**Remember your username** - you'll need it in the next steps.

## Step 2: Add SSH Key to GitHub

1. Go to https://github.com/settings/keys (or Settings → SSH and GPG keys)
2. Click "New SSH key"
3. Title: "My Mac" (or any descriptive name)
4. Key type: "Authentication Key"
5. Key: Paste the SSH key from above
6. Click "Add SSH key"

Your computer can now authenticate with GitHub using SSH!

## Step 3: Create Repository on GitHub

1. Go to https://github.com/new (or click "+" → "New repository")
2. Fill in:
   - **Repository name**: `java-copilot-demo`
   - **Description**: "A demo Java project with debugging guide and git integration"
   - **Visibility**: Choose "Public" (anyone can see) or "Private" (only you)
   - **Skip** "Initialize this repository with:" (we already have local commits)
3. Click "Create repository"

## Step 4: Connect Your Local Repository to GitHub

Replace `YOUR_USERNAME` with your actual GitHub username, then run:

```bash
cd /Users/benjamin/java-copilot-demo
git remote add origin git@github.com:YOUR_USERNAME/java-copilot-demo.git
```

**Verify the connection:**
```bash
git remote -v
# Output should show:
# origin  git@github.com:YOUR_USERNAME/java-copilot-demo.git (fetch)
# origin  git@github.com:YOUR_USERNAME/java-copilot-demo.git (push)
```

## Step 5: Push Your Code to GitHub

```bash
cd /Users/benjamin/java-copilot-demo
git branch -M main
git push -u origin main
```

**Expected output:**
```
Enumerating objects: 6, done.
Counting objects: 100% (6/6), done.
...
To github.com:YOUR_USERNAME/java-copilot-demo.git
 * [new branch]      main -> main
Branch 'main' set up to track remote branch 'main' from 'origin'.
```

## Step 6: Verify on GitHub

Visit: `https://github.com/YOUR_USERNAME/java-copilot-demo`

You should see:
- Your code files (Main.java, pom.xml, etc.)
- Your commit history
- The DEBUGGING_GUIDE.md and GIT_GUIDE.md files

## Continuing Workflow

After the initial setup, your workflow is simple:

### Making Changes and Pushing
```bash
# 1. Make changes to your files
# 2. Check status
git status

# 3. Stage changes
git add .

# 4. Commit changes
git commit -m "Describe your changes"

# 5. Push to GitHub
git push
```

### Pulling Latest Changes (if working on multiple machines)
```bash
git pull origin main
```

### Creating Branches for Features
```bash
# Create a new feature branch
git checkout -b feature/add-division

# Make your changes
# Stage and commit
git add .
git commit -m "Add division function"

# Push the branch
git push -u origin feature/add-division

# Go to GitHub to create a Pull Request (PR)
# Or merge back to main when done
```

## Common GitHub Terms

- **Pull Request (PR)**: Propose changes from one branch to another
- **Fork**: Create your own copy of someone else's repository
- **Clone**: Download a repository from GitHub
- **Upstream**: The original repository you forked from
- **Issues**: Bug reports, feature requests, discussions
- **Actions**: Automated testing and deployment
- **Pages**: Host a website from your repository

## Next Steps After First Push

1. **Explore GitHub Features**
   - Add a description to your repository
   - Add topics (java, debugging, education)
   - Enable GitHub Pages (optional)

2. **Invite Collaborators**
   - Go to Settings → Collaborators
   - Share your username with others

3. **Keep Practicing Git**
   - Make more commits
   - Create and merge branches
   - Try creating pull requests

4. **Backup Your Code**
   - Your repository is now backed up on GitHub
   - You can clone it on any other machine

## Troubleshooting

### SSH Connection Failed
If you get: `ssh: connect to host github.com port 22: Connection refused`

Try using HTTPS instead:
```bash
git remote set-url origin https://github.com/YOUR_USERNAME/java-copilot-demo.git
```

Then push:
```bash
git push -u origin main
# Enter your GitHub username and personal access token
```

### Permission Denied
If you see: `Permission denied (publickey)`

Make sure your SSH key is added to GitHub:
1. Go to https://github.com/settings/keys
2. Verify your SSH key is listed
3. Test SSH connection: `ssh -T git@github.com`

### Push Rejected
If push fails with merge conflicts, pull first:
```bash
git pull origin main
# Resolve any conflicts
git push origin main
```

