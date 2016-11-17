# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'reverse'

class ReverseTest < Test::Unit::TestCase
  def test_foo
    assert_equal(List[], List[].reverse_endrec)
    assert_equal(List[2,3], List[3,2].reverse_endrec)
    assert_equal(List[2,3], List[3,2].reverse_reduce)
    assert_equal(List[1,2], List[1] + List[2])
    assert_equal(List[1,2,3], List[1,2] + List[3])
    assert_equal(List[],List[].reverse_reduce)
    assert_equal(List[1,2,3],List[3,2,1].reverse_reduce)
    assert_equal(List[3,2,1],List[1,2,3].reverse_reduce)
  end
end
