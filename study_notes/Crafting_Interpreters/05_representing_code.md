# Representing Code

One common way to represent precedence is using a tree solving the leaves operations until the root expression is
solved. In the previous chapter the **scanner** extracted lexical grammar from the raw text, now we want to produce a
syntactic grammar (moving one level up on the **Chomsky hierarchy**). A formal grammar takes a set of atomic pieces it
calls its "alphabet". Then it defines a (usually infinite) set of "strings" that are "in" the grammar. Each string is a
sequence of "letters" in the alphabet.

|                | Lexical | Syntatic    |
| -------------- | ------- | ----------- |
| alphabet       | letters | tokens      |
| strings        | tokens  | expressions |
| implemented by | scanner | parser      |

A formal grammar job is to separate valid from invalid strings. To specify the syntactic grammar is common to use a
notation with a **head** (rule label) and a **body** that contains **symbols**. Symbols might be **terminals** (node
leaves) or **non-terminals** (to be expanded). Here is the book example on a grammar that produces breakfast.

```ebnf
breakfast → protein ( "with" breakfast "on the side" )?
          | bread ;

protein   → "really"+ "crispy" "bacon"
          | "sausage"
          | ( "scrambled" | "poached" | "fried" ) "eggs" ;

bread     → "toast" | "biscuits" | "English muffin" ;
```

To represent this in Java the book suggests a series of classes and the visitor pattern. Since it is tedious to write
all this classes (20 something) by hand, the book suggesting writing a tool to generate the classes. Although it does
this using java, I wrote the tools using js and handlebars (see `./src/jlox/tools/jlox_expr_gen`).

Then we will write a AST lisp printer visitor so we can visualize operation precedence. Examples:

```
1 + 2 * 3         →     (+ (* (2 3)) 1)
-123 * (45.67)    →     (* (- 123) (group 45.67))
```

# Challenges

1. Earlier, I said that the `|`, `*`, and `+` forms we added to our grammar metasyntax were just syntactic sugar. Take
   this grammar:

```ebnf
expr → expr ( "(" ( expr ( "," expr )* )? ")" | "." IDENTIFIER )+
     | IDENTIFIER
     | NUMBER
```

Produce a grammar that matches the same language but does not use any of that notational sugar.

_Bonus:_ What kind of expression does this bit of grammar encode?

> This grammar represents a function invocation (although it allows the function name to be a number).

```ebnf
expr → expr fn_call
expr → IDENTIFIER
expr → NUMBER

fn_call → fn_call call
fn_call → call

call → "(" ")"
call → "(" arguments ")"
call → "." IDENTIFIER

arguments → expr
arguments → arguments "," expr
```

2. The Visitor pattern lets you emulate the functional style in an object-oriented language. Devise a complementary
   pattern for a functional language. It should let you bundle all of the operations on one type together and let you
   define new types easily. (SML or Haskell would be ideal for this exercise, but Scheme or another Lisp works as well.)

> ???

3. In [reverse Polish notation](https://en.wikipedia.org/wiki/Reverse_Polish_notation) (RPN), the operands to an
   arithmetic operator are both placed before the operator, so `1 + 2` becomes `1 2 +`. Evaluation proceeds from left to
   right. Numbers are pushed onto an implicit stack. An arithmetic operator pops the top two numbers, performs the
   operation, and pushes the result. Thus, this:

```
(1 + 2) * (4 - 3)
```

in RPN becomes:

```
1 2 + 4 3 - *
```

Define a visitor class for our syntax tree classes that takes an expression, converts it to RPN, and returns the
resulting string.

> Defined on the `jlox` source.
