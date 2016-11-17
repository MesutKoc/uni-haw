$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib') 
# INCLUDES FOR THE TASK 5
# ============================
# Mesut K., Anton K.
require 'ext_pr1_v4'

class List    
  # List#==(obj) ::= Any -> Bool
  def ==(obj)
    self.equal?(obj) or (obj.list? and (self.to_a == obj.to_a))
  end
  
  # Function reserve with endrecursion
  # List#reserve_endrec ::= List x List -> List
  # Test: {...}
  def reverse_endrec
    reverse_endrec_(List[], self)
  end
  
  def reverse_endrec_(accu, rest)
    rest.empty? ? accu : (reverse_endrec_(accu.prepend(rest.first), rest.rest))
  end
  
  # Reserve while
  # List#reserve_while ::= List -> List
  # Test: {...}
  def reverse_while
    accu = List[]
    rest = self
    while not(rest.empty?)
      accu = accu.prepend(rest.first)
      rest = rest.rest
    end
    accu
  end
  
  # Reserve with reduce
  # List#reverse_reduce
  # Test: {...}
  def reverse_reduce
    res = 
		self.reduce([]) { |arg,x| res = [x] + arg}
    res.to_l
	end
  
  # Plus for the Lists
  # List#_+_(obj) ::= List x List -> List
  # Test: {...}
  def +(obj)
    check_pre(obj.list?)
    plus(self, obj)
  end
 
  # Plus with Endrecursion
  # List#plus(accu,list) ::= List x List -> List
  def plus(accu, list)
    check_pre((accu.list? and list.list?))
    list.empty? ? accu : plus(accu.prepend(list.first), list.rest).to_a.sort.to_l
  end
end