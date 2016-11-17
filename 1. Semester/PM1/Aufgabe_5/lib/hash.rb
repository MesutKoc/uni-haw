$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib') 
# INCLUDES FOR THE TASK 5
# ============================
# Mesut K., Anton K.
require 'ext_pr1_v4'

class Hash
  # Proofs if the hash a valid (positive integer)
  # Hash#valid_hash? 
  # Tests: {...}
  def valid_hash?
    self.values.all? {|arg| arg.int? and arg.int_pos?}
  end
  
  # Writer over hashes
  # Hash#add_counts(a_hash) ::= Hash x Hash -> Hash
  # Tests: {...}
  def add_counts(a_hash)
    check_pre((a_hash.hash? and self.valid_hash? and a_hash.valid_hash?))
    self.merge(a_hash) { |key, self_key,other_key| self_key + other_key }
  end
end