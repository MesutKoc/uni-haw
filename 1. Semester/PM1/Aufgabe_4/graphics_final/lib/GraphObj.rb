$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class GraphObj
  def self.[](*args)
    check_inv(self.new(*args))
  end
  
  # TYPE
  def graph_obj?; true end;
  
  # SEL
  def dim;      abstract end
  
  # EQUI
  def ==(obj)
    self.equal?(obj) or (self.equal_by_type?(obj)) or (self.to_a == obj.to_a)
  end
  
  # CONVERSIONEN
  def to_s;   "#{self.class.name}#{self.to_a}" end;
  def to_a;   abstract end;
  
  # METHOSD
  def include?;               abstract end
  def translate;              abstract end
  def bounds;                 abstract end
  def equal_by_trans?;        abstract end
  def equal_by_type?(obj);    abstract end
    
 def equal_by_dim?(obj)
   self.dim == obj.dim
 end
 
  def equal_by_tree?(obj)
    ((obj.graph_obj?) and self == obj)
  end
 #equal_by_trans
  def equal_by_trans?(another_obj)
    if not(self.equal_by_type?(another_obj))     then false 
    elsif self.range1d? and another_obj.range1d? then self.equal_by_tree?(another_obj.translate(Point1d[self.bounds.first.point - another_obj.bounds.first.point]))
    elsif self.range2d? and another_obj.range2d? then new_obj=(another_obj.translate(Point2d[self.bounds.x_range.first.point - another_obj.bounds.x_range.first.point, 
                                                                                                 self.bounds.y_range.first.point - another_obj.bounds.y_range.first.point]))
     self.equal_by_tree?(new_obj)
     else check_pre (false)           
     end
    end
  end
 
