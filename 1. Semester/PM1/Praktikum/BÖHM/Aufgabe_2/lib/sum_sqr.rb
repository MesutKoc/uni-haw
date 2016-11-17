$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extensions/lib')
require 'ext_pr1_v4'
 
# squared the largest element  
# larger_sum_sqr::=(val)
#             :: Array[int,int,int] x Array[int,int,int] ->? Int :: (square_sum(get_two_max(val))
# Test: {
#         [1,2,3] => 13, [3,2,1] => 13, [3,2,3] => 18,
#         [4,2,3] => 25, [1,1,1] => 2, [0,0,0] => 0, 
#         [1,2,2] => Err, ['a','b','c'] => Err, 
#         [5.0,3.0,10.0] => Err
#       }
def larger_sum_sqr(val)
  check_pre((val.array? ))
  square_sum(get_two_max(val))
end
 
# get two max elements from an array 
# get_two_max::=(arr)
#             :: Array[int, int] x Bool ->? Bool :: 
# Fall 1: Arr-Index[0-3]: [0] > [1] and [1] > [2] then [0] and [1]
# Fall 2: Arr-Index[0-3]: [0] < [1] and [1] < [2] then [1] and [2]                      
# Fall 2: Arr-Index[0-3]: [0] > [1] and [1] < [2] then [0] and [2]
# Test: {
#         [1,2,3] => [2,3], [1,0,3] => [1,3], [2,6,8] => [6,8],
#         [4,2,3] => 25, [1,1,1] => 2, [0,0,0] => 0, 
#         [0,5,2] => Err, ['a','b','c'] => Err, 
#         [5.0,3.0,10.0] => Err
#       }
def get_two_max(arr)
 check_pre((arr[0-2].int?))
    if(arr[0] >= arr[1] and arr[1] >= arr[2]) then [arr[0], arr[1]]
 elsif(arr[0] <= arr[1] and arr[1] <= arr[2]) then [arr[1], arr[2]]
 elsif(arr[0] >= arr[1] and arr[1] <= arr[2]) then [arr[0], arr[2]]
 else check_pre(false)
 end
end
 
# square a integer
# square::=(val)
#             :: Int x Int ->? Int :: (val ** 2)
# Test: {[6] => 36, [7] => 49, [1] => 1, ['a'] => Err}
def square(int_val)
  check_pre(((int_val.int?)))
  int_val ** 2
end

# square a integer
# square::=(val)
#             :: Array[Int] x Array[Int] ->? Int :: (square(val) + square(val))
# Test: {
#         [6,2] => 40, [8,2] => 68, [1,1] => 2, 
#         [-5,5] => 50, ['a', 'b'] => Err
#        }
def square_sum(val)
  check_pre((val[0-1].int?))
  square(val.first) + square(val.last)
end