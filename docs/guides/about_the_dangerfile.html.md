---
title: About the Dangerfile
subtitle: The Dangerfile
layout: guide_kt
order: 1
blurb: Step two on using Danger in your app, how to work locally and nuances around working with files.
---

## Writing your Dangerfile

All of Danger is built in Kotlin, aims to have 100% in-line docs. This means a lot of your exploration can be done inside Intellij Idea. 
This document aims to give you some high level knowledge on how to work on your `Dangerfile.df.kts`.

## Working on your Dangerfile

There are two ways to locally work on your Dangerfile. These both rely on using an external API locally, so you may hit
their API rate-limits or need to have authenticated request for private repos. In which case you can use an access token
to do authenticated requests by exposing a token to Danger.

```sh
export DANGER_GITHUB_API_TOKEN='xxxx'

# or for BitBucket by username and password
export DANGER_BITBUCKETSERVER_HOST='xxxx' DANGER_BITBUCKETSERVER_USERNAME='yyyy' DANGER_BITBUCKETSERVER_PASSWORD='zzzz'

# or for BitBucket by username and personal access token
export DANGER_BITBUCKETSERVER_HOST='xxxx' DANGER_BITBUCKETSERVER_USERNAME='yyyy' DANGER_BITBUCKETSERVER_TOKEN='zzzz'
```

Then the danger CLI will use authenticated API calls, which don't get hit by API limits.

### Using danger pr

The command `danger-kotlin pr` expects an argument of a PR url, e.g:

```sh
danger-kotlin pr https://github.com/danger/kotlin/pull/64
```

This will use your local `Dangerfile.df.kts` against the metadata of the linked PR. Danger will then output the results
into your terminal, instead of inside the PR itself.

This _will not_ post comments in that PR.

### Using `danger` and Faking being on a CI

If you create an
[appropriately scoped temporary api token](http://danger.systems/js/guides/getting_started.html#setting-up-an-access-token)
for your GitHub account, this can be a good way to see if danger is suitable for you before integrating it into your CI
system.

You can manually trigger danger against a pull request on the command line by setting the following environmental
variables:

```bash
export DANGER_FAKE_CI="YEP"
export DANGER_TEST_REPO='username/reponame'
```

Then you can run against a local branch that is attached to a pull-request, by running the following:

```bash
git checkout branch-for-pr-1234
DANGER_TEST_PR='1234' danger-kotlin ci
```

Assuming that your local file-system matches up to that branch for your code review, this will be a good approximation
of how danger will work when you integrate it into your CI system. Note: this **will** leave a comment on the PR.

### Plugins

You can use any external dependency by adding the following lines at the top of your Dangerfile.df.kts

```kotlin
@file:Repository("https://repo.maven.apache.org")
@file:DependsOn("groupId:artifactId:version")
```

In case you are importing a Danger Kotlin plugin, you will have to register it with:

```kotlin
register plugin AndroidLint
```

## Finding more info

The [CHANGELOG][changelog] for Danger is kept entirely end-user focused, so if there is an aspect of the Dangerfile that
you do not know, or looks confusing and there is nothing in the documentation - [check the CHANGELOG][changelog]. This
is where we write-up why a change happened, and how it can affect Danger users.

[changelog]: http://danger.systems/kotlin/changelog.html
