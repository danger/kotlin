---
title: Fast Feedback via Danger Local
subtitle: Platformless
layout: guide_kt
order: 4
blurb: How to use Danger to get per-commit feedback
---

## Before we get started

This tutorial continues after "Getting Started" - it's not required that you have Danger Kotlin running on your CI
though, but assumes some familiarity.

## Locality

With Danger, the typical flow is to help you can check rules on CI and get feedback inside your PR. With Peril you can
move those rules to run on an external server making feedback instant. `danger-kotlin local` provides a somewhat hybrid
approach.

`danger-kotlin local` provides a way to run a Dangerfile based on git-hooks. This let's you run rules while you are still in
the same context as your work as opposed to later during the code review. Personally, I find this most useful on
projects when I ship 90% of the code to it.

## How it works

Where `danger-kotlin ci` uses information from the Pull Request to figure out what has changed, `danger-kotlin local` naively uses the
local differences in git from master to the current commit to derive the runtime environment. This is naive because if
you don't keep your master branch in-sync, then it will be checking across potentially many branches.

Inside a Dangerfile `danger.github` and `danger.bitbucketServer` will be `nil`, so you can share a Dangerfile between
`danger-kotlin local` and `danger-kotlin ci` as long as you verify that these objects exist before using them.

When I thought about how I wanted to use `danger-kotlin local` on repos in the Danger org, I opted to make a separate
Dangerfile for `danger-kotlin local` and import this at the end of the main Dangerfile. This new Dangerfile only contains rules
which can run with just `danger.git`, e.g. CHANGELOG/README checks. I called it `Dangerfile.lite.kt`.
