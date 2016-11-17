$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extensions/lib')
require 'ext_pr1_v4'

LIMIT_TEMP_HIGH = 21
LIMIT_TEMP_LOW = 16

# proofs for temparatures if too cold
# zu_kalt?::=(temp)
#        :: Int x Int ->? Bool :: (((temp < LIMIT_TEMP_LOW)))
# Test: {
#         [15] => true, [14] => true, [17] => false, ['a'] => Err
#       }
def zu_kalt?(temp)
  check_pre((temp.int?))
  (temp < LIMIT_TEMP_LOW)?(true):(false)
end

# proofs for temparatures if too hot
# zu_warm?::=(temp)
#        :: Int x Int ->? Bool :: ((temp > LIMIT_TEMP_HIGH))
# Test: {
#       [15] => false, [22] => true, [25.0] => Err, [12] => false
#       }
def zu_warm?(temp)
  check_pre((temp.int?))
  (temp > LIMIT_TEMP_HIGH)?(true):(false)
end

# proofs for temparatures if temp = pleasant
# angenehm?::=(temp)
#        :: Int x Int ->? Bool :: 
#        not(zu_kalt?(temp) or zu_warm?(temp)) 
#        not(zu_kalt?(temp) and not(zu_warm?(temp)))
#        ((zu_kalt?(temp))? false: ((zu_warm?(temp))
# Test: {
#       (1) [15] => false, [17} => true, [9] => false, ['z'] => false 
#       (2) [18] => true, [5] = false, [30] => true
#       (3) [5] => false, [60} => true
#       }
def angenehm?(temp)
  check_pre((temp.int?))
  not(zu_kalt?(temp) or zu_warm?(temp))?(true):(false)
  #not(zu_kalt?(temp) and not(zu_warm?(temp)))?(true):(false)
  #((zu_kalt?(temp))? false: ((zu_warm?(temp))? true:false))
end

# proofs for temparatures if temp unpleasant
# unangehem?::=(temp)
#        :: Int x Int ->? Bool :: ( not(angenehm(temp) )
# Test: {
#     
#       }
def unangenehm?(temp)
  check_pre((temp.int?))
  not(angenehm?(temp))
end