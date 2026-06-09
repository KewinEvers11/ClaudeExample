# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Desktop Pomodoro timer application built with Java and JavaFX.

## Commands

Conventions

- Apply SOLID principles for all classes created
- When necesary apply pattern design
- Always request human review and approval before creating commits or pull requests


## Build & Run

This is a JavaFX project. Expected commands (update once build tooling is added):

```bash
# If using Maven:
mvn clean compile
mvn javafx:run
mvn test
mvn test -Dtest=ClassName#methodName   # single test

# If using Gradle:
./gradlew build
./gradlew run
./gradlew test
./gradlew test --tests "ClassName.methodName"   # single test
```

## Architecture

- **UI layer**: JavaFX with vanilla GUI (no additional UI framework)
- **Language**: Java
