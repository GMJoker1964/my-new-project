import java.util.ArrayList;

public class Dictionary {
    static ArrayList<Word> dictionary = new ArrayList<Word>();                  //Initialize dictionary by arraylist

    //Get and set function

    public ArrayList<Word> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Word w) {
        (this.dictionary).add(w);
    }
}