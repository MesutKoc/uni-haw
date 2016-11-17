# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'min_max'

class MinMaxTest < Test::Unit::TestCase
  def test_min_max
    #positive tests
    assert_equal(1,min_int(2,1))
    assert_equal(2,max_int(1,2))
    assert_equal(3,min_int(4,3))
    assert_equal(4,max_int(3,4))
    assert_equal(1,max_int(-2,1))
    
    #illigal types
    assert_raise(RuntimeError){min_int(1,1)}
    assert_raise(RuntimeError){max_int(0,0)}
    
    #illigal values
    assert_raise(RuntimeError){min_int('a','b')}
    
  end
end
    