$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib') 
# INCLUDES FOR THE TASK 5
# ============================
# Mesut K., Anton K.
require 'ext_pr1_v4'

def_class(:FullName,[:first_name,:last_name]){
  include Comparable
  def ==(obj)
    self.equal?(obj) or (obj.full_name? and (self.to_a == obj.to_a))
  end
 
  def <=>(obj)
    check_pre((obj.first_name?))
    if(self.first_name == obj.first_name) 
      self.first_name <=> obj.first_name
    else 
      self.last_name <=> obj.last_name
    end
  end
}

class Hash
  def ==(aHash)
    self.equal?(aHash) or (aHash.hash? and self.size == aHash.size and self.to_a.sort == aHash.to_a.sort)
  end
end
