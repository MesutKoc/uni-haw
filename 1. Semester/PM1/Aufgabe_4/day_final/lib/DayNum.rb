$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_4/lib')

require 'ext_pr1_v4'
require 'constant'
require 'Day'

class DayNum < Day
  # SELECTORS
  def num; value end
  def values; DAY_NUM_SEQ end
  
  # PREDS
  def day_num?; true end
  
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
end

DAY_NUM_CLASS_SEQ = DAY_NUM_SEQ.map{|num| DayNum[num]}