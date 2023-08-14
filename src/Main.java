import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String ex = "tmmzuxt";
        System.out.println(new Solution().lengthOfLongestSubstring(ex));
    }

}


class Solution {
    public int lengthOfLongestSubstring(String s) {
        int longestLength = 0;
        int L = 0;
        Map<Character, Integer> m = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            if (!m.containsKey(s.charAt(i)) || m.get(s.charAt(i))<L) {
                m.put(s.charAt(i), i);
                if (((i+1) - L) > longestLength) {
                    longestLength = i + 1 - L;
                }
            } else {
                L = m.get(s.charAt(i)) + 1;
                m.put(s.charAt(i), i);
            }
        }
        return longestLength;
    }
}



/*
class Solution {
    public String lengthOfLongestSubstring(String s) {
        int[] temp = {0, 0}; // stores indexes for largest substring
        int L = 0; // stores left most index until duplicate found
        int R = 0; // stores right index and  increments
        Map<Character, Integer> m = new HashMap<>(); // map for constant time look up

        for (int i = 0; i < s.length(); i++) { // loops through all chars in string
            if (!m.containsKey(s.charAt(i))) { // if map does not contain the char at string index 'i'
                m.put(s.charAt(i), i); // put the char as key and its index as the value
            } else { // if map does contain char, we must save the indexes if the span is the greatest
                if ((R - L) > (temp[1] - temp[0])) { // initial span is 0-0 = 0,
                    temp[0] = L; // save the left index in the span
                    temp[1] = R; // save the right index in the span
                } // but in subsequent iterations, if saved span is larger than the current R-L, then no change
                L = m.get(s.charAt(i)) + 1; // regardless, must move L past the first char of the duplicate pair
                // where m.get aquires the curent value at the char key then +1 to increment L one index past that redundant char
                m.put(s.charAt(i),1); // value of the char key rewritten as the most recent index occurence of that char
                // so that if it occurs again, we can save the L variable past that
            }
            R = i; // R incremented. the R variable is not strictly necessary, and can be substituted for 'i', but it
            // helps to build the left and right bounds imagery
        } // ultimately the loop ends and the temp should contain the indexes associated with the largest span
        return s.substring(L,R+1); // Substring end parameter non-inclusive so R+1
        // JUST REALIZED I DID WAY MORE THAN WAS ASKED. I DON'T NEED THE INDEXES OR TO OUTPUT THE SUBSTRING. JUST LENGTH
        // PFFFFFTTTTTTT
    }
}\

 */

// can do nested loop but On^2
// problem is needing to backtrack. can have string 'woaswdfghjklzxcvbnm' which the 'w'
// repeats but if we start at f then we miss 'oasw'
// array? can store a count where if value exists then false, but doesn't solve wheree to backtrack to
// map? can record index as a value. so in above m.put(w,0) so when we get to 'w' again
// and find value, go back to index m.get(w) and run again since all characters afterr that index non-redundant, but record a 2 value array of start and end indexes and a count
// actually, we don't need to re-run the loop, just have start index and end index value where start index changed to +1 of saved redundant value. example
// abCdfghiCjklmnopqrStS. up until second C, the longest substring is 8 chars. going back to 'b' won't help to remove redundancy. need to get to d first and then largest substring
// is 19. so the saved valuee of 8 is overwritten and stored start index is changed to 3
// now if scond redundancy found such as StS but starting at and going to S is only 2 chars
// so need to have both a stored count for comparrison and stored indexes forr rsubstring
// return