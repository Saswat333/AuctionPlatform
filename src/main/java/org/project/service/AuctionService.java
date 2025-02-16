package org.project.service;

import org.project.exceptions.AuctionAlreadyExist;
import org.project.exceptions.EntityDoesNotExistException;
import org.project.model.Auction;
import org.project.model.Member;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class AuctionService {
    private final Map<Integer, Auction> auctionMap;
    private final Set<String> auctionNameSet;
    private AtomicInteger numAuction = new AtomicInteger(0);

    private final MemberService memberService;

    public AuctionService(MemberService memberService) {
        this.auctionMap = new HashMap<>();
        this.auctionNameSet = new HashSet<>();
        this.memberService = memberService;
    }

    public Auction createAuction(String auctionName, String prizeName, Integer lowBidVal, Integer highBidVal) throws AuctionAlreadyExist {
        int numVal = numAuction.incrementAndGet();
        if(auctionNameSet.contains(auctionName)){
            throw new AuctionAlreadyExist("Auction", auctionName);
        }
        Auction auction = new Auction(numVal, auctionName, prizeName, lowBidVal, highBidVal);
        auctionMap.put(numVal, auction);
        return auction;
    }

    public void registerMember(int memberId, int auctionId) throws EntityDoesNotExistException {
        Member member = memberService.getMemberById(memberId);
        if(member == null){
            throw new EntityDoesNotExistException("Member", String.valueOf(memberId));
        }
        Auction auction = this.auctionMap.get(auctionId);
        if(auction == null){
            throw new EntityDoesNotExistException("Auction", String.valueOf(auctionId));
        }

        auction.addMember(member);

        System.out.println(member.getName() +" registered to "+ auction.getAuctionName()+" successfully");
    }

    public Auction getAuctionById(int eventId) {

        return auctionMap.get(eventId);
    }

    public boolean isMemberRegisteredToAuction(int auctionId, int memberId) {
        Auction auction = auctionMap.get(auctionId);
        for (Member member : auction.getMembers()) {
            if (member.getId() == memberId) {
                return true;
            }
        }
        return false;
    }

    // withdraw:
}
