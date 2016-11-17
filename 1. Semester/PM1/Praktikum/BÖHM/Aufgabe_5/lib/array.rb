$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib') 
# INCLUDES FOR THE TASK 5
# ============================
# Mesut K., Anton K.
require 'ext_pr1_v4'

class Array
 
 def to_int
    check_pre((self.array?))
    self.join.to_i
  end
  
  def even_only
    check_pre((self.array?))
    self.select { |num| (num%2 == 0) }
  end
  
  def flatten_
    self.reduce([]) {|accu, elem| (elem.array?)?(accu + elem.flatten):(accu << elem)}
  end
  
  def no_of_leaves
    no_of_leaves_(self)
  end
 
  def no_of_leaves_(array)
    if(array.empty?)
      0
    else
      getLength = array.length
      a = [getLength].map { || ((array.first.array?) ? (array.first.no_of_leaves):(1)) + self.no_of_leaves_(array.rest) }
      a.to_int
    end
	end
  
  def no_of_leaves_size
    self.flatten.size
  end
  
  def each_pair(&block)
    check_pre(( self.all? { |arg| arg.array? and arg.size == 2 } ))
    check_pre((block_given?))
    self.each {|key| yield key[0], key[1]}
  end
end
