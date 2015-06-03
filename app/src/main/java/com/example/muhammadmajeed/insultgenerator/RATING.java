package com.example.muhammadmajeed.insultgenerator;

public enum RATING {
	PG(0),
	ADULT(1);
	
	private final int arrayIndex;

    private RATING(final int newValue) {
        arrayIndex = newValue;
    }

    public int getArrayIndex() {
    	return arrayIndex;
    }
}
