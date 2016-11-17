package units.enums;

public enum SpeedUnit implements BaseUnit {
	// CONSTANTS
	METERPERSECOND(1.0),
	KILOMETREPERHOUR(0.2777777777777778);

	// ATTRIBUTES
	private double multiplier;
	
	// CREATION
	private SpeedUnit(double multiplier) {
		this.multiplier = multiplier;
	}
	
	// SELECTORS
    @Override
    public double multiplier() {
        return multiplier;
    }
}
