package com.dhl.g05.trading;

public interface ICheckLossPoint {

	public int getLossPoint();

	public void setLossPoint(int lossPoint);

	public int getLossCount();

	public void setLossCount(int lossCount);

	public boolean checkLossPoint();
}

