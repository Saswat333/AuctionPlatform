package org.project.model;

import java.util.Date;
import java.util.List;

public class Bid {
    private int memberId;
    private int bidAmount;
    private int eventId;

    private Date bidDate;

    public Bid(int memberId, int eventId, int bidAmount) {
        this.memberId = memberId;
        this.eventId = eventId;
        this.bidAmount = bidAmount;
    }

    public int getMemberId() {

        return memberId;
    }

    public int getBidAmount() {
        return bidAmount;
    }

    public int getEventId() {
        return eventId;
    }

}
