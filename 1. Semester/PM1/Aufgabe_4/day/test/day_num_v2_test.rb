# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'day_num_v2'

class Day_num_v2_test < Test::Unit::TestCase
  def test_class
    assert_nothing_raised {DayNum[1]}
    assert_nothing_raised {DayNum[7]}
    assert_raise(RuntimeError) {DayNum['b']}
  end
  def test_to_s
    assert_equal("DayNum[1]", DayNum[1].to_s)
    assert_equal("DayNum[2]", DayNum[2].to_s)
    assert_equal("DayNum[3]", DayNum[3].to_s)
    assert_equal("DayNum[4]", DayNum[4].to_s)
    assert_equal("DayNum[5]", DayNum[5].to_s)
    assert_raise(RuntimeError){DayNum['f'].to_s}
  end
  
  def test_to_day_num
    assert_equal(DayNum[1], DayNum[1].to_day_num)
    assert_equal(DayNum[2], DayNum[2].to_day_num)
    assert_equal(DayNum[2], DayNum[2].to_day_num)
    assert_equal(DayNum[3], DayNum[3].to_day_num)
    assert_raise(RuntimeError){DayNum[-1].to_day_num}
  end
end
