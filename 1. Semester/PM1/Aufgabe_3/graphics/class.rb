$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Point/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'ext_modules_v2'
require 'types'

def_class(:Point1d, [:x]){
  def invariant?() 
    self.x.int?
  end
}

def_class(:Range1d, [:first, :last]){
  def invariant?() 
    self.first.point1d? and self.last.point1d?
  end
}

def_class(:Union1d, [:left, :right]){
  def invariant?()
    shape1d?(self.left) and shape1d?(self.right)
  end
}

