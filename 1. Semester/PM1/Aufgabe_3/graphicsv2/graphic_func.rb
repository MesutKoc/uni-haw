$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
require 'ext_pr1_v4'
require 'ext_modules_v2'



def within?(val,lower,upper)
  check_pre((((val.int?) and (lower.int?) and (upper.int?) )))
  check_pre(((lower != upper) and (lower < upper)))
  ((lower <= val and val <=upper))
end

def point1d?(obj)
    check_pre(((obj).int? and obj >= 0))
end

def range1d_include?(r1, p1)
  check_pre((r1.range1d and point1d?(p1)))
  within?(p1, r1.first, r1.last)
end

def shape1d?(shape)
  shape.union1d or shape.range1d
end

def shape2d?(shape)
  shape.unuion2d or shape.union2d
end

def point?(point)
  point.point1d or point.point2d
end

def prim_shape?(shape)
  shape.range1d or shape.range2d
end

def union_shape?(union)
  union.union1d or union.union2d
end

def comp_shape?(shape)
  union_shape?(shape)
end

def shape?(shape)
  prim_shape?(shape) or comp_shape?(shape)
end

def graphobj?(obj)
  point?(obj) or shape?(obj)
end

def shape_include?(shape, point)
  check_pre((shape?(shape) and point?(point)))
  if prim_shape?(shape) then   range1d_include?(shape, point) or
                             range1d_include?(shape.x_range, point.x) and 
                             range1d_include?(shape.y_range, point.y)
  elsif union_shape?(shape) then      shape_include?(shape.left, point.x) or
                                      shape_include?(shape.right, point.y)
  else check_pre(false)
end

 def bounding_range(range1, range2)
  smallest = min(range1.first,range2.first)
  biggest = max(range1.last,range2.last)
  
  x_range = min,max(range1.x_range, range2.x_range)
  y_range = min,max(range1.y_range, range2.y_range)
  
   if 
 end
