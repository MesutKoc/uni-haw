# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','ext_pr1/lib')
require 'ext_pr1_v4'

#zylinder volumen :: U=r**2*h*PI :: float x float x math::pi -> float :::: (r,h,math::pi)
#:: (r>=1 , h>=1)
#test {
#    [0/0] => err , [1/0] => err, [0/1] => err, [1/1]=>3,14 , [2/2]=>25,13
#    [-2/3]=>err
#}
 

def zylinder_volume(r,h)
  check_pre((r.float? and r >=1 and h.float? and h >=1))
  ((r**2) * h) * Math::PI
  
end
