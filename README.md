# kotlin
⚠️ Stop saying "you forgot to …" in code review in Kotlin

# Project status
The project is still work in progress.
Is possible to generate a working `danger-kotlin` instance from the code that is currently on this repo, but is not ready to be distributed yet.
If you want to try it:
- clone the project
- run
```sh
brew install kotlin
brew install holgerbrandl/tap/kscript
brew install maven
brew install gradle
```
- go on the folder `danger-kotlin-library`
- run `gradle publishToMavenLocal`
- go on the folder `danger-kotlin`
- run `gradle build`
- now you can run `danger command --process danger-kotlin/build/bin/$os_dir/main/release/executable/danger-kotlin.kexe`

# Authors
`Danger-kotlin` was developed by [@gianluz][] and [@f-meloni][]

[@f-meloni]: https://github.com/f-meloni
[@gianluz]: https://github.com/gianluz
