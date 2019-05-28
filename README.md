# kotlin
⚠️ Stop saying "you forgot to …" in code review in Kotlin

# Project status
The project is still work in progress.
Is possible to generate a working `danger-kotlin` instance from the code that is currently on this repo, but is not ready to be distributed yet.
If you want to try it:
- clone the project
- run
```sh
curl -s "https://get.sdkman.io" | bash
source ~/.bash_profile
sdk install kscript
sdk install gradle
sdk install kotlin
npm install -g danger
```
- run `make install`
- now you can run `danger command`

### Commands

- `danger-kotlin ci` - Use this on CI
- `danger-kotlin pr https://github.com/Moya/Harvey/pull/23` - Use this to build your Dangerfile
- `danger-kotlin local` - Use this to run danger against your local changes from master
- `danger-kotlin edit` - Creates a temporary project for working on a Dangerfile (Requires IntelliJ IDEA)

# Authors
`danger-kotlin` was developed by [@gianluz][] and [@f-meloni][]

[@f-meloni]: https://github.com/f-meloni
[@gianluz]: https://github.com/gianluz
