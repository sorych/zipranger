# Zip Ranger

The console application to solve zip ranges task

## The task

BACKGROUND
Sometimes items cannot be shipped to certain zip codes, and the rules for these restrictions are stored as a series of ranges of 5 digit codes. For example if the ranges are:

[94133,94133] [94200,94299] [94600,94699]

Then the item can be shipped to zip code 94199, 94300, and 65532, but cannot be shipped to 94133, 94650, 94230, 94600, or 94299.

Any item might be restricted based on multiple sets of these ranges obtained from multiple sources.

PROBLEM
Given a collection of 5-digit ZIP code ranges (each range includes both their upper and lower bounds), provide an algorithm that produces the minimum number of ranges required to represent the same restrictions as the input.

NOTES
- The ranges above are just examples, your implementation should work for any set of arbitrary ranges
- Ranges may be provided in arbitrary order
- Ranges may or may not overlap
- Your solution will be evaluated on the correctness and the approach taken, and adherence to coding standards and best practices

EXAMPLES:
If the input = [94133,94133] [94200,94299] [94600,94699]
Then the output should be = [94133,94133] [94200,94299] [94600,94699]

If the input = [94133,94133] [94200,94299] [94226,94399] 
Then the output should be = [94133,94133] [94200,94399]



### Running

```
Build the project using gradle and then start jar in console.

Put in the line of zip ranges, you can use multi-lines input, just tupe in X when you are done
```

## Running the tests

```
tests are the part of standard gradle build procedure
```

## Console output sample

```
dimasmac:zipranger DimaS$ ./gradlew clean build
BUILD SUCCESSFUL in 1s
8 actionable tasks: 8 executed

dimasmac:zipranger DimaS$ java -jar build/libs/zipranger-1.0.jar 
please insert next line or type 'X' to finish input
[15432,18312] [23451,38765] [43451,78795] [15432,18312] [23451,38765] [43451,78795] [15432,18312] [23451,38765] [43451,78795]
please insert next line or type 'X' to finish input
[15432,18312] [23451,38765] [43451,78795]
please insert next line or type 'X' to finish input
X
Ranges processing result:
[15432,18312]
[23451,38765]
[43451,78795]

```

## Implementation details

1. Strategy pattern was used to provide different readers and result receivers tp the range processor.
2. Treeset was used to store ranges as it makes them sorted right after insertion and we just need to find overlaps. Two iterators are used to merge.
3. The app doesn't store the merged ranges - it is passed to receiver and it depents on it what to do. Default one just prints to console.
4. Input validator checks for input mask, that is configurable in Application Configuration class. So is a delims string.



## Authors

* **Dmytro Sorych** - (https://github.com/sorych)


