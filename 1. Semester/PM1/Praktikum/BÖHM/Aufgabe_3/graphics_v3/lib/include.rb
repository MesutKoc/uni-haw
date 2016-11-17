$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_3/lib')

#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'graphics_main'
#=================================

# include? checks if the given shape in a given point
# include? ::= (shape, point) :: Shape x Point ->? Bool
# Test: {...}
def include?(shape, point)
  if    shape.range1d?      then range1d_include?(shape, point)
  elsif shape.range2d?      then range1d_include?(shape.x_range, point) and 
                                 range1d_include?(shape.y_range, point)
  elsif union_shape?(shape) then include?(shape.left, point) or
                                 include?(shape.right, point)
  end
end

# checks if the given range1 and point1 include
# range1d_include? ::= (r1, p1) :::: Range1d x Point1d ->? Bool
def range1d_include?(r1, p1)
  check_pre((r1.range1d? and point1d?(p1)))
  within?(p1, r1.first, r1.last)
end