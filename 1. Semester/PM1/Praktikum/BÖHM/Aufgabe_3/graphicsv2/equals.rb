$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'types'

# Checks if the objects are in the same dimension
# equal_by_dim ::= (obj1, obj2) :: GraphObj x GraphObj -> Boolean
def equal_by_dim?(obj1, obj2)
  check_pre((graph_obj?(obj1) and graph_obj?(obj2)))
  dim?(obj1) == dim?(obj2)
end

# Checks if the given graph objects are equal in tree
# equal_by_tree ::= (obj1, obj2) :: GraphObj x GraphObj -> Boolean
def equal_by_tree?(obj1, obj2)
  if    union_shape?(obj1) then (union_shape?(obj2) and equal_union(obj1,obj2))
  elsif obj1.range1d?      then (obj2.range1d?)     and equal_range(obj1,obj2)
  elsif obj1.range2d?      then (obj2.range2d?)     and equal_range2d(obj1,obj2)
  end
end

def equal_range2d(obj1, obj2)
  (equal_by_tree?(obj1.x, obj2.x) and equal_by_tree?(obj1.y, obj2.y))
end

def equal_union(obj1, obj2)
  check_pre((union_shape?(obj1) and union_shape?(obj2)))
  (equal_by_tree?(obj1.left, obj2.left) and equal_by_tree(obj1.right, obj2.right))
end

def equal_range(obj1, obj2)
  check_pre((obj1.range1d? and obj2.range1d?))
  ((obj1.first) == (obj2.first) and (obj1.last) == (obj2.last))
end

# Checks if the given objectss can be translated (that equal in their tree?)
# equal_by_trans ::= (obj1, obj2) :: GraphObj x GraphObj -> Bool
def equal_by_trans?(obj1,obj2)
  if(point?(obj1))      then true
  elsif (dim?(obj1)==1) then equals_by_tree?(obj1, translate(obj2, Point1d[bounds(obj1).first.x - bounds(obj2).first.x]))
  elsif (dim?(obj1)==2) then equals_by_tree?(obj1, translate(obj2, Point2d[Point1d[bounds(obj1).x_range.first.x - bounds(obj2).x_range.first.x], 
                                                                   Point1d[bounds(obj1).y_range.first.x - bounds(obj2).y_range.first.x]]))
  else check_pre(false)
  end
end
