
#Element of an Interval: Funktion proofs if value is between the upper and
#lower area.
#:: Z(int) ->? Z(int) :::: var, lower, upper :: lower < upper :: lower != upper 
#(lower<= val and val <=upper)? (True):(False)  
#Positive Tests :  [6,3,10]=>true , [4,5,10]=>false , [9,1,13]=>true , 
#[1,0,4]=>true , [-2,-5,1]=>true , [-6,-10,-1]=>true
#Illigal Types: [0,0,0]=>Err , [1,2,2]=>Err, [1,2,1]=>Err 
#Illegal Values : ['a','b','c']=>Err, [5.0,3.0,10.0]=>Err




$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extensions/lib')
require 'ext_pr1_v4'

def within(val,lower,upper)
  check_pre((((val.int?) and (lower.int?) and (upper.int?) )))
  check_pre(((lower != upper) and (lower < upper)))
  (lower <= val and val <=upper)?(TRUE):(FALSE)
  end
