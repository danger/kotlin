# kotlin
⚠️ Stop saying "you forgot to …" in code review in Kotlin

# Project status
The project is still work in progress.
Is possible to generate a working `danger-kotlin` instance from the code that is currently on this repo, but is not ready to be distributed yet.

### What it looks like today
You can make a `Dangerfile.df.kts` in your root project that looks through PR metadata, it's fully typed.

```kotlin
import systems.danger.kotlin.*
import org.jetbrains.kotlin.script.util.*

val danger = Danger(args)

val allSourceFiles = danger.git.modifiedFiles + danger.git.createdFiles

val changelogChanged = allSourceFiles.contains("CHANGELOG.md")
val sourceChanges = allSourceFiles.firstOrNull { it.contains("src") }
val isTrivial = danger.github.pullRequest.title.contains("#trivial")

if (!isTrivial && !changelogChanged && sourceChanges != null) {
    warn("Any changes to library code should be reflected in the Changelog.\n\nPlease consider adding a note there and adhere to the [Changelog Guidelines](https://github.com/Moya/contributors/blob/master/Changelog%20Guidelines.md).")
}

if (danger.git.createdFiles.size + danger.git.modifiedFiles.size - danger.git.deletedFiles.size > 10) {
    warn("Big PR, try to keep changes smaller if you can")
}

if (danger.github.pullRequest.title.contains("WIP" ,false)) {
    warn("PR is classed as Work in Progress")
}
```

### Setup
#### MacOs
```sh
brew install danger/tap/danger-kotlin
```

### Linux
```sh
bash <(curl -s https://raw.githubusercontent.com/danger/kotlin/master/scripts/install.sh)
source ~/.bash_profile
```

### GitHub Actions
You can add danger/kotlin to your actions

```yml
jobs:
  build:
    runs-on: ubuntu-latest
    name: "Run Danger"
    steps:
      - uses: actions/checkout@v1
      - name: Danger
        uses: danger/kotlin@0.2.0
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

### Autocomplete and Syntax highlighting in IntelliJ IDEA or Android Studio
You can activate the autocomplete following this additional steps:
- Install danger on your local machine
- Go to `Preferences -> Build, Execution, Deployment -> Compiler -> Kotlin Compiler` (`Preferences -> Kotlin Compiler` in Android Studio)
- At the bottom you will find a section `Kotlin Scripting`
- Complete the field `Script template classes` with  `systems.danger.kts.DangerFileScript`
- Complete the field `Script templates classpath` with `/usr/local/lib/danger/danger-kotlin.jar`
- Go to `Preferences -> Language & Frameworks -> Kotlin -> Kotlin Scripting`
- Make sure the script template `DangerFileScript` is active and above the default `Kotlin Script`
- Apply changes
- If opening the `Dangerfile.df.kts` the autocomplete and syntax highlighting doesn't work, try to reboot your IDE.

### Using external maven dependencies into your Dangerfile
You can use any external dependency by adding the following lines at the top of your `Dangerfile.df.kts`
```kotlin
@file:Repository("https://repo.maven.apache.org")
@file:DependsOn("groupId:artifactId:version")
```

### Commands

- `danger-kotlin ci` - Use this on CI
- `danger-kotlin pr https://github.com/Moya/Harvey/pull/23` - Use this to build your Dangerfile
- `danger-kotlin local` - Use this to run danger against your local changes from master

# Authors
`danger-kotlin` was developed by [@gianluz][] and [@f-meloni][]

[@f-meloni]: https://github.com/f-meloni
[@gianluz]: https://github.com/gianluz
