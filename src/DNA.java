/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: Eisha Yadav
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        // Handle Edge Cases
        if (sequence.isEmpty() || sequence.length() < STR.length()){
            return 0;
        }

        // Parse sequence to identify beginning of STR
        // Continue to add to run-length as STR
        // When STR has a mistake still keep known prior segment of STR
        int strLen = STR.length();
        int seqLen = sequence.length();
        int[] seqRep = new int[seqLen];
//        String[] STRsegments = new String[strLen];
//        for(int i = 0; i < strLen; i++){
//            STRsegments[i] = STR.substring(0,i);
//        }
//        int counter = 0;
//        int numRepeats = 0;
        int maxNumRepeats = 0;
        for (int i = seqLen - strLen; i >= 0; i--) {
            // I don't know if the region matching function is allowed
            // Since it does do a comparision-- and I am not yet sure of the runtime of the comparison
            if (sequence.regionMatches(i, STR, 0, strLen)) {
                // Uses ternary operator to check if there is a continuing repeat
                seqRep[i] = 1 + ((i + strLen < seqLen) ? seqRep[i + strLen] : 0);
                // Update Maximum value
                if (seqRep[i] > maxNumRepeats) maxNumRepeats = seqRep[i];
            } else {
                // Mark broken repeat
                seqRep[i] = 0;
            }
        }
        return maxNumRepeats;
    }
}




