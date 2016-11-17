# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'day_sum'

class DaySumTest < Test::Unit::TestCase
  def test_day
#day? tests:

      assert_equal(true,day?(2))
      assert_equal(true,day?(:SO))
      assert_equal(false,day?(:Hallo))
      assert_equal(false,day?(14))
      assert_equal(false,day?(14.0))
      
#day_sym? tests:

      assert_equal(true,day_sym?(:SO))
      assert_equal(true,day_sym?(:MO))
      assert_equal(false,day_sym?(1))
      assert_equal(false,day_sym?(:Hallo))
      assert_equal(false,day_sym?(14))
      assert_equal(false,day_sym?(14.0))
      
#day_num? tests:

      assert_equal(true,day_num?(2))
      assert_equal(false,day_num?(:MO))
      assert_equal(true,day_num?(1))
      assert_equal(false,day_num?(:Hallo))
      assert_equal(false,day_num?(14))
      assert_equal(false,day_num?(14.0))
      
#day_sym_to_day_num tests:

      assert_equal(2,day_sym_to_day_num(:DI))
      assert_equal(3,day_sym_to_day_num(:MI))
      assert_raise(RuntimeError){day_sym_to_day_num(8)}
      assert_raise(RuntimeError){day_sym_to_day_num(:Hallo)}
      assert_raise(RuntimeError){day_sym_to_day_num(5)}
      
#day_num_to_day_sym tests:

      assert_equal(:DI,day_num_to_day_sym(2))
      assert_equal(:DO,day_num_to_day_sym(4))
      assert_raise(RuntimeError){(day_num_to_day_sym(8))}
      assert_raise(RuntimeError){(day_num_to_day_sym(:Hallo))}
      assert_raise(RuntimeError){day_num_to_day_sym(:MO)}
    
#to_day_sym? tests:

      assert_equal(true,to_day_sym?(:DI))
      assert_equal(:MO,to_day_sym?(1))
      assert_equal(:DI,to_day_sym?(2))
      assert_equal(true,to_day_sym?(:MI))
      assert_raise(RuntimeError){to_day_sym?(:Hallo)}
      assert_raise(RuntimeError){to_day_sym?(0)}
      
#to_day_num? tests:

      assert_equal(true,to_day_num?(1))
      assert_equal(true,to_day_num?(2))
      assert_equal(2,to_day_num?(:DI))
      assert_equal(4,to_day_num?(:DO))
      assert_raise(RuntimeError){to_day_num?(:Hallo)}
      assert_raise(RuntimeError){to_day_num?(0)}
      
#day_num_succ tests:

      assert_equal(2,day_num_succ(1))
      assert_equal(3,day_num_succ(2))
      assert_equal(4,day_num_succ(3))
      assert_equal(1,day_num_succ(7))
      assert_raise(RuntimeError){day_num_succ(:Hallo)}
      assert_raise(RuntimeError){day_num_succ(8)}
      assert_raise(RuntimeError){day_num_succ(0)}
      
#day_num_pred tests:

      assert_equal(4,day_num_pred(5))
      assert_equal(1,day_num_pred(2))
      assert_equal(7,day_num_pred(1))
      assert_equal(2,day_num_pred(3))
      assert_raise(RuntimeError){day_num_pred(:Hallo)}
      assert_raise(RuntimeError){day_num_pred(8)}
      assert_raise(RuntimeError){day_num_pred(0)}
      
#day_sym_succ tests:

      assert_equal(:DI,day_sym_succ(:MO))
      assert_equal(:MO,day_sym_succ(:SO))
      assert_equal(:MI,day_sym_succ(:DI))
      assert_equal(:DO,day_sym_succ(:MI))
      assert_raise(RuntimeError){day_sym_succ(:Hallo)}
      assert_raise(RuntimeError){day_sym_succ(8)}
      assert_raise(RuntimeError){day_sym_succ(0)}
     
#day_sym_pred tests: 

      assert_equal(:DO,day_sym_pred(:FR))
      assert_equal(:SO,day_sym_pred(:MO))
      assert_equal(:MO,day_sym_pred(:DI))
      assert_raise(RuntimeError){day_sym_pred(:Hallo)}
      assert_raise(RuntimeError){day_sym_pred(8)}
      assert_raise(RuntimeError){day_sym_pred(0)}
      
#day_succ tests:

      assert_equal(1,day_succ(7))
      assert_equal(:MO,day_succ(:SO))
      assert_equal(2,day_succ(1))
      assert_equal(:DO,day_succ(:MI))
      assert_raise(RuntimeError){day_succ(:Hallo)}
      assert_raise(RuntimeError){day_succ(8)}
      assert_raise(RuntimeError){day_succ(0)}
      
#day_pred tests:

      assert_equal(2,day_pred(3))
      assert_equal(:SO,day_pred(:MO))
      assert_equal(7,day_pred(1))
      assert_equal(:DI,day_pred(:MI))
      assert_raise(RuntimeError){day_pred(:Hallo)}
      assert_raise(RuntimeError){day_pred(8)}
      assert_raise(RuntimeError){day_pred(0)}
  end
end
