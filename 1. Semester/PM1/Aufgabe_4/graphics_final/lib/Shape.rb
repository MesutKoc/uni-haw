$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class Shape < GraphObj
  # TYP
  def shape?; true end
  
  # OPERATIONS
  def +(obj)
    (self.dim == 1? Union1d[self,obj]: self.dim == 2? Union2d[self, obj]: false)
  end
  
  def -(obj)
    (self.dim == 1? Union1d[self,obj]: self.dim == 2? Union2d[self, obj]: false)
  end
end