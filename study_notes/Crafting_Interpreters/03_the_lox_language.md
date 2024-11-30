# The Lox Language

Lox is the simple interpreted language that the book uses. The syntax is C based for familiarity but it does not have
strong types and support classes and simple inheritance (one level). There is no standard library except for the
function `clock()` that should return the number of seconds since the program started.

# Challenges

1. Write some sample Lox programs and run them (you can use the implementations of Lox in
   [my repository](https://github.com/munificent/craftinginterpreters)). Try to come up with edge case behavior I didn't
   specify here. Does it do what you expect? Why or why not?

```lox
fun fib(n) {
	// TODO: do some typechecking
    return noTypeCheckFib(n);
}

fun noTypeCheckFib(n) {
    if (n < 1) {
	    return 0;
    }
    if (n <= 2) {
	    return 1;
    }
    return noTypeCheckFib(n - 1) + noTypeCheckFib(n - 2);
}
```

> Since the lang does not support `integers` we have to ensure. Here I have the ed

2. This informal introduction leaves a _lot_ unspecified. List several open questions you have about the language's
   syntax and semantics. What do you think the answers should be?

   > Can we reference functions declared later on the file? Probably not.
   >
   > Can we use + to concatenate strings? Probably yes.

3. Lox is a pretty tiny language. What features do you think it is missing that would make it annoying to use for real
   programs? (Aside from the standard library, of course.)

> Since it is not strongly typed we will miss reflection. Integer support Error handling Import / export functions and
> classes Private / Public
