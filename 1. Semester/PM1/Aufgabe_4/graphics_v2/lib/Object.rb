$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class Object
  def graph_obj?;   false end
  def point?;       false end
  def point1d?;     false end
  def point2d?;     false end
  def shape?;       false end
  def prim_shape?;  false end
  def range1d?;     false end
  def range2d?;     false end
  def union_shape?; false end
  def union1d?;     false end
  def union2d?;     false end
  def diff?;        false end
  def diff1d?;      false end
  def diff2d?;      false end
  
end
