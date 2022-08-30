# Structurizr Site Generatr

A static site generator for C4 architecture models created with [Structrizr DSL](https://github.com/structurizr/dsl).
See [Background](#background) for the story behind this tool.

[Click here to see an example of a generated site](https://avisi-cloud.github.io/structurizr-site-generatr) based on
the [Big Bank plc example](https://structurizr.com/dsl?example=big-bank-plc) from <https://structurizr.com>. This site
is generated from the example workspace in this repository.

## Features

- Generate a static HTML site, based on a Structurizr DSL workspace.
- Generates diagrams in SVG, PNG and PlantUML format, which can be viewed and downloaded from the generated site.
- Start a development server which generates a site, serves it and updates the site automatically whenever a file that's
  part of the Structurizr workspace changes.
- Include workspace-level documentation (in Markdown format) in the generated site.
- Include system-level ADR's in the generated site.
- Include static assets in the generated site, which can be used in ADR's and workspace-level documentation.
- Generate a site from a Structurizr DSL model in a Git repository. Supports multiple branches, which makes it possible
  to for example maintain an actual state in `master` and one or more future states in feature branches. The generated
  site includes diagrams for all configured branches.
- Include a version number in the generated site.

## Getting Started

To get started with the Structurizr Site Generatr, you can either:

- Install it to your local machine (requires Homebrew), or
- Execute it on your local machine via a container (requires Docker)

### Installation

As this approach relies on [Homebrew](https://brew.sh/), ensure this is already installed. For Windows and other operating
systems not supported by Homebrew, please use the [Docker approach](#docker) instead.

To install Structurizr Site Generatr execute the following commands in your terminal:

```shell
brew tap avisi-cloud/tools
brew install structurizr-site-generatr

structurizr-site-generatr --help
```

Periodically, you would have to update your local installation to take advantage of any new
[Structurizr Site Generatr releases](https://github.com/avisi-cloud/structurizr-site-generatr/releases).

### Docker

Though local installation is recommended for development where possible, Structurizr Site Generatr is also a packaged
as a Docker image. Therefore to use this approach, ensure [Docker](https://www.docker.com/) is already installed.
Additionally, for Windows 10+ users, you may want to take advantage of
[WSL2](https://docs.microsoft.com/en-us/windows/wsl/install) (Windows Subsystem for Linux). Both Docker and WSL2
are topics too vast to repeat here, so you are invited to study these as prerequisite learning for this approach.

Then to download our packaged image, consider the
[Structurizr Site Generatr releases](https://github.com/avisi-cloud/structurizr-site-generatr/releases) and choose
the version you wish to use. Then, in your terminal, execute the following:

```shell
docker pull ghcr.io/avisi-cloud/structurizr-site-generatr:<version>
```

Note: The `<version>` suffix should be replaced with your chosen version number. For example
`ghcr.io/avisi-cloud/structurizr-site-generatr:1.0.9`.

Once downloaded, you can execute Structurizr Site Generatr via a temporary Docker container by executing the
following in your terminal:

```shell
docker run -it --rm ghcr.io/avisi-cloud/structurizr-site-generatr:<version> --help
```

Note: The `<version>` suffix should be replaced with same version number of your downloaded image. For example
`ghcr.io/avisi-cloud/structurizr-site-generatr:1.0.9`.

#### [Optional] Verify the Structurizr Site Generatr image with [CoSign](https://github.com/sigstore/cosign)

```shell
cat cosign.pub
-----BEGIN PUBLIC KEY-----
MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEzezKl0vAWSHosQ0JLEsDzNBd2nGm
08KqX+imYqq2avlbH+ehprJFMqKK0/I/bY0q5W9hQC8SLzTRJ9Q5dB9UiQ==
-----END PUBLIC KEY-----
cosign verify --key cosign.pub ghcr.io/avisi-cloud/structurizr-site-generatr:<version>
```

Replace `<version>` with the version you want to verify.

## Usage

These examples use the [example workspace](docs/example) in this repository.

Once installed, Structurizr Site Generatr is operated via your terminal by issuing commands. Each command is
explained here:

### Help

To learn about available commands, or parameters for individual commands, call Structurizr Site Generatr with the
`--help` argument.

```shell
installed> structurizr-site-generatr --help

   docker> docker run -it --rm ghcr.io/avisi-cloud/structurizr-site-generatr:<version> --help

Usage: structurizr-site-generatr options_list
Subcommands:
    serve - Start a development server
    generate-site - Generate a site for the selected workspace.
    version - Print version information

Options:
    --help, -h -> Usage info
```

Note: The `<version>` suffix should be replaced with same version number of your downloaded image. For example
`ghcr.io/avisi-cloud/structurizr-site-generatr:1.0.9`.

### Version

To query the version of Structurizr Site Generatr installed / used.

```shell
installed> structurizr-site-generatr version

   docker> docker run -it --rm ghcr.io/avisi-cloud/structurizr-site-generatr:<version> version

Structurizr Site Generatr v1.0.9
```

Note: The `<version>` suffix should be replaced with same version number of your downloaded image. For example
`ghcr.io/avisi-cloud/structurizr-site-generatr:1.0.9`.

### Generate a website from a [C4 Workspace](https://github.com/structurizr/dsl)

This is the primary use case of Structurizr Site Generatr -- to generate a website from a
[C4 Workspace](https://github.com/structurizr/dsl).

```shell
installed> structurizr-site-generatr generate-site -w workspace.dsl

   docker> docker run -it --rm -v c:/projects/c4:/var/model ghcr.io/avisi-cloud/structurizr-site-generatr:<version> generate-site -w workspace.dsl
```

Note: The `<version>` suffix should be replaced with same version number of your downloaded image. For example
`ghcr.io/avisi-cloud/structurizr-site-generatr:1.0.9`.

Here, the `--workspace-file` or `-w` parameter specifies the input
[C4 Workspace DSL file](https://github.com/structurizr/dsl) to the `generate-site` command. Additional
parameters that affect website generation can be reviewed using the `--help` operator.

By default, the generated website will be placed in `./build`, which is overwritten if it already exisits.

#### For those taking the Docker approach

When using the Docker approach, the local file system must be made available to the temporary Structurizr Site
Generatr container via a [Docker file system volume mount](https://docs.docker.com/engine/reference/commandline/run/#mount-volume--v---read-only).
This is achieved by specifying the `-v` parameter with a linux-like **absolute** path to the folder containing
the .dsl file specified via `-w`. See how `C:\Projects\C4` has become `-v c:/projects/c4:/var/model` in the above example?

#### Generate a website from a Git repository

Instead of relying on local .dsl files only, the `generate-site` command can also retrieve input files from a
Git repository as follows. This is particularly advantageous for demos, documentation, or CI/CD pipelines.

```shell
structurizr-site-generatr generate-site
    --git-url https://github.com/avisi-cloud/structurizr-site-generatr.git
    --workspace-file docs/example/workspace.dsl
    --branches main
    --default-branch main
```

### Start a development web server around the generated website

To aid composition of [C4 Workspace DSL files](https://github.com/structurizr/dsl), the `serve` command will
both monitor the input .dsl file specified with `-w` and automatically re-generate the website when changes are
detected. Additional parameters that affect website generation and the development web server can be reviewed
using the `--help` operator.

```shell
installed> structurizr-site-generatr serve -w workspace.dsl

   docker> docker run -it --rm -v c:/projects/c4:/var/model -p 8080:8080 ghcr.io/avisi-cloud/structurizr-site-generatr:<version> serve -w workspace.dsl
```

Note: The `<version>` suffix should be replaced with same version number of your downloaded image. For example
`ghcr.io/avisi-cloud/structurizr-site-generatr:1.0.9`.

By defalult, a development web server will be started and accessible at http://localhost:8080/ (if available).

#### For those taking the Docker approach

However, when using the Docker approach, this development web server is within the temporary Structurizr Site
Generatr container. So [Docker port mapping](https://docs.docker.com/engine/reference/commandline/run/#publish-or-expose-port--p---expose)
is needed to expose the container's port 8080 to the host (web browser). In the example above, the
`-p 8080:8080` argument tells Docker to bind the local machine / host's port 8080 to the container's port 8080.

## Background

At Avisi, we're big fans of the [C4 model](https://c4model.com). We use it in many projects, big and small, to document
the architecture of the systems and system landscapes we're working on.

We started out by using PlantUML, combined with the [C4 extension](https://github.com/plantuml-stdlib/C4-PlantUML). This
works well for small systems. However, for larger application landscapes, maintained by multiple development teams, this
lead to duplication and inconsistency across diagrams.

To solve this problem, we needed a model based approach. Rather than maintaining separate diagrams and trying to keep
them consistent, we needed to have a single model, from which diagrams could be generated. This is why we started using
the [Structurizr for Java](https://github.com/structurizr/java) library. About a year later, we migrated our models to
Structurizr DSL.

We created custom tooling to generate diagrams and check them in to our Git repository. All diagrams could be seen by
navigating to our GitLab repository. This is less than ideal, because we like to make these diagrams easily accessible
to everyone, including our customers. This is why we decided that we needed a way of publishing our models to an easily
accessible website.

This tool is the result. It's still a work in progress, but it has already proven to be very useful to us.
