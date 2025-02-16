package org.project.exceptions;

public class AuctionAlreadyExist extends Exception {
    public AuctionAlreadyExist(String auction, String auctionName) {
        super(auction + ": " + auctionName + " already exists.");
    }
}
