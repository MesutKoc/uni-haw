$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4/lib')
require 'ext_pr1_v4'
require 'constant'

class DaySym
   # CREATION
   def self.[](*args)
    check_inv(self.new(*args))
  end
 
  def initialize(sym) 
    @sym = sym
  end
  
  def invariant?() 
    DAY_SYM_SEQ.include?(self.sym)
  end
  
  # SELECTORS
  def day_sym; @sym end
  def num; to_day_num.num end
  
  # TYP
  def day; true end
  def day_sym?; true end
  
  # getter method
  attr_reader(:sym)
  
  # Äquivalenz
  def ==(obj)
    self.equal?(obj) or (obj.day_sym? and obj.sym == self.sym)
  end
  
  # DaySym#to_s ::= DaySym -> String
  # Test: (DaySym[:MO] => "DaySym[:MO]",
  #       (DaySym[:DI] => "DaySym[:DI])
  def to_s
    "DaySym[#{self.sym()}]"
  end
  
  # DaySym#to_day_sym ::= DaySym -> DaySym
  # Test: (DaySym[:FR]) => DaySym[:FR],
  #       (DaySym['b']) => Err
  def to_day_sym
     self
  end
  
  # DaySym#to_day_num ::= DaySym -> DayNum
  # Test: (DaySym[:MO]) => DayNum[1],
  #       (DaySym[:DI]) => DayNum[2]
  def to_day_num
    DAY_NUM_CLASS_SEQ[DAY_SYM_CLASS_SEQ.index(self)]
  end
  
  # to_day ::= DayNum x Day -> DayNum
  # Test: {...}
  def to_day(day)
    day.to_day_sym
  end
  
  # day shift for pred or succ any day
  # DaySym#day_shift ::= Day x Int -> Day
  # Test: {...}
  def day_shift(offset)
    self.to_day(DAY_NUM_CLASS_SEQ[DAY_SYM_CLASS_SEQ.index(self).+(offset) % WEEK_IN_DAYS])
  end
  
end

# our seq map
DAY_SYM_CLASS_SEQ = DAY_SYM_SEQ.map{|sym| DaySym[sym]}