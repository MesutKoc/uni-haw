$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_3/lib')

#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'graphics_main'
#=================================

# translate checks if the given shape in a given point
# translate ::= (shape, point) :: Shape x Point ->? Shape
# Test: (...)
def translate(shape, point)
  #check_pre((point?(point)))
  if    shape.union1d? then Union1d[translate(shape.left,point), translate(shape.right,point)] 
  elsif shape.union2d? then Union2d[translate(shape.left,point), translate(shape.right,point)]
  elsif shape.range1d? then Range1d[shape.first.+(point), shape.last.+(point)]
  elsif shape.range2d? then Range2d[translate(shape.x_range,point.x), translate(shape.y_range,point.y)]
  else check_pre(false)
  end
end