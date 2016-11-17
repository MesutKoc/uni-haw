$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'sum_sqr'

class Sum_quad_test < Test::Unit::TestCase
  def test_larger_sum_sqr
    #Positive Tests
    assert_equal(13, larger_sum_sqr([1,2,3]))
    assert_equal(13, larger_sum_sqr([3,2,1]))
    assert_equal(18, larger_sum_sqr([3,2,3]))
    assert_equal(25, larger_sum_sqr([4,2,3]))
    assert_equal(2, larger_sum_sqr([1,1,1]))
    assert_equal(0, larger_sum_sqr([0,0,0]))
    #negative Tests
    assert_raise(RuntimeError) {larger_sum_sqr([5.0,3.0,10.0])}
    assert_raise(RuntimeError) {larger_sum_sqr(['a','b','c'])}
  end
  
  def test_get_two_max
    assert_equal([2,3],get_two_max([1,2,3]))
    assert_equal([1,3],get_two_max([1,0,3]))
    assert_equal([6,8],get_two_max([2,6,8]))
    #negative Tests
    assert_raise(RuntimeError) {get_two_max([0,5,2])}
    assert_raise(RuntimeError) {get_two_max([5.0,3.0,10.0])}
    assert_raise(RuntimeError) {get_two_max(['a','b','c'])}
  end
  
  def test_square
    assert_equal(36, square(6))
    assert_equal(49, square(7))
    assert_equal(1, square(1))
    #negativ Test
    assert_raise(RuntimeError) {square('a')}
  end
  
  def test_square_sum
    assert_equal(40, square_sum([6,2]))
    assert_equal(68, square_sum([8,2]))
    assert_equal(2, square_sum([1,1]))
    assert_equal(50, square_sum([-5,5]))
    #negativ Test
    assert_raise(RuntimeError) {square_sum(['a','b'])}
  end
end
