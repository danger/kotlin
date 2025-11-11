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

# 1.3.4

- Fix GitLab pipeline ID overflow by changing Int to Long for all GitLab ID
  fields [@gianluz] - [#317](https://github.com/danger/kotlin/pull/317)
- Add Docker instructions and syntax highlighting
  tips [@hubertgrzeskowiak] - [#305](https://github.com/danger/kotlin/pull/305)
- Update actions/download-artifact from 4 to 5 [@dependabot] - [#307](https://github.com/danger/kotlin/pull/307)

# 1.3.3

- Fix working with raw git diff [@grine4ka] - [#297](https://github.com/danger/kotlin/pull/297)
- Update Kotlin libraries [@grine4ka] - [#298](https://github.com/danger/kotlin/pull/298)

# 1.3.2

- Upgrade JDK to 23 [@vilmosnagy] - [#294](https://github.com/danger/kotlin/pull/294)
- Update to JDK 23 [@Vacxe] - [#296](https://github.com/danger/kotlin/pull/296)
- Update actions/checkout to v4 in README.md [@tinyboxvk] - [#293](https://github.com/danger/kotlin/pull/293)
- Fix using `head_pipeline` instead of `pipeline` in
  GitLab [@grine4ka] - [#289](https://github.com/danger/kotlin/pull/289)
- Fix typo in GitLab Pipeline status name [@grine4ka] - [#287](https://github.com/danger/kotlin/pull/287)
- Make GitLab MR descriptions optional [@mrbass21] - [#285](https://github.com/danger/kotlin/pull/285)
- Update CI checks node version to 22.10.0 [@Vacxe] - [#288](https://github.com/danger/kotlin/pull/288)
- Update Kotlin to 2.0.21 [@Vacxe]
- Update Gradle to 8.10.2 [@Vacxe]

# 1.3.1

- Fix GitHub user optional fields [@Vacxe] - [#278](https://github.com/danger/kotlin/pull/278)
- Fix missed default serialization values [@Vacxe] - [#281](https://github.com/danger/kotlin/pull/281)
- Bump Kotlin version to 1.7.22 [@Vacxe] - [#275](https://github.com/danger/kotlin/pull/275)
- Update README.md [@r0adkll] - [#284](https://github.com/danger/kotlin/pull/284)

# 1.3.0

- Add draft field to Pull Request [@msarelo] - [#254](https://github.com/danger/kotlin/pull/254)
- Add new GitHub action [@Vacxe] - [#269](https://github.com/danger/kotlin/pull/269)
- Add release flow and distribution
  pipeline [@Vacxe] - [#263](https://github.com/danger/kotlin/pull/263), [#268](https://github.com/danger/kotlin/pull/268)
- Update actions/checkout from 3 to 4 [@dependabot] - [#272](https://github.com/danger/kotlin/pull/272)
- Update actions/setup-java from 3 to 4 [@dependabot] - [#271](https://github.com/danger/kotlin/pull/271)
- Fixed MissingFieldException's during parsing Bitbucket Server
  responses [@msarelo] - [#254](https://github.com/danger/kotlin/pull/254)
- Add accessors for Danger reports [@417-72KI] - [#245](https://github.com/danger/kotlin/pull/245)
- Update README.md with guidance to enable auto-complete in Android Studio [@gianluz] - [#242](https://github.com/danger/kotlin/pull/242)
- Update install script with Kotlin compiler 1.7.0 [@gianluz] - [#241](https://github.com/danger/kotlin/pull/241)

# 1.2.0
- Update `Kotlin` to `1.7.0` and added support for Apple Silicon Chipset [@gianluz] - [#231](https://github.com/danger/kotlin/pull/231)
- Make user property nullable for cases when non BB user did a commit [@vchernyshov]
- Make GitLab approvals_before_merge variable nullable [#227](https://github.com/danger/kotlin/pull/227)

# 1.1.0

- Add support of BitBucketCloud [@vchernyshov] - [#214](https://github.com/danger/kotlin/pull/214)
- Make `force_remove_source_branch` nullable in GitLab Merge request entity [@davidbilik] - [#197](https://github.com/danger/kotlin/pull/197)
- Make `lastReviewedCommit` nullable on BitBucket Server [@f-meloni] - [#211](https://github.com/danger/kotlin/pull/211)
- Update `GitLabMergeRequest` model: add `squash` field [@sonulen] - [#212](https://github.com/danger/kotlin/pull/212)
- Add Gitlab extensions for url's: to project, to file diff, to current version of file [@sonulen] - [#212](https://github.com/danger/kotlin/pull/212)
- Upgrade action to use node14 [@eygraber] - [#215](https://github.com/danger/kotlin/pull/215)

# 1.0.0-beta4, 1.0.0

- Create the Danger main instance only once [@f-meloni] - [#185](https://github.com/danger/kotlin/pull/185)

# 1.0.0-beta3

- Coroutines compatibility [@gianluz] - [#177](https://github.com/danger/kotlin/pull/177)
- Improving error message for when a DangerPlugin was not registered [@rojanthomas] - [#181](https://github.com/danger/kotlin/pull/181)
- Fix body parameter in github models  [@tegorov] - [#175](https://github.com/danger/kotlin/pull/175)

# 1.0.0-beta2

- Update kotlinx-datetime to 0.1.1 [@f-meloni] - [@gianluz] - [#167](https://github.com/danger/kotlin/pull/167)
- Support GitLab different time zones on the JSON [@f-meloni] - [#169](https://github.com/danger/kotlin/pull/169)
- Update Kotlin to 1.5.0 [@gianluz] - [#171](https://github.com/danger/kotlin/pull/171)

# 1.0.0-beta

- Support --help parameter [@f-meloni] - [#153](https://github.com/danger/kotlin/pull/155)
- Update Kotlin to 1.4.10 [@gianluz] - [#140](https://github.com/danger/kotlin/pull/140)
- Migrate from moshi to kotlinx serialization [@gianluz] - [#141](https://github.com/danger/kotlin/pull/141)
- Fix incorrect url in install.sh script and in Dockerfile [@davidbilik] - [#144](https://github.com/danger/kotlin/pull/144)
- Road to 1.0 - Refactor project structure [@gianluz] - [#142](https://github.com/danger/kotlin/pull/142)
- Handle danger-js custom paths with parameter `--danger-js-path` [@f-meloni] - [#153](https://github.com/danger/kotlin/pull/153)
- Update Kotlin to 1.4.20 [@gianluz] - [#148](https://github.com/danger/kotlin/pull/148)
- Fix gitlab defaults following kotlinx serialisation [@gianluz] - [#146](https://github.com/danger/kotlin/pull/146)
- Road to 1.0 - Migrate from java.util.Date to kotlinx.datetime [@gianluz] - [#147](https://github.com/danger/kotlin/pull/147)
- Fix typo in Github Milestone serialization [@doodeec] - [#151](https://github.com/danger/kotlin/pull/151)
- Use fixed commit of danger/kotlin repository in install.sh script [@davidbilik]- [#152](https://github.com/danger/kotlin/pull/152)
- Update Kotlin to 1.4.31 [@gianluz] - [#160](https://github.com/danger/kotlin/pull/160)
- Library resolver and plugin installer gradle plugin [@gianluz] - [#158](https://github.com/danger/kotlin/pull/158)

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
[@tegorov]: https://github.com/tegorov
[@rojanthomas]: https://github.com/rojanthomas
[@eygraber]: https://github.com/eygraber
[@417-72KI]: https://github.com/417-72KI
[@vchernyshov]: https://github.com/vchernyshov
[@msarelo]: https://github.com/msarelo
[@vilmosnagy]: https://github.com/vilmosnagy
[@Vacxe]: https://github.com/Vacxe
[@hubertgrzeskowiak]: https://github.com/hubertgrzeskowiak
[@grine4ka]: https://github.com/grine4ka
[@tinyboxvk]: https://github.com/tinyboxvk
[@mrbass21]: https://github.com/mrbass21
[@r0adkll]: https://github.com/r0adkll
[@sonulen]: https://github.com/sonulen
[@doodeec]: https://github.com/doodeec
