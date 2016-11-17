$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_3/lib')

#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'graphics_main'
#=================================

# Class for Range2d
# Range2d[x_range,y_range] ::= (Range1d x Range1d)
def_class(:Range2d, [:x_range, :y_range]){
  def invariant?() 
    self.x_range.range1d? and self.y_range.range1d?
  end
}

# Class for Point2d
# Point2d[x,y] ::= (Point1d x Point1d)
def_class(:Point2d, [:x, :y]){
  def invariant?()
    #point1d?(self.x_p1) and point1d?(self.y_p1)
    #self.x_p1.point1d? and self.y_p1.point1d?
    point1d?(self.x) and point1d?(self.y)
  end
}

# Class for Union2d
# Union2d[left, right] ::= (Shape2d x Shape2d)
def_class(:Union2d, [:left, :right]){
  def invariant?()
    shape2d?(self.left) and shape2d?(self.right)
  end
}
