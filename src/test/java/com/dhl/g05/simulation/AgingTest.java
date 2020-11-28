package com.dhl.g05.simulation;
//package com.dhl.g05.gameplayconfig;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertSame;
//import static org.junit.Assert.assertTrue;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//public class AgingTest {
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
//    public void getAverageRetirementAgeTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setAverageRetirementAge(37);
//        assertSame(aging.getAverageRetirementAge(),37);
//    }
//
//    @Test
//    public void setAverageRetirementAgeTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setAverageRetirementAge(37);
//        assertSame(aging.getAverageRetirementAge(),37);
//    }
//
//    @Test
//    public void getMaximumAgeTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setMaximumAge(50);
//        assertSame(aging.getMaximumAge(),50);
//    }
//
//    @Test
//    public void setMaximumAgeTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setMaximumAge(50);
//        assertSame(aging.getMaximumAge(),50);
//    }
//
//    @Test
//    public void setStatDecayChanceTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setStatDecayChance(0.05);
//        assertEquals(aging.getStatDecayChance(),0.05,0);
//    }
//
//    @Test
//    public void getStatDecayChanceTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setStatDecayChance(0.01);
//        assertEquals(aging.getStatDecayChance(),0.01,0);
//    }
//
//    @Test
//    public void isStatDecayChanceValidTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setStatDecayChance(0.05);
//        assertFalse(aging.isStatDecayChanceNotValid(aging.getStatDecayChance()));
//    }
//
//    @Test
//    public void isStatDecayChanceNotValidTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setStatDecayChance(-0.05);
//        assertTrue(aging.isStatDecayChanceNotValid(aging.getStatDecayChance()));
//    }
//
//    @Test
//    public void isAverageRetirementAgeValidTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setAverageRetirementAge(50);
//        assertFalse(aging.isAverageRetirementAgeNotValid(aging.getAverageRetirementAge()));
//    }
//
//    @Test
//    public void isAverageRetirementAgeNotValidTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setAverageRetirementAge(0);
//        assertTrue(aging.isAverageRetirementAgeNotValid(aging.getAverageRetirementAge()));
//    }
//
//    @Test
//    public void isMaximumAgeValidTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setMaximumAge(40);
//        assertFalse(aging.isMaximumAgeNotValid(aging.getMaximumAge()));
//    }
//
//    @Test
//    public void isMaximumAgeNotValidTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setMaximumAge(0);
//        assertTrue(aging.isMaximumAgeNotValid(aging.getMaximumAge()));
//    }
//
//    @Test
//    public void maximumAgeValidateTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setMaximumAge(-1);
//        assertSame(AgingConstant.MaximumAgeNotValid,aging.validate());
//    }
//
//    @Test
//    public void retirementAgeValidateTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setMaximumAge(50);
//        aging.setAverageRetirementAge(-1);
//        assertSame(AgingConstant.AverageRetirementAgeNotValid,aging.validate());
//    }
//
//    @Test
//    public void statDecayChanceValidateTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setMaximumAge(50);
//        aging.setAverageRetirementAge(35);
//        aging.setStatDecayChance(0);
//        assertSame(AgingConstant.StatDecayChanceNotValid,aging.validate());
//    }
//
//	@Test
//	public void validateTest() {
//        IAging aging = gamePlayConfigFactory.getAging();
//        aging.setMaximumAge(50);
//        aging.setAverageRetirementAge(35);
//        aging.setStatDecayChance(0.1);
//        assertSame(AgingConstant.Success,aging.validate());
//	}
//}
