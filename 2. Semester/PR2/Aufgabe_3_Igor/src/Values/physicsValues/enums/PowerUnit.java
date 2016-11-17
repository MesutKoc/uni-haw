package Values.physicsValues.enums;

public enum PowerUnit implements BaseUnit {
	// CONSTANTS
	WATT(1.0),
	KILOWATT(1000.0),
	HORSEPOWER(735.49875);

	// ATTRIBUTES
	private double multiplier;
	
	// CREATION
	private PowerUnit(double multiplier) {
		this.multiplier = multiplier;
	}
	
	// SELECTORS
    @Override
    public double multiplier() {
        return multiplier;
    }
}