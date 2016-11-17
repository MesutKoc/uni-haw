$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'potenz'

class PotenzTest < Test::Unit::TestCase
  def test_foo
    assert_equal(4, p(2,2))
    assert_equal(p(2,2), p3(2,2))
    assert_equal(27, p3(3,3))
    assert_equal(27, p3(3,3))
    assert_equal(27, p3(3,3))
    assert_equal(27, p3(3,3))
    assert_equal(27, p3(3,3))
    assert_equal(27, p3(3,3))
    assert_equal(27, p3(3,3))
    assert_equal(27, p3(3,3))
    assert_equal(1, p(1,0))
    assert_equal(4, p(2,2))
    assert_equal(27, p(3,3))

    assert_raise(RuntimeError){ p(1.0, 2.0)}
  end
end
