# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'fibonacci'

class FibonacciTest < Test::Unit::TestCase
  def test_foo
    assert_equal(2, fibonacci(3))
    assert_equal(fibonacci(3), fibonacci_it(3))
    assert_equal(2, fibonacci_it(3))
    assert_equal(fibonacci(5), fibonacci_it(5))
    assert_equal(1,fibonacci(2))
    assert_equal(2,fibonacci(3))
    assert_equal(3,fibonacci(4))
    
    # negativ tests
    #assert_raise(RuntimeError){(fibonacci('b'))}
  end
end
