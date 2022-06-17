/** 
 * A class that represents a text document
 */
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{	
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.  You should write this and use it in your 
	 * BasicDocument class.
     *
	 * Just use a loop to loop through the 
	 * characters in the string and write your own logic for counting 
	 * syllables.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 */
	public boolean isVowel(char c) 
	{
        if ((c == 'a'||c == 'A') || (c == 'e'|| c == 'E') || (c == 'i' || c == 'I') || (c == 'o'|| c == 'E') || (c == 'u'|| c == 'U') || (c == 'y'|| c == 'Y')) {
			return true;
        } 
        else {
            return false;
		}
	}
	
	protected int countSyllables(String word)
	{
		// TODO: Implement this method so that you can call it from the 
	    // getNumSyllables method in BasicDocument
		if(word.length()==0){return 0;}
		int numsyl = 0;
		for (int i = 1; i < word.length(); i++) {
			char actual = word.charAt(i);
            char prior = word.charAt(i-1);
            if (isVowel(actual) == true && isVowel(prior) == false) {
                numsyl++;
            }
		}
        char first = word.charAt(0);
        char last = word.charAt(word.length()-1);
		//char sec_to_last = word.charAt(word.length()-2);
		if (isVowel(first) == true){
        	 numsyl++;
         } 
        
        if ((last == 'E'|| last == 'e') && !isVowel(word.charAt(word.length()-2))){
            numsyl-=1;
        }
        
        if(numsyl <= 0){
            numsyl = 1;
        }
	    
        return numsyl;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
	    // TODO: Implement this method.
		//FleshScore = 206.835-1.015(#words/#sentences)-84.6(#syllables/#words)
	    Double FleshScore = 0.0;
	    int word_sum = getNumWords();
	     int sentence_sum = getNumSentences();
	    int syllable_sum = getNumSyllables();
	    FleshScore = 206.835-(1.015*(word_sum/sentence_sum))-(84.6*(syllable_sum/word_sum));
		return FleshScore;
	}

	
}
