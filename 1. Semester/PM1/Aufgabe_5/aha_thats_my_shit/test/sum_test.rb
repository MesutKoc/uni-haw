# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'sum'

class RekursionTest < Test::Unit::TestCase
  def test_sum
    
   assert_equal(1,sum(1))
   assert_equal(3,sum(2))
  end
end
