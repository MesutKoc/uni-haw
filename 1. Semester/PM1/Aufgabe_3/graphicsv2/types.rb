$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
#===========INCLUDES:=============
require 'class2d'
require 'ext_pr1_v4'

# point1d? checks if the point is a integer
# point1d? ::= (point) :: Any -> Bool
# Tests: [1] => true, [2]=> true,
# [2.0] => false, [0.0] => false
def point1d?(point)
  point.int?
end

# shape1d? checks if the shape a one dimensional object
# shape1d? ::= (shape) :: Any -> Bool
def shape1d?(shape)
  shape.range1d? or shape.union1d?
end

# shape2d? checks if the shape a one dimensional object
# shape2d? ::= (shape) :: Any -> Bool
def shape2d?(shape)
  shape.range2d? or shape.union2d?
end

# point? checks if the given point a one dimensional obj or two dimensional.
# point? ::= (point) :: Any -> Bool
def point?(point)
  point1d?(point) or point.point2d?
end

# prim_shape? checks if the given shape a prim shape
# prim_shape? ::= (shape) :: Any -> Bool
def prim_shape?(shape)
  shape.range1d? or shape.range2d?
end

# union_shape? checks if the given shape a union shape
# union_shape? ::= (shape) :: Any -> Bool
def union_shape?(shape)
  shape.union1d? or shape.union2d?
end

# comp_shape? checks if the given shape a comp shape
# union_shape? ::= (comp_shape) :: Any -> Bool
def comp_shape?(comp_shape)
  union_shape?(comp_shape)
end

# shape? checks if the given object a shape
# shape?(obj) :: Any -> Bool :::: (prim_shape?(obj) or comp_shape?(obj))
def shape?(obj)
  prim_shape?(obj) or comp_shape?(obj)
end

# graph_obj? checks if the given object a graph obj
# graph_obj? ::= (object) :: Any -> Bool
def graph_obj?(object)
  point?(object) or shape?(object)
end

def dim?(obj)
  ((shape1d?(obj) or point1d?(obj) ? 1: 2))
end
