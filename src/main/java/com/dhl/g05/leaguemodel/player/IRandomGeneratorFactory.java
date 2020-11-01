package com.dhl.g05.leaguemodel.player;

public interface IRandomGeneratorFactory {
    int getRandomIntegerNumber(int minimum, int maximum);
    double getRandomDoubleNumber(int minimum, int maximum);
    double roundDoubleNumber(double number, int decimalPlaces);
}

