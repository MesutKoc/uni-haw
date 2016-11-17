# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
#
# A simple tool that convert lengths to metric, angel-saxon and potrzebie measures.

# initialize the class which creates a table to compare one unit with every other supported measurement unit for different values.

class Table
  def initialize(start_length, unit, increment, end_length)
    @start_length = start_length      #Start value for the first step (user input)
    @unit = unit #Start unit
    @increment = increment #Increment from start to end (up and down)
    @end_length = end_length  # End of the calculating process
    @units = ["mm", "cm", "dm", "m", "km", "in", "h", "ft", "yd", "fm", "cn", "fg", "mi", "nm", "fp", "p", "mp", "cp", "dp", "DP", "HP", "KP", "FP"] # short units
    @units_complete = ["millimeter", "centimeter", "decimeter", "meter", "kilometer", "inches", "hand", "foot", "yard", "farthom", "chain", "furlong", "mile", "nauticalmile", "farshimmeltpotrzebie", "potrzebie", "millipotrzebie", "centipotrzebie", "decipotrzebie", "dekapotrzebie", "Hectopotrzebie", "Kilopotrzebie", "furshlugginerpotrzebie"] # long units
    @conversionfactors = [1.0, 10, 100, 1000, 1000000, 25.4, 101.6, 304.8, 914.4, 1828.8, 20116.8, 201168, 1609344, 1852000, 0.0000022633484517438173216473, 2.2633484517438173216473, 2263.3484517438173216473, 22633.484517438173216473, 226334.84517438173216473, 22.633484517438173216473, 226.33484517438173216473, 2263.3484517438173216473, 2263348,4517438173216473] # calculate factor
    $length_mm = nil #all units are converted to mm first
    $table_unit = nil #unit in string
    $number = nil #Calculated number
    $calculation_output = "" #Full output

    return table
  end #end initialize

  def table
    #@unit = unit
    @start_length = @start_length.to_f
    @increment = @increment.to_f
    @end_length = @end_length.to_f
    counter = nil #count the repition to interrupt the loop at the right moment
    counter_each = 23 #only a counter to format the output. Because of the related index the name for the belonging unit of measurement can be inserted

    #CALCULATE
    
    @end_length <=  @start_length ? counter = (@start_length - @end_length) / @increment : counter = (@end_length - @start_length) / @increment #Ternary notation to compare if start or end is greater (in this case end is larger)

    loop do #calculate 
      x = @units.index(@unit)
      $length_mm = @start_length.to_f  * @conversionfactors[x]

      @units.each do |y| #calculate every block (y correspond the Unit in the array units)
        belonging_index = @units.index(y)
        $number = $length_mm / @conversionfactors[belonging_index]

        $calculation_output = $number.to_s + " " + y.to_s

        counter_each -= 1
        if counter_each == 22 #makes a line before the start of the metric system 
          $calculation_output =  "_________________________________________________" "\n" +$calculation_output
          $calculation_output =  "\n\t\tMetric System" "\n" +$calculation_output
        elsif counter_each == 17 #makes a line before the start of the Anglo-Saxon system
          $calculation_output =  "_________________________________________________" "\n" +$calculation_output
          $calculation_output =  "\n\t\tAnglo-Saxon System" "\n" +$calculation_output

        elsif counter_each == 8 #makes a line before the start of the Protzebie system
          $calculation_output =  "_________________________________________________" "\n" +$calculation_output
          $calculation_output =  "\n\t\tProtzebie System" "\n" +$calculation_output

        end #end of counter_each 
        puts $calculation_output  

      end #End each do
      counter_each = 23 #reset the counter

      puts 
      puts

      @end_length <=  @start_length ?  @start_length -= @increment : @start_length += @increment #Ternary notation to compare if start or end is greater (in this case end is smaller)
      counter -=1
      break if counter <= 0

    end #End loop
#Same calculation as above but to show the last one (the biggest unit)
    v = @units.index(@unit)
    $length_mm = @end_length.to_f  * @conversionfactors[v]

    @units.each do |z|
      belonging_index = @units.index(z)
      $number = $length_mm / @conversionfactors[belonging_index]
      $table_unit = z

      $calculation_output = $number.to_s + " " + z.to_s #compare the number with the unit

      counter_each -= 1 #Build the Table (Lines puts at the correct place)
      if counter_each == 22#
        $calculation_output =  "_________________________________________________" "\n" +$calculation_output
        $calculation_output =  "\n\t\tMetric System" "\n" +$calculation_output
      elsif counter_each == 17#
        $calculation_output =  "_________________________________________________" "\n" +$calculation_output
        $calculation_output =  "\n\t\tAnglo-Saxon System" "\n" +$calculation_output

      elsif counter_each == 8#
        $calculation_output =  "_________________________________________________" "\n" +$calculation_output
        $calculation_output =  "\n\t\tProtzebie System" "\n" +$calculation_output

      end#
      puts $calculation_output

    end
    counter_each = 23 #reset counter 
    interaction = Interaction.new(nil, nil, nil).input
  end #End method table

end #End class
