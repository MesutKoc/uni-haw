# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
require 'ext_pr1_v4'

DAY_NUM=[1,2,3,4,5,6,7]
DAY_SYM=[:MO,:DI,:MI,:DO,:FR,:SA,:SO]
DAY_END=7 


#day? checks if the objekt we get is a Day
#day?:: Any -> Bool :: Day_num? or Day_Sym?
#Tests: (2.day?) => true , (:SO.day?)=> true,
#(Hallo.day?) => false, (14.day?) => false, (14.0.day?) => false
def day?(arr)
#check_pre((arr.any? true))  
( day_num?(arr) or day_sym?(arr))
end

#day_say? checks if the objekt we get is a symbol
#day_sym?::= Any -> Bool
#Tests: (SO.day_sym?) => true , (:MO.day_sym?)=> true,
#(1.day_sym?) => false, (Hallo.day_sym?) => false, 
#(14.0.day_sym?) => false
def day_sym?(arr)  
#check_pre ((arr.any? true)) 
DAY_SYM.include?(arr)
end

#day_num? checks if the objekt we get is a numeric 
#day_num?::= Any -> Bool
#Tests: (2.day_num?) => true , (:MO.day_sym?)=> false,
#(1.day_sym?) => true, (Hallo.day_sym?) => false, 
#(14.0.day_sym?) => false
def day_num?(arr)
 #check_pre(arr.any? true) 
 DAY_NUM.include?(arr)
end

#day_num_to_day_sym converts the numeric obj into a symbol 
#day_num_to_day_sym::= day_num -> day_sym
#Tests: (2.day_num_to_day_sym) => :DI , (3.day_num_to_day_sym)=> :MI,
#(8.day_num_to_day_sym) => Err, (Hallo.day_num_to_day_sym?) => Err, 
#(MO.day_num_to_day_sym) => Err
def day_num_to_day_sym(arr)
  check_pre(day_num?(arr))
  DAY_SYM[count?(2,arr)]
end

#day_sym_to_day_num converts a symbol into a numeric obj 
#day_sym_to_day_num::= day_sym -> day_num
#Tests: (DI.day_sym_to_day_num) => 2 , (MI.day_sym_to_day_num)=> 3,
#(8.day_sym_to_day_num) => Err, (Hallo.day_sym_to_day_num?) => Err, 
#(5.day_sym_to_day_num) => Err
def day_sym_to_day_num(arr)
  check_pre(day_sym?(arr))
  count?(1,DAY_SYM.index(arr))
end
#to_day_sym converts only if the obj is a numeric , else it does nothing
#to_day_sym::= day_sym -> true or day_num -> day_sym
#Tests : (DI.to_day_num) => true, (1.to_day_num) => MO, (2.to_day_num) => DI,
#(MI.to_day_num) => true , (Hallo.to_day_sym) => Err , (0.to_day_sym) => Err
def to_day_sym?(arr)
   check_pre(day?(arr))
  (day_sym?(arr)? true: day_num_to_day_sym(arr))
end

#to_day_num converts only if the obj is a symbol , else it does nothing
#to_day_num::= day_num -> true or day_sym -> day_num
#Tests : (DI.to_day_num) => true, (1.to_day_num) => MO, (DI.to_day_num) => 2,
#(DO.to_day_num) => 4 (Hallo.to_day_num) => Err , (0.to_day_num) => Err
def to_day_num?(arr)
  check_pre(day?(arr))
  (day_num?(arr)? true: day_sym_to_day_num(arr))
end

#day_num_succ counts the next day
#day_num_succ::= day_num -> day_num
#Tests : (1.day_num_succ) => 2, (2.day_num_succ) => 3, (3.day_num_succ) => 4,
#(1.day_num_succ) => 7 (Hallo.day_num_succ) => Err (8.day_num_succ) => Err,
#(0.day_num_succ) => Err
def day_num_succ(arr) 
  check_pre(day_num?(arr)) 
  (count?(1, count?(2, arr.succ) % DAY_END))
end

#day_num_pred counts the day before
#day_num_pred::= day_num -> day_num
#Tests : (5.day_num_pred) => 4, (2.day_num_pred) => 1, (1.day_num_pred) => 7,
#(3.day_num_pred) => 2, (Hallo.day_num_pred) => Err, (8.day_num_pred) => Err,
#(0.day_num_pred) => Err
def day_num_pred(arr)     
  check_pre(day_num?(arr)) 
  #Möglichkeit 1:
 (count?(1, count?(2, arr.pred) % DAY_END))
 #Bosstransformation:
 #(count?(1, count?(2, count?(2, count?(2,arr.succ)))% DAY_END))
end

#day_sym_succ counts the next day 
#day_sym_succ::= day_sym -> day_sym
#Tests : (MO.day_sym_succ) => DI, (SO.day_sym_succ) => MO, (DI.day_sym_succ) => MI,
#(MI.day_sym_succ) => DO, (Hallo.day_sym_succ) => Err, (8.day_sym_succ) => Err,
#(0.day_sym_succ) => Err
def day_sym_succ(arr) 
  check_pre(day_sym?(arr))
  day_num_to_day_sym(day_num_succ(day_sym_to_day_num(arr)))
end

#day_sym_pred counts the day after 
#day_sym_pred::= day_sym -> day_sym
#Tests : (FR.day_sym_pred) => DO, (MO.day_sym_pred) => SO, 
#(DI.day_sym_pred) => MO, (Hallo.day_sym_pred) => Err, (8.day_sym_pred) => Err 
#(0.day_sym_pred) => Err
def day_sym_pred(arr) 
  check_pre(day_sym?(arr))
  day_num_to_day_sym(day_num_pred(day_sym_to_day_num(arr)))
end

#day_succ counts the next day with a numeric obj. or with a symbol  
#day_succ::= day -> day 
#Tests : (7.day_succ) => 1, (SO.day_succ) => MO, (1.day_succ) => 2,
#(MI.day_succ) => DO, (Hallo.day_succ) => Err, (8.day_succ) => Err 
#(0.day_succ) => Err
def day_succ(arr)
  check_pre(day?(arr))  
  if day_num?(arr) then day_num_succ(arr)
  elsif day_sym?(arr) then day_sym_succ(arr)
  else check_pre (false)
  end
end

#day_pred counts the day after with a numeric obj. or with a symbol  
#day_pred::= day -> day 
#Tests : (3.day_pred) => 2, (MO.day_pred) => SO, (1.day_pred) => 7,
#(MI.day_pred) => DI, (Hallo.day_pred) => Err, (8.day_pred) => Err, 
#(0.day_pred) => Err 
def day_pred(arr)
  check_pre(day?(arr))
  if day_num?(arr) then day_num_pred(arr)
  elsif day_sym?(arr) then day_sym_pred(arr)
  else check_pre (false)
  end
end

def count?(mod, index)
  ((mod == 1 ? index+1:index-1))
end
