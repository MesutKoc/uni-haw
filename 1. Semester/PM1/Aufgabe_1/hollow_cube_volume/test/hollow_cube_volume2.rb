# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'hollow_cube_volume2'

# Test [1,0,4] => 1, [2,1,3] => 7, [3,2,5] => , [0,0,0] => Err,
#       [3,-2,3]=> Err [0,0,0] =>Err



class HollowCubeVolumeTest < Test::Unit::TestCase
  def test_hollow_cube2
    
    #Test postiv
    assert_equal(1,hohl_wuerfel_n(1,0,4))
    assert_equal(7,hohl_wuerfel_n(2,1,3))
    assert_equal(211,hohl_wuerfel_n(3,2,5))
    #illigal Test
    assert_raise(RuntimeError) {hohl_wuerfel_n(3,-2,3)}
    assert_raise(RuntimeError) {hohl_wuerfel_n(0,0,0)}
  
end