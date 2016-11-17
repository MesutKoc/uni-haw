# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'day_sym_v2'
require 'day_num_v2'

class Day_sym_v2_test < Test::Unit::TestCase
  def test_class
    assert_nothing_raised {DaySym[:MO]}
    assert_nothing_raised {DaySym[:DI]}
    assert_raise(RuntimeError) {DaySym['b']}
  end
  def test_to_s
    assert_equal("DaySym[MO]", DaySym[:MO].to_s)
    assert_equal("DaySym[DI]", DaySym[:DI].to_s)
    assert_equal("DaySym[MI]", DaySym[:MI].to_s)
    assert_equal("DaySym[DO]", DaySym[:DO].to_s)
    assert_equal("DaySym[FR]", DaySym[:FR].to_s)
    assert_raise(RuntimeError){DaySym['f'].to_s}
  end
  
  def test_day_sym_to_day_num
    assert_equal(DayNum[1], DaySym[:MO].to_day_num)
  end
  
  def test_day_shift
    assert_equal(DaySym[:FR],DaySym[:DO].day_shift(1))
  end
end
