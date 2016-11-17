$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'


module Enumerable
  
  def to_a_
    accu= []
    self.each{|elem| accu << elem}
    accu
  end
  
  def map(&block)
    accu=[]
    self.each {|elem| accu << yield(elem)}
  end
  
  def any?(&block)
    check_pre(block_given?)
    accu = false
    self.each {|elem| res =yield(elem); accu = (accu or not(res == nil or res == false))}
    accu
  end
  
  def max(&block)
    accu =[]
    (not(block_given?)?proc{|a,b| a<=> b} : nil)
    self.each {|elem| accu << elem}
    string = accu.sort(&block).reverse.take(1)
    return string.join.to_s
  end
  
  def freq_count
    accu= {}
    self.each {|elem| (accu.has_key?(elem))? accu[elem] +=1 : accu[elem] = 1}
    accu
  end 
end