# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'Union1d'
require 'Union2d'

class UnionTest < Test::Unit::TestCase
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
	
  def test_creation
    assert_nothing_raised {Union1d[Range1d[Point1d[-3], Point1d[-5]], Range1d[Point1d[-3], Point1d[5]]]}
    assert_nothing_raised {Union1d[Range1d[Point1d[3], Point1d[-5]], Range1d[Point1d[3], Point1d[5]]]}
    # negative tests
    assert_raise(ArgumentError) {Union1d[Range1d[Point1d[-3], Point1d[-5]]]}
  end
  def test_left
    assert_equal(true, U11 == U11)
    assert_equal(true, U12 == U12)
    assert_equal(true, U14 == U14)
  end
  
  def test_to_s
    assert_equal("Union1d[Range1d[Point1d[1], Point1d[3]], Range1d[Point1d[2], Point1d[4]]]", U12.to_s)
  end
  def test_include
    assert_equal(true, U11.include?(P11))
  end
end
