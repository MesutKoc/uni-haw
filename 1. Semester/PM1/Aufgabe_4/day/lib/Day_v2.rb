$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4/lib')
require 'ext_pr1_v4'
require 'constant'

class Day
  # CREATION
  def self.[](*args)
    check_inv(self.new(args))
  end
  
  def invariant?
    val.include?(self.value)
  end
  
  def initialize(val)
    @value = val
  end
  
  # SELECTORS
  def sym;                to_day_sym.sym end
  def num;                to_day_num.sym end
  def value;              @value   end
  def val;                abstract end
  
  # TYPE PREDS
  def day?; true end
  
  # SAME
  def ==(obj)
    self.equal?(obj) or (obj.day? and (obj.value = self.value))
  end
  
  # CONVERSIONS
  def to_s;               abstract end
  def to_day_sym;         abstract end;
  def to_day_num;         abstract end;
  def to_day;             abstract end;
  def day_shift(offset)   abstract end;
end