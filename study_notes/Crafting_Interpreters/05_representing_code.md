# Representing Code

One common way to represent precedence is using a tree solving the leaves operations until the root expression is solved. In the previous chapter the **scanner** extracted lexical grammar from the raw text, now we want to produce a syntactic grammar (moving one level up on the **Chomsky hierarchy**).
A formal grammar takes a set of atomic pieces it calls its "alphabet". Then it defines a (usually infinite) set of "strings" that are "in" the grammar. Each string is a sequence of "letters" in the alphabet.

|                | Lexical | Syntatic    |
| -------------- | ------- | ----------- |
| alphabet       | letters | tokens      |
| strings        | tokens  | expressions |
| implemented by | scanner | parser      |
A formal grammar job is to separate valid from invalid strings. To specify the syntactic grammar is common to use a notation with a **head** (rule label) and a **body** that contains **symbols**. Symbols might be **terminals** (node leaves) or **non-terminals** (to be expanded). Here is the book example on a grammar that produces breakfast.

```
breakfast → protein ( "with" breakfast "on the side" )?
          | bread ;

protein   → "really"+ "crispy" "bacon"
          | "sausage"
          | ( "scrambled" | "poached" | "fried" ) "eggs" ;

bread     → "toast" | "biscuits" | "English muffin" ;
```

 To represent this in Java  the book suggests a series of classes and the visitor pattern. Since it is tedious to write all this classes (20 something) by hand, the book suggesting writing a tool to generate the classes. Although it does this using java, I wrote the tools using js and handlebars (see `./src/jlox/tools/jlox_expr_gen`). 
 
# Challenges

