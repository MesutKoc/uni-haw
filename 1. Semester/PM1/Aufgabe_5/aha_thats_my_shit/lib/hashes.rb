$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'


class Hash
  
  def valid_hash?
    self.values.all?{|elem| elem.int? and elem.int_pos?}
  end
  
  def add_counts(hash)  
    check_pre((hash.hash? and self.valid_hash? and hash.valid_hash?))
    self.merge(hash){|key, self_key, other_key| self_key + other_key}
  end
end