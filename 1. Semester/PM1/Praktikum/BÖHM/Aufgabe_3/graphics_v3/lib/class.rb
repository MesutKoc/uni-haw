$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_3/lib')

#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'graphics_main'
#=================================

# Class for Range1d
# Range1d[first, last] ::= (Point1d x Point1d)
def_class(:Range1d, [:first, :last]){
	def invariant?()
		point1d?(self.first) and point1d?(self.last)
	end
}

# Class for Union1d
# Union1d[left, right] ::= (Shape1d x Shape1d)
def_class(:Union1d, [:left, :right]){
  def invariant?()
    shape1d?(self.left) and shape1d?(self.right)
  end
}

