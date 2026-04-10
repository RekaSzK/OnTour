package com.nhlstenden.ontour;

import java.time.LocalDate;
import java.util.List;

public class TicketService
{
    private TourManager tourManager;

    public TicketService(TourManager tourManager)
    {
        this.tourManager = tourManager;
    }

    public TourManager getTourManager()
    {
        return this.tourManager;
    }

    public void setTourManager(TourManager tourManager)
    {
        if (tourManager == null)
        {
            throw new IllegalArgumentException("Tour manager cannot be null.");
        }

        this.tourManager = tourManager;
    }

    public void sellTickets(String artistName, String venueName, LocalDate date, int amountOfTickets)
    {
        if (artistName == null || artistName.isBlank() || venueName == null || venueName.isBlank() || date == null || amountOfTickets < 1)
        {
            throw new IllegalArgumentException("Artist name, venue name and date cannot be null or blank, amount of tickets cannot be negative or zero.");
        }

        Concert concertNewTicketsSold = this.getTourManager().getConcert(artistName, venueName, date);

        if (this.isExisting(concertNewTicketsSold))
        {
            if (concertNewTicketsSold.hasOccurred() || !(this.isSoldOut(amountOfTickets, concertNewTicketsSold)))
            {
                for (int i = 0; i < amountOfTickets; i++)
                {
                    Ticket newTicketSold = new Ticket(concertNewTicketsSold);
                    concertNewTicketsSold.addTicket(newTicketSold);
                }
            }
            else
            {
                throw new IllegalArgumentException("Concert has already happened or tickets sold would exceed the maximum capacity of the venue.");
            }
        }
        else
        {
            throw new IllegalArgumentException("Concert does not exist.");
        }
    }

    public int getTotalRevenueInEuro(String artistName)
    {
        Artist artist = this.getTourManager().getArtistByName(artistName);

        List<Concert> concerts = this.getTourManager().getConcertsByArtist(this.getTourManager().getConcerts(), artist);

        int totalRevenueInEuro = 0;

        for (Concert concert : concerts)
        {
            totalRevenueInEuro += concert.getRevenueInEuro();
        }

        return totalRevenueInEuro;
    }

    public boolean isSoldOut(int amountOfTickets, Concert concert)
    {
        return (concert.getSoldTickets().size() + amountOfTickets >= concert.getAmountOfTickets());
    }

    private void reserveTickets(int amountOfTickets, Concert concert)
    {

    }

    private boolean isExisting(Concert concert)
    {
        return (this.getTourManager().getConcerts().contains(concert));
    }
}
