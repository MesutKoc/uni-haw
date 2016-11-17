$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'clock'

class UhrenartihmetikTest < Test::Unit::TestCase
  def test_set_clock
    #positive tests
    assert_equal([12,50,0],set_clock([10,20,0],[2,30,0]))
    assert_equal([22,1,0],set_clock([20,59,59],[1,1,1]))
    
    #negative tests
    assert_raise(RuntimeError) {set_clock([5,10],[3,5,10])}
    assert_raise(RuntimeError) {set_clock([10,5,0],[5,10])}
    assert_raise(RuntimeError) {set_clock([-5,30,10],[3,5,10])}
  end
end
