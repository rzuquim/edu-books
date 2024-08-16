# Scanning

The `jlox` interpreter should have two `modes` a `REPL` and a parse file. I will put the source on `src/jlox`.

It is a good practice to separate the actual parsing code from the error reporting.

The whole purpose of scanning (or lexing)  is to categorize the source code string into **tokens**. A blob of text parsed into a **token** is called **lexeme**. **Lexemes** are the raw substrings from the source code that originated a **token**.

A token, in addition to the lexeme, contains location information (which line and position in the line it is found). 

> Some token implementations store the location as two numbers: the offset from the beginning of the source file to the beginning of the lexeme, and the length of the lexeme. The scanner needs to know these anyway, so there's no overhead to calculate them. An offset can be converted to line and column positions later by looking back at the source file and counting the preceding newlines. That sounds slow, and it is. However, you need to do it _only when you need to actually display a line and column to the user_. Most tokens never appear in an error message. For those, the less time you spend calculating position information ahead of time, the better.

The rules that determine how a particular language groups characters into lexemes are called its **lexical grammar**. To identify tokens we can use Regex. Tools like [Lex](http://dinosaur.compilertools.net/lex/) or [Flex](https://github.com/westes/flex) do this by mapping regular expressions into tokens. See: [the dragon book](https://en.wikipedia.org/wiki/Compilers:_Principles,_Techniques,_and_Tools).
# Challenges

1. The lexical grammars of Python and Haskell are not _regular_. What does that mean, and why aren't they?
> Python and Haskell's lexical grammars are not regular because they require parsing mechanisms that go beyond the capabilities of regular expressions and finite automata. Both requires context to be parsed (for instance how deep is the token identation).

2. Aside from separating tokens -- distinguishing `print foo` from `printfoo` -- spaces aren't used for much in most languages. However, in a couple of dark corners, a space _does_ affect how code is parsed in CoffeeScript, Ruby, and the C preprocessor. Where and what effect does it have in each of those languages?
> In coffee or ruby calling functions without  the explicit parenthesis can cause issues depending on the use of spaces.
```coffeescript
square = (x) -> x * x
result = square 5    # Interpreted as square(5)
result = square 5, 2 # Interpreted as square(5, 2), not as (square 5), 2
```

```ruby
def foo(arg) 
	arg + 1 
end 

foo -1 # Interpreted as foo(-1)
foo-1  # Interpreted as foo minus 1 (subtraction between foo and 1)
```

> Spaces are also significant in C when expanding macros. 
```c
#define CONCAT(a, b) a##b
CONCAT(var, 1)   // Expands to var1
CONCAT(var ,1)   // Expands to var 1 (space between var and comma)
```

3. Our scanner here, like most, discards comments and whitespace since those aren't needed by the parser. Why might you want to write a scanner that does _not_ discard those? What would it be useful for?
To generate automatic documentation, embed comments in generated symbols (for debugging) and for a LSP.

4. Add support to Lox's scanner for C-style `/* ... */` block comments. Make sure to handle newlines in them. Consider allowing them to nest. Is adding support for nesting more work than you expected? Why?
> It would be hard to nest comments because we would need to keep track of of how deep we are on the comments sections to check if we should stop ignoring the contents of the comments. 
