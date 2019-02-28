package com.agileengine.test.htmlcrawler.exception;

public class ElementNotFoundException extends RuntimeException {
    public ElementNotFoundException(String elementDescription) {
        super(String.format("Cannot find element (%s)", elementDescription));
    }
}
