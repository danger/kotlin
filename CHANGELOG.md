<!--

// Please add your own contribution below inside the Master section, no need to
// set a version number, that happens during a deploy. Thanks!
//
// These docs are aimed at users rather than danger developers, so please limit technical
// terminology in here.

// Note: if this is your first PR, you'll need to add your URL to the footnotes
//       see the bottom of this file

-->
## Master
- Update Kotlin to 1.4.10 [@gianluz] - [#140](https://github.com/danger/kotlin/pull/140)
- Migrate from moshi to kotlinx serialization [@gianluz] - [#141](https://github.com/danger/kotlin/pull/141)
- Fix incorrect url in install.sh script and in Dockerfile [@davidbilik] - [#144](https://github.com/danger/kotlin/pull/144)
- Road to 1.0 - Refactor project structure [@gianluz] - [#142](https://github.com/danger/kotlin/pull/142)
- Fix gitlab defaults following kotlinx serialisation [@gianluz][@fmeloni] - [#146](https://github.com/danger/kotlin/pull/146)

# 0.7.1

- Make milestone description optional [@f-meloni] - [#136](https://github.com/danger/kotlin/pull/136)
- Optimise Dockerfile layers to make Danger-Kotlin faster when the image is pulled on CI [@f-meloni] - [#129](https://github.com/danger/kotlin/pull/129)
- Add action.yml to make it possible to run locally through [act](https://github.com/nektos/act) [@mariusgreve] - [#135](https://github.com/danger/kotlin/pull/135)

# 0.7.0

- Add logger [@f-meloni] - [#126](https://github.com/danger/kotlin/pull/126)
- Fix DangerKotlinScriptDefinition [@gianluz] - [#121](https://github.com/danger/kotlin/pull/121)
- Update Kotlin to 1.4.0 [@uzzu][] - [#116](https://github.com/danger/kotlin/pull/116)
- Fix crash at milestone.dueOn [@anton46][] - [#108](https://github.com/danger/kotlin/pull/119) 

# 0.6.1

- Fix crash on milestone.closedAt [@anton46][] - [#108](https://github.com/danger/kotlin/pull/112)
- Add abstraction for executing shell commands via `ShellExecutor` [@davidbilik][] - [#105](https://github.com/danger/kotlin/pull/105)

# 0.6.0

- Fix to allow for large GitHub id values [@brentwatson][] - [#108](https://github.com/danger/kotlin/pull/108)
- Fix invalid parsing of changes in diff [@davidbilik][] - [#106](https://github.com/danger/kotlin/pull/106)
- Add extensions for changed lines in Git [@davidbilik][] - [#102](https://github.com/danger/kotlin/pull/102)
- Add exec function [@f-meloni][] - [#97](https://github.com/danger/kotlin/pull/97)
- Add readFile function [@f-meloni][] - [#93](https://github.com/danger/kotlin/pull/93)
- Github exposing user avatar [@gianluz] - [#96](https://github.com/danger/kotlin/pull/96)

[@f-meloni]: https://github.com/f-meloni
[@gianluz]: https://github.com/gianluz
[@davidbilik]: https://github.com/davidbilik
[@brentwatson]: https://github.com/brentwatson
[@anton46]: https://github.com/anton46
[@uzzu]: https://github.com/uzzu
[@mariusgreve]: https://github.com/mariusgreve
