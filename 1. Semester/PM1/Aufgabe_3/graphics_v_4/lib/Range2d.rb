$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
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
  
  # CONVERSION
  def to_a
    [self.x_range, self.y_range]
  end
  
  def include?(point)
		check_pre((point.point2d?))
		self.x_range.include?(point.x) and self.y_range.include?(point.y)
	end
  
  def translate(point)
    self.class[self.x_range.translate(point.x), self.y_range.translate(point.y)]
  end
end