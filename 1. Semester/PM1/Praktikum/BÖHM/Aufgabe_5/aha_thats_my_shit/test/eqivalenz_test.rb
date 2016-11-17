# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'equivalenz'

class EqivalenzTest < Test::Unit::TestCase
  def test_equivalenz
     a = Fullname["Max","Mustermann"]
    assert_equal(true, a == a)
    assert_equal(true, a == Fullname["Max","Mustermann"])
    assert_equal(false, a== Fullname["Peter","Pan"])
    assert_equal(false,Fullname["Peter","Pan"]== Fullname["Max","Mustermann"])
    assert_equal(0, a <=> a)
    assert_equal(0, a <=> Fullname["Max","Mustermann"])
    assert_equal(-1, a <=> Fullname["Peter","Pan"])
    end
end
