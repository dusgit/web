package com.yinrun.controller;

public class Test
{
    public static void main(String[] args)
    {
        for (int i = 0; i < 100; i++)
        {
            Double a = Math.random() * 100;
            Double b = Math.random() * 100;
            System.out.println((a.intValue() + b.intValue()) / 2);
        }
    }
}
