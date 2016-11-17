$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Point/lib')
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

def_class(:Point2d, [:x_p1, :y_p1]){
  def invariant?()
    #point1d?(self.x_p1) and point1d?(self.y_p1)
    self.x_p1.point1d? and self.y_p1.point1d?
  end
}

def_class(:Union2d, [:left_u1, :right_u1]){
  def invariant?()
    self.left_u1.union1d? and self.right_u1.union1d?
  end
}
