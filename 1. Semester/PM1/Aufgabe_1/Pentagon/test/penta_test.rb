# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'pentagon'

class PentaTest < Test::Unit::TestCase
  DELTA=0.00001
  def test_penta
    
    #positive tests
    
    assert_in_delta(20.64572,penta(1.0),DELTA)
    assert_in_delta(82.58292,penta(2.0),DELTA)
    assert_in_delta(185.81156,penta(3.0),DELTA)
    
    #test illigal type 
    
    assert_raise(RuntimeError){penta("a")}
    assert_raise(RuntimeError){penta(-10.0)}
    
     # test illegal values of legal types
    assert_raise(RuntimeError){penta(0.0)}
    
  end
end
