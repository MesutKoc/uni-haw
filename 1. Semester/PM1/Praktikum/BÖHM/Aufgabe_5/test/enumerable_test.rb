# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'enumerable'

class EnumerableTest < Test::Unit::TestCase
  def test_foo
    a = (1..7)
    # map
    assert_equal(a.map {|elem| elem+2}, a.map_ {|elem| elem+2})
    # any
    assert_equal(true, [1,2,3,4,5].any_? {|elem| elem>0})
    assert_equal([1,2,3,4,5].any? {|elem| elem>0}, [1,2,3,4,5].any_? {|elem| elem>0})
    # max
    assert_equal("horse", ["albatross", "dog", "horse"].max_)
    assert_equal("test", ["leon", "test", "mesut"].max_)
    assert_equal("albatross", ["albatross", "dog", "horse"].max_ {|a,b| a.size <=> b.size })
    assert_equal(["albatross", "dog", "horse"].max {|a,b| a.size <=> b.size }, ["albatross", "dog", "horse"].max_ {|a,b| a.size <=> b.size })
    # freq_count
    assert_equal({'c'=>2,'a'=>3,'b'=>1}, ['a','b','a','c','a','c'].freq_count)
  end
end
