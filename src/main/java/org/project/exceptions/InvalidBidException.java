package org.project.exceptions;

public class InvalidBidException extends Exception{
    public InvalidBidException(String lowVal, String highVal) {

        super("Bid value should be between " +lowVal +" and "+highVal+" .");
    }
}
