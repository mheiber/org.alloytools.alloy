![Logo](https://avatars3.githubusercontent.com/u/30268214?v=4&s=200)
![Release build](https://github.com/alloytools/org.alloytools.alloy/actions/workflows/release.yml/badge.svg)
![Snapshot build](https://github.com/alloytools/org.alloytools.alloy/actions/workflows/snapshot.yml/badge.svg)


# Alloy VSCode with Custom Visualizations

All you need to run Alloy with custom visualizations.

## Installation

- Install [the Alloy VSCode Extension](https://marketplace.visualstudio.com/items?itemName=ArashSahebolamri.alloy)
- Replace its version of the Alloy jar with the one from https://github.com/mheiber/org.alloytools.alloy/releases/download/1/org.alloytools.alloy.dist.jar. On Mac you can do this with the following shell command:

```
curl -L https://github.com/mheiber/org.alloytools.alloy/releases/download/1/org.alloytools.alloy.dist.jar > ~/.VSCode/extensions/arashsahebolamri.alloy-0.7.1/org.alloytools.alloy.dist.jar
```


## How to use



## How to hack on this hack of a hack for Hack


This is a fork of [A fork of Alloy that adds a language server](https://github.com/s-arash/org.alloytools.alloy), see there for full instructions on how to build. The quick version is: `./gradlew build`.

For fast iteration, I run the following, which builds directly into the VSCode extension directory:

```sh
./gradlew build && rm -f ~/.vscode/extensions/arashsahebolamri.alloy-0.7.1/org.alloytools.alloy.dist.jar && cp org.alloytools.alloy.dist/target/org.alloytools.alloy.dist.jar ~/.vscode/extensions/arashsahebolamri.alloy-0.7.1/org.alloytools.alloy.dist.jar
```

Then I do CMD-Shift-P Reload in VSCode to pick up the new version of Alloy.

