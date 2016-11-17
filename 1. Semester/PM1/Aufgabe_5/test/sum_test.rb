$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'sum'

class SumTest < Test::Unit::TestCase
  def test_sum
    assert_equal(55,sum(10))
    assert_equal(66,sum(11))
    assert_equal(78,sum(12))
    assert_equal(91,sum(13))
    assert_equal(105,sum(14))
    assert_equal(1,sum(1))
    assert_equal(3,sum(2))
    assert_equal(6,sum(3))
    assert_equal(45,sum(9))
    assert_equal(1,sum(1))
    assert_equal(3,sum(2))
    # negativ
    assert_raise(RuntimeError){(sum('b'))}
  end
end
