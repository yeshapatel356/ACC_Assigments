package assignment3;

import java.util.Arrays;

public class Assignment3_BoyerMoore {

    private final int RADIX_yesha; // ASCII character set size
    private int[] rightmostOccurrences_yesha; // array to store rightmost character occurrences in the pattern
    private char[] pattern_yesha; // pattern stored as character array
    private String patternString_yesha; // pattern stored as string

    // Constructor with pattern provided as a string
    public Assignment3_BoyerMoore(String pattern_yesha) {
        this.RADIX_yesha = 256; // size of ASCII character set
        
        // Initialize pattern details
        this.patternString_yesha = pattern_yesha;
        
        // Initialize array to store rightmost occurrences of characters in the pattern
        rightmostOccurrences_yesha = new int[RADIX_yesha];
        Arrays.fill(rightmostOccurrences_yesha, -1); // Initialize all elements to -1
        
        // Store rightmost occurrences of characters in the pattern
        for (int index_yesha = 0; index_yesha < pattern_yesha.length(); index_yesha++) {
            rightmostOccurrences_yesha[pattern_yesha.charAt(index_yesha)] = index_yesha;
        }
    }

    // Method to count occurrences of pattern in text
    public int countOccurrences(String text_yesha) {
        int patternLength_yesha = patternString_yesha.length();
        int textLength_yesha = text_yesha.length();
        int skip_yesha;
        int count_yesha = 0;

        // Search for pattern in text using Boyer-Moore algorithm
        for (int i_yesha = 0; i_yesha <= textLength_yesha - patternLength_yesha; i_yesha += skip_yesha) {
            skip_yesha = 0;

            // Check pattern from right to left
            for (int j_yesha = patternLength_yesha - 1; j_yesha >= 0; j_yesha--) {
                char textChar_yesha = text_yesha.charAt(i_yesha + j_yesha);

                // Mismatch found
                if (patternString_yesha.charAt(j_yesha) != textChar_yesha) {
                    // Calculate skip based on rightmost occurrence of mismatched character
                    if (textChar_yesha < RADIX_yesha) {
                        skip_yesha = Math.max(1, j_yesha - rightmostOccurrences_yesha[textChar_yesha]);
                    } else {
                        skip_yesha = Math.max(1, j_yesha + 1); // Skip entire pattern length if character is out of range
                    }
                    break;
                }
            }

            // Increment count if pattern found, and move past the match
            if (skip_yesha == 0) {
                count_yesha++;
                skip_yesha = patternLength_yesha;
            }
        }

        return count_yesha;
    }
}
