# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'Range1d'
require 'Range2d'

class RangeTest < Test::Unit::TestCase
  P11 = Point1d[1]
	P12 = Point1d[2]
	P13 = Point1d[3]
	P14 = Point1d[4]
	R11 = Range1d[P11, P12]
	R12 = Range1d[P13, P14]
	R13 = Range1d[P11, P13]
	R14 = Range1d[P12, P14]
	U11 = Union1d[R11, R12]
	U12 = Union1d[R13, R14]
	U13 = Union1d[R11, R13]
	U14 = Union1d[R12, R14]
	
	R21 = Range2d[R11, R12]
	R22 = Range2d[R13, R14]
	R23 = Range2d[R12, R14]
	R24 = Range2d[R11, R13]

  def test_range
    assert_equal(true, Range1d[Point1d[1], Point1d[2]].range1d?)
    assert_equal(true, Range2d[Range1d[Point1d[2],Point1d[3]],
                               Range1d[Point1d[2],Point1d[3]]].range2d?)
  end
  def test_include
   assert_equal(true, Range1d[Point1d[1], Point1d[2]].include?(Point1d[1]))
   assert_equal(true, R11.include?(P11))
   assert_equal(R11, R12.translate(Point1d[-2]))
  end
  
  
  def test_to_s
    assert_equal("Range2d[Range1d[Point1d[-3], Point1d[-5]], Range1d[Point1d[-3], Point1d[5]]]", Range2d[Range1d[Point1d[-3], Point1d[-5]], Range1d[Point1d[-3], Point1d[5]]].to_s)

  end
end
