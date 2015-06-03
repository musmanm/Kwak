package com.example.muhammadmajeed.insultgenerator;

public enum TYPE{
	CLASSICAL(0),
	SCIFI(1),
	HIPHOP(2),
	NORMAL(3),
	BRITISH(4),
	SMART(5),
	MAMA(6);
	
	private final int arrayIndex;

    private TYPE(final int newValue) {
        arrayIndex = newValue;
    }

    public int getArrayIndex() {
    	return arrayIndex;
    }
}
