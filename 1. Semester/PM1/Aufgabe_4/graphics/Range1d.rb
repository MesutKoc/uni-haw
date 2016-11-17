$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')

require 'ext_pr1_v4'
require 'main'

class Range1d < PrimShape
  def initialize(first,last)
    @first = first
    @last  = last
  end
  
  def invariant?
    self.first.point1d? and self.last.point1d?
  end
  
  # SELECTORS
  def dim; 1 end
  def first; @first end
  def last; @last end
  
  # TYPE
  def range1d?; true end;
 
  # CONVERSION
  def to_a
    [self.first, self.last]
  end
  
  def include?(point)
    ((self.first.range1d?..self.last.range1d?)).include?(point.point)
  end
  
  def translate(point)
    self.class[Point1d[self.first.+(point)], Point1d[self.last.+(point)]]
  end
  
  def bounding_range(r2)
    min = self.first.point.min(self.last.point).min(r2.first.point.min(r2.last.point))
    max = self.first.point.max(self.last.point).max(r2.first.point.max(r2.last.point))
    self.class[self.first.class[min], self.last.class[max]]
  end
end