# Code Logger
Collections of Minispec Add ons, especially Circuit visualizer

## Author
- Krittamate Tiankanon `boomza654`
## Purpose
- Provide a visualization tool to visualize raw semantic circuits as written in the code for educational purpose
- Provide a debugging tool for language that has less debugging tool ( especially Minispec) For educational purpose

## Usage
### Visualizer
  - Main usage is using java to run `bmsc.BluespecTranslator` class
  - argument `-p` can set path of the src file
  - followed by `<file_name> <module/function_name>`
  - This program is dependent on `bsc`,`yosys`,and `netlistsvg`
### Debugger
  - Main usage is using java to run `translator.Translator` to add printf statement into minispec module
  - argument `<input file name> <output file name>`
  - To effectively debug, need to run `ms` or `msc` to compile and run the simulation after translation
  - TODO: Create a real debugger from those output
  - TODO: Make a class that centralize Debug adding and real debugging
## Version
- 1.1 with Circuit Visualizer
## Dependency
- `Java` ( Not quite sure which version it should be but the development was done using `Java(TM) SE Runtime Environment (build 11.0.4)`
- `ANTLR v 4.7.2` as attached into the lib folder
- `json simple 1.1` as attached into the lib folder
- `Minispec` compiler from (https://github.mit.edu/6004/minispec) (For debugger)
- `bsc` (Bluespec System Verilog Compiler). The same version that Minispec is using.
- `yosys` 
- `netlistsvg` as can be downloaded from npm somewhere
