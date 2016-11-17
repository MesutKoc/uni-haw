$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')

require 'ext_pr1_v4'
require 'main'

class Range2d < PrimShape
  def initialize(x_range,y_range)
    @x_range = x_range
    @y_range=  y_range
  end
  
  def invariant?
    self.x_range.range1d? and self.y_range.range1d?
  end
  
  # SELECTORS
  def dim; 2 end
  def x_range; @x_range end
  def y_range; @y_range end
  
  # TYPE
  def range2d?; true end;
  
  # EQUI TODO
  #fehlt
  
  # CONVERSION
  def to_a
    [self.x_range, self.y_range]
  end
end