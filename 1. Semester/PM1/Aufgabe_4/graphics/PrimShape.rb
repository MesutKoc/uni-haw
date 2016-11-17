$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')

require 'ext_pr1_v4'
require 'main'


class PrimShape < Shape
  # TYPE PREDS
  def prim_shape?;   true end

  # EQUIVALENCES
  def equal_by_type?(other)
    other.prim_shape? and self.equal_by_dim?(other)
  end

  def include?(point)
    check_pre(point.point?)
    check_pre(self.equal_by_dim?(point))

    index = 0
    self.to_a.reduce(true) {|accu,elem| new_accu = (elem.include?(point.to_a[index]))?(accu):(false); index = index + 1; new_accu}
  end

  def translate(point)
    check_pre(point.point?)
    check_pre(self.equal_by_dim?(point))

    index = 0
    param = self.to_a.reduce([]) {|accu,elem| new_range = elem.translate(point.to_a[index]); index = index + 1; accu << new_range; accu}
    eval("#{self.class}#{param}")
  end

  def bounding_range(other_range)
    check_pre(self.equal_by_type?(other_range))

    index = 0
    param = self.to_a.reduce([]) {|accu,elem| bounding_range = elem.bounding_range(other_range.to_a[index]); index = index + 1; accu << bounding_range; accu}
    eval("#{self.class}#{param}")
  end

  def bounds
    self
  end
end