$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')

require 'ext_pr1_v4'
require 'main'

class Point2d < Point
  def initialize(x, y)
    @x = x
    @y = y
  end
  
  def invariant?
    self.x.int? and self.y.int?
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