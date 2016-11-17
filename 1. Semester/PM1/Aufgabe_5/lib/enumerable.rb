$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib') 
# INCLUDES FOR THE TASK 5
# ============================
# Mesut K., Anton K.
require 'ext_pr1_v4'

module Enumerable
  # Converts to array
  # Enumerable#to_a_ ::= Any -> Array
  # Tests: {...}
  def to_a_
    accu = []
    self.each{ |elem| accu << elem }
    #self.each_with_index { |obj| obj.to_a}
  end
  
  # map_
  # Enumerable#map_ ::= Any
  # Tests: {...}
  def map_(&block)
    accu = []
    self.each{ |item| accu << yield(item)}
    accu
  end
  
  # Enumerable#any_?
  # Tests: {...}
  def any_?(&block)
    check_pre(block_given?)
    bool = false
    self.each{ |elem| bool = (bool or not(yield(elem) == nil or yield(elem) == false))}
    bool
  end
  
  # Enumerable#max_
  # Tests: {...}
   def max_(&block)
     arr = []
     (not(block_given?)?(proc {|a,b| a <=> b}) : nil )
     self.each {|elem| arr << elem}
     s = arr.sort(&block).reverse.take(1)
     return s.join.to_s
   end
  
  # Enumerable#freq_count
  def freq_count
		hash = {}
		self.each { |item| (hash.has_key?(item))? hash[item] += 1 : hash[item] = 1}
		hash
	end
end
