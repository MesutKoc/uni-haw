$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4/lib')

require 'ext_pr1_v4'
require 'constant'
require 'Day'

class DaySym < Day
   # SELECTORS
  def num; value end
  def values; DAY_SYM_SEQ end

  # PREDS
  def day_sym?; true end
  
  # DaySym#to_day_num ::= DaySym -> DayNum
  # Test: (DaySym[:MO]) => DayNum[1],
  #       (DaySym[:DI]) => DayNum[2]
  def to_day_num
    DAY_NUM_CLASS_SEQ[DAY_SYM_CLASS_SEQ.index(self)]
  end
  
  # DaySym#to_day_sym ::= DaySym -> DaySym
  # Test: (DaySym[:FR]) => DaySym[:FR],
  #       (DaySym['b']) => Err
  def to_day_sym
    self
  end
  
  # to_day ::= DayNum x Day -> DayNum
  # Test: {...}
  def to_day(day)
    day.to_day_sym
  end
end

# our seq map
DAY_SYM_CLASS_SEQ = DAY_SYM_SEQ.map{|sym| DaySym[sym]}