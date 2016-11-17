package Values.physicsValues.enums;

public enum MassUnit implements BaseUnit {
	// CONSTANTS
	KILOGRAM(1.0),
	CENTIGRAM(1.0E-5),
	MILLIGRAM(1.0E-6),
	NANOGRAM(1.0E-12),
	PICOGRAM(1.0E-14),
	GRAM(0.001);

	// ATTRIBUTES
	private double multiplier;
	
	// CREATION
	private MassUnit(double multiplier) {
		this.multiplier = multiplier;
	}
	
	// SELECTORS
    @Override
    public double multiplier() {
        return multiplier;
    }
}