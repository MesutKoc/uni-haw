$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_3/lib')

#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'graphics_main'
#=================================

# Checks if the objects are in the same dimension
# equal_by_dim ::= (obj1, obj2) :: GraphObj x GraphObj -> Boolean
# Test: {...}
def equal_by_dim?(obj1, obj2)
  check_pre((graph_obj?(obj1) and graph_obj?(obj2)))
  dim?(obj1) == dim?(obj2) 
end

# Checks if the given graph objects are equal in tree
# equal_by_tree ::= (obj1, obj2) :: GraphObj x GraphObj -> Boolean
# Test: {...}
def equal_by_tree?(obj1, obj2)
  if    union_shape?(obj1) and union_shape?(obj2)  then equal_union(obj1,obj2)
  elsif obj1.range1d?      and obj2.range1d?       then equal_range(obj1,obj2)
  elsif obj1.range2d?      and obj2.range2d?       then equal_range2d(obj1,obj2)
  end
end

def equal_range2d(obj1, obj2)
  (equal_by_tree?(obj1.x_range, obj2.x_range) and equal_by_tree?(obj1.y_range, obj2.y_range))
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
# Test: {...}
def equal_by_trans?(obj1,obj2)
  if    not(equal_by_dim?(obj1,obj2))   then false
  elsif point?(obj1)  and point?(obj2)  then true
  elsif obj1.range1d? and obj2.range1d? then obj1.first - obj2.first
  elsif obj1.range2d? and obj2.range2d? then equal_by_tree?(translate(obj1, Point2d[equal_by_trans?(bounds(obj1.x_range),bounds(obj2.x_range)), equal_by_trans?(bounds(obj1.y_range),bounds(obj2.y_range))]),obj2)
  elsif check_pre(false)
  end
end
