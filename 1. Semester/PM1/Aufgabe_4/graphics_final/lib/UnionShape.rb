$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class UnionShape < Shape
  # CREATE
  def initialize(left, right)
    @left  = left
    @right = right
  end
  
  def invariant?
    self.left.shape? and self.right.shape?
  end
  
  # SELECTOR
  def left;  @left end
  def right; @right end
  
  # TYP PRED
  def union_shape?; true end
  
  # CONVERSIONEN
  def to_a
    [self.left, self.right]
  end
  
  def include?(point)
    self.left.include?(point) or self.right.include?(point)
  end
  
  def bounds()
    bounding_range(self.left.bounds,self.right.bounds)
  end
  
  def translate(point)
    self.class[self.left.translate(point),self.right.translate(point)]
  end
  
   def equal_by_type?(obj)
    obj.union_shape? and self.equal_by_dim?(obj)
  end
end