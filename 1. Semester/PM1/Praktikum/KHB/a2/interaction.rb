# Author:: Sascha Majewsky
# Author:: Maximilian Janzen
#
# 

class Interaction
  
  def input_prime_factors
    input = gets.chomp
    if input < 2 
      puts "Bitte geben Sie nur Zahlen über 2 an"
    else
      Prime_factors.new.prime_factors(input)
    end
  end
  
  def output_prime_factors 
    
  end
  
end