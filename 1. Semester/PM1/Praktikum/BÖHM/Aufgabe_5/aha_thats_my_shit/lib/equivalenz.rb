$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'



def_class(:Fullname,[:first_name, :last_name]){
  def invariant?
    first_name.string? and last_name.string?
  end
  
  def ==(other_name)
    self.equal?(other_name) or (other_name.fullname?) and (self.to_a == other_name.to_a)
  end
  
  def <=>(other_name)
    check_pre(other_name.fullname?)
    if (self.last_name == other_name.last_name)
      self.first_name <=> other_name.first_name
    else
      self.last_name <=> other_name.last_name
    end
  end
    } 
  
class Hash
  def == (other_name)
    self.equal?(other_name) or (other_name.hash?) and (self.to_a.sort == other_name.to_a.sort)
  end
end
  
    