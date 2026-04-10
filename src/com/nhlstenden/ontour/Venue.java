package com.nhlstenden.ontour;

public class Venue
{
    private static final int MINIMUM_CAPACITY = 0;
    private String name;
    private String city;
    private int maxCapacity;

    public Venue(String name, String city, int maxCapacity)
    {
        this.setName(name);
        this.setCity(city);
        this.setMaxCapacity(maxCapacity);
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

    public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        if (city == null || city.isBlank())
        {
            throw new IllegalArgumentException("City cannot be null or blank.");
        }

        this.city = city;
    }

    public int getMaxCapacity()
    {
        return this.maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity)
    {
        if (maxCapacity <= MINIMUM_CAPACITY)
        {
            throw new IllegalArgumentException("Maximum capacity cannot be less than or equal to the minimum capacityS.");
        }

        this.maxCapacity = maxCapacity;
    }
}
