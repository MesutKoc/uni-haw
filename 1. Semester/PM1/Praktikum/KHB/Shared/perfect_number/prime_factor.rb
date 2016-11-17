# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
# A simple program that prime factorize a given number

require 'prime'

class Prime_factorization

 def initialize
   @divisor_array = []
end
  
  def smallest_divisor(number)
    # finds the smallest prime divisor
    (2..number).each do |i| 
       if number % i == 0 && Prime.prime?(i)  # the number has to be modulo 0 AND a prime number
     return i
     end # end if
     end # end do
  end # end method
 
  def prime_factorization(number)
    # prime factorize as long your number is above 1
    x = 0
     while number > 1
     @divisor_array.push(Prime_factorization.new.smallest_divisor(number))
     number = (number / @divisor_array[x]) # the number will be divided 
       x= x +1
     end
  puts @divisor_array.join(" ")
    end # end method
end # end class

test = Prime_factorization.new
test.prime_factorization(69)
