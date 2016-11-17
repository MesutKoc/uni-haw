$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class Point1d < Point
  def initialize(val)
    @point = val
  end
  
  def invariant?
    self.point.int?
  end
  
  # SELECTORS
  def dim; 1 end
  def point; @point end
  
  # TYPE
  def point1d?; true end;
  
  # CONVERSION
  def to_a
    [self.point]
  end
 
end