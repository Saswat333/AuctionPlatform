package org.project.service;

import org.project.exceptions.EntityDoesNotExistException;
import org.project.exceptions.InsufficientSuperCoinException;
import org.project.exceptions.InvalidBidException;
import org.project.exceptions.MemberNotRegisteredException;
import org.project.model.Auction;
import org.project.model.Bid;
import org.project.model.Member;
import org.project.strategies.BidWinningStrategy;

import java.util.*;

public class BidService {
    private final Map<Integer, List<Bid>> eventIdToBidsMap; //auction_id, lis to bids for the auction
    private final MemberService memberService;
    private final AuctionService auctionService;
    private final BidWinningStrategy bidWinningStrategy;
    private final Map<Member, Auction> leaderboard;

    public BidService(MemberService memberService, AuctionService auctionService, BidWinningStrategy bidWinningStrategy) {

        this.memberService = memberService;
        this.auctionService = auctionService;
        this.bidWinningStrategy = bidWinningStrategy;
        this.leaderboard = new HashMap<>(); // to keep who has already participated and give priorioty
        this.eventIdToBidsMap = new HashMap<>();
    }

    public Bid placeBid(int memberId, int auctionId, int bidAmount) throws InvalidBidException, EntityDoesNotExistException, MemberNotRegisteredException, InsufficientSuperCoinException {
        Auction auction = auctionService.getAuctionById(auctionId);
//        boolean exceptionRaised = false;
        if(bidAmount<auction.getLowBidVal() || bidAmount > auction.getHighBidVal()){
            throw new InvalidBidException(auction.getLowBidVal().toString(), auction.getHighBidVal().toString());
        }
        Member member = this.memberService.getMemberById(memberId);
        if (member == null) {
            throw new EntityDoesNotExistException("member", String.valueOf(memberId));
        }

        if (!auctionService.isMemberRegisteredToAuction(auctionId, memberId)) {
            throw new MemberNotRegisteredException(String.valueOf(auctionId), String.valueOf(memberId));
        }


        if (member.getSuperCoins() < bidAmount) {
            throw new InsufficientSuperCoinException(String.valueOf(memberId), String.valueOf(bidAmount));
        }

        if (auction == null) {
            throw new EntityDoesNotExistException("event", String.valueOf(auctionId));
        }

        Bid bid = new Bid(memberId, auctionId, bidAmount);
        eventIdToBidsMap.putIfAbsent(auctionId, new LinkedList<>());
        eventIdToBidsMap.get(auctionId).add(bid);

        System.out.println("bids submitted successfully");

        return bid;
    }

    public List<Bid> getBidsByEventId(int eventId) {
        return eventIdToBidsMap.get(eventId);
    }

    public Member declareWinner(int eventId) {
        Auction auction = auctionService.getAuctionById(eventId);
        List<Bid> bids = this.getBidsByEventId(eventId);
        //synchronise
        //validation
        Member winner = bidWinningStrategy.decideWinner(bids);

        auction.setWinner(winner);
        //priority winner
        leaderboard.put(auction.getWinner(), auction);
        return winner;
    }


}
