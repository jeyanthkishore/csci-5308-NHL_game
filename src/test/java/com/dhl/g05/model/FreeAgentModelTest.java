package com.dhl.g05.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.dhl.g05.ApplicationConfiguration;
import com.dhl.g05.simulation.SimulationAbstractFactory;
import com.dhl.g05.simulation.statemachine.IAging;

public class FreeAgentModelTest {
	
	private static SimulationAbstractFactory simulationAbstractFactory;
    private static ModelAbstractFactory modelAbstractFactory;

    @BeforeClass
    public static void init() {
        simulationAbstractFactory = ApplicationConfiguration.instance().getSimulationConcreteFactoryState();
        modelAbstractFactory = ApplicationConfiguration.instance().getModelConcreteFactoryState();
    }

	@Test
	public void constructorTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		assertNull(freeAgent.getPlayerName());
		assertNull(freeAgent.getPosition());
		assertFalse(freeAgent.getInjuryStatus());
		assertFalse(freeAgent.getRetirementStatus());
	}

	@Test
	public void parameterConstructorTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel("Alex", "Defense", 10, 11, 12, 13, 14, 11, 2000);
		assertSame("Alex", freeAgent.getPlayerName());
		assertSame("Defense", freeAgent.getPosition());
		assertEquals(10, freeAgent.getSkating(),0);
		assertEquals(11, freeAgent.getShooting(),0);
		assertEquals(12, freeAgent.getChecking(),0);
		assertEquals(13, freeAgent.getSaving(),0);
		assertEquals(14, freeAgent.getBirthDay());
		assertEquals(11, freeAgent.getBirthMonth());
		assertEquals(2000, freeAgent.getBirthYear());
	}

	@Test
	public void getPlayerNameTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPlayerName("Ronaldo");
		assertSame(freeAgent.getPlayerName(),"Ronaldo");
	}

	@Test
	public void setPlayerNameTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPlayerName("Ronaldo");
		assertSame(freeAgent.getPlayerName(),"Ronaldo");
	}

	@Test
	public void getPositionTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPosition("forward");
		assertSame(freeAgent.getPosition(),"forward");
	}

	@Test
	public void setPositionTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPosition("forward");
		assertSame(freeAgent.getPosition(),"forward");
	}

	@Test
	public void setAgeTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setAge(10);
		assertSame(freeAgent.getAge(),10);
	}

	@Test
	public void getAgeTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setAge(10);
		assertSame(freeAgent.getAge(),10);
	}

	@Test
	public void getSkatingTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSkating(10.0);
		assertEquals(freeAgent.getSkating(),10.0,0);
	}

	@Test
	public void setSkatingTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSkating(10.0);
		assertEquals(freeAgent.getSkating(),10.0,0);
	}

	@Test
	public void getShootingTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setShooting(10.0);
		assertEquals(freeAgent.getShooting(),10.0,0);
	}

	@Test
	public void setShootingTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setShooting(10.0);
		assertEquals(freeAgent.getShooting(),10.0,0);
	}

	@Test
	public void getCheckingTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setChecking(10.0);
		assertEquals(freeAgent.getChecking(),10.0,0);
	}

	@Test
	public void setCheckingTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setChecking(10.0);
		assertEquals(freeAgent.getChecking(),10.0,0);
	}

	@Test
	public void getSavingTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSaving(10.0);
		assertEquals(freeAgent.getSaving(),10.0,0);
	}

	@Test
	public void setSavingTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSaving(10.0);
		assertEquals(freeAgent.getSaving(),10.0,0);
	}

	@Test
	public void getPlayerStrengthTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPlayerStrength(15);
		assertEquals(freeAgent.getPlayerStrength(),15,0);
	}

	@Test
	public void setPlayerStrengthTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPlayerStrength(15);
		assertEquals(freeAgent.getPlayerStrength(),15,0);
	}

	@Test
	public void getInjuryStatusTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setInjuryStatus(true);
		assertTrue(freeAgent.getInjuryStatus());
	}

	@Test
	public void setInjuryStatusTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setInjuryStatus(true);
		assertTrue(freeAgent.getInjuryStatus());
	}

	@Test
	public void getRetirementStatusTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setRetirementStatus(true);
		assertTrue(freeAgent.getRetirementStatus());
	}

	@Test
	public void setRetirementStatusTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setRetirementStatus(true);
		assertTrue(freeAgent.getRetirementStatus());
	}

	@Test
	public void calculateForwardPlayerStrengthTest(){
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSkating(12);
		freeAgent.setShooting(12);
		freeAgent.setChecking(12);
		freeAgent.setPosition("forward");
		assertEquals(freeAgent.calculatePlayerStrength(), 30.0, 0);
	}

	@Test
	public void calculateDefensePlayerStrengthTest(){
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSkating(12);
		freeAgent.setShooting(12);
		freeAgent.setChecking(12);
		freeAgent.setPosition("defense");
		assertEquals(freeAgent.calculatePlayerStrength(), 30.0, 0);
	}

	@Test
	public void calculateGoaliePlayerStrengthTest(){
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSaving(12);
		freeAgent.setSkating(12);
		freeAgent.setPosition("goalie");
		assertEquals(freeAgent.calculatePlayerStrength(), 24.0, 0);
	}

	@Test
	public void isPlayerPositionValidTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPosition("forward");
		assertFalse(freeAgent.isPlayerPositionNotValid());
	}

	@Test
	public void isPlayerPositionNotValidTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPosition("Keeper");
		assertTrue(freeAgent.isPlayerPositionNotValid());
	}

	@Test
	public void isPlayerAgeValidTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setAge(25);
		assertFalse(freeAgent.isPlayerAgeNotValid());
	}

	@Test
	public void isPlayerAgeNotValidTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setAge(-2);
		assertTrue(freeAgent.isPlayerAgeNotValid());
	}

	@Test
	public void isPlayerStatValidTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSkating(12);
		freeAgent.setShooting(10);
		freeAgent.setChecking(6);
		freeAgent.setSaving(15);
		assertFalse(freeAgent.isPlayerStatNotValid());
	}

	@Test
	public void isPlayerStatNotValidTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setSkating(12);
		freeAgent.setShooting(10);
		freeAgent.setChecking(-6);
		freeAgent.setSaving(15);
		assertTrue(freeAgent.isPlayerStatNotValid());
	}

	@Test
	public void playerPositionValidateTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPlayerName("Alex");
		freeAgent.setPosition("Keeper");
		assertSame(FreeAgentConstant.PlayerPositionWrong,freeAgent.validate());
	}

	@Test
	public void playerStatValidateTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPlayerName("Alex");
		freeAgent.setPosition("forward");
		freeAgent.setSkating(-1);
		assertSame(FreeAgentConstant.PlayerStateInvalid,freeAgent.validate());
	}

	@Test
	public void validateTest() {
		IFreeAgent freeAgent = modelAbstractFactory.createFreeAgentModel();
		freeAgent.setPlayerName("Alex");
		freeAgent.setPosition("forward");
		freeAgent.setSkating(10);
		freeAgent.setSaving(12);
		freeAgent.setChecking(12);
		freeAgent.setSaving(10);
		freeAgent.setBirthDay(29);
		freeAgent.setBirthMonth(12);
		freeAgent.setBirthYear(2001);
		assertSame(FreeAgentConstant.Success, freeAgent.validate());
	}

	@Test
	public void playerListEmptyTest() {
		LeagueMockData mock = new LeagueMockData();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
	}

	@Test
	public void checkPlayerDetailsEmpty() {
		LeagueMockData mock = new LeagueMockData();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
	}

	@Test
	public void playerNameEmptyTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.setPlayerNameEmpty();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void playerNameNullTest() {
		LeagueMockData mock = new LeagueMockData();
		mock.setPlayerNameNull();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void isbirthDayValidTest1()
	{
		IFreeAgent validate =  modelAbstractFactory.createFreeAgentModel();
		validate.setBirthDay(22);
		assertEquals(validate.isbirthDayValid(),true);
	}

	@Test
	public void isbirthDayValidTest2()
	{
		IFreeAgent validate =  modelAbstractFactory.createFreeAgentModel();
		validate.setBirthDay(-1);
		assertEquals(validate.isbirthDayValid(),false);
	}
	
	@Test
	public void isbirthDayValidTest3()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		validate.setBirthDay(33);
		assertEquals(validate.isbirthDayValid(),false);
	}

	@Test
	public void isbirthYearValidTest1()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		validate.setBirthYear(1995);
		assertEquals(validate.isbirthYearValid(),true);
	}

	@Test
	public void isbirthYearValidTest2()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		validate.setBirthYear(-1997);
		assertEquals(validate.isbirthYearValid(),false);
	}
	
	@Test
	public void isbirthYearValidTest3()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		validate.setBirthYear(997);
		assertEquals(validate.isbirthYearValid(),false);
	}

	@Test
	public void isbirthMonthValidTest1()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		validate.setBirthMonth(12);
		assertEquals(validate.isbirthMonthValid(),true);
	}

	@Test
	public void isbirthMonthValidTest2()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		validate.setBirthMonth(-1);
		assertEquals(validate.isbirthMonthValid(),false);
	}
	
	@Test
	public void isbirthMonthValidTest3()
	{
		IFreeAgent validate =modelAbstractFactory.createFreeAgentModel();
		validate.setBirthMonth(16);
		assertEquals(validate.isbirthMonthValid(),false);
	}

	@Test
	public void isBirthDateValidTest1()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		validate.setBirthDay(20);
		validate.setBirthMonth(10);
		validate.setBirthYear(2000);
		assertEquals(validate.isBirthDateNotValid(),false);
	}

	@Test
	public void isBirthDateValidTest2()
	{
		IFreeAgent validate =modelAbstractFactory.createFreeAgentModel();
		validate.setBirthDay(31);
		validate.setBirthMonth(11);
		validate.setBirthYear(2000);
		assertEquals(validate.isBirthDateNotValid(),true);
	}

	@Test
	public void isBirthDateValidTest3()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		validate.setBirthDay(29);
		validate.setBirthMonth(2);
		validate.setBirthYear(2004);
		assertEquals(validate.isBirthDateNotValid(),false);
	}

	@Test
	public void calculateAgeTest()
	{
		IFreeAgent validate =modelAbstractFactory.createFreeAgentModel();
		validate.setBirthDay(22);
		validate.setBirthMonth(9);
		validate.setBirthYear(1994);
		LocalDate today= LocalDate.now();
		validate.calculateAge(today);
		assertEquals(validate.getAge(),26);
	}
	
	@Test
	public void ConvertPlayerToFreeAgentTestSize()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		LeagueMockData data = new LeagueMockData();
		 List<IFreeAgent> freeAgentList = validate.ConvertPlayerToFreeAgent(data.playerList);
		assertEquals(data.playerList.size(),freeAgentList.size());
	}
	@Test
	public void ConvertPlayerToFreeAgentTestName()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		LeagueMockData data = new LeagueMockData();
		 List<IFreeAgent> freeAgentList = validate.ConvertPlayerToFreeAgent(data.playerList);
		assertEquals(((FreeAgentModel) data.playerList.get(1)).getPlayerName(),freeAgentList.get(1).getPlayerName());
	}

	@Test
	public void ConvertPlayerToFreeAgentTestCaptain()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		LeagueMockData data = new LeagueMockData();
		 List<IFreeAgent> freeAgentList = validate.ConvertPlayerToFreeAgent(data.playerList);
		assertNotEquals(data.playerList.get(1),freeAgentList.get(1));
	}

	@Test
	public void ConvertPlayerToFreeAgentTestAge()
	{
		IFreeAgent validate = modelAbstractFactory.createFreeAgentModel();
		LeagueMockData data = new LeagueMockData();
		List<IFreeAgent> freeAgentList = validate.ConvertPlayerToFreeAgent(data.playerList);
		data.playerList.get(1).calculateAge(LocalDate.now());
		freeAgentList.get(1).calculateAge(LocalDate.now());
		assertSame(data.playerList.get(1).getAge(),freeAgentList.get(1).getAge());
	}
	
	@Test
	public void FreeAgentStatDecayOnBirthdayTest()
	{
		IAging aging = simulationAbstractFactory.createAgingConfig();
		IFreeAgent freeagent=modelAbstractFactory.createFreeAgentModel();
		freeagent.setPlayerName("FreeAgent1");
		freeagent.setPosition("goalie");
		LocalDate todaysDate= LocalDate.now();
		freeagent.setBirthDay(todaysDate.getDayOfMonth());
		freeagent.setBirthMonth(todaysDate.getMonthValue());
		freeagent.setBirthYear(2000);
		freeagent.setSkating(10);
		freeagent.setShooting(12);
		freeagent.setChecking(15);
		freeagent.setSaving(18);
		aging.setStatDecayChance(1);
		freeagent.decreaseFreeAgentStatOnBirthday(freeagent, aging);
		assertEquals(freeagent.getChecking(), 14, 0);
		assertEquals(freeagent.getSaving(), 17, 0);
		assertEquals(freeagent.getSkating(), 9, 0);
		assertEquals(freeagent.getShooting(), 11, 0);
	}
	
	@Test
	public void FreeAgentStatNotDecayOnBirthdayTest()
	{
		IAging aging = simulationAbstractFactory.createAgingConfig();
		IFreeAgent freeagent=modelAbstractFactory.createFreeAgentModel();
		freeagent.setPlayerName("FreeAgent1");
		freeagent.setPosition("goalie");
		LocalDate todaysDate= LocalDate.now();
		freeagent.setBirthDay(todaysDate.getDayOfMonth());
		freeagent.setBirthMonth(todaysDate.getMonthValue());
		freeagent.setBirthYear(2000);
		freeagent.setSkating(10);
		freeagent.setShooting(12);
		freeagent.setChecking(15);
		freeagent.setSaving(18);
		aging.setStatDecayChance(0);
		freeagent.decreaseFreeAgentStatOnBirthday(freeagent, aging);
		assertEquals(freeagent.getChecking(), 15, 0);
		assertEquals(freeagent.getSaving(), 18, 0);
		assertEquals(freeagent.getSkating(), 10, 0);
		assertEquals(freeagent.getShooting(), 12, 0);
	}
}
