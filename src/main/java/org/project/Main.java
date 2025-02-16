package org.project;

import org.project.exceptions.*;
import org.project.model.Auction;
import org.project.model.Bid;
import org.project.model.Member;
import org.project.service.AuctionService;
import org.project.service.BidService;
import org.project.service.MemberService;
import org.project.strategies.BidWinningStrategy;
import org.project.strategies.HighestBidWinStrategy;

public class Main {
    public static void main(String[] args)  {
        MemberService memberService = new MemberService();
        AuctionService auctionService = new AuctionService(memberService);
        BidWinningStrategy bidWinningStrategy = new HighestBidWinStrategy(memberService);
        BidService bidService = new BidService(memberService, auctionService, bidWinningStrategy);

        try {
            Member alice = memberService.addMember("Alice", 50);
            Member bob = memberService.addMember("Bob", 30);
            Member charlie = memberService.addMember("Charlie", 40);
            System.out.println("------------------------");

            Auction bbdEvent = null;
            try {
                bbdEvent = auctionService.createAuction("bbd", "IPHONE-14", 10, 25);
            } catch (AuctionAlreadyExist e) {
                System.out.println(e.toString());
            }
            try {
                auctionService.registerMember(alice.getId(), bbdEvent.getId());
                auctionService.registerMember(bob.getId(), bbdEvent.getId());
                auctionService.registerMember(charlie.getId(), bbdEvent.getId());
            } catch (EntityDoesNotExistException e) {
                System.out.println(e.toString());
            }

            System.out.println("------------------------------------");

            try {
                Bid bid1 = bidService.placeBid(alice.getId(), bbdEvent.getId(),20);
            } catch (InvalidBidException | EntityDoesNotExistException | MemberNotRegisteredException |
                     InsufficientSuperCoinException e) {
                System.out.println(e.toString());
            }
            try {
                Bid bid2 = bidService.placeBid(bob.getId(), bbdEvent.getId(), 20);
            } catch (InvalidBidException | EntityDoesNotExistException | MemberNotRegisteredException |
                     InsufficientSuperCoinException e) {
                System.out.println(e.toString());
            }
            try {
                Bid bid3 = bidService.placeBid(charlie.getId(), bbdEvent.getId(), 10);
            } catch (InvalidBidException | EntityDoesNotExistException | MemberNotRegisteredException |
                     InsufficientSuperCoinException e) {
                System.out.println(e.toString());
            }

            System.out.println("--------------------");
            //close and declare winner
            Member winner = bidService.declareWinner(bbdEvent.getId());
            System.out.println("winner = " + winner);
            System.out.println("-------------------------");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


        System.out.println("=====================================");

        try {
            Member alice = memberService.addMember("Alice", 50);
            Member bob = memberService.addMember("Bob", 30);
            Member charlie = memberService.addMember("Charlie", 40);
            System.out.println("------------------------");

            Auction bbdEvent = null;
            try {
                bbdEvent = auctionService.createAuction("bbd", "IPHONE-14", 10, 25);
            } catch (AuctionAlreadyExist e) {
                System.out.println(e.toString());
            }
            try {
                auctionService.registerMember(alice.getId(), bbdEvent.getId());
                auctionService.registerMember(bob.getId(), bbdEvent.getId());
                auctionService.registerMember(charlie.getId(), bbdEvent.getId());
            } catch (EntityDoesNotExistException e) {
                System.out.println(e.toString());
            }

            System.out.println("------------------------------------");

            try {
                Bid bid1 = bidService.placeBid(alice.getId(), bbdEvent.getId(),20);
            } catch (InvalidBidException | EntityDoesNotExistException | MemberNotRegisteredException |
                     InsufficientSuperCoinException e) {
                System.out.println(e.toString());
            }
            try {
                Bid bid2 = bidService.placeBid(bob.getId(), bbdEvent.getId(), 20);
            } catch (InvalidBidException | EntityDoesNotExistException | MemberNotRegisteredException |
                     InsufficientSuperCoinException e) {
                System.out.println(e.toString());
            }
            try {
                Bid bid3 = bidService.placeBid(charlie.getId(), bbdEvent.getId(), 10);
            } catch (InvalidBidException | EntityDoesNotExistException | MemberNotRegisteredException |
                     InsufficientSuperCoinException e) {
                System.out.println(e.toString());
            }

            System.out.println("--------------------");
            //close and declare winner
            Member winner = bidService.declareWinner(bbdEvent.getId());
            System.out.println("winner = " + winner);
            System.out.println("-------------------------");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}