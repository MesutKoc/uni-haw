# Testfälle für UngeradeZahl
# Author:: Bernd Kahlbrandt
require 'test/unit'
require_relative 'ungerade_zahl'

class TestUngeradeZahl < Test::Unit::TestCase
  MAX_FIXNUM = 2**30-1
  MIN_FIXNUM = -MAX_FIXNUM - 1
  def setup
  end

  def test_initialize
    assert_equal(1,UngeradeZahl.new(1).instance_variables.length)
    assert_equal(1,UngeradeZahl.new(-1).instance_variables.length)
    assert_equal(UngeradeZahl.new(1),UngeradeZahl.new(1))
    assert_equal(UngeradeZahl.new(-1),UngeradeZahl.new(-1))
    assert_raise(ArgumentError){UngeradeZahl.new(0)}
    assert_raise(ArgumentError){UngeradeZahl.new(2)}
    assert_raise(ArgumentError){UngeradeZahl.new(Math::PI)}
    assert(MAX_FIXNUM.class.is_a?(Fixnum.class))
    assert(MIN_FIXNUM.class.is_a?(Fixnum.class))
  end

  def test_spaceship
    assert_equal(1,UngeradeZahl.new(11)<=>UngeradeZahl.new(7))
    assert_equal(-1,UngeradeZahl.new(-7)<=>UngeradeZahl.new(11))
    assert_equal(0,UngeradeZahl.new(11)<=>UngeradeZahl.new(11))
  end

  def test_succ
    assert_equal(UngeradeZahl.new(1),UngeradeZahl.new(-1).succ)
    assert_equal(UngeradeZahl.new(MAX_FIXNUM+2),UngeradeZahl.new(MAX_FIXNUM).succ)
    assert_equal(UngeradeZahl.new(MIN_FIXNUM + 3),UngeradeZahl.new(MIN_FIXNUM + 1).succ)
  end

  def test_range
    range = UngeradeZahl.new(-1)..UngeradeZahl.new(3)
    assert_equal(2,(UngeradeZahl.new(-1)...UngeradeZahl.new(3)).count)
    assert_equal(3,range.count)
    assert_equal(UngeradeZahl.new(-1),range.min)
    assert_equal(UngeradeZahl.new(3),range.max)
    assert((UngeradeZahl.new(-1)...UngeradeZahl.new(3)).include?(UngeradeZahl.new(1)))
    refute((UngeradeZahl.new(MIN_FIXNUM+1)...UngeradeZahl.new(MIN_FIXNUM+3)).include?(UngeradeZahl.new(43)))
  end
  def test_to_s
    assert(UngeradeZahl.instance_methods(false).include?(:to_s),"Sie haben vergessen, to_s zu überschreiben!")
  end
  end