# Pomodoro Timer

A desktop Pomodoro timer application built with Java and JavaFX.

## Features

- Work sessions (25 min), short breaks (5 min), and long breaks (15 min after every 4 pomodoros)
- Start, pause, and reset controls
- Automatic transitions between work and break phases
- Visual indicator of the current phase
- Completed pomodoro counter (0-4 per cycle)

## Prerequisites

- Java 17+

## Build & Run

### Compile

```bash
mvn clean compile
```

### Run the application

```bash
mvn javafx:run
```

This launches the Pomodoro Timer window. Use the **Start**, **Pause**, and **Reset** buttons to control the countdown.

### Run tests

```bash
mvn test
```

## How It Works

The app implements the [Pomodoro Technique](https://en.wikipedia.org/wiki/Pomodoro_Technique):

1. Work for 25 minutes
2. Take a 5-minute short break
3. Repeat steps 1-2 four times
4. Take a 15-minute long break
5. Start over

The timer counts down in `MM:SS` format and automatically moves to the next phase when time expires.

## Project Structure

```
docs/specs/       - Feature specifications
CLAUDE.md         - Development conventions and build instructions
```

## Contributing

- Follow SOLID principles
- Apply design patterns where appropriate
- All commits and pull requests require human review before merging
