#Min Max Task: Funktion delivers min and max value.
#:: Z(int) ->? Z(int) :::: (int1!=int2) :: (int1>int2)? (int2):(int1):: 
#(int1>int2)? (int2):(int1)  
#Positive Tests :  [2,1]=>1 , [1,2]=>2 , [4,3]=>3 , [3,4]=>4 , [-2,1]=>1
#Illigal Types: [1,1]=>Err , [0,0]=>Err 
#Illegal Values : ['a','b']=>Err

$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extensions/lib')
require 'ext_pr1_v4'

def min_int(a,b)
  check_pre((a.int? and b.int?)) 
  check_pre((a!=b))
  (a>b)? (b):(a) 
end

def max_int(a,b)
check_pre((a.int? and b.int?)) 
check_pre((a!=b))
(a<b)? (b):(a)
end


