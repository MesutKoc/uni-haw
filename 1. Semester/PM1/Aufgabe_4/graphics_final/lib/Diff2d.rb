$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')
require 'main'

class Diff2d < Diff
  # ho
  def diff2d?; true end
  
  def dim; 2 end
end
