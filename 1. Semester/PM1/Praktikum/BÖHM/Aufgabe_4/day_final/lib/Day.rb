$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4/lib')

require 'ext_pr1_v4'
require 'constant'

# class for day
class Day
  # CREATION
  def self.[](*args)
    check_inv(self.new(*args))
  end

  def initialize(val)
    @value = val
  end

  def invariant?
    values.include?(self.value)
  end

  # SELECTORS
  def sym;                to_day_sym.sym end
  def num;                to_day_num.num end
  def value;              @value end
  def values;             abstract end
  def index;              values.to_a.index(value) end
  
  # TYPE PREDS
  def day?;               true end

  # EQUIVALENCES
  def ==(other)
    self.equal?(other) or (other.day? and (self.index == other.index))
  end
  
  # CONVERSIONS
  # Day#to_s ::= Day -> String
  # Tests(..)
  def to_s
     "#{self.class.name}[#{self.num()}]"
  end

  def to_day_sym;         abstract end
  def to_day_num;         abstract end
  def to_day;             abstract end
  
  # offset in days
  # Day#day_shift ::= Day x Int -> Day
  # Tests: (..)
  def day_shift(offset)
    self.to_day(DAY_NUM_CLASS_SEQ[DAY_SYM_CLASS_SEQ.index(self).+(offset) % WEEK_IN_DAYS])
  end
  
  def +(val)
    self.day_shift(+val)
  end
  
  def -(val)
    self.day_shift(-val)
  end
  
  def succ
    self.day_shift(+1)
  end
  
  def pred
    self.day_shift(-1)
  end
end