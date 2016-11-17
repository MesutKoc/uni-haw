$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'

class Array
  
  def to_int
    check_pre(self.array?)
    self.join.to_i
  end
  
  def even_only
    self.select {|elem| elem.int? and elem%2==0}
  end
  
  def flatten
    self.reduce(Array[]){|accu, elem|(elem.array?)?(accu + elem.flatten):(accu << elem)}
  end
  
  
  def no_of_leaves
    no_of_leaves_(self)
  end
  
  def no_of_leaves_(array)
    if (array.empty?)
      0
    else
      ((array.first.array?)?(array.first.no_of_leaves):(1)) + no_of_leaves_(array.rest)
    end
  end
  
  def no_of_leaves
    self.flatten.size
  end
  
  def each_pair(&block)
    check_pre(self.all? {|elem| elem.array? and elem.size >=2})
    check_pre(block_given?)
      each_pair_(self,&block)
      self
  end
  
  def each_pair_(rest,&block)
    if (rest.empty?)
      nil
    else
      yield(rest.first)
      each_pair_(rest,&block)
    end
  end
  
end