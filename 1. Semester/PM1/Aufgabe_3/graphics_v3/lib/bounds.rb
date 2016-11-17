$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_3/lib')

#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'graphics_main'
#=================================

# check the bounds of the given object 
# bounds  ::= (shape) :: Shape -> (Range1d | Range2d)
# Test: {...}
def bounds(shape)  
  if    prim_shape?(shape) then shape
  elsif comp_shape?(shape) then bounding_range(shape.left, shape.right)
  else check_pre(false)
  end
end