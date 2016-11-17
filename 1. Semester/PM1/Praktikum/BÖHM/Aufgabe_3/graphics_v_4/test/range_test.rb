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
    #positive tests:
    assert_equal(true, Range1d[Point1d[1], Point1d[2]].range1d?)
    assert_equal(true, Range1d[Point1d[2], Point1d[4]].range1d?)
    #illegal tests:
    assert_raise(RuntimeError){Range1d[Point1d['y'],Point1d['y']].range1d?}
    assert_raise(RuntimeError){Range1d[Point1d[0.1],Point1d[0.l2]].range1d?}
    assert_raise(RuntimeError){Range1d[Point1d[:a],Point1d['y']].range1d?}
  end
  
  def test_include
   assert_equal(true, Range1d[Point1d[1], Point1d[2]].include?(Point1d[1]))
   assert_equal(true, R11.include?(P11))
 
  end
  
  def test_to_s
    #postive tests:
    assert_equal("Range1d[Point1d[-3], Point1d[-5]]", Range1d[Point1d[-3], Point1d[-5]].to_s)
    assert_equal("Range1d[Point1d[2], Point1d[3]]", Range1d[Point1d[2], Point1d[3]].to_s)
  end
 
   def test_to_a
    assert_equal([Point1d[-3], Point1d[-5]], Range1d[Point1d[-3], Point1d[-5]].to_a)
    assert_equal([Point1d[-2], Point1d[-3]], Range1d[Point1d[-2], Point1d[-3]].to_a)
    
  end
  
   def test_equi
     assert_equal(true, R21 == R21)
   end
 
   def test_translate
     assert_equal(R11, R12.translate(Point1d[-2]))
   end
   
   def test_equal_by_trans
     assert_equal(true, Point1d[2].equal_by_trans?(Point1d[5]))
     assert_equal(true, Range1d[Point1d[1], Point1d[3]].equal_by_trans?(Range1d[Point1d[0], Point1d[2]]))
     #noch machen
   end
end 
  


