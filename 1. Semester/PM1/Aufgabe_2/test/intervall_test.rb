# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'intervall'

class IntervallTest < Test::Unit::TestCase
  def test_interval
    #positive tests
    assert_equal(true,within(6,3,10))
    assert_equal(false,within(4,5,10))
    assert_equal(true,within(9,1,13))
    assert_equal(true,within(1,0,4))
    assert_equal(true,within(-2,-5,1))
    assert_equal(true,within(-6,-10,-1))
    
    #Illigal types
    assert_raise(RuntimeError){within(0,0,0)}
    assert_raise(RuntimeError){within(1,2,2)}
    assert_raise(RuntimeError){within(1,2,1)}
    #Illigal values
    
    assert_raise(RuntimeError){within('a','b','c')}
    assert_raise(RuntimeError){within(5.0,3.0,10.0)}
    
    end
end
