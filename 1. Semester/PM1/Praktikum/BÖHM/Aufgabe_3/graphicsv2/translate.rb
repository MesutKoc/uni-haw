$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'class'
require 'class2d'
require 'types'


#translates range1d
=begin
def range1d_trans(shape, point)
    if shape.range1d? 
    then Range1d[Point1d[shape.first.x + point.x], Point1d[shape.last.x + point.x]]
    else check_pre(false)
  end
end
#translates range2d
def range2d_trans(shape, point)
    if shape.range2d? 
      then Range2d[(range2d_trans(shape.x_range, point.x)),(range2d_trans(shape.y_range, shape.y))]    
    #then (range1d_trans(shape.x_range, point) and range1d_trans(shape.y_range,point))
     #     Range1d[Point1d[shape.first.x + point.x], Point1d[shape.last.x + point.x]], Range1d[Point1d[shape.first.x + point.x], Point1d[shape.last.x + point.x]]
    else check_pre(false)
  end
end

#checks if  range1d or range2d
def prim_shape_trans(shape)
  if shape.range1d? then range1d_trans
  elsif shape.range2d? then range2d_trans
  else check_pre(false)
  end
end

def union1d_trans(shape, point)
  if shape.union1d? 
  then (union1d_trans(shape.left , point) and union1d_trans(shape.right, point))
  else check_pre(false)  
  end 
end

def union2d_trans(shape, point)
  if shape.union2d?
  then(union1d_trans(shape.left, point) and union1d_trans(shape.right, point))
  else check_pre(false)
  end
end

def comp_shape_trans(shape)
  if shape.union1d? then union1d_trans
  elsif shape.union2d? then union2d_trans
  else check_pre(false)  
  end
end
=end

 #translate checks if the given shape in a given point
 #translate ::= (shape, point) :: Shape x Point ->? Shape
def translate(shape, point)
  #check_pre((point?(point)))
  if    shape.union1d? then Union1d[translate(shape.left,point), translate(shape.right,point)] 
  elsif shape.union2d? then Union2d[translate(shape.left,point), translate(shape.right,point)]
  elsif shape.range1d? then Range1d[addition(shape.first, point), addition(shape.last, point)]
  elsif shape.range2d? then Range2d[translate(shape.x_range,point.x), translate(shape.y_range,point.y)]
  else check_pre(false)
  end
end

def addition(point1, point2)
  point1 + point2
end