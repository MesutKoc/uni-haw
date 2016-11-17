# A class whose objects represent even numbers.
# Author:: Bernd Kahlbrandt
class GeradeZahl
  include Comparable, Enumerable
  attr_reader :num
  def initialize(num)
    raise ArgumentError, "#{num} is odd" if (num % 2 != 0)
    @num = num
  end
  def succ()
    GeradeZahl.new(@num + 2)
  end
  def <=>(other)
    @num <=> other.num
  end
  def to_s()
    "#{@num}"
  end
end