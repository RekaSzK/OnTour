package com.nhlstenden.ontour;

public class Artist
{
    private String name;
    private String genre;

    public Artist(String name, String genre)
    {
        this.setName(name);
        this.setGenre(genre);
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

    public String getGenre()
    {
        return this.genre;
    }

    public void setGenre(String genre)
    {
        if (genre == null || genre.isBlank())
        {
            throw new IllegalArgumentException("Genre cannot be null or blank.");
        }

        this.genre = genre;
    }
}
