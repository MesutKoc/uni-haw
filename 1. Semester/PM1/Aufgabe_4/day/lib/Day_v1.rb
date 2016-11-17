$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4/lib')
require 'ext_pr1_v4'
require 'constant'

class Day
  def invariant?
    val.include?(self.value)
  end
  
  # SELECTORS
  def sym;                abstract end
  def num;                abstract end
  def value;              @value   end
  def val;                abstract end
  # TYPE PREDS
  def day?; true end
  
  # SAME
  def ==(obj);            abstract end
  
  # CONVERSIONS
  def to_s;               abstract end
  def to_day_sym;         abstract end;
  def to_day_num;         abstract end;
  def to_day;             abstract end;
  def day_shift(offset)   abstract end;
  
end