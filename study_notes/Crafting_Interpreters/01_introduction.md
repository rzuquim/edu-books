# Introduction

The book is a practical one, not much theory on type systems, for instance. We will build two interpreters for a made up
language called **Lox**.

This book won't use you **compiler-compilers** like [Lex](https://silcnitc.github.io/lex.html),
[Yacc](https://en.wikipedia.org/wiki/Yacc) or [ANTLR](https://www.antlr.org/). We will write the interpreter by hand.

Although the first interpreter **jlox** is written in Java. I'll try to rewrite the code in Rust in parallel to practice
it. I'll try to validate the result using the [code crafters](https://app.codecrafters.io/courses/interpreter/) tests
and also compare to the ports to rust available
[here](https://github.com/munificent/craftinginterpreters/wiki/Lox-implementations).

## Challenges

1. There are at least six domain-specific languages used in
   the [little system I cobbled together](https://github.com/munificent/craftinginterpreters) to write and publish this
   book. What are they?

   > HTML, SCSS, CSS, Markdown, Make, Mustache

2. Get a "Hello, world!" program written and running in Java. Set up whatever makefiles or IDE projects you need to get
   it working. If you have a debugger, get comfortable with it and step through your program as it runs.

   > Since I am not a Java programmer I figure out how to setup my dev env using
   > [this video](https://www.youtube.com/watch?v=C7juSZsM2Fg) The tools I am using are:
   >
   > - https://sdkman.io/
   > - `jdtls` and [jdtls-nvim](https://github.com/mfussenegger/nvim-jdtls)

3. Do the same thing for C. To get some practice with pointers, define
   a [doubly linked list](https://en.wikipedia.org/wiki/Doubly_linked_list) of heap-allocated strings. Write functions
   to insert, find, and delete items from it. Test them.
   > I also don't have any experience with C so we went with the basic setting up nvim using `clangd`, `clang-formatter`
   > The exercise source code is `./src/challenges/double_linked_list`

# Introduction

The book is a practical one, not much theory on type systems, for instance. We will build two interpreters for a made up
language called **Lox**.

This book won't use **compiler-compilers** like [Lex](https://silcnitc.github.io/lex.html),
[Yacc](https://en.wikipedia.org/wiki/Yacc) or [ANTLR](https://www.antlr.org/). We will write the interpreter by hand.

Although the first interpreter **jlox** is written in Java. I'll try to rewrite the code in Rust in parallel to practice
it. I'll try to validate the result using the [code crafters](https://app.codecrafters.io/courses/interpreter/) tests
and also compare to the ports to rust available
[here](https://github.com/munificent/craftinginterpreters/wiki/Lox-implementations).

# Challenges

1. There are at least six domain-specific languages used in
   the [little system I cobbled together](https://github.com/munificent/craftinginterpreters) to write and publish this
   book. What are they?

   > HTML, SCSS, CSS, Markdown, Make, Mustache

2. Get a "Hello, world!" program written and running in Java. Set up whatever makefiles or IDE projects you need to get
   it working. If you have a debugger, get comfortable with it and step through your program as it runs.

   > Since I am not a Java programmer I figure out how to setup my dev env using
   > [this video](https://www.youtube.com/watch?v=C7juSZsM2Fg) The tools I am using are:
   >
   > - https://sdkman.io/
   > - `jdtls` and [jdtls-nvim](https://github.com/mfussenegger/nvim-jdtls)

3. Do the same thing for C. To get some practice with pointers, define
   a [doubly linked list](https://en.wikipedia.org/wiki/Doubly_linked_list) of heap-allocated strings. Write functions
   to insert, find, and delete items from it. Test them.
   > I also don't have any experience with C so we went with the basic setting up `nvim` using `clangd`,
   > `clang-formatter` The exercise source code is `./src/challenges/double_linked_list`
