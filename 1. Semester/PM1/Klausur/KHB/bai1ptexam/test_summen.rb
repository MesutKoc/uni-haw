# Testfälle für summen.rb
# Author:: Bernd Kahlbrandt
methods_at_start = public_methods
require_relative 'summen'
$methods_to_test =   public_methods - methods_at_start
require 'test/unit'

class TestSummen < Test::Unit::TestCase
  def setup
    @test_empty = []
    @test1 = [1]
    @test_not_enum = 42
    @test_not_num = ["a","b","c"]
    @test_int = [1, 2, 3, 4, 5]
    @test_float = [1.2, 2.3, 4.5, 6.7]
    @test_mixed = [1, 3.1415]
  end

  def test_all
    $methods_to_test.each{|meth| test(meth)}
  end

  def test(meth)
    assert_equal(summe(@test_empty),send(meth,@test_empty),meth.to_s)
    assert_equal(summe(@test1),send(meth,@test1),meth.to_s)
    assert_equal(summe(@test_int),send(meth,@test_int),meth.to_s)
    assert_equal(summe(@test_float),send(meth,@test_float),meth.to_s)
    assert_equal(summe(@test_mixed),send(meth,@test_mixed),meth.to_s)
    assert_raise(ArgumentError, meth.to_s) {send(meth,@test_not_enum)}
    assert_raise(ArgumentError, meth.to_s) {send(meth,@test_not_num)}
  end
  #Referenzimplementierung
  private

  def summe(num)
    num.reduce(0,:+)
  end
end