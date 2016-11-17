# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'point'
require 'Point1d'
require 'Point2d'

class PointTest < Test::Unit::TestCase
  def test_point2d
    #positive tests:
    assert_equal(true,Point2d[1,3].point2d?)
    assert_equal(true,Point2d[2,4].point2d?)
    assert_equal(true,Point2d[5,6].point2d?)
    assert_equal(true,Point2d[2,5].point2d?)
    assert_equal(true,Point2d[2,2].point2d?)
    #illegal tests:
    assert_raise(RuntimeError){Point2d['a','Ä'].point2d?}
    assert_raise(RuntimeError){Point2d['f','g'].point2d?}
    assert_raise(RuntimeError){Point2d['0.1','0.5'].point2d?}
    assert_raise(RuntimeError){Point2d[0.1,0.4].point2d?}
    assert_raise(RuntimeError){Point2d[:D,:T].point2d?}
   
  end
end
