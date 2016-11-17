# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
require 'ext_pr1_v4'

# volume of a hollow cube 
# hohl_wuerfel ::= Nat x Nat ->? Nat :: (l_außen, l_innen,n) ::::
# l_outer > l_inner  :::: l_outer**n - l_inner**n :: n >=1
# Test{ 
#       [0,0] => Err, [1,0] => 1, [1,2] => Err, [2,1] => 7, [3,2] => 19,
#       [3,-2]=> Err  
#      }

def hohl_wuerfel_n(l_außen, l_innen, n)
  check_pre((l_außen.nat? and l_innen.nat? and n.nat?))
  check_pre(((l_außen >= l_innen) and (n >=3)))
  volume_of_n(l_außen, n) - volume_of_n(l_innen, n)
end

def volume_of_n(l, n)
  l**n
end
