//package com.dhl.g05.gameplayconfig;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertSame;
//import static org.junit.Assert.assertTrue;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class InjuryTest {
//
//    private static AbstractGamePlayConfigFactory gamePlayConfigFactory;
//
//    @BeforeClass
//    public static void setup() {
//        AbstractGamePlayConfigFactory.setFactory(new GamePlayConfigFactory());
//        gamePlayConfigFactory = AbstractGamePlayConfigFactory.getFactory();
//    }
//
//    @Test
//    public void getRandomInjuryChanceTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setRandomInjuryChance(0.05);
//        assertEquals(injury.getRandomInjuryChance(),0.05,0);
//    }
//
//    @Test
//    public void setRandomInjuryChanceTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setRandomInjuryChance(0.05);
//        assertEquals(injury.getRandomInjuryChance(),0.05,0);
//    }
//
//    @Test
//    public void getInjuryDaysLowTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setInjuryDaysLow(1);
//        assertSame(injury.getInjuryDaysLow(),1);
//    }
//
//    @Test
//    public void setInjuryDaysLowTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setInjuryDaysLow(1);
//        assertSame(injury.getInjuryDaysLow(),1);
//    }
//
//    @Test
//    public void getInjuryDaysHighTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setInjuryDaysHigh(15);
//        assertSame(injury.getInjuryDaysHigh(),15);
//    }
//
//    @Test
//    public void setInjuryDaysHighTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setInjuryDaysHigh(16);
//        assertSame(injury.getInjuryDaysHigh(),16);
//    }
//
//    @Test
//    public void isRandomInjuryChanceValidTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setRandomInjuryChance(1.5);
//        assertFalse(injury.isRandomInjuryChanceNotValid(injury.getRandomInjuryChance()));
//    }
//
//    @Test
//    public void isRandomInjuryChanceNotValidTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setRandomInjuryChance(-5);
//        assertTrue(injury.isRandomInjuryChanceNotValid(injury.getRandomInjuryChance()));
//    }
//
//    @Test
//    public void isInjuryDaysHighValueValidTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setInjuryDaysHigh(60);
//        injury.setInjuryDaysLow(50);
//        assertFalse(injury.isInjuryDaysHighValueNotValid(injury.getInjuryDaysLow(), injury.getInjuryDaysHigh()));
//    }
//
//    @Test
//    public void isInjuryDaysHighValueNotValidTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setInjuryDaysHigh(60);
//        injury.setInjuryDaysLow(70);
//        assertTrue(injury.isInjuryDaysHighValueNotValid(injury.getInjuryDaysLow(), injury.getInjuryDaysHigh()));
//    }
//
//    @Test
//    public void randomInjuryChanceValidateTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setRandomInjuryChance(-5);
//        assertSame(InjuryConstant.RandomInjuryChanceError, injury.validate());
//    }
//
//    @Test
//    public void injuryDaysValidateTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setRandomInjuryChance(0.05);
//        injury.setInjuryDaysLow(55);
//        injury.setInjuryDaysHigh(50);
//        assertSame(InjuryConstant.InjuryDaysError, injury.validate());
//    }
//
//    @Test
//    public void validateTest() {
//        IInjury injury = gamePlayConfigFactory.getInjury();
//        injury.setInjuryDaysLow(10);
//        injury.setInjuryDaysHigh(25);
//        injury.setRandomInjuryChance(0.1);
//        assertSame(InjuryConstant.Success, injury.validate());
//    }
//}
