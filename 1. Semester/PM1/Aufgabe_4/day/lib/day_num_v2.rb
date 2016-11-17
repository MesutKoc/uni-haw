$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4/lib')
require 'ext_pr1_v4'
require 'constant'

class DayNum
  def self.[](*args)
    check_inv(self.new(*args))
  end
 
  def initialize(num) 
    @num = num 
  end
  
  def invariant?() 
    self.num.in?(DAY_NUM_SEQ)
  end
  
  # SELECTORS
  def day_num; @num end
  def sym; to_day_sym.sym end;
  
  # PREDS
  def day?; true end;
  def day_num?; true end
  
  # getter methods
  attr_reader(:num)
 
  # Äquivalenz
  def ==(obj)
    self.equal?(obj) or (obj.day_num? and obj.num == self.num)
  end
  
  # DayNum#to_s ::= DayNum -> String
  # Test: (DayNum[5] => "DayNum[5]",
  #       (DayNum[1] => "DayNum[1])
  def to_s
    "DayNum[#{self.num()}]"
  end
 
  # DayNum#to_day_sym ::= DayNum -> DaySym
  # Test: (DayNum[5]) => DaySym[:Fr],
  #       (DayNum[9]) => Err
  def to_day_sym
     DAY_SYM_CLASS_SEQ[DAY_NUM_CLASS_SEQ.index(self)]
  end
  
  # DayNum#to_day_num ::= DayNum -> DayNum
  # Test: (DayNum[5]) => DayNum[5],
  #       (DayNum[2]) => DayNum[1]
  def to_day_num
    self
  end
  
  # to_day ::= DayNum x Day -> DayNum
  # Test: {...}
  def to_day(day)
    day.to_day_num
  end
  
  # DayNum#day_shift ::= Day x Int -> Day
  # Test: {...}
  def day_shift(offset)
    self.to_day(DAY_NUM_CLASS_SEQ[DAY_SYM_CLASS_SEQ.index(self).+(offset) % WEEK_IN_DAYS])
  end
  
end

# our seq map
DAY_NUM_CLASS_SEQ = DAY_NUM_SEQ.map{|num| DayNum[num]}