# Code Logger
For Minispec language with almost no debugging tool except printf's

## Author
- Krittamate Tiankanon `boomza654`

## Purpose
- Provide a debugging tool for language that has less debugging tool ( especially Bluespec and minispec) For educational purpose

## General Idea
- We generate a code that basically add a printf statement to any point in the module code that 
   - Every start of the rule ( displaying Regsiters value and inputs)
   - involve assigning variable/ state
   - Branching taken for both If and Case Statement
   - Scope Entering and exiting
- TODO: Create a real debugger from those output
- TODO: Make a class that centralize Debug adding and real debugging
- Doing right now: Bsc Translator (To be used with visualization tools)
## Usage
- add `bin` and `lib/antlr-4.7.2-complete.jar` to class path
  - On window : `set CLASSPATH="%CLASSPATH%;bin;lib/antlr-4.7.2-complete.jar"`
- run `java translator.Translator --help` for all usage
- Main Usage: `java translator.Translator <input file name> <output file name>` to translate the minispec source file in inputfile Name into a version with debugging statement and write it down to output file name.
## Verion
- 1.0 with adding display ability

## Dependency
- `Java` ( Not quite sure which version it should be but the development was done using `Java(TM) SE Runtime Environment (build 12.0.2+10)`
- `ANTLR v 4.7.2` as attached into the lib folder
- `Minispec` compiler from (https://github.mit.edu/6004/minispec)
