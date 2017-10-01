package edu.gcccd.csis.p1;

import java.util.Arrays;

public class SequenceSearchImpl extends SequenceSearch {

    public SequenceSearchImpl(final String content, final String start, final String end) {
        super(content, start, end);
    }

    @Override
    public String[] getAllTaggedSequences() {

        // This worked without editing for the getAllTaggedSequencesEqualDelimiters Test
        // I think it worked because I used the length of the tags
        // Need to go back and test for learning

        // Create variables and array
        int endingLocation;
        String[] results = new String[0];
        int startingLocation = 0;
        int altStartingLocation;
        int arraySlot = 0;

        while (startingLocation >= 0) {

            startingLocation = this.content.indexOf(this.startTag, startingLocation);
            // Create alternate starting location in case tags are out of order
            altStartingLocation = this.content.indexOf(this.startTag, startingLocation + this.startTag.length());

            if (startingLocation >= 0) {
                endingLocation = this.content.indexOf(this.endTag, startingLocation + this.startTag.length());
                // Uncomment to see results while running through
                // System.out.println(content.substring(startingLocation + startTag.length(), endingLocation));

                // Found good pairing if endingLocation is before altStartingLocation
                // Allow to pass through if we found no alternate starting location
                if ((altStartingLocation >= endingLocation) || (altStartingLocation == -1)) {
                    // Couldn't find an ending location
                    if ((endingLocation == -1)) {
                        startingLocation = altStartingLocation;
                    } else {
                        results = SequenceSearch.adds(results, this.content.substring(startingLocation + this.startTag.length(), endingLocation));
                        startingLocation = endingLocation + this.endTag.length();
                        arraySlot = ++arraySlot;
                    }
                } else {
                    // Didn't find a good pairing at last starting location, move forward to alternate location
                    startingLocation = altStartingLocation;
                }



            }
        }

        return results;
    }

    @Override
    public String getLongestTaggedSequence() {

        // Create variables and array
        String longest = null;
        String[] results = getAllTaggedSequences();

        // Uncomment to see Arrays
        // System.out.println(Arrays.toString(results));

        for ( String result : results) {
            if ((longest == null) || (result.length() >= longest.length())) {
                longest = result;
            }
        }

        return longest;
    }

    @Override
    public String displayStringArray() {

        // Create variables and array
        String[] results = getAllTaggedSequences();
        String newResults = null;
        String tempString;

        for ( String result : results) {
            // Should I be using a list here?
            // Concatenating String seems like brute force
            tempString = result + " : " + result.length() + "\n";
            if (newResults == null) {
                newResults = tempString;
            }
            newResults = newResults + tempString;
        }

        return newResults;
    }

    @Override
    public String toString() {

        // Create variables and array
        String result;

        result = this.content.replace(this.startTag,"");
        result = result.replace(this.endTag,"");

        return result;
    }

      // Original toString method.  It worked, but so much more work
//    @Override
//    public String toString() {
//
//        // Create variables and array
//        String result = null;
//        int endingLocation = 0;
//        int startingLocation = 0;
//
//        while (startingLocation >= 0) {
//
//            startingLocation = this.content.indexOf(this.startTag, startingLocation);
//
//            // Seems like nested if statements are not the cleanest method to use
//            if (startingLocation >= 0) {
//                // Is checking for null the cleanest way to start this?
//                if (result == null) {
//                    // Hardcoding 0 here seems like I should use something else
//                    result = this.content.substring(0,startingLocation);
//                }
//                else {
//                    result = result + this.content.substring(endingLocation + this.endTag.length(),startingLocation);
//                }
//                endingLocation = this.content.indexOf(this.endTag, startingLocation + this.startTag.length());
//                result = result + this.content.substring(startingLocation + this.startTag.length(),endingLocation);
//                startingLocation = endingLocation + this.endTag.length();
//            }
//            else {
//                result = result + this.content.substring(endingLocation + this.endTag.length(),this.content.length());
//            }
//        }
//
//        return result;
//    }

}