# Parsing Expressions

Parsers, given a string (a series of tokens) map those tokens to **terminals** in the grammar to figure out which rules could have generated that string. It is possible to create ambiguous grammars, and, when parsing we must create **precedence** rules to ensure cohesion.

There is also **associativity** that determines which operator should be evaluated first in a series of the same operator. Minus (-) is left-associative (evaluated "left-to-right") while an assignment (=) is right-associative.

There is a lot of parsing techniques, this book will use **Recursive Descending Parsing**. It is a **top-down** parser because it starts on the outermost grammar rule and goes down the tree. 

Like the scanner, the parser consumes a flat input alphabet (now tokens, not characters).
# Challenges
