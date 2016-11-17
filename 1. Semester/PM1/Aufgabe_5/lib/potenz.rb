$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib') 
# INCLUDES FOR THE TASK 5
# ============================
# Mesut K., Anton K.
require 'ext_pr1_v4'

# Pow with Endrecursion
# p ::= (n,n_potenz) :: Nat x Nat -> Nat 
# Test: {...}
def p(n, n_potenz)
  potenz_(1, n, n_potenz)
end

def potenz_(accu, n, n_potenz)
  check_pre((n.nat? and n_potenz.nat?))
  (n and n_potenz == 0 ? accu : potenz_(accu * n, n, n_potenz-1))
end

# Potenz with while
# p_while ::= (n, n_potenz) Nat x Nat -> Nat
# Test: {...}
def p2(n, n_potenz)
  accu = 1
  while not(n_potenz == 0)
    accu *= n
    n_potenz -= 1
  end
  accu
end

# POW with REDUCE
# p3(number, potenz) ::= Nat x Nat -> Nat
# Test: {...}
def p3(number, potenz)
  check_pre((number.nat? and potenz.nat?))
  [number].reduce(1){|res| res = number ** potenz}
end