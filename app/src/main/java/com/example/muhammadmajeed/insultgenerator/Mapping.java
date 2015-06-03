package com.example.muhammadmajeed.insultgenerator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Mapping {
	//adult and young arrays contain the mature and PG insults respectively
	private ArrayList<ArrayList<ArrayList<String>>> adult = new ArrayList<ArrayList<ArrayList<String>>>();
	private ArrayList<ArrayList<ArrayList<String>>> young = new ArrayList<ArrayList<ArrayList<String>>>();
	private String rating;
	private String type;

	public void setRating(RATING input) {
		rating=input.toString();
	}
	public void setType (TYPE input) {
		type=input.toString();
	}

	public String getInsult() {
		//pointer for list
		ArrayList<ArrayList<ArrayList<String>>> list;
		if (!isRating(rating) || !isType(type)) {
			return "INVALID RATING AND TYPE PROVIDED";
		}
		String output;
		String subject = "";
		String adjective = "";
		String noun = "";
		String location = "";
		String intro = "";
		int typeIndex = getTypeArray(type);

		if (rating.equalsIgnoreCase(RATING.ADULT.toString())) {
			list=adult;
		}
		else {
			list=young;
		}

		adjective = getAdjectivePhrase(typeIndex, list);
		if (!adjective.startsWith("#")) {
			subject = getSubjectPhrase(typeIndex,list) + " ";
			intro = getIntro(typeIndex);
		}
		if (!adjective.endsWith("#")) {
			noun = " " + getNounPhrase(typeIndex, list);
		}
		adjective=removeHashes(adjective);
		if (noun.endsWith("@") || adjective.endsWith("@")) {
			location=" in " + getLocationPhrase(typeIndex, list);
			if (typeIndex==TYPE.HIPHOP.getArrayIndex()||typeIndex==TYPE.SCIFI.getArrayIndex()) {
				intro = getIntro(typeIndex);
			}
			if (intro.length()>0) {
				intro = intro.substring(0, intro.length()-3);
				intro = intro + " the most ";
			}
			noun = removeStars(noun);
			adjective = removeStars(adjective);
		}
		if (typeIndex==TYPE.MAMA.getArrayIndex()) {
			output = getIntro(typeIndex) + adjective + "!";
		}
		else if (typeIndex==TYPE.HIPHOP.getArrayIndex() || typeIndex==TYPE.SCIFI.getArrayIndex()) {
			output = intro + adjective + noun + location;
		}
		else {
			output = intro + subject + adjective + noun + location + ".";
		}
		return output;
	}
	

	public String getYodaInsult() {
		//pointer for list
		ArrayList<ArrayList<ArrayList<String>>> list;
		if (!isRating(rating) || !isType(type)) {
			return "INVALID RATING AND TYPE PROVIDED";
		}
		String output;
		String subject = "";
		String adjective = "";
		String noun = "";
		String intro = "";
		int typeIndex = getTypeArray(type);

		if (rating.equalsIgnoreCase(RATING.ADULT.toString())) {
			list=adult;
		}
		else {
			list=young;
		}
		while (!adjective.contains("#")) {
			adjective = getAdjectivePhrase(typeIndex, list);
		}
		noun = getNounPhrase(typeIndex, list);
		subject=getSubjectPhrase(typeIndex, list);
		intro = getIntro(typeIndex);
		noun = removeStars(noun);
		adjective = removeStars(adjective);
		output = subject + " " + adjective + " " + noun + " " + intro + ".";
		return output;
	}


	//returns the given string without hashes
	private String removeHashes (String input) {
		return input.replaceAll("#", "");
	}

	//returns the given String without stars
	private String removeStars (String input) {
		return input.replaceAll("@", "");
	}

	//gets a random subject phrase from the given list with the given type index
	private String getSubjectPhrase(int typeIndex, ArrayList<ArrayList<ArrayList<String>>> list) {
		int randomIndex = randInt(list.get(typeIndex).get(GRAMMAR.SUBJECT.getArrayIndex()).size());
		return list.get(typeIndex).get(GRAMMAR.SUBJECT.getArrayIndex()).get(randomIndex);
	}

	//gets a random adjective phrase from the given list with the given type index
	private String getAdjectivePhrase(int typeIndex, ArrayList<ArrayList<ArrayList<String>>> list) {
		int randomIndex = randInt(list.get(typeIndex).get(GRAMMAR.ADJECTIVE.getArrayIndex()).size());
		return list.get(typeIndex).get(GRAMMAR.ADJECTIVE.getArrayIndex()).get(randomIndex);
	}
	//gets a random Noun phrase from the given list with the given type index
	private String getNounPhrase(int typeIndex, ArrayList<ArrayList<ArrayList<String>>> list) {
		int randomIndex = randInt(list.get(typeIndex).get(GRAMMAR.NOUN.getArrayIndex()).size());
		return list.get(typeIndex).get(GRAMMAR.NOUN.getArrayIndex()).get(randomIndex);
	}
	
	//gets a random Location phrase from the given list with the given type index
	private String getLocationPhrase(int typeIndex,
			ArrayList<ArrayList<ArrayList<String>>> list) {
		int randomIndex = randInt(list.get(typeIndex).get(GRAMMAR.LOCATION.getArrayIndex()).size());
		return list.get(typeIndex).get(GRAMMAR.LOCATION.getArrayIndex()).get(randomIndex);
	}
	
	//generates a custom intro based on the typeIndex provided
	private String getIntro(int typeIndex) {
		if (typeIndex==TYPE.CLASSICAL.getArrayIndex() || typeIndex==TYPE.BRITISH.getArrayIndex()
				|| typeIndex==TYPE.HIPHOP.getArrayIndex()) {
			return "Thou art a ";
		}
		else if (typeIndex==TYPE.MAMA.getArrayIndex()){
			return "Yo Mama so ";
		}
		else {
			return "You're a ";
		}
	}

	//returns a random number between 0 and the maximum (minus 1)
	private int randInt(int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max));
		return randomNum;
	}

	public Mapping (InputStream inputStream) {
		setUpArrayLists();
		try {
			readFile(inputStream);
		} catch (FileNotFoundException e) {
			System.err.println("CANNOT FIND INSULT FILE");
		} catch (IOException e) {
			System.err.println(e.toString());
		}
	}

	//creates empty ArrayLists so that strings may be entered correctly
	private void setUpArrayLists() {
		int i,j;
		//adds the top layers of the first ArrayList.
		//these represent the 'types' of insults
		//which are also arrayList
		for (i=0;i<7;i++) {
			adult.add(new ArrayList<ArrayList<String>>());
			young.add(new ArrayList<ArrayList<String>>());
			//adds the second layer of ArrayLists.
			//these represent the grammar components
			//such as SUBJECT, ADJECTIVE, NOUN and LOCATION
			for (j=0;j<4;j++) {
				adult.get(i).add(new ArrayList<String>());
				young.get(i).add(new ArrayList<String>());
			}
		}
	}

	//converts text file into proper arrays using submethods
	private void readFile (InputStream inputStream) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
		String line;
		int currentType=-1;
		int currentGrammer = -1;
		while ((line =br.readLine()) != null) {
			if (isType(line)) {
				currentType=getTypeArray(line);
			}
			else if (isGrammer(line)) {
				currentGrammer=getGrammarArray(line);
			}
			else if (!isCommentOrEmpty(line)){
				Sort(line, currentType, currentGrammer);
			}
		}
		inputStream.close();
	}

	//returns true if the line has a comment flag (%)
	//or the line was NULL or empty
	private boolean isCommentOrEmpty(String input) {
		return input.startsWith("%") || input.equalsIgnoreCase("NULL")
				|| input.equalsIgnoreCase("");
	}


	//places the String in the proper ArrayList based on provided 
	//rating, type and grammar array index locations.
	//will split String based on comma separator
	private void Sort(String text,int type,int grammar) {
		String [] words = text.split(",");
		for (String word : words) {
			if (!isAdult(word)) {
				young.get(type).get(grammar).add(word);
			}
			adult.get(type).get(grammar).add(removeAdultFlag(word));
		}
	}

	//checks if the given String has the adult flag (! at the start)
	private boolean isAdult(String input) {
		return input.startsWith("!");
	}

	//returns a copy of the given string with the adult flag removed.
	//if there is no adult flag, will return the same string
	private String removeAdultFlag(String text) {
		if (isAdult(text))
			return text.substring(1);
		else
			return text;
	}

	//checks if the current String is a rating based on the RATING Enum.
	private boolean isRating (String input) {
		for (RATING t : RATING.values()) {
			if (input.equalsIgnoreCase(t.toString())) {
				return true;
			}
		}
		return false;
	}

	//checks if the current String is a type based on the TYPE Enum.
	private boolean isType (String input) {
		for (TYPE t : TYPE.values()) {
			if (input.equalsIgnoreCase(t.toString())) {
				return true;
			}
		}
		return false;
	}

	//converts the Enum value to it's respective array index
	private int getTypeArray(String input) {
		for (TYPE t : TYPE.values()) {
			if (input.equalsIgnoreCase(t.toString())) {
				return t.getArrayIndex();
			}
		}
		return -1;
	}

	//checks if the current String is grammar based on the GRAMMAR Enum.
	private boolean isGrammer (String input) {
		for (GRAMMAR t : GRAMMAR.values()) {
			if (input.equalsIgnoreCase(t.toString())) {
				return true;
			}
		}
		return false;
	}

	//converts the Enum value to it's respective array index
	private int getGrammarArray(String input) {
		for (GRAMMAR t : GRAMMAR.values()) {
			if (input.equalsIgnoreCase(t.toString())) {
				return t.getArrayIndex();
			}
		}
		return -1;
	}
}
