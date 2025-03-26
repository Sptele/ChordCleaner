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

	/* Specific Checks */

	public boolean hasMinorThird() {
		return has[3];
	}

	public boolean hasMajorThird() { return !hasMinorThird() && has[4]; }

	public boolean hasPerfectFifth() { return has[7]; }

	public boolean hasFlatFive() { return !hasPerfectFifth() && has[6]; }

	public boolean hasAugmentedFifth() { return !hasFlatFive() && has[8]; }


}
