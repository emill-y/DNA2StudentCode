///**
// * DNA
// * <p>
// * A puzzle created by Zach Blick
// * for Adventures in Algorithms
// * at Menlo School in Atherton, CA
// *</p>
// * <p>
// * Completed by: Eisha Yadav
// *</p>
// */
//
        public class DNA {
            public static int STRCount(String sequence, String STR) {
                // Store length of sequence and STR repeat
                int n = sequence.length();
                int m = STR.length();
                // Sequence shorter than STR can never contain STR
                if (n < m) {
                    return 0;
                }
                // Encode DNA bases as 2-bit integers
                // A=0, C=1, G=2, T=3
                // Similar to doing a hash of radix 4
                // If wanted to be appilied to other problem sets, would need a radix of 256
                int[] seq = new int[n];
                for (int i = 0; i < n; i++) {
                    seq[i] = encode(sequence.charAt(i));
                }
                // Compute hash of STR
                int strHash = 0;
                // Use two bit shifting to create hash
                // | is bitwise inclusive or
                for (int i = 0; i < m; i++) {
                    strHash = (strHash << 2) | encode(STR.charAt(i));
                }
                // Keep last 2*m bits
                // Implements the 11111 on/off functionality we discussed in class
                int mask = (1 << (2 * m)) - 1;
                int hash = 0;
                int maxRepeats = 0;

                // Repeat for sequence length
                for (int i = 0; i < n; i++) {
                    // Bitshift hash and add old hash
                    hash = ((hash << 2) | seq[i]) & mask;
                    if (i >= m - 1 && hash == strHash) {
                        // Found one STR, count consecutive repeats
                        int repeats = 1;
                        int pos = i + 1;
                        while (pos + m - 1 < n) {
                            // Compute next window hash without nested loops
                            int nextHash = 0;
                            for (int j = 0; j < m; j++) nextHash = (nextHash << 2) | seq[pos + j];
                            // Check if segment matches STR
                            if (nextHash == strHash) {
                                // Increment over and continue
                                repeats++;
                                pos += m;
                            }
                            else {
                                break;
                            }
                        }
                        // Reset maxRepeats if longer run found
                        if (repeats > maxRepeats) {
                            maxRepeats = repeats;
                        }
                        // skip past this run
                        i = pos - 1;
                    }
                }
                // Return solution
                return maxRepeats;
            }
            // Encoding for each char
            // Only works for this specific problem, but for this problem is extremely efficient
            private static int encode(char c) {
                switch (c) {
                    case 'A':
                        return 0;
                    case 'C':
                        return 1;
                    case 'G':
                        return 2;
                    case 'T':
                        return 3;
                    default:
                        return 0;
                }
            }
        }