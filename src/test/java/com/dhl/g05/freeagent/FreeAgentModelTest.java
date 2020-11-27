package com.dhl.g05.freeagent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.dhl.g05.coach.CoachModel;
import com.dhl.g05.mockdata.JsonMockDataDb;

public class FreeAgentModelTest {

	@Test
	public void constructorTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		assertNull(freeAgent.getPlayerName());
		assertNull(freeAgent.getPosition());
		assertFalse(freeAgent.getInjuryStatus());
		assertFalse(freeAgent.getRetirementStatus());
	}

	@Test
	public void parameterConstructorTest() {
		JsonMockDataDb freeAgentMock = new JsonMockDataDb();
		IFreeAgent freeAgent = new FreeAgentModel(freeAgentMock.freeAgentOne, freeAgentMock.positionDefense, freeAgentMock.skating, freeAgentMock.shooting, freeAgentMock.checking, freeAgentMock.saving, freeAgentMock.birthDay, freeAgentMock.birthMonth, freeAgentMock.birthYear);
		assertSame(freeAgentMock.freeAgentOne, freeAgent.getPlayerName());
		assertSame(freeAgentMock.positionDefense, freeAgent.getPosition());
		assertEquals(freeAgentMock.skating, freeAgent.getSkating(),0);
		assertEquals(freeAgentMock.shooting, freeAgent.getShooting(),0);
		assertEquals(freeAgentMock.checking, freeAgent.getChecking(),0);
		assertEquals(freeAgentMock.saving, freeAgent.getSaving(),0);
		assertEquals(freeAgentMock.birthDay, freeAgent.getBirthDay());
		assertEquals(freeAgentMock.birthMonth, freeAgent.getBirthMonth());
		assertEquals(freeAgentMock.birthYear, freeAgent.getBirthYear());
	}

	@Test
	public void getPlayerNameTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setPlayerName("Ronaldo");
		assertSame(freeAgent.getPlayerName(),"Ronaldo");
	}

	@Test
	public void setPlayerNameTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setPlayerName("Ronaldo");
		assertSame(freeAgent.getPlayerName(),"Ronaldo");
	}

	@Test
	public void getPositionTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setPosition("forward");
		assertSame(freeAgent.getPosition(),"forward");
	}

	@Test
	public void setPositionTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setPosition("forward");
		assertSame(freeAgent.getPosition(),"forward");
	}

	@Test
	public void setAgeTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setAge(10);
		assertSame(freeAgent.getAge(),10);
	}

	@Test
	public void getAgeTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setAge(10);
		assertSame(freeAgent.getAge(),10);
	}

	@Test
	public void getSkatingTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSkating(10.0);
		assertEquals(freeAgent.getSkating(),10.0,0);
	}

	@Test
	public void setSkatingTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSkating(10.0);
		assertEquals(freeAgent.getSkating(),10.0,0);
	}

	@Test
	public void getShootingTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setShooting(10.0);
		assertEquals(freeAgent.getShooting(),10.0,0);
	}

	@Test
	public void setShootingTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setShooting(10.0);
		assertEquals(freeAgent.getShooting(),10.0,0);
	}

	@Test
	public void getCheckingTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setChecking(10.0);
		assertEquals(freeAgent.getChecking(),10.0,0);
	}

	@Test
	public void setCheckingTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setChecking(10.0);
		assertEquals(freeAgent.getChecking(),10.0,0);
	}

	@Test
	public void getSavingTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSaving(10.0);
		assertEquals(freeAgent.getSaving(),10.0,0);
	}

	@Test
	public void setSavingTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSaving(10.0);
		assertEquals(freeAgent.getSaving(),10.0,0);
	}

	@Test
	public void getPlayerStrengthTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setPlayerStrength(15);
		assertEquals(freeAgent.getPlayerStrength(),15,0);
	}

	@Test
	public void setPlayerStrengthTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setPlayerStrength(15);
		assertEquals(freeAgent.getPlayerStrength(),15,0);
	}

	@Test
	public void getInjuryStatusTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setInjuryStatus(true);
		assertTrue(freeAgent.getInjuryStatus());
	}

	@Test
	public void setInjuryStatusTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setInjuryStatus(true);
		assertTrue(freeAgent.getInjuryStatus());
	}

	@Test
	public void getRetirementStatusTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setRetirementStatus(true);
		assertTrue(freeAgent.getRetirementStatus());
	}

	@Test
	public void setRetirementStatusTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setRetirementStatus(true);
		assertTrue(freeAgent.getRetirementStatus());
	}

	@Test
	public void calculateForwardPlayerStrengthTest(){
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSkating(12);
		freeAgent.setShooting(12);
		freeAgent.setChecking(12);
		freeAgent.setPosition("forward");
		assertEquals(freeAgent.calculatePlayerStrength(), 30.0, 0);
	}

	@Test
	public void calculateDefensePlayerStrengthTest(){
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSkating(12);
		freeAgent.setShooting(12);
		freeAgent.setChecking(12);
		freeAgent.setPosition("defense");
		assertEquals(freeAgent.calculatePlayerStrength(), 30.0, 0);
	}

	@Test
	public void calculateGoaliePlayerStrengthTest(){
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSaving(12);
		freeAgent.setSkating(12);
		freeAgent.setPosition("goalie");
		assertEquals(freeAgent.calculatePlayerStrength(), 24.0, 0);
	}

	@Test
	public void isPlayerPositionValidTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setPosition("forward");
		assertFalse(freeAgent.isPlayerPositionNotValid());
	}

	@Test
	public void isPlayerPositionNotValidTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setPosition("Keeper");
		assertTrue(freeAgent.isPlayerPositionNotValid());
	}

	@Test
	public void isPlayerAgeValidTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setAge(25);
		assertFalse(freeAgent.isPlayerAgeNotValid());
	}

	@Test
	public void isPlayerAgeNotValidTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setAge(-2);
		assertTrue(freeAgent.isPlayerAgeNotValid());
	}

	@Test
	public void isPlayerStatValidTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSkating(12);
		freeAgent.setShooting(10);
		freeAgent.setChecking(6);
		freeAgent.setSaving(15);
		assertFalse(freeAgent.isPlayerStatNotValid());
	}

	@Test
	public void isPlayerStatNotValidTest() {
		IFreeAgent freeAgent = new FreeAgentModel();
		freeAgent.setSkating(12);
		freeAgent.setShooting(10);
		freeAgent.setChecking(-6);
		freeAgent.setSaving(15);
		assertTrue(freeAgent.isPlayerStatNotValid());
	}

	@Test
	public void playerListEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
	}

	@Test
	public void checkPlayerDetailsEmpty() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
	}

	@Test
	public void playerNameEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameEmpty();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}


	@Test
	public void playerNameNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerNameNull();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void playerPositionEmptyTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPositionEmpty();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void playerPositionNullTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPlayerPostitionNull();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,validate.validate());
	}

	@Test
	public void playerPositionValidTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.Success,validate.validate());
	}

	@Test
	public void playerPositionCheckTest() {
		JsonMockDataDb mock = new JsonMockDataDb();
		mock.setPositionDifferent();
		FreeAgentModel validate = new FreeAgentModel(mock);
		assertSame(FreeAgentConstant.PlayerPositionWrong,validate.validate());
	}

	@Test
	public void isbirthDayValidTest1()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthDay(22);
		assertEquals(validate.isbirthDayValid(),true);
	}
	@Test
	public void isbirthDayValidTest2()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthDay(-1);
		assertEquals(validate.isbirthDayValid(),false);
	}
	
	@Test
	public void isbirthDayValidTest3()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthDay(33);
		assertEquals(validate.isbirthDayValid(),false);
	}
	@Test
	public void isbirthYearValidTest1()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthYear(1995);
		assertEquals(validate.isbirthYearValid(),true);
	}
	@Test
	public void isbirthYearValidTest2()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthYear(-1997);
		assertEquals(validate.isbirthYearValid(),false);
	}
	
	@Test
	public void isbirthYearValidTest3()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthYear(997);
		assertEquals(validate.isbirthYearValid(),false);
	}
	@Test
	public void isbirthMonthValidTest1()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthMonth(12);
		assertEquals(validate.isbirthMonthValid(),true);
	}
	@Test
	public void isbirthMonthValidTest2()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthMonth(-1);
		assertEquals(validate.isbirthMonthValid(),false);
	}
	
	@Test
	public void isbirthMonthValidTest3()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthMonth(16);
		assertEquals(validate.isbirthMonthValid(),false);
	}
	@Test
	public void isBirthDateValidTest1()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthDay(20);
		validate.setBirthMonth(10);
		validate.setBirthYear(2000);
		assertEquals(validate.isBirthDateValid(),true);
	}
	@Test
	public void isBirthDateValidTest2()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthDay(31);
		validate.setBirthMonth(11);
		validate.setBirthYear(2000);
		assertEquals(validate.isBirthDateValid(),false);
	}
	@Test
	public void isBirthDateValidTest3()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthDay(29);
		validate.setBirthMonth(2);
		validate.setBirthYear(2004);
		assertEquals(validate.isBirthDateValid(),true);
	}
	@Test
	public void calculateAgeTest()
	{
		FreeAgentModel validate = new FreeAgentModel();
		validate.setBirthDay(22);
		validate.setBirthMonth(9);
		validate.setBirthYear(1994);
		validate.calculateAge();
		assertEquals(validate.getAge(),26);
	}
	
	@Test
	public void ConvertPlayerToFreeAgentTestSize()
	{
		FreeAgentModel validate = new FreeAgentModel();
		JsonMockDataDb data = new JsonMockDataDb();
		 List<IFreeAgent> freeAgentList = validate.ConvertPlayerToFreeAgent(data.playerList);
		assertEquals(data.playerList.size(),freeAgentList.size());
	}
	@Test
	public void ConvertPlayerToFreeAgentTestName()
	{
		FreeAgentModel validate = new FreeAgentModel();
		JsonMockDataDb data = new JsonMockDataDb();
		 List<IFreeAgent> freeAgentList = validate.ConvertPlayerToFreeAgent(data.playerList);
		assertEquals(((FreeAgentModel) data.playerList.get(1)).getPlayerName(),freeAgentList.get(1).getPlayerName());
	}
	@Test
	public void ConvertPlayerToFreeAgentTestCaptain()
	{
		FreeAgentModel validate = new FreeAgentModel();
		JsonMockDataDb data = new JsonMockDataDb();
		 List<IFreeAgent> freeAgentList = validate.ConvertPlayerToFreeAgent(data.playerList);
		assertNotEquals(data.playerList.get(1),freeAgentList.get(1));
	}
	public void ConvertPlayerToFreeAgentTestAge()
	{
		FreeAgentModel validate = new FreeAgentModel();
		JsonMockDataDb data = new JsonMockDataDb();
		 List<IFreeAgent> freeAgentList = validate.ConvertPlayerToFreeAgent(data.playerList);
		 data.playerList.get(1).calculateAge();
		 freeAgentList.get(1).calculateAge();
		 assertSame(data.playerList.get(1).getAge(),freeAgentList.get(1).getAge());
	}

	@Test
	public void validateTest() {
		JsonMockDataDb freeAgentMock = new JsonMockDataDb();
		IFreeAgent freeAgent = new FreeAgentModel(freeAgentMock);
		assertSame(FreeAgentConstant.Success,freeAgent.validate());
		freeAgentMock.setPlayerPositionEmpty();
		freeAgent = new FreeAgentModel(freeAgentMock);
		assertSame(FreeAgentConstant.PlayerValueEmpty,freeAgent.validate());
		freeAgentMock = new JsonMockDataDb();
		freeAgentMock.setPositionDifferent();
		freeAgent = new FreeAgentModel(freeAgentMock);
		assertSame(FreeAgentConstant.PlayerPositionWrong,freeAgent.validate());
		freeAgentMock = new JsonMockDataDb();
		freeAgent = new FreeAgentModel(freeAgentMock);
		freeAgent.setBirthDay(55);
		assertSame(FreeAgentConstant.PlayerBirthdateInvalid,freeAgent.validate());
		freeAgentMock = new JsonMockDataDb();
		freeAgent = new FreeAgentModel(freeAgentMock);
		freeAgent.setSkating(-1);
		assertSame(FreeAgentConstant.PlayerStateInvalid,freeAgent.validate());
	}

}
