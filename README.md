# Lead Tracker — Technical Assessment

Here is a quick guide on how to run and test everything.

## Running the Java App

1. Open your terminal and navigate to the `java/` folder.
2. Compile the files:

```bash
javac *.java

```

3. Run the application:

```bash
java Main

```

4. Use the console menu to add a few leads (name, email, score 0-100) and view them to see how their priorities get calculated.

## Running the JavaScript Tests

1. Make sure Node.js is installed.
2. Head into the `javascript/` folder and run:

```bash
node problems.js

```

3. This will print out a PASS/FAIL breakdown for every test case straight to your console.

## How I tackled the JS problems

- **`firstNonRepeatingChar`**: Counts character frequencies in a single pass (ignoring case for the count), then scans the string again to grab the first character that only appeared once, preserving its original case. Spaces and punctuation are treated like regular characters.
- **`findTwoSum`**: Used a map to keep track of numbers we've seen and their indices. This lets us solve it in a single pass ($O(n)$ time) instead of doing nested loops.
- **`countLeadsByStatus`**: Walks the leads array once to build a status count object, making sure not to mutate the original array.

## Test cases

You can look at `javascript/problems.js` for the full suite, which includes:

- The standard examples from the assignment.
- Edge cases like empty strings and empty arrays.
- Case-insensitive checks that still spit out the right original casing.
- Handling duplicate values in two-sum (`[3, 3]`, target `6`).
- Making sure the original leads array doesn't get mutated.

For the Java app, if you want to try breaking it:

- Blank names or emails are caught and rejected cleanly.
- Boundary scores (0, 49, 50, 79, 80, 100) correctly flip between COLD, WARM, and HOT.
- Bad inputs like `-1`, `101`, or typing letters instead of a score are handled gracefully without crashing.
- Trying to view leads when the list is empty just shows a friendly notice.

## One design choice I made

I made `Lead` a Java `record` and purposely left `priority` out of it. Because priority is always calculated on the fly using `PriorityCalculator.calculate(score)`, it can never get out of sync or stored incorrectly if something changes.

## If I had more time...

I would add options to edit or delete leads after adding them, and I'd write proper unit tests using JUnit instead of just testing it manually through the console.

## AI tools used

I used Claude to draft an
initial version of the Java classes and the JavaScript functions,
which I then reviewed, tested, and adjusted. All code was compiled,
run, and tested by me before submission.
