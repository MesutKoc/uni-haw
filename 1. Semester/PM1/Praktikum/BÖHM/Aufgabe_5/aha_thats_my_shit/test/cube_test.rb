# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'cube'

class CubeTest < Test::Unit::TestCase
  def test_cube
    assert_equal(1,cube(1,0))
    assert_equal(4,cube(2,2))
    assert_equal(27,cube(3,3))
  end
end
