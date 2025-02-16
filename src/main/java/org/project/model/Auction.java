package org.project.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Auction {
    private int id;
    private String auctionName;
    private String prizeName;
    private Date auctionDate;

    private List<Member> members;
    private Member winner;
    private Integer lowBidVal;
    private Integer highBidVal;

    public Auction(int id, String auctionName, String prizeName, Integer lowBidVal, Integer highBidVal ) {
        this.id = id;
        this.auctionName = auctionName;
        this.prizeName = prizeName;
//        this.auctionDate = auctionDate;

        this.members = new LinkedList<>();
        this.winner= null;

        this.lowBidVal =lowBidVal;
        this.highBidVal = highBidVal;
    }


    public int getId() {
        return id;
    }

    public Integer getLowBidVal() {
        return lowBidVal;
    }

    public Integer getHighBidVal() {
        return highBidVal;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public List<Member> getMembers() {
        return members;
    }

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void addMember(Member member){

        members.add(member);
    }

    public void setWinner(Member winner) {
        this.winner = winner;
    }

    public Member getWinner(){
        return winner;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id=" + id +
                ", auctionName='" + auctionName + '\'' +
                ", prizeName='" + prizeName + '\'' +
                ", auctionDate=" + auctionDate +
                ", members=" + members +
                ", winner=" + winner +
                '}';
    }
}
