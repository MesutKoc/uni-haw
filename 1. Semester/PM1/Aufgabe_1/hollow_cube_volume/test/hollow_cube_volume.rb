# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'hollow_cube_volume'

#[0,0] => Err, [1,0] => 1, [1,2] => Err, [2,1] => 7, [3,2] => 19,
#       [3,-2]=> Err
class HollowCubeVolumeTest < Test::Unit::TestCase
  def test_hollow_cube
    assert_equal(1,hohl_wuerfel(1,0))
    assert_equal(7,hohl_wuerfel(2,1))
    assert_equal(19,hohl_wuerfel(3,2))
  end
  def test_hollow_cube_err
    assert_raise(RuntimeError) {hohl_wuerfel(0,0)}
  end
end