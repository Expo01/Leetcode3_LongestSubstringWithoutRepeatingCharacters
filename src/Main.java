public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
    }


}

class Solution {
    public int lengthOfLongestSubstring(String s) {

    }
}

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