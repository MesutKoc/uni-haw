# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'union2d'

class Union2dTest < Test::Unit::TestCase
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
  P21 = Point2d[P11,P12]
  
  U2 = Union2d[Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]],Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]]]
  U21 = Union2d[R21, R21]
  def test_equal
    assert_equal(true, Union2d[Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]],
                       Union2d[Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]],
                               Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]]]] == 
                       Union2d[Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]], Union2d[Range2d[Range1d[Point1d[0],Point1d[0]],
                                       Range1d[Point1d[0],Point1d[0]]],Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]]]])
    
  end
  
  def test_translate
    
  end
  
  def test_include?
   assert_equal(true,(Union2d[Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]],Range2d[Range1d[Point1d[0],Point1d[0]],Range1d[Point1d[0],Point1d[0]]]]).include?(Point2d[Point1d[0],Point1d[0]]))
   assert_equal(true,(Union2d[Range2d[Range1d[Point1d[1],Point1d[1]],Range1d[Point1d[1],Point1d[1]]],Range2d[Range1d[Point1d[1],Point1d[1]],Range1d[Point1d[1],Point1d[1]]]]).include?(Point2d[Point1d[1],Point1d[1]]))
   assert_equal(true,(Union2d[Range2d[Range1d[Point1d[2],Point1d[2]],Range1d[Point1d[2],Point1d[2]]],Range2d[Range1d[Point1d[2],Point1d[2]],Range1d[Point1d[2],Point1d[2]]]]).include?(Point2d[Point1d[2],Point1d[2]]))
   assert_equal(false, U21.include?(P21))
  end
  
  end
