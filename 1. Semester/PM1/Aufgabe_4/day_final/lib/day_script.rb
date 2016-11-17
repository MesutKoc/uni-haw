$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
require 'ext_pr1_v4'

# ==========================================
DAY_SYM_SEQ = [:MO,:DI,:MI,:DO,:FR,:SA,:SO]
DAY_NUM_SEQ = [1,2,3,4,5,6,7]
WEEK_IN_DAYS = DAY_SYM_SEQ.size
# ==========================================

def_class(:DaySym, [:sym]){
  def invariant?()
    self.sym.symbol? and DAY_SYM_SEQ.include?(self.sym)
  end
  
  # DaySym#to_day_sym ::= DaySym -> DaySym
  # Tests: ( (DaySym[:MO]) => DaySym[:MO],
  #          (DaySym[:DI]) => DaySym[:DI]
  #        )
  def to_day_sym
    self
  end
  
  # DaySym#to_day_num ::= DaySym -> DayNum
  # Tests: (
  #          (DaySym[:MO] => DayNum[1]
  #          (DaySym[:SO] => DayNum[7]
  def to_day_num
    DAY_NUM_CLASS_SEQ[DAY_SYM_CLASS_SEQ.index(self)]
  end
  
  # to_day ::= DaySym x Day -> DaySym
  # Tests: {...}
  def to_day(day)
    day.to_day_sym
  end
  
  # day_shift for offset in days
  # day_shift ::= Day x Int -> Day ::
  # Tests: {...}
  def day_shift(offset)
    self.to_day(DAY_NUM_CLASS_SEQ[DAY_SYM_CLASS_SEQ.index(self).+(offset) % WEEK_IN_DAYS])
  end
}

def_class(:DayNum, [:num]) {
  def invariant?
    self.num.nat? and DAY_NUM_SEQ.include?(self.num)
  end

  # DayNum#to_day_sym ::= DayNum -> DaySym
  # Tests(
  #   (DayNum[1]) => DaySym[:Mo],
  #   (DayNum[7]) => DaySym[:So])
  def to_day_sym
    DAY_SYM_CLASS_SEQ[DAY_NUM_CLASS_SEQ.index(self)]
  end

  # DayNum#to_day_num ::= DayNum -> DayNum
  # Tests(
  #   (DayNum[1]) => DayNum[1],
  #   (DayNum[7]) => DayNum[7])
  def to_day_num
    self
  end
  
  # to_day ::= DayNum x Day -> DaySym
  # Tests: {...}
  def to_day(day)
    day.to_day_num
  end
  
  # day_shift for offset in days
  # day_shift ::= Day x Int -> Day ::
  # Tests: {...}
  def day_shift(offset)
    self.to_day(DAY_NUM_CLASS_SEQ[DAY_SYM_CLASS_SEQ.index(self).+(offset) % WEEK_IN_DAYS])
  end
} 

# our seq map
DAY_SYM_CLASS_SEQ = DAY_SYM_SEQ.map{|sym| DaySym[sym]}
DAY_NUM_CLASS_SEQ = DAY_NUM_SEQ.map{|num| DayNum[num]}