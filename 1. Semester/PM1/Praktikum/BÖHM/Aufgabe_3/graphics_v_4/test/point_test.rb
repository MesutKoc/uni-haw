# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'point'
require 'Point1d'
require 'Point2d'

class PointTest < Test::Unit::TestCase
  def test_point1d
    #positive tests:
    assert_equal(true,Point1d[1].point1d?)
    assert_equal(true,Point1d[2].point1d?)
    assert_equal(true,Point1d[3].point1d?)
    assert_equal(true,Point1d[4].point1d?)
    #illegal tests:
    assert_raise(RuntimeError){Point1d['a'].point1d?}
    assert_raise(RuntimeError){Point1d['b'].point1d?}
    assert_raise(RuntimeError){Point1d[0.1].point1d?}
    assert_raise(RuntimeError){Point1d[4.5].point1d?}
    assert_raise(RuntimeError){Point1d[:d].point1d?}
    
  end
end
