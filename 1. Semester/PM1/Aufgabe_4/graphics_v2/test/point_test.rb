# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'point'
require 'Point1d'
require 'Point2d'

class PointTest < Test::Unit::TestCase
  def test_point
    assert_equal(true,Point1d[1].point1d?)
    assert_raise(RuntimeError){Point1d['a'].point1d?}
    assert_equal(true, Point2d[2,2].point2d?)
    assert_raise(RuntimeError){Point2d['a','Ä'].point2d?}
  end
end
