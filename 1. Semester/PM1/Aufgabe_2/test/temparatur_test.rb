# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'temperatur'

class MinMaxTest < Test::Unit::TestCase
  def test_zu_kalt
    #positive tests
    assert_equal(true,zu_kalt?(15))
    assert_equal(true,zu_kalt?(14))
    assert_equal(false,zu_kalt?(17))
    
    #illigal values
    assert_raise(RuntimeError){zu_kalt?('a')}
  end
  
  def test_zu_warm
    assert_equal(false,zu_warm?(15))
    assert_equal(true,zu_warm?(22))

    #illigal values
    assert_raise(RuntimeError){zu_warm?('a')}
    assert_raise(RuntimeError){zu_warm?(25.0)}
  end
=begin
  def test_angenehm
    assert_equal(false, angenehm?(15))
    assert_equal(true, angenehm?(17))
    assert_equal(false, angenehm?(9))
    #2
    assert_equal(true, angenehm?(18))
    assert_equal(false, angenehm?(5))
    assert_equal(true, angenehm?(30))
    #3
    assert_equal(false, angenehm?(5))
    assert_equal(true, angenehm?(60))
    
    #illigal values
    assert_raise(RuntimeError){angenehm?('a')}
    assert_raise(RuntimeError){angenehm?(25.0)}
  end
=end
  
  def test_unangenehm
    assert_equal(true, unangenehm?(15))
    assert_equal(true, unangenehm?(1))
    
    #illigal values
    assert_raise(RuntimeError){angenehm?('a')}
    assert_raise(RuntimeError){angenehm?(25.0)}
  end
end
    