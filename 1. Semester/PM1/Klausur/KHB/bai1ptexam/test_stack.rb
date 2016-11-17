# Testfälle für Stack
# Author:: Bernd Kahlbrandt
require 'test/unit'

require_relative 'stack'
require_relative 'empty_stack_error'

class TestStack < Test::Unit::TestCase
  def setup
    @stack_num = Stack.new
    @stack_string = Stack.new
    @stack_mix = Stack.new
  end

  def test_initialize
    @stack_num = Stack.new()
    assert(@stack_num.empty?())
  end

  def test_push
    assert_raise(ArgumentError,"Sie haben versucht nil einzufügen!") {@stack_num.push(nil)}
    refute(@stack_num.push(1).empty?)
  end

  def test_pop
    assert_raise(EmptyStackError){@stack_mix.pop}
    @stack_num.push(1)
    assert_equal(1,@stack_num.pop)
    @stack_mix.push(42).push("08/15")
    assert_equal("08/15",@stack_mix.pop)
  end

  def test_empty
    assert(Stack.new().empty?)
    refute(@stack_num.push(1).empty?)
  end
end