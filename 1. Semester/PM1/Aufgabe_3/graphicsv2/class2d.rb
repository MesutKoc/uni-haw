$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'ext_modules_v2'
require 'class'
require 'types'

def_class(:Range2d, [:x_range, :y_range]){
  def invariant?() 
    self.x_range.range1d? and self.y_range.range1d?
  end
}

def_class(:Point2d, [:x, :y]){
  def invariant?()
    #point1d?(self.x_p1) and point1d?(self.y_p1)
    #self.x_p1.point1d? and self.y_p1.point1d?
    point1d?(self.x) and point1d?(self.y)
  end
}

def_class(:Union2d, [:left, :right]){
  def invariant?()
    shape2d?(self.left) and shape2d?(self.right)
  end
}
