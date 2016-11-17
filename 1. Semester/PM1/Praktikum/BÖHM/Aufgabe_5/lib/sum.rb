$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib') 
# INCLUDES FOR THE TASK 5
# ============================
# Mesut K., Anton K.
require 'ext_pr1_v4'

# sum calculates the given number
# sum ::= (n) :: Nat -> Nat 
# Test: {..}
def sum(n)
  sum_(0, n)
end

# sum with Endrecursion
def sum_(accu, n)
  check_pre((n.nat?))
  (n == 0 ? accu: sum_(accu + n, n-1))
end

# Sum with while 
# Test: {...}
def sum_while(n)
  accu = 0
  while not(n == 0)
    accu += n
    n -= 1
  end
  accu
end

# sum with reduce
# Test: {...}
def sum_reduce(n)
  check_pre((n.nat?))
  n.reduce(0) {|accu,elem| accu +=elem}
end