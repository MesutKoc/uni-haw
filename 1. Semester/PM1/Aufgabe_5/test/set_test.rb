# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'sets'

class SetTest < Test::Unit::TestCase
  def test_foo
    assert_equal(true, Set[1,2,3,4].superset?(Set[1]))
    assert_equal(true,Set[1,2,3,4].superset?(Set[1,2]))
    assert_equal(true,Set[].superset?(Set[]))
    assert_equal(false,Set[1,3,4].superset?(Set[1,2]))
    assert_equal(true,Set[1,2,3]==(Set[1,2,3]))
  end
end
