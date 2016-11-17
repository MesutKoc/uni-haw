$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Point/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'methods'
require 'types'

def shape_include_v2(shape, point)
  if    prim_shape?(shape)  then range1d_include?(shape, point) or
                                 range1d_include?(shape.x_range, point.x) and 
                                 range1d_include?(shape.y_range, point.y)
  elsif union_shape?(shape) then shape_include_v2(shape.left, point) or
                                 shape_include_v2(shape.right, point)
  else check_pre(false)
  end
end
