$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
require 'ext_pr1_v4'


DAY_SYM=[:MO,:DI,:MI,:DO,:FR,:SA,:SO]
DAY_NUM=[1,2,3,4,5,6,7]
WEEK_IN_DAYS=DAY_SYM.size




def_class(:DaySym, [:sym]){
  def invariant?()
    #self.sym.symbol? and DAY_SYM.include?(sym)
    DAY_SYM.include?(self.sym)
  end
}

def_class(:DayNum, [:num]){
  def invariant?()
    #self.num.num? and DAY_NUM.include?(num)
    self.num.nat? and DAY_NUM.include?(self.num)
  end
} 


def count?(mod, index)
  ((mod == 1 ? index+1:index-1))
end


def day?(days)
#check_pre((arr.any? true))  
#(DayNum(1) =>
days.day_sym? or days.day_num?
#( day_num?((days)) or day_sym?((days)))
end

def day_sym?(days)  
#check_pre ((arr.any? true)) 
DAY_SYM.include?(days)
end

def day_num?(days)
 #check_pre(arr.any? true) 
 DAY_NUM.include?(days)
end

def day_sym_to_day_num(days)
  check_pre(day_sym?(days))
  count?(1,DAY_SYM.index(days))
end

def day_num_to_day_sym(days)
  check_pre(day_num?(days))
  DAY_SYM[count?(2,days)]
end

def to_day_sym?(days)
  check_pre(day?(days))
  (days.day_sym? ? days: day_num_to_day_sym(days))
end

def to_day_num?(days)
  check_pre(day?(days))
  (days.day_num? ? days: day_sym_to_day_num(days))
end

#to_day:= Day x Day -> Day
def to_day(proto_day, days)
#  check_pre((day?(days) and day?(proto_day)))
if    (proto_day.day_sym?) then to_day_sym?(days)
elsif (proto_day.day_num?) then to_day_num?(days)
else  check_pre(false)
  end
end


#day_shift ::= Day x Int -> Day
def day_shift(days, val)
  check_pre(day?(days)) and (val.int?)
  to_day(days,DAY_SYM[((DAY_NUM.index(days) + val) % WEEK_IN_DAYS)])
end

