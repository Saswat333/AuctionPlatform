package org.project.exceptions;

public class InsufficientSuperCoinException extends Exception{
    public InsufficientSuperCoinException(String memberId, String amount) {
        super("member: " + memberId + " does not have " + amount + " supercoins.");
    }
}
