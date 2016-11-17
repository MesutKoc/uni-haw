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
  P15 = Point1d[5]
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
  U21 = Union2d[R21,R22]
  P21 = Point2d[P11,P12]
	
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
    assert_equal(false,U11.include?(P15))
  end
  
  def test_translate
    assert_equal(Union1d[Range1d[Point1d[5],Point1d[10]],Union1d[Range1d[Point1d[17],Point1d[19]],Range1d[Point1d[20],Point1d[22]]]],
      (Union1d[Range1d[Point1d[-5],Point1d[0]],Union1d[Range1d[Point1d[7],Point1d[9]],Range1d[Point1d[10],Point1d[12]]]]).translate(Point1d[10]))
    assert_equal(Union2d[Range2d[Range1d[Point1d[5],Point1d[5]],Range1d[Point1d[5],Point1d[5]]],Range2d[Range1d[Point1d[5],Point1d[5]],Range1d[Point1d[5],Point1d[5]]]],
      (Union2d[Range2d[Range1d[Point1d[4],Point1d[4]],Range1d[Point1d[4],Point1d[4]]],Range2d[Range1d[Point1d[4],Point1d[4]],Range1d[Point1d[4],Point1d[4]]]]).translate(Point2d[Point1d[1],Point1d[1]]))
    assert_equal(U21, U21.translate(P21))
  end 
end
