# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# a simple program that outputs the first 5 perfect numbers

class User_interface

  def output_arithmetic
    perfect_number = []
    x = 1

    # as long as there are less than 5 perfect number found search for new ones
    while perfect_number.length < 5
      if Arithmetic.new.perfect?(x)
        perfect_number.push(Arithmetic.new.perfect?(x))
        puts "Eine perfekte Zahl ist #{x} !"
      end

      x = x + 1
    end
  end

end
