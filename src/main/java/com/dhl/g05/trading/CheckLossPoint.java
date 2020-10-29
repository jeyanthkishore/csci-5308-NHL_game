	package com.dhl.g05.trading;
	
	public class CheckLossPoint implements ICheckLossPoint{

		private int lossPoint;
		private int lossCount;

		public int getLossPoint() {
			return lossPoint;
		}

		public void setLossPoint(int lossPoint) {
			this.lossPoint = lossPoint;
		}

		public int getLossCount() {
			return lossCount;
		}

		public void setLossCount(int lossCount) {
			this.lossCount = lossCount;
		}

		public boolean checkLossPoint() {
			if (lossCount >= lossPoint) {
				return true;
			} else {
				return false;
			}
		}

	}
