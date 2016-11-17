$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class Union1d < UnionShape
  # DIM = 1
  def dim; 1 end
  # TYP 
  def union1d?; true end
end