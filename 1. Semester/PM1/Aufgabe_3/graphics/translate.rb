$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Point/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'class'
require 'class2d'
require 'types'

 #translate checks if the given shape in a given point
 #translate ::= (shape, point) :: Shape x Point ->? Shape
def translate(shape, point)
  #check_pre((point?(point)))
  if    shape.union1d? then Union1d[translate(shape.left,point), translate(shape.right,point)] 
  elsif shape.union2d? then Union2d[translate(shape.left,point), translate(shape.right,point)]
  elsif shape.range1d? then Range1d[Point1d[shape.first.x + point.x], Point1d[shape.last.x + point.x]]
  elsif shape.range2d? then Range2d[Point2d[shape.x_range,point.x],Point2d[shape.y_range,point.y]]
  else check_pre(false)
  end
end

#