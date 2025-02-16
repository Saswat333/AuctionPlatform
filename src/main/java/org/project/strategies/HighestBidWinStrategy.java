package org.project.strategies;

import org.project.model.Bid;
import org.project.model.Member;
import org.project.service.MemberService;

import java.util.*;

public class HighestBidWinStrategy implements BidWinningStrategy {
    private final MemberService memberService;

    public HighestBidWinStrategy(MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public Member decideWinner(List<Bid> bids) {
        //check if the supercoin are in limit
        int winningBidAmount = Integer.MIN_VALUE;
        int winnerId = -1;
        Bid winner = null;

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (Bid bid : bids) {
            int num = bid.getBidAmount();
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Bid> maxHeap = new PriorityQueue<>(new Comparator<Bid>() {
            @Override
            public int compare(Bid o1, Bid o2) {
                return Integer.compare(o2.getBidAmount(), o1.getBidAmount());
            }
        });
        //  Add numbers to the priority queue (only unique numbers)
        for (Bid bid : bids) {
            int num = bid.getBidAmount();
            if (frequencyMap.get(num) == 1) {
                maxHeap.add(bid);
            }
        }
        //can handle if there is no winner
        if (!maxHeap.isEmpty()) {
            winner = maxHeap.poll();
            winnerId = winner.getMemberId();
        }

        // remove the amount of supercoins

        if(winnerId != -1) {
            int winningAmount = winner.getBidAmount();
            Member winningMember = memberService.getMemberById(winner.getMemberId());
            int balance = memberService.getMemberById(winner.getMemberId()).getSuperCoins() - winningAmount;
            winningMember.setSuperCoins(balance);
            return memberService.getMemberById(winnerId);
        }
        return null;
    }
}
