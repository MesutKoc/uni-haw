$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class Point2d < Point
  def initialize(x, y)
    @x = x
    @y = y
  end
  
  def invariant?
    self.x.point1d? and self.y.point1d?
  end
  
  # SELECTORS
  def dim; 2 end
  def x; @x  end
  def y; @y  end
  
  # TYPE
  def point2d?; true end;
  
  # CONVERSION
  def to_a
    [self.x,self.y]
  end
    
end