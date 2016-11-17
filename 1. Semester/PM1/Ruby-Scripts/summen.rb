# Summen - Eine Klasse zum Summieren von Numerischen Werten
# Author:: Uwe Krause

class Summen
  private
  def check(summanden)
    raise ArgumentError, "Only appliable on numerables" unless summanden.is_a?(Enumerable)
    raise ArgumentError, "Only numeric input!" if summanden.select {|summand| summand.is_a?(Numeric)}.length < summanden.length
  end

  public

  def summe(num)
    check(num)
    sum = 0
    num.each{|z| sum +=z}
    sum
  end

  def summe2(num)

    check(num)
    num.inject(0.0, :+)

  end
  
  def summe3(num)

    check(num)
    
    
    

  end

end

p s = Summen.new()
p s.summe([1,2.4,3])
