# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'array'

class ArrayTest < Test::Unit::TestCase
  def test_array
    #to_int
    assert_equal(1,[1].to_int)
    assert_equal(2,[2].to_int)
    assert_equal(-2345,[-2,3,4,5].to_int)
    #even_only
    assert_equal([4,6,12],[4,5,6,12].even_only)
    assert_equal([2,4],[1,2,3,4].even_only)
    #flatten
    assert_equal([],[].flatten)
    assert_equal([1,2,3],[1,[2,3]].flatten)
    #no_of_leaves
    assert_equal(5,[1,[2,3,[4]],5].no_of_leaves)
    assert_equal(0,[].no_of_leaves)
    #each_pair

  end
end
