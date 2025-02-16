package org.project.strategies;

import org.project.model.Bid;
import org.project.model.Member;

import java.util.List;

public interface BidWinningStrategy {
    Member decideWinner(List<Bid> bids);

}
