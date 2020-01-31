---
title: Architecture
subtitle: How is Danger Kotlin architected
layout: guide_kt
order: 1
blurb: How do Danger Kotlin and JS interact?
---

## How does Danger Kotlin work?

Danger provides an evaluation system for creating per-application rules. Basically, it is running arbitrary Kotlin with
some extra PR metadata added in at runtime.

Pulling this off though, is a bit of a thing.

## Setup

Danger Kotlin is powered by Danger JS. Think of it as a Kotlin sandwich: `[Danger JS] -> [Danger Kotlin] -> [Danger JS]`.
Danger JS first gets all the CI and Platform metadata, then passes that to Danger Kotlin, which returns the results of
your Kotlin rules back to Danger JS.

```
+--------------------------------+          +----------------------+          +--------------------+
|                                |          |                      |          |                    |
| ## Danger JS                   |          | ## Danger Kotlin     |          | ## Danger JS       |
|                                |          |                      |          |                    |
|  Get GitHub/BitBucket/etc info |  +---->  |  Setup plugins       |  +---->  |  Update PR info    |
|                                |          |                      |          |                    |
|  Transform into JSON           |          |  Evaluate Dangerfile |          |  Pass / fail build |
|                                |          |                      |          |                    |
+--------------------------------+          +----------------------+          +--------------------+
```

**Step 1: CI**. Danger JS needs to figure out what CI we're running on. You can see them all [in
`source/ci_source/providers`][provs]. These use ENV VARs to figure out which CI `danger ci` is running on and validate
whether it is a pull request.

**Step 2: Platform**. Next, Danger JS needs to know which platform the code review is happening in. Today it's GitHub, BitBucket Server and GitLab.

**Step 3: JSON DSL**. All the metadata from the above two steps are transformed into a JSON file, which is passed into
Danger Kotlin.

**Step 4: Kotlin Plugin Setup**. Danger has to prepare your code to be compiled, so any plugins need to be set up before
compilation and runtime evaluation.

**Step 5: Evaluation**. Most of the Danger Kotlin setup occurs when you run, `val danger = Danger(args)` in your
`Dangerfile.df.kts` - it's nearly all smart JSON parsing into real Kotlin objects. The dangerfile uses `markdown`,
`warning`, `fail` or `message` to pass results to a singleton.

**Step 6: Passing Results**. The results from the evaluation are turned into JSON, and then passed back to Danger JS.

**Step 6: Feedback**. Danger JS reads the results, then chooses whether to create/delete/edit any messages in the code
review site. From the results of the run, Danger JS will then pass or fail the build.

[provs]: https://github.com/danger/danger-js/tree/master/source/ci_source/providers
[dangerdsl]: https://github.com/danger/danger-js/blob/master/sourformace/dsl/DangerDSL.ts
[runner]: https://github.com/danger/danger-js/blob/master/source/commands/danger-runner.ts
[in_runner]: https://github.com/danger/danger-js/blob/master/source/runner/runners/inline.ts
