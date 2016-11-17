$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib') 
# INCLUDES FOR THE TASK 5
# ============================
# Mesut K., Anton K.
require 'ext_pr1_v4'

class Set
  # Function proof if a set in a other set 
  # Set#superset?(set) ::= Set x Set -> Bool
  # Tests: {...}
  def superset?(param_set)
    check_pre(param_set.set?)
    #param_set.to_a.each { |x| (self.to_a.include?(x)) ? true : false }
    param_set.to_a.reduce(true) {|accu, elem| (elem.in?(self.to_a)?(accu):(false))}
  end

  # Function proofs if the sets equal
  # Set #==(aSet) ::= Set x Any -> Bool
  # Tests: {...}
  def ==(aSet)
    self.equal?(aSet) or (aSet.set? and aSet.length == self.lenght and 
                          self.superset?(aSet) and aSet.superset?(self))
  end
end