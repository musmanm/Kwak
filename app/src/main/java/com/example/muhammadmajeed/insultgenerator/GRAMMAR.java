package com.example.muhammadmajeed.insultgenerator;

public enum GRAMMAR {
	SUBJECT(0),
	ADJECTIVE(1),
	NOUN(2),
    LOCATION(3);
	
	private final int arrayIndex;

    private GRAMMAR(final int newValue) {
        arrayIndex = newValue;
    }

    public int getArrayIndex() {
    	return arrayIndex;
    }
}
