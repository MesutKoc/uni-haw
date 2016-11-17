package units.enums;

public enum ForceUnit implements BaseUnit {
	// CONSTANTS
	NEWTON(1.0),
	KILONEWTON(1000.0);

	// ATTRIBUTES
	private double multiplier;
	
	// CREATION
	private ForceUnit(double multiplier) {
		this.multiplier = multiplier;
	}
	
	// SELECTORS
    @Override
    public double multiplier() {
        return multiplier;
    }
}
