# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# 

class Arithmetic
  def initialize
    @perfect = nil
  end # end method

  def perfect?(number)
    counter = 1
    divisor = []
 
     # counts upwards and only saves the counted numbers as divisor which are divisors without remainder
    while counter < number
      if number % counter == 0
        divisor.push(counter) 
      end  # end if
      counter += +1
    end # end schleife

    # when all elements from the divisor array added together equal number it is a perfect number
    if divisor.inject(:+) == number 
      @perfect = true
    else
      @perfect = false
    end # end if

    @perfect #  true when perfect number false when not
  end # end method
end # end class
