# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'day'

class DayTest < Test::Unit::TestCase
  def test_day
    assert_equal(true,day?(DaySym[:MO]))
    assert_equal(DaySym[:DI],to_day(DaySym[:MO],DaySym[:DI]))
    assert_equal(DaySym[:MI],to_day(DaySym[:MO],DaySym[:MI]))
#    assert_equal(DayNum[2],to_day(DayNum[1],DaySym[:DI]))
  #  assert_equal(DaySym[:SA], day_shift(DaySym[:MO],-2))
  end
end
