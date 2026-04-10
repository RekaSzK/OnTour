package com.nhlstenden.ontour;

public class Ticket
{
    private String code;

    public Ticket(Concert concert)
    {
        this.setCode(concert);
    }

    public String getCode()
    {
        return this.code;
    }

    public void setCode(Concert concert)
    {
        this.code = (concert.getArtist().getName() + " - " + concert.getVenue().getName() + " - " + concert.getDate());
    }
}
