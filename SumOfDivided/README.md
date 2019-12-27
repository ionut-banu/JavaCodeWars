# Sum by Factors

Given an array of positive or negative integers

I= [i1,..,in]

you have to produce a sorted array P of the form

[ [p, sum of all ij of I for which p is a prime factor (p positive) of ij] ...]

P will be sorted by increasing order of the prime numbers. The final result has to be given as a string in Java, C#, C, C++ and as an array of arrays in other languages.

Example:

I = {12, 15}; // result = "(2 12)(3 27)(5 15)"

[2, 3, 5] is the list of all prime factors of the elements of I, hence the result.
