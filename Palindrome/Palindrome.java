import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary();



	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		//System.out.println("Possible Words: "+ words.toString());

		return words.toArray(new String[0]);
	}



	public static String reverseStringAndRemoveNonAlpha(String text) {
	    text = text.toLowerCase();
		Stack<Character> stack = new Stack<Character>();
		while(text.length()>0){
		    Character x = new Character(text.charAt(0));
		    if(Character.isAlphabetic(x)){
		        stack.push(x);
            }
            text = text.substring(1);
        }
        String ans = "";
		while(stack.size()>0){
		    ans = ans + stack.pop();
        }
		return ans;
	}

	public static String stackToReverseString(Stack<String> stack){
	    String[] arr = new String[stack.size()];
	    String ans = "";
	    //System.out.println("Trying to print stack: "+ stack.toString());
	    for(int i=0; i<arr.length; i++)
        {
	        String temp = stack.pop();
	        //ans = ans + temp + " ";
	        arr[i]=temp;
	        //System.out.println(temp);
	        //ans= ans +temp + " ";
        }
        //System.out.println("stack after: " +stack.toString());
	    //System.out.println("array after: " + arrayToString(arr));
        for(int i=arr.length-1; i>=0; i--){
	        ans = ans + arr[i] + " ";
	        stack.push(arr[i]);
        }
        return ans;
    }



	// Returns true if the text is a palindrome, false if not.
	public static boolean isPalindrome(String text) {
	    String str = reverseStringAndRemoveNonAlpha(text);
	    Stack<Character> stack = new Stack<Character>();
	    Queue<Character> queue = new Queue<Character>();
	    //System.out.println("Queue size right after creating: " +queue.size());
	    while(str.length()>0){
	        Character ch = str.charAt(0);
	       // System.out.println("about to push into stack and enqueue: " +ch);
	        stack.push(ch);
           // System.out.println("Size of queue before calling enqueue: "+queue.size());
	        queue.enqueue(ch);
	        str = str.substring(1);
        }
        for(int i=0; i<stack.size(); i++){
	        if(!(stack.pop().equals(queue.dequeue())))
	            return false;
        }
        return true;
	}



	public static void explorePalindrome(String text)
    {
	    String reversed = reverseStringAndRemoveNonAlpha(text);
	    System.out.println("reversed string: " +reversed);
	    Stack<String> decomposition = new Stack<String>();
	    decomposeText(text, reversed, 0, decomposition);
	    //System.out.println(decomposition);

	}

	public static String arrayToString(String[] array){
	    String ans="[";
	    for(int i=0; i<array.length; i++){
	        ans = ans + array[i] + " ";
        }
        return ans + "]";

    }

	public static void decomposeText( String originalText,
                                    String textToDecompose,
                                    int index,
                                    Stack<String> decomposition)
    {

        if(index==textToDecompose.length()){
            //System.out.println("Stack size before: " + decomposition.size());
            System.out.println("Done: " +stackToReverseString(decomposition));
            //System.out.println("Stack size: " + decomposition.size());
        }
        else
        {
            //System.out.println("index: " + index);
            String[] words = getWords(textToDecompose, index);
            //System.out.println("List of words at index " + index + " : "+ arrayToString(words));
            for (int i=1; i<words.length; i++){
                //System.out.println("Word: " + words[i]);
                decomposition.push(words[i]);
                //System.out.println("Temporary Stack: "+decomposition);
                index = index + words[i].length();
                decomposeText(originalText, textToDecompose, index, decomposition);
                index=index - words[i].length();
                //System.out.println("stack length: " + decomposition.size());
                if(decomposition.size()>0) decomposition.pop();
            }

        }

	}

	// This function looks at the arguments that are passed
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {

       // System.out.println(reverseStringAndRemoveNonAlpha("What 1s UP"));


		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = {"A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami"};
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);


			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					//System.out.println("testing if " + text+ " is a palindrome:");
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {
					explorePalindrome(testPalindromes[i]);
				}
			}
			else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}
