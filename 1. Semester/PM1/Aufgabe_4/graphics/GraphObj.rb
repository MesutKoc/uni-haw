$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')

require 'main'
require 'ext_pr1_v4'

class GraphObj
  def self.[](*args)
    check_inv(self.new(*args))
  end
  
  # TYPE
  def graph_obj?; true end;
  
  # SELECTOR
  def dim;      abstract end
  
  # EQUI
  def ==(obj)
    obj.equal? or ((self.empty? and obj.empty?) or self.to_a = obj.to_a)
  end
  
  # CONVERSIONEN
  def to_s;   "#{self.class.name}#{self.to_a}" end;
  def to_a;   abstract end;
  
  # METHOSD
  def include?;           abstract end
  def translate;          abstract end
  def bounds;             abstract end
  def equal_by_trans?;    abstract end
  def empty?;             abstract end
  def equal_by_typ?(obj); abstract end
    
 def equal_by_dim?(obj)
   self.dim == obj.dim
 end
 
  def equal_by_tree?(obj)
    ((obj.graph_obj?) and self == obj)
  end
  
  def equal_by_trans?(obj)
    #todo
  end
end
