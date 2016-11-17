$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'ext_modules_v2'
require 'types'


def_class(:Range1d, [:first, :last]){
	def invariant?()
		point1d?(self.first) and point1d?(self.last)
	end
}

def_class(:Union1d, [:left, :right]){
  def invariant?()
    shape1d?(self.left) and shape1d?(self.right)
  end
}

