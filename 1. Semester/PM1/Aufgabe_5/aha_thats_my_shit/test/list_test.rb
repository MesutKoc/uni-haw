# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'list'

class ListTest < Test::Unit::TestCase
  def test_list
    assert_equal(List[],List[].list_rev)
    assert_equal(List[1,2,3],List[3,2,1].list_rev)
    assert_equal(List[3,2,1],List[1,2,3].list_rev)
    assert_equal(List[3,2,1],List[1,2,3].rev_while)
    assert_equal(List[3,2,1],List[1,2,3].rev_reduce)
  #  assert_equal(List[2,2],plus_list(2,2))
  end
end
