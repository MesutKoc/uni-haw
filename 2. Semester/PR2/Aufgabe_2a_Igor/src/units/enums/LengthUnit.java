package units.enums;

public enum LengthUnit implements BaseUnit {
	// CONSTANTS
	METRE(1.0),
	CENTIMETRE(0.01),
	MILLIMETRE(0.001),
	NANOMETRE(1.0E-9),
	KILOMETRE(1000.0);

	// ATTRIBUTES
	private double multiplier;
	
	// CREATION
	private LengthUnit(double multiplier) {
		this.multiplier = multiplier;
	}
	
	// SELECTORS
    @Override
    public double multiplier() {
        return multiplier;
    }
}
