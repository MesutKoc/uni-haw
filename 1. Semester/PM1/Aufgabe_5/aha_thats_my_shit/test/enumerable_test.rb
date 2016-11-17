# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'enumerable'

class EnumerableTest < Test::Unit::TestCase
  def test_enumerable
    a=(1..7)
    assert_equal([1,2,3,4,5,6,7],(1..7).to_a_)
    assert_equal(['a','b','c','d'],('a'..'d').to_a_)
    assert_equal(a.map {|elem| elem+2}, a.map {|elem| elem+2})
    assert_equal(a.any? {|elem| elem < 0}, a.any? {|elem| elem < 0})
    assert_equal(a.max, a.max)
    assert_equal("horse",["albatross", "dog", "horse"].max)
    assert_equal({'c'=>2,'a'=>3,'b'=>1}, ['a','b','a','c','a','c'].freq_count)
  end
end
