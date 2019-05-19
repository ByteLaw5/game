package com.game.util.math;

public class MathUtils
{
    private MathUtils() {};

    public static int floor(float a)
    {
        int b = (int) a;
        return b < a ? b : b - 1;
    }

    public static int floor(double a)
    {
        int b = (int) a;
        return b < a ? b : b - 1;
    }

    public static int abs(int a)
    {
        return a < 0 ? -a : a;
    }
}
