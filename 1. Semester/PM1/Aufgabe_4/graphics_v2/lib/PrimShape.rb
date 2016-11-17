$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class PrimShape < Shape
  # TYPE PREDS
  def prim_shape?;   true end

  # EQUIVALENCES
  def equal_by_type?(other)
    other.prim_shape? and self.equal_by_dim?(other)
  end

  def bounds
    self
  end
  
end