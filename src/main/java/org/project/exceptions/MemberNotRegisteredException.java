package org.project.exceptions;

public class MemberNotRegisteredException extends Exception{
    public MemberNotRegisteredException(String auctionId, String memberId) {

        super(memberId+"Member not registered to auction "+auctionId);
    }
}
