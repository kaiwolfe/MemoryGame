package com.example.memorygame;

public class Word {
	
	//Variables that make up word object
    private int wordID = 0;
    private String english;
    private String spanish;
    private String japanese;
    private String german;
    
    //Word constructor; forces all translations to be set, auto-increments ID
    public Word(String eng, String spa, String jap, String ger){
        wordID++;
        english = eng;
        spanish = spa;
        japanese = jap;
        german = ger;
    }

    //Returns the word ID
    public int getID(){
        return wordID;
    }

    //Returns the word in all languages
    public String getWord(){
    	return "ENG: " + english + "; SPA: " + spanish + "; JAP: " + japanese + "; GER: " + german;
    }
    
    //Returns the word in the specified language
    public String getWord(String lang){

        if(lang.equals("spanish") || lang.equals("spa"))
            return spanish;

        else if(lang.equals("japanese") || lang.equals("jap"))
            return japanese;

        else if(lang.equals("german") || lang.equals("ger"))
            return german;

        else
            return english;
    }

}
