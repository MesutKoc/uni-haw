$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe3/lib')
require 'ext_pr1_v4'
require 'ext_modules_v2'
require 'graphic_func'



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

def_class(:Union1d, [:left,:right]){
  def invariant?()
    (shape1d?(self.left) and shape1d?(self.right))    
  end
}

def_class(:Point2d, [:x , :y]){
  def invariant?()
     point1d?(self.x) and point1d?(self.y)  
  end
}

def_class(:Range2d, [:first, :last]){
  def invariant?()
    self.first.range1d and self.last.range1d
  end
}


def_class(:Union2d, [:left, :right]){
  def invariant?()
  (shape2d?(self.left) and shape2d?(self.right)) 
  end
}

