# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'fibonacci'

class FiboTest < Test::Unit::TestCase
  def test_fibo
    assert_equal(1,fibo(2))
    assert_equal(2,fibo(3))
    assert_equal(3,fibo(4))
    assert_equal(8,fibo(6))
  end
end
