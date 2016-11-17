# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'bounds'
require 'equals'
require 'include'
require 'methods'
require 'translate'
require 'types'

class BoundsTest < Test::Unit::TestCase
  def test_bounds
    assert_equal(Range1d[Point1d[1],Point1d[3]], bounds(Union1d[Range1d[Point1d[1],Point1d[2]], Range1d[Point1d[2],Point1d[3]]]))
  end
  def test_translate
    assert_equal(Range1d[Point1d[2],Point1d[3]], translate(Range1d[Point1d[1],Point1d[2]],Point1d[1]))
  end
  def test_equal_by_trans
    assert_equal(true, equal_by_trans?(Point1d[2],Point1d[3]))
    assert_equal(true, equal_by_trans?(Point1d[0],Point1d[3]))
    assert_equal(true, equal_by_trans?(Point2d[Point1d[2],Point1d[1]],Point1d[3]))
  end
end
