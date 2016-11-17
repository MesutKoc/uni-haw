$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'methods'
require 'types'

# checks if the given shape and point include
# prim_shape_include? ::= (shape, point) :::: PrimShape x Point ->? Bool
def prim_shape_include?(shape, point)
  check_pre((prim_shape?(shape) and point?(point)))
  if    shape.range1d then range1d_include?(shape, point)
  elsif shape.range2d then range1d_include?(shape.x_range, point.x) and 
                           range1d_include?(shape.y_range, point.y)
  end
end

# checks if the given range1 and point1 include
# range1d_include? ::= (r1, p1) :::: Range1d x Point1d ->? Bool
def range1d_include?(r1, p1)
  check_pre((r1.range1d and point1d?(p1)))
  within?(p1, r1.first, r1.last)
end

# checks if the given shape and point include
# union_shape_include? ::= (shape, point) :::: UnionShape x Point ->? Bool
def union_shape_include?(shape, point)
  check_pre((shape?(shape) and point?(point)))
  shape_include(shape.left, point) or shape_include(shape.right, point)
end

# checks if the given shape and point include
# cmp_shape_include? ::= (shape, point) :::: CompShape x Point ->? Bool
def comp_shape_include?(shape, point)
  if comp_shape?(shape) and point?(point) then union_shape_include?(shape, point)
  else check_pre(false)
  end
end

# shape_include? checks if the given shape in a given point
# shape_include? ::= (shape, point) :: Shape x Point ->? Bool
def shape_include?(shape, point)
  if    prim_shape?(shape)  then prim_shape_include?(shape, point)
  elsif union_shape?(shape) then union_shape_include?(shape, point)
  else  check_pre(false)
  end
end
