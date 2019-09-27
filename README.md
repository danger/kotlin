# kotlin
⚠️ Stop saying "you forgot to …" in code review in Kotlin

# Project status
The project is still work in progress.
Is possible to generate a working `danger-kotlin` instance from the code that is currently on this repo, but is not ready to be distributed yet.

### Setup
#### MacOs
```sh
brew install danger/tap/danger-kotlin
```

### Linux
```sh
npm install -g danger
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

### Commands

- `danger-kotlin ci` - Use this on CI
- `danger-kotlin pr https://github.com/Moya/Harvey/pull/23` - Use this to build your Dangerfile
- `danger-kotlin local` - Use this to run danger against your local changes from master

# Authors
`danger-kotlin` was developed by [@gianluz][] and [@f-meloni][]

[@f-meloni]: https://github.com/f-meloni
[@gianluz]: https://github.com/gianluz
