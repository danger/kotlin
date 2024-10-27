[![Current 
Version](https://img.shields.io/badge/danger%20kotlin-v1.3.1-orange)](https://danger.systems/kotlin/)
[![Maven Central - SDK](https://img.shields.io/maven-central/v/systems.danger/danger-kotlin-sdk.svg?label=danger-kotlin-sdk)](https://search.maven.org/search?q=g:%22systems.danger%22%20AND%20a:%22danger-kotlin-sdk%22)
[![Awesome Kotlin Badge](https://kotlin.link/awesome-kotlin.svg)](https://github.com/KotlinBy/awesome-kotlin)

<p align="center">
<img width=200 src="https://danger.systems/images/js/danger-js-ktln-logo-hero-cachable@2x.png" /></br>
⚠️ Stop saying "you forgot to …" in code review in Kotlin
</p>

# Project status
The project is now on a stable version.
Is possible to generate a working `danger-kotlin` instance from the code that is currently on this repo, or use it via GitHub actions or `brew`.

### What it looks like today
You can make a `Dangerfile.df.kts` in your root project that looks through PR metadata, it's fully typed.

```kotlin
import systems.danger.kotlin.*

danger(args) {

    val allSourceFiles = git.modifiedFiles + git.createdFiles
    val changelogChanged = allSourceFiles.contains("CHANGELOG.md")
    val sourceChanges = allSourceFiles.firstOrNull { it.contains("src") }

    onGitHub {
        val isTrivial = pullRequest.title.contains("#trivial")

        // Changelog
        if (!isTrivial && !changelogChanged && sourceChanges != null) {
            warn(WordUtils.capitalize("any changes to library code should be reflected in the Changelog.\n\nPlease consider adding a note there and adhere to the [Changelog Guidelines](https://github.com/Moya/contributors/blob/master/Changelog%20Guidelines.md)."))
        }

        // Big PR Check
        if ((pullRequest.additions ?: 0) - (pullRequest.deletions ?: 0) > 300) {
            warn("Big PR, try to keep changes smaller if you can")
        }

        // Work in progress check
        if (pullRequest.title.contains("WIP", false)) {
            warn("PR is classed as Work in Progress")
        }
    }
}
```

### Setup

#### macOS (ARM)
```sh
brew install danger/tap/danger-kotlin
```

#### macOS (Intel)
```sh
brew install danger/tap/danger-kotlin-intel
```

You need to have Xcode installed and not relying on command line tools.
If you're seeing this error when running xcodebuild:

```sh
$ xcodebuild -version
xcode-select: error: tool 'xcodebuild' requires Xcode, but active developer directory '/Library/Developer/CommandLineTools' is a command line tools instance
```

You can fix it with:

```sh
sudo xcode-select -s /Applications/Xcode.app/Contents/Developer
```

### Linux
```sh
bash <(curl -s https://raw.githubusercontent.com/danger/kotlin/master/scripts/install.sh)
source ~/.bash_profile
```

### GitHub Actions
You can add danger/kotlin to your actions

Parameters:
* `dangerfile`: Path to danger file,  required: `false`,  default: `Dangerfile.df.kts`
* `run-mode`: Run mode: `ci`, `local`, `pr`, required: `false`  default: `ci`
* `job-id:` Reported CI job ID, required: `false`, default: `danger/kotlin`
* `args`: Extra custom arguments like "--failOnErrors --no-publish-check" and etc, required: `false`

```yml
jobs:
  build:
    runs-on: ubuntu-latest
    name: "Run Danger"
    steps:
      - uses: actions/checkout@v4
      - name: Danger
        uses: danger/kotlin@1.3.1
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

Danger a pre built images that you can use with your action:

https://github.com/orgs/danger/packages/container/package/danger-kotlin
In order to import one of those use the docker:// prefix

```yml
jobs:
  build:
    runs-on: ubuntu-latest
    name: "Run Danger"
    container:
      image: docker://ghcr.io/danger/danger-kotlin:1.3.1
    steps:
      - uses: actions/checkout@v4
      - name: Run Danger
        run: danger-kotlin ci --failOnErrors --no-publish-check
        env:
          GITHUB_TOKEN: ${{ secrets.GITHUB_TOKEN }}
```

### Autocomplete and Syntax highlighting in IntelliJ IDEA or Android Studio
You can activate the autocomplete following this additional steps:
- Install danger on your local machine
- Go to `Preferences -> Build, Execution, Deployment -> Compiler -> Kotlin Compiler` (`Preferences -> Kotlin Compiler` in Android Studio, Recent Android Studio versions will show this option when you close all project and open the Settings from the initial screen)
- At the bottom you will find a section `Kotlin Scripting`
- Complete the field `Script template classes` with  `systems.danger.kts.DangerFileScript`
- Complete the field `Script templates classpath` with `/usr/local/lib/danger/danger-kotlin.jar`
- Go to `Preferences -> Language & Frameworks -> Kotlin -> Kotlin Scripting`
- Make sure the script template `DangerFileScript` is active and above the default `Kotlin Script`
- Apply changes
- If opening the `Dangerfile.df.kts` the autocomplete and syntax highlighting doesn't work, try to reboot your IDE or open the Dangerfile from your IDE as a single file.

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
