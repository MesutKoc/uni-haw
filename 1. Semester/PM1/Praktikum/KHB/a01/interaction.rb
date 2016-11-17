# A simple tool that convert lengths to metric, angel-saxon and potrzebie measures.

# initialize the class which contains methods for input/output from/for user
class Interaction
  def initialize(length, given_unit, wanted_unit)
    @length = length
    @given_unit = given_unit
    @wanted_unit = wanted_unit
  end #end Class

  # method input will handle every input given by user
  def input

    # question user to distinguish between normal conversion function or creating a table to compare one unit with every other supported measurement unit for different values.
    puts "Please select your conversion mode. Enter \"convert\" to make a simple conversion. Enter \"table\" to receive a whole table of conversions with a step value"
    input_select = gets.chomp

    # user wants to use normal conversion mode
    if input_select.include?("convert")

      puts "To start the conversion of your length to other measurement units please \nenter your data in this way: length givenunit desiredunit       e.g. 10 cm dm"
      puts "You can use the \"help\" command to see what units and abbreviations are supported."

      user_data = gets.chomp

      # user has to enter the correct syntax which includes spaces, so if no space is included puts help info
      unless user_data.include?(" ")
        puts "Help"
        puts "I'm afraid your given data were incorrect use \"mm\" for millimeters, \"cm\" for centimeters, \"dm\" for decimeters, \"m\" for meters, \"km\" for kilometers, \"in\" for inch,"
        puts"\"h\" for hand, \"ft\" for foot, \"yd\" for yard, \"fm\" for fathom, \"cn\" for chain, \"fg\" for furlong, \"mi\" for mile, \"nm\" for nauticalmile, \"fp\" for farshimmelt potrzebie,"
        puts"\"p\" for potrzebie, \"mp\" for millipotrzebie, \"cp\" for centipotrzebie, \"dp\" for decipotrzebie, \"DP\" for dekapotrzebie, \"HP\" for Hectopotrzebie, \"KP\" for Kilopotrzebie, \"FP\" for furshlugginer potrzebie."
        puts
        puts
        # after that restart interaction with no parameters
        interaction = Interaction.new(nil, nil, nil).input

        # user entered data in correct syntax with spaces so this data which should look like e.g. "20 cm dm" will be splitted before and afters every space and this will be saved as array
      else
        user_data = user_data.split(" ")
        @length = user_data[0]
        @given_unit = user_data[1]
        @wanted_unit = user_data[2]

        # after splitting user data in an array start the conversion in different file with user datas as parameter
        calculate = Converter.new(@length, @given_unit, @wanted_unit)

      end #end unless

      # user wants to use the table function instead of normal conversion
    elsif input_select.include?("table")

      puts "Please enter your: startvalue unit incrementvalue endvalue."

      user_data = gets.chomp

      # split user data in an array with 4 elements
      user_data = user_data.split(" ")
      @start_length = user_data[0]
      @unit = user_data[1]
      @increment = user_data[2]
      @end_length = user_data[3]

      # after splitting user data in an array create new Table in different file with user datas as parameter
      table = Table.new(@start_length, @unit, @increment, @end_length)
      
    end # end if
  end # end method

  def output

    # this will be displayed as output information for user after conversion
    puts "Your given information were: #{@length}#{@given_unit} to #{@wanted_unit}"
    puts "#{@length} #{@given_unit} equals #{$length_output.to_f.to_s} #{@wanted_unit}"
    puts
    puts
    # after that restart interaction with no parameters
    interaction = Interaction.new(nil, nil, nil).input
  end # end method

end # end class
