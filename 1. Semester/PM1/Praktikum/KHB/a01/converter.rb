# A simple tool that convert lengths to metric, angel-saxon and potrzebie measures.

# initialize the class which converts an amount and given unit to a desired unit
class Converter
  def initialize(length, given_unit, wanted_unit)
    @length = length
    @given_unit = given_unit
    @wanted_unit = wanted_unit
    @units = ["mm", "cm", "dm", "m", "km", "in", "h", "ft", "yd", "fm", "cn", "fg", "mi", "nm", "fp", "p", "mp", "cp", "dp", "DP", "HP", "KP", "FP"      ]
    @units_complete = ["millimeter", "centimeter", "decimeter", "meter", "kilometer", "inches", "hand", "foot", "yard", "farthom", "chain", "furlong", "mile", "nauticalmile", "farshimmelt potrzebie", "potrzebie", "millipotrzebie", "centipotrzebie", "decipotrzebie", "dekapotrzebie", "Hectopotrzebie", "Kilopotrzebie", "furshlugginer"]
    # the conversionfactor contains every factor to convert a unit in "mm" to every other unit, each index is in place relating to it's unit in the arrays above.
    @conversionfactors = [1.0, 10, 100, 1000, 1000000, 25.4, 101.6, 304.8, 914.4, 1828.8, 20116.8, 201168, 1609344, 1852000, 0.0000022633484517438173216473, 2.2633484517438173216473, 2263.3484517438173216473, 22633.484517438173216473, 226334.84517438173216473, 22.633484517438173216473, 226.33484517438173216473, 2263.3484517438173216473, 2263348,4517438173216473    ]
    @length_mm = nil
    return conversion
  end

  # method for the calculation of the conversion
  def conversion

    # if the unit given by user is an abbreviation or is written-out x will become the indexnumber of this unit
    if @units.include?(@given_unit) == true || @units_complete.include?(@given_unit) == true
      if @units.include?(@given_unit) == true
        x = @units.index(@given_unit)

      elsif @units_complete.include?(@given_unit) == true
        x = @units_complete.index(@given_unit)
      end

      # firstly every unit will be converted to millimeter as a base unit
      $length_mm = @length.to_f  * @conversionfactors[x]

    else
      # the unit given by user is not contained in supported units
      puts "klappt nicht, bitte geben sie es konform den regeln ein"#
      return Interaction.new(nil, nil, nil).input

    end #end if

    # if the unit wanted by user is an abbreviation or is written-out x will become the indexnumber of this unit
    if @units.include?(@wanted_unit) == true || @units_complete.include?(@wanted_unit) == true

      if @units.include?(@wanted_unit) == true
        y = @units.index(@wanted_unit)

      elsif @units_complete.include?(@wanted_unit) == true
        y = @units_complete.index(@wanted_unit)
      end

      # the length that is the correct in ammoun in the wanted unit is calculated
      #$length_output = 
      $length_mm / @conversionfactors[y]

    else
length_mm
    end #end if
    # go to method output in Class Interaction
    Interaction.new(@length, @given_unit, @wanted_unit).output()

  end
  
end
