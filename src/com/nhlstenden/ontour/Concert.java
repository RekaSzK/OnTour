package com.nhlstenden.ontour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Concert
{
    private static final int DEFAULT_TICKET_PRICE_IN_EUROS = 50;
    private static final int MINIMUM_PRICE = 1;
    private Artist artist;
    private Venue venue;
    private LocalDate date;
    private List<Ticket> soldTickets;
    private int priceInEuro;

    public Concert(Artist artist, Venue venue, LocalDate date)
    {
        this.setArtist(artist);
        this.setVenue(venue);
        this.setDate(date);
        this.setSoldTickets(new ArrayList<>());
        this.setPriceInEuro(DEFAULT_TICKET_PRICE_IN_EUROS);
    }

    public Artist getArtist()
    {
        return this.artist;
    }

    public void setArtist(Artist artist)
    {
        if (artist == null)
        {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        this.artist = artist;
    }

    public Venue getVenue()
    {
        return this.venue;
    }

    public void setVenue(Venue venue)
    {
        if (venue == null)
        {
            throw new IllegalArgumentException("Venue cannot be null.");
        }

        this.venue = venue;
    }

    public LocalDate getDate()
    {
        return this.date;
    }

    public void setDate(LocalDate date)
    {
        if (date == null)
        {
            throw new IllegalArgumentException("Date cannot be null.");
        }

        this.date = date;
    }

    public List<Ticket> getSoldTickets()
    {
        return this.soldTickets;
    }

    public void setSoldTickets(List<Ticket> soldTickets)
    {
        if (soldTickets == null)
        {
            throw new IllegalArgumentException("Sold tickets cannot be null.");
        }

        this.soldTickets = soldTickets;
    }

    public int getPriceInEuro()
    {
        return this.priceInEuro;
    }

    public void setPriceInEuro(int priceInEuro)
    {
        if (priceInEuro <= MINIMUM_PRICE)
        {
            throw new IllegalArgumentException("Ticket price must be above or equal to minimum price.");
        }

        this.priceInEuro = priceInEuro;
    }

    public boolean hasOccurred()
    {
        return (this.getDate().isBefore(LocalDate.now()));
    }

    public void addTicket(Ticket ticket)
    {
        if (ticket == null)
        {
            throw new IllegalArgumentException("Ticket cannot be null.");
        }

        this.getSoldTickets().add(ticket);
    }

    public void removeTicket(Ticket ticket)
    {
        if (ticket == null)
        {
            throw new IllegalArgumentException("Ticket cannot be null.");
        }

        this.getSoldTickets().remove(ticket);
    }

    public int getAmountOfTickets()
    {
        return (this.getVenue().getMaxCapacity());
    }

    public int getRevenueInEuro()
    {
        return (this.getSoldTickets().size() * this.getPriceInEuro());
    }

    public boolean isSoldOut()
    {
        return (this.getAmountOfTickets() == this.getSoldTickets().size());
    }
}
