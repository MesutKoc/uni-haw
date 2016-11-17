$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'


class List
  
  
  def size
    self.empty? ? 0: 1 + self.rest.size
  end
  
  def ==(obj)
    self.equal?(obj) or (obj.list? and (self.to_a == obj.to_a))
  end
  
  #endrekursion
  def list_rev()
    list_rev_(List[],self)
  end
  
  def list_rev_(accu, rest)
    rest.empty? ? accu : (list_rev_(cons(rest.first, accu), rest.rest))
  end
  
  
  #while
  def rev_while()
    accu=List[]
    rest=self
    while not (rest.empty?)
      accu= cons(rest.first, accu)
      rest= rest.rest
    end
    accu
  end
  
  
  #reduce
  def rev_reduce()
    accu = []
    self.reduce([]){|elem,x| accu = [x]+ elem}
    accu.to_l
  end
  
  def plus_list(obj)
    sum_(self, obj)
  end
  
  def sum_(accu, sec_list)
    if sec_list.empty?
      accu
    else 
      sum_(cons(sec_list.first, accu.rev_while).rev_while,other_list.rest)
    end
  end
   
end