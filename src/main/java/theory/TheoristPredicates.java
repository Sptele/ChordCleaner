package theory;

public class TheoristPredicates {
	private boolean[] has;

	public TheoristPredicates(boolean[] intervals) {
		this.has = intervals;
	}

	public boolean[] getIntervals() {
		return has;
	}

	public boolean getInterval(int ind) {
		return has[ind];
	}

	/* General Checks */

	/**
	 * Checks for a Fifth, including Diminished and Augmented Variations
	 */
	public boolean hasFifth() {
		return has[6] || has[7] || has[8];
	}

	/**
	 * Checks for a major or minor Third;
	 */
	public boolean hasThird() {
		return has[3] || has[4];
	}

	public boolean hasFourth() { return has[5]; }

	/**
	 * Checks for a dominant or major Seventh
	 */
	public boolean hasSeven() {
		return has[10] || has[11];
	}

	/**
	 * Checks for a tritone (sixth scale degree). Equivalent to hasFlatFive()
	 */
	public boolean hasTritone() {
		return has[6];
	}

	public boolean hasADominantSeventh() { return hasDominantSeven() || has[14] || has[17] || has[21]; }

	/* Specific Checks */

	public boolean hasMajorSecond() {
		return has[2];
	}

	public boolean hasMinorThird() {
		return has[3];
	}

	public boolean hasMajorThird() { return has[4]; }

	public boolean hasPerfectFifth() { return has[7]; }

	public boolean hasFlatFive() { return has[6]; }

	public boolean hasAugmentedFifth() { return has[8]; }

	public boolean hasDominantSeven() { return has[10]; }

	public boolean hasMajorSeven() { return has[11]; }

	public boolean hasMajorSix() {
		return has[9];
	}

	public boolean hasMinorSecond() {
		return has[1];
	}

	public boolean hasNine() { return has[14]; }

	public boolean hasFlatNine() { return has[13]; }

	public boolean hasSharpNine() { return has[15]; }

	public boolean hasEleven() { return has[17]; }

	public boolean hasFlatEleven() { return has[16]; }

	public boolean hasSharpEleven() { return has[18]; }

	public boolean hasThirteen() { return has[21]; }

	public boolean hasFlatThirteen() { return has[20]; }

	public boolean hasSharpThirteen() { return has[22]; }


}
