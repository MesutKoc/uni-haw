# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'array'

class ArrayTest < Test::Unit::TestCase
  def test_foo
    # to_int
    assert_equal(123, [1,2,3].to_int)
    assert_equal(223, [2,2,3].to_int)
    assert_equal(143, [1,4,3].to_int)
    assert_equal(103, [1,0,3].to_int)
    assert_equal(-123, [-1,2,3].to_int)
    #to_a
    assert_equal([1, 2, 3, 4, 5, 6, 7],(1..7).to_a)
    assert_equal(['a','b','c','d'],('a'..'d').to_a)

    # even_only
    assert_equal([2,4], [1, 2, 3, 4].even_only)
    
    # flatten
    assert_equal([1,2,3,4,5], [1,[2,3,[4]],5].flatten_)
    assert_equal([1,[2,3,[4]],5].flatten, [1,[2,3,[4]],5].flatten_)
    
    # no_of_leaves
    assert_equal(0, [].no_of_leaves)
    assert_equal(2, [1,2].no_of_leaves)
    assert_equal(3, [1,[2,3]].no_of_leaves)
    assert_equal(2, [1,List[2,3]].no_of_leaves)
    assert_equal(5, [1,[2,3,[4]],5].no_of_leaves)
    # each_pair
 
    sum_of_sum = 0
    [[1,3],[1,5],[1,2]].each_pair {|y,x| sum_of_sum = sum_of_sum + y + x}
    assert_equal(13, sum_of_sum)

  end
end
