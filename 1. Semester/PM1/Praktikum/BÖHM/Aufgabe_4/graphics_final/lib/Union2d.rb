$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class Union2d < UnionShape
  # DIM
  def dim; 2 end
  
  # TYP
  def union2d?; true end
end
