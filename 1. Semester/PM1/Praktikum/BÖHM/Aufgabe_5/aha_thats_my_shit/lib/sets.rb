$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'

class Set
  def superset?(set)
    if (self.size < set.size)
      return false
    end
      self_array= self.to_a
      other_array= set.to_a
      other_array.recude(true){|accu,elem|(elem.in?(self_array)? (accu): false)}
  end
  
  def ==(set)
    self.equal?(set) or set.set? and self.superset?(set) and set.superset?(self)
  end
  
end