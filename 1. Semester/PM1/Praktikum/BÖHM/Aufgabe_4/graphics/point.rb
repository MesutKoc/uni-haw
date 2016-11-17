$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')

require 'main'
require 'ext_pr1_v4'

class Point < GraphObj
  # TYPE?
  def point?; true end;
  
  # EQUI
  def equal_by_typ?(obj)
    obj.point? and self.equal_by_dim?(obj)
  end
  
  # CONVERSION
  def equal_by_trans?(obj)
    self.equal_by_typ?(obj)
  end
end