# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'Day'
require 'DayNum'
require 'DaySym'

class DayTest < Test::Unit::TestCase
  def test_to_s
    assert_equal("DayNum[1]", DayNum[1].to_s)
    assert_equal("DayNum[2]", DayNum[2].to_s)
    assert_equal("DayNum[3]", DayNum[3].to_s)
    assert_equal("DayNum[4]", DayNum[4].to_s)
    assert_equal("DayNum[5]", DayNum[5].to_s)
  end
  def test_day_shift
    assert_equal(DayNum[2],DayNum[1].day_shift(1))
    assert_equal(DayNum[4],DayNum[1].day_shift(3))
    assert_equal(DayNum[5],DayNum[1].day_shift(-3))
    
    assert_equal(DayNum[2],DayNum[1].+(1))
    assert_equal(DayNum[2],DayNum[1].succ)
    assert_equal(DayNum[1],DayNum[2].pred)
  end
end
