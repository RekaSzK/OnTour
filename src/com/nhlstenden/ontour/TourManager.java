package com.nhlstenden.ontour;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TourManager
{
    private String name;
    private int maxAmountOfArtists;
    private List<Artist> artists;
    private List<Venue> venues;
    private List<Concert> concerts;

    public TourManager(String name, int maxAmountOfArtists)
    {
        this.setName(name);
        this.setMaxAmountOfArtists(maxAmountOfArtists);
        this.setArtists(new ArrayList<>());
        this.setVenues(new ArrayList<>());
        this.setConcerts(new ArrayList<>());
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }

        this.name = name;
    }

    public int getMaxAmountOfArtists()
    {
        return this.maxAmountOfArtists;
    }

    public void setMaxAmountOfArtists(int maxAmountOfArtists)
    {
        if (maxAmountOfArtists < 0)
        {
            throw new IllegalArgumentException("Maximum amount of artists cannot be negative.");
        }

        this.maxAmountOfArtists = maxAmountOfArtists;
    }

    public List<Artist> getArtists()
    {
        return this.artists;
    }

    public void setArtists(List<Artist> artists)
    {
        if (artists == null)
        {
            throw new IllegalArgumentException("Artists cannot be null.");
        }

        this.artists = artists;
    }

    public List<Venue> getVenues()
    {
        return this.venues;
    }

    public void setVenues(List<Venue> venues)
    {
        if (venues == null)
        {
            throw new IllegalArgumentException("Venues cannot be null.");
        }

        this.venues = venues;
    }

    public List<Concert> getConcerts()
    {
        return this.concerts;
    }

    public void setConcerts(List<Concert> concerts)
    {
        if (concerts == null)
        {
            throw new IllegalArgumentException("Concerts cannot be null.");
        }

        this.concerts = concerts;
    }

    public void addArtist(Artist artist)
    {
        if (artist == null)
        {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        if (this.getArtists().size() == this.getMaxAmountOfArtists())
        {
            throw new IllegalArgumentException("Maximum amount of artists reached.");
        }

        this.getArtists().add(artist);
    }

    public void addVenue(Venue venue)
    {
        if (venue == null)
        {
            throw new IllegalArgumentException("Venue cannot be null.");
        }

        this.getVenues().add(venue);
    }

    public void addConcert(Concert concert)
    {
        if (concert == null)
        {
            throw new IllegalArgumentException("Concert cannot be null.");
        }

        this.getConcerts().add(concert);
    }

    public Concert getConcert(Artist artist, Venue venue, LocalDate date)
    {
        if (!this.getConcertsByDate(this.getConcertsByVenue(this.getConcertsByArtist(this.getConcerts(), artist), venue), date).isEmpty())
        {
            return (this.getConcertsByDate(this.getConcertsByVenue(this.getConcertsByArtist(this.getConcerts(), artist), venue), date).getFirst());
        }

        return null;
    }

    public Concert getConcert(String artistName, String venueName, LocalDate date)
    {
        if (artistName == null || artistName.isBlank() || venueName == null || venueName.isBlank() || date == null)
        {
            throw new IllegalArgumentException("Artist, venue and date cannot be null or blank.");
        }

        for (Concert concert : this.getConcerts())
        {
            if (concert.getArtist().getName().equals(artistName) && concert.getVenue().getName().equals(venueName) && concert.getDate().equals(date))
            {
                return concert;
            }
        }

        return null;
    }

    public List<Concert> getConcertsByArtist(List<Concert> concerts, Artist artist)
    {
        if (concerts == null || artist == null)
        {
            throw new IllegalArgumentException("Concerts and artist cannot be null.");
        }

        List<Concert> concertsByArtist = new ArrayList<>();

        for (Concert concert : concerts)
        {
            if (concert.getArtist().equals(artist))
            {
                concertsByArtist.add(concert);
            }
        }

        return concertsByArtist;
    }

    private List<Concert> getConcertsByDate(List<Concert> concerts, LocalDate date)
    {
        if (concerts == null || date == null)
        {
            throw new IllegalArgumentException("Concerts and date cannot be null.");
        }

        List<Concert> concertsByDate = new ArrayList<>();

        for (Concert concert : concerts)
        {
            if (concert.getDate().equals(date))
            {
                concertsByDate.add(concert);
            }
        }

        return concertsByDate;
    }

    private List<Concert> getConcertsByVenue(List<Concert> concerts, Venue venue)
    {
        if (concerts == null || venue == null)
        {
            throw new IllegalArgumentException("Concerts and venue cannot be null.");
        }

        List<Concert> concertsByVenue = new ArrayList<>();

        for (Concert concert : concerts)
        {
            if (concert.getVenue().equals(venue))
            {
                concertsByVenue.add(concert);
            }
        }

        return concertsByVenue;
    }

    public Artist getArtistByName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }

        for (Artist artist : this.getArtists())
        {
            if (artist.getName().equals(name))
            {
                return artist;
            }
        }

        return null;
    }

    public Venue getVenueByName(String name)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Name cannot be null or blank.");
        }

        for (Venue venue : this.getVenues())
        {
            if (venue.getName().equals(name))
            {
                return venue;
            }
        }

        return null;
    }

    public void scheduleConcert(String artistName, String venueName, LocalDate date)
    {
        if (artistName.isBlank() || venueName.isBlank() || date == null)
        {
            throw new IllegalArgumentException("Artist name, venue name and date cannot be null or blank.");
        }

        if (this.getArtistByName(artistName) == null || this.getVenueByName(venueName) == null)
        {
            throw new IllegalArgumentException("Artist or venue does not exist in Tour Manager.");
        }

        Concert newConcert = new Concert(this.getArtistByName(artistName), this.getVenueByName(venueName), date);

        this.getConcerts().add(newConcert);
    }

    private boolean hasShowOnDate(Artist artist, LocalDate date)
    {
        if (artist == null || date == null)
        {
            throw new IllegalArgumentException("Artist and date cannot be null.");
        }

        for (Concert concert : this.getConcerts())
        {
            if (concert.getArtist().equals(artist) && concert.getDate().equals(date))
            {
                return true;
            }
        }

        return false;
    }

    private List<Concert> getConcertsInFuture(List<Concert> concerts)
    {
        if (concerts == null || concerts.isEmpty())
        {
            throw new IllegalArgumentException("Concerts cannot be null or empty.");
        }

        List<Concert> concertsInFuture = new ArrayList<>();

        for (Concert concert : concerts)
        {
            if (concert.getDate().isAfter(LocalDate.now()))
            {
                concertsInFuture.add(concert);
            }
        }

        return concertsInFuture;
    }

    private List<Concert> getConcertsWithZeroTicketsSold(List<Concert> concerts)
    {
        if (concerts == null || concerts.isEmpty())
        {
            throw new IllegalArgumentException("Concerts cannot be null or empty.");
        }

        List<Concert> concertsWithZeroTicketsSold = new ArrayList<>();

        for (Concert concert : concerts)
        {
            if (concert.getSoldTickets().isEmpty())
            {
                concertsWithZeroTicketsSold.add(concert);
            }
        }

        return concertsWithZeroTicketsSold;
    }

    public int getTotalRevenueInEuros(String artistName)
    {
        Artist artist = this.getArtistByName(artistName);

        int totalRevenueInEuros = 0;

        for (Concert concert : this.getConcertsByArtist(this.getConcerts(), artist))
        {
            totalRevenueInEuros += concert.getRevenueInEuro();
        }

        return totalRevenueInEuros;
    }
}
