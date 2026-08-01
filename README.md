<h1 align="center">🤖 RobotRescue</h1>

<p align="center">
A text-based Java adventure game about a robot stranded on an alien planet,
racing to repair its shuttle and escape before running out of energy.
</p>

## Story & Gameplay

Your shuttle has crashed. To repair it and get home, your robot must explore a
series of rooms, each hiding either an **enemy** to defeat, a **puzzle** to
solve, or an **artifact** needed to fix the shuttle. Every decision costs
energy — run out, and the mission fails.

- **Explore rooms** — each one is unlocked by defeating an enemy or solving a
  timed challenge.
- **Fight enemies** — different enemy types (`SpaceCreeper`, `ElderGuardian`, …)
  drain energy or deal damage in their own way.
- **Solve challenges** — decode binary, Morse code, or a Caesar-cipher secret
  message against the clock (`StopWatch`-based time limits).
- **Play mini-games** — Nim and Tic-Tac-Toe appear as alternative challenges.
- **Collect artifacts** — find and install the navigation module, control
  system, and energy crystal to repair the shuttle.
- **Save & load** — the full game state can be serialized to disk and resumed
  later.

## Tech stack & methods

- **Java** — plain console application, no external dependencies
- **Object-oriented design** — abstract `Enemy` class with concrete
  subclasses; `Challenge` and `Game` interfaces implemented by multiple
  independent challenge/mini-game types
- **Java Serialization** (`Serializable`, `ObjectOutputStream`/`ObjectInputStream`)
  for save/load functionality
- **Encapsulation** — game state (energy, damage, discovered rooms, found
  artifacts) is fully managed inside the model classes, not the UI layer
- **Scanner-based console I/O** for all user interaction

## Developer Team
- Nando Patton
- Adam Tuffaha
