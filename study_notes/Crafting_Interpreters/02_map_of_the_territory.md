# Map of the Territory

The processing a language requires common steps to almost every language.

## Scanning

Scanning or lexing group characters on the source code to form tokens. For instance:

```
var average = (min + max) / 2;
```

After a lexer, this source code will produce the following **tokens**: `var`, `average`, `=`, `(`, `min`, `max`, `)`,
`/`, `2` and finally `;`.

## Parsing

The **parser** takes the flat sequence of **tokens** and builds a **tree structure** that mirrors the **grammar** of the
language. This trees are called **abstract syntax trees (ASTs)**. The other parser job is to inform the programmer of
syntax errors.

## Static analysis

Here is where we do the **binding** of the identifier. Searching for each symbol declaration. On statically typed
languages, here we find out the type of each variable and report possible **type errors**. Also this is where scopes are
solved.

The result of this analysis can be stored:

- As attributes on the syntax tree
- On an external **symbols table** where the keys are the **identifiers**.
- Or to transform the tree into a new data structure: **the intermediate representation**

Everything up to this point is called the **front end** of the compiler. The front end is specific to the language, and
the **back end** is concerned with the target architecture where the program will run.

## Intermediate representations

The compiler is a pipeline where each stage's job is to organize the data that represents the source code in a way that
makes the next stage simpler. The **IR (Intermediate Representation)** isn't tied to language nor to the target
architecture , to is called the **middle end**. It can work as an interface to multiple target platforms with less
effort.

## Optimization

Also in the **middle end**, once we have the programs representation we can apply optimizations that ensures the same
semantics but with better performance. A simple one is **constant folding**, where a constant mathematical expression is
calculated in compile time to avoid the computation in run time.

## Code generation

Finally at the back end we **generate code**: assembly-like instructions a CPU can run. The code can target a real CPU
(with a specific architecture) or a virtual one (byte code, not coupled to any CPU).

## Runtime

We finally have an executable, but we can also insert functionality on the original source code in runtime. For instance
garbage collection in Go is inserted directly on the program, while Java's GC (and runtime) lives on the JVM.

# Shortcut alternatives

- **Single pass compilers** interleave parsing, analysis and code generation without producing ASTs or intermediate
  representations. That is mostly due to computer limitations. That is why in C you can't call a function defined later
  on the code.
- **Tree walk interpreters** code is produced right after the ASTs, traversing the tree. This is the case of the
  canonical Ruby interpreter.
- **Transpilers** transforms source code of a language into another language. Like Typescript or coffee-script producing
  JS.
- **JIT** complex runtime compilation to target the specific architecture, sometimes profiling hot spots.

## Compilers and Interpreters

Compiler produces code that is not immediately executed. The interpreter executes "from source" while interpreting the
code.

# Challenges

1. Pick an open source implementation of a language you like. Download the source code and poke around in it. Try to
   find the code that implements the scanner and parser. Are they handwritten, or generated using tools like Lex and
   Yacc? (`.l` or `.y` files usually imply the latter.)

   > The C# compiler is available here https://github.com/dotnet/roslyn Scanner:
   > https://github.com/dotnet/roslyn/blob/main/src/Compilers/CSharp/Portable/Parser/Lexer.cs Parser:
   > https://github.com/dotnet/roslyn/blob/main/src/Compilers/CSharp/Portable/Syntax/CSharpSyntaxTree.cs

2. Just-in-time compilation tends to be the fastest way to implement dynamically typed languages, but not all of them
   use it. What reasons are there to *not* JIT?

   > Slow down the execution while compiling Development complexity

3. Most Lisp implementations that compile to C also contain an interpreter that lets them execute Lisp code on the fly
   as well. Why?
   > Lisp is hard to reason about so a REPL is welcome (partial evaluation to debug)
