/**
 * Problem 1: First non-repeating character.
 *
 * Approach: count how many times each character appears, treating
 * letters case-insensitively (so 'S' and 's' count as the same
 * character). Spaces and punctuation are counted as ordinary
 * characters too, since the task only calls out case-insensitivity
 * for letters and gives no instruction to strip anything else. Then
 * scan the string again in its original order and return the first
 * character whose total count is exactly 1, in its original case.
 */
function firstNonRepeatingChar(str) {
  if (!str) return null;

  const counts = {};
  for (const char of str) {
    const key = char.toLowerCase();
    counts[key] = (counts[key] || 0) + 1;
  }

  for (const char of str) {
    if (counts[char.toLowerCase()] === 1) {
      return char;
    }
  }

  return null;
}

/**
 * Problem 2: Find two numbers that add up to a target.
 *
 * Approach: single pass using a Map from "value seen so far" to its
 * index. For each number, check whether its complement
 * (target - number) was already seen; if so, return the stored index
 * and the current index. This runs in O(n) time and O(n) space,
 * instead of O(n^2) for comparing every pair directly.
 */
function findTwoSum(nums, target) {
  const seen = new Map();

  for (let i = 0; i < nums.length; i++) {
    const complement = target - nums[i];
    if (seen.has(complement)) {
      return [seen.get(complement), i];
    }
    seen.set(nums[i], i);
  }

  return null;
}

/**
 * Problem 3: Count leads by status.
 *
 * Approach: walk the array once, building a plain object whose keys
 * are status strings and whose values are running counts. The
 * original array is only read, never mutated.
 *
 * To also report statuses with a count of zero, you'd need a known
 * list of every possible status value up front (e.g. from the CRM's
 * schema or a config list), pre-fill the result object with 0 for
 * each of those, and then increment as normal while iterating over
 * the leads.
 */
function countLeadsByStatus(leads) {
  const counts = {};
  for (const lead of leads) {
    counts[lead.status] = (counts[lead.status] || 0) + 1;
  }
  return counts;
}

// ---------------------------------------------------------------------
// Tests
// ---------------------------------------------------------------------

function assertEqual(actual, expected, label) {
  const pass = JSON.stringify(actual) === JSON.stringify(expected);
  console.log(`${pass ? "PASS" : "FAIL"} - ${label}`);
  if (!pass) {
    console.log(`  expected: ${JSON.stringify(expected)}`);
    console.log(`  actual:   ${JSON.stringify(actual)}`);
  }
}

console.log("--- firstNonRepeatingChar ---");
assertEqual(firstNonRepeatingChar("aabbcdd"), "c", "basic case");
assertEqual(firstNonRepeatingChar("swiss"), "w", "basic case 2");
assertEqual(firstNonRepeatingChar("aabb"), null, "no unique character");
assertEqual(firstNonRepeatingChar(""), null, "empty string");
assertEqual(firstNonRepeatingChar("Sswiss"), "w", "case-insensitive counting, original case returned");
assertEqual(firstNonRepeatingChar("a a b b c"), "c", "spaces counted as characters");

console.log("\n--- findTwoSum ---");
assertEqual(findTwoSum([2, 7, 11, 15], 9), [0, 1], "basic case");
assertEqual(findTwoSum([3, 2, 4], 6), [1, 2], "basic case 2");
assertEqual(findTwoSum([3, 3], 6), [0, 1], "duplicate values");
assertEqual(findTwoSum([1, 2, 3], 10), null, "no pair exists");

console.log("\n--- countLeadsByStatus ---");
const sampleLeads = [
  { name: "Alice", status: "NEW" },
  { name: "Bob", status: "CONTACTED" },
  { name: "Charlie", status: "NEW" },
  { name: "David", status: "QUALIFIED" },
];
assertEqual(
  countLeadsByStatus(sampleLeads),
  { NEW: 2, CONTACTED: 1, QUALIFIED: 1 },
  "basic case"
);
assertEqual(countLeadsByStatus([]), {}, "empty array");
assertEqual(sampleLeads.length, 4, "original array is not mutated");

module.exports = { firstNonRepeatingChar, findTwoSum, countLeadsByStatus };
