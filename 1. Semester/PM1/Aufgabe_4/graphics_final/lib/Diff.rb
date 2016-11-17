$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')

require 'main'

class Diff < Shape
  # CREAETION
  def intialize(left, right)
    @left  = left
    @right = right
  end
  
  def invariant?
    self.left.shape? and self.right.shape?
  end
  
  # SELECT
  def left;  @left  end
  def right; @right end
  
  # PRED
  def diff; true end
  
  # ==
  def equal_by_typ?(obj)
    obj.diff? and self.equal_by_dim?(obj)
  end
  
  def bounds()
    self.left.bounds
  end
  
  def translate(point)
    self.class[self.left.translate(point), self.right.traslate(point)]
  end
 
end
