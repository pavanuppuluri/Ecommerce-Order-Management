package com.techtalks.ordermanagement.exceptions;

public class OrderNotFoundException extends RuntimeException
{
    public OrderNotFoundException(String exception)
    {
        super(exception);
    }
}
