$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'class'
require 'methods'
require 'types'

# bounds is a bounding box in a form from range 
# bounds  ::= (shape) :: Shape -> (Range1d | Range2d)
def bounds(shape)  
  if    prim_shape?(shape) then shape
  elsif comp_shape?(shape) then bounding_range(shape.left, shape.right)
  else check_pre(false)
  end
end