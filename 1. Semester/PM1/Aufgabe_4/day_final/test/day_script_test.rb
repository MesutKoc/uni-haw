$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'day_script'

class Day_script_test < Test::Unit::TestCase
  def test_to_day_sym
    assert_equal(DaySym[:MO],DaySym[:MO].to_day_sym)
    assert_equal(DaySym[:DI],DaySym[:DI].to_day_sym)
  end
  
  def test_to_day_num
    assert_equal(DayNum[1], DayNum[1].to_day_num)
    assert_equal(DayNum[7], DayNum[7].to_day_num)
    assert_equal(DayNum[1], DaySym[:MO].to_day_num)
    assert_equal(DayNum[7], DaySym[:SO].to_day_num)
  end
  
  def test_to_day
    assert_equal(DaySym[:DI], DaySym[:DI].to_day(DayNum[2]))
    assert_equal(DaySym[:SO], DaySym[:MO].to_day(DayNum[7]))
    assert_equal(DaySym[:DO], DaySym[:DO].to_day(DaySym[:DO]))
    assert_equal(DayNum[3], DayNum[3].to_day(DayNum[3]))
    assert_equal(DayNum[1], DayNum[1].to_day(DaySym[:MO]))
    assert_equal(DayNum[2], DayNum[1].to_day(DaySym[:DI]))
  end
  
  def test_day_shift
    assert_equal(DaySym[:FR],DaySym[:DO].day_shift(1))
    assert_equal(DaySym[:DI],DaySym[:MO].day_shift(1))
    assert_equal(DaySym[:DI], DaySym[:MI].day_shift(-1))
    assert_equal(DaySym[:MI], DaySym[:MI].day_shift(0))
    assert_equal(DaySym[:DO], DaySym[:MI].day_shift(1))
    assert_equal(DaySym[:DI], DaySym[:MI].day_shift(6))
  end
end
