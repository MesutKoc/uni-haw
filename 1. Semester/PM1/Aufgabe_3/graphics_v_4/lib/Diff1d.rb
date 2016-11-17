$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4_b/lib')

require 'main'

class Diff1d < Diff
  # ho
  def diff1d?; true end
  
  def dim; 1 end
end
