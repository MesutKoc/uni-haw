# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'clock2'

class ClockTest2 < Test::Unit::TestCase
  def test_clock2
   #positive tests
    assert_equal([9, 0, 0], set_clock_analog([19, 0, 0],[2, 0, 0], 12))
    assert_equal([15, 1, 0], set_clock_analog([14, 59, 59],[0, 1, 1], 24))
    assert_equal([2, 31, 0], set_clock_analog([14, 15, 59],[0, 15, 1], 12))
    assert_equal([17, 31, 0], set_clock_analog([15, 15, 59],[2, 15, 1], 24))
    
    #negative tests
    assert_raise(RuntimeError) {set_clock_analog([15,10],[3,5,10],24)}
    assert_raise(RuntimeError) {set_clock_analog([15,41,10],[3,5],24)}
    assert_raise(RuntimeError) {set_clock_analog([-15,41,10],[3,5,7],24)}
  end
end
