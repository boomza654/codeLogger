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
  - The general flow of this Visualizer is 
    1. `bmsc` translates the Minispec files into Bluespec files that are more ready for synthesis
    2. `bsc` compiles bluespec source files to verilog modules
    3. `yosys` imports all the verilog modules and synthesize circuits
    4. `netlistsvg`  draw the circuit diagram out in svg file format
  - add `/*bmsc_pragma:nosynth*/` into module definition / function definition in order to exapnd the module rendering 
  - TODO: replace `netlistsvg` with a better Circuit visualization tool (Looking at Sukiyama algorithm)
### Debugger
  - Main usage is using java to run `translator.Translator` to add printf statement into minispec module
  - argument `<input file name> <output file name>`
  - To effectively debug, need to run `ms` or `msc` to compile and run the simulation after translation
  - The general flow of this debugger is
    1. `translator` add display statement to the source code
    2. `ms` / `msc` will run the instrumented source code and print out the debuggable information in the module
  - TODO: Create a real debugger from those output
  - TODO: Make a class that centralize Debug adding and real debugging
## BMSC workflow ( for developers who want to modify code)
Boomzaza's MiniSpec Compiler (BMSC) is bascially a modified Minispec compiler for using with `yosys` especially.

Most of the workflow goes along the traditional `msc` compiler but with additional type checking and stuff. 

All source files related to BMSC lives inside `src/bmsc` directory

The general workflow of BMSC is according to the following list:
1. Parse all files and create Parsed ordering (Import graph should be DAG: there should not be 2 sources files importing each other)
  - The Details on Files parsing / ordering can be seen in `ParsedFile.java`
2. Registering outer-most parametrics/Typedef/ModuleDef/FunctionDefs into `GeneralizedIdentifierManager` and into the synthesize queue
  - The identifier with its parameter are called `GeneralizedIdentifier` (same identifier with different parameter are essentially referring to different things)
  - The details of the first pass is in the `FirstPassGidRegister.java`
  - Integer varaibles are also elaborated in in this pass using `ExpressionEvaluator.java`
3. Each element in the synthesize queue are now translated into the output BSV file
  - The details of translating to bsv is described in the `Translator.java`
  - Integer varaible and Loops are elaborated as well
  - New Reference to parametric module / function will add GeneralizedIdentifier to the synthesize queue

`GeneralizedIdentifier` is a class that represents the identifier with parameters (with associated method for translation to BSV name). This is really essential to our translation process sincce traditionl `msc` still retains `#` sign in the identifier making `yosys` canot read the verilog module because # sign indicates verilog parameter not Minispec Parameter. In order to avoid that, we escape the using of # and detect which # corresponds Minispec Parameter and which corresponds to Bluespec Parameter.
## Version
- 1.2 with Circuit Visualizer and `/*bmsc_pragma*/` to exclude modules/functions from diagram encapsulation
- 1.1 with Circuit Visualizer
- 1.0 with Debugger
## Dependency
- `Java` ( Not quite sure which version it should be but the development was done using `Java(TM) SE Runtime Environment (build 11.0.4)`
- `ANTLR v 4.7.2` as attached into the lib folder
- `json simple 1.1` as attached into the lib folder
- `Minispec` compiler from (https://github.mit.edu/6004/minispec) (For debugger)
- `bsc` (Bluespec System Verilog Compiler). The same version that Minispec is using.
- `yosys` 
- `netlistsvg` as can be downloaded from npm somewhere
