$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'bounds'
require 'equals'
require 'include'
require 'methods'
require 'translate'
require 'types'

class TestAll < Test::Unit::TestCase
  def test_translate
    assert_equal(Range1d[2,3], translate(Range1d[1,2],1))
    assert_equal(Range1d[3,4], translate(Range1d[2,3],1))
    assert_equal(Range1d[1,2], translate(Range1d[0,1],1))
    assert_equal(Range1d[5,6], translate(Range1d[4,5],1))
    assert_equal(Range1d[2,2], translate(Range1d[1,1],1))
    assert_equal(Range1d[5,5], translate(Range1d[4,4],1))
    assert_equal(Range1d[7,9], translate(Range1d[6,8],1))
    assert_equal(Range1d[4,4], translate(Range1d[2,2],2))
    assert_equal(Range1d[3,4], translate(Range1d[2,3],1))
    assert_equal(Range1d[7,7], translate(Range1d[5,5],2))
    assert_equal(Range1d[6,6], translate(Range1d[4,4],2))
    assert_equal(Range1d[5,2], translate(Range1d[3,0],2))
    assert_equal(Range1d[6,6], translate(Range1d[0,0],6))
    assert_raise(RuntimeError){translate(Range1d['a','5'],'1')}
  end
  
  def test_equal_by_dim
    assert_equal(true, equal_by_dim?(Range1d[1,2],Range1d[2,1]))
    assert_equal(true, equal_by_dim?(Range1d[2,2],Range1d[2,2]))
    assert_equal(true, equal_by_dim?(Range1d[1,2],Range1d[4,4]))
    assert_equal(true, equal_by_dim?(Range1d[5,6],Range1d[2,1]))
    assert_equal(true, equal_by_dim?(Range1d[9,2],Range1d[1,1]))
    assert_equal(true, equal_by_dim?(Range1d[1,5],Range1d[6,1]))
    assert_equal(false,equal_by_dim?(Range1d[1,2],Range2d[Range1d[2,1],Range1d[2,1]]))
    assert_raise(RuntimeError){equal_by_dim?(Range1d['a','5'],Range1d['4','1'])}
  end
  
  def test_equal_by_trans
    assert_equal(true, equal_by_trans?(1,1))
    assert_equal(true, equal_by_trans?(1,2))
    assert_equal(0, equal_by_trans?(Range1d[1,2],Range1d[1,2]))
    assert_equal(1, equal_by_trans?(Range1d[2,2],Range1d[1,2]))
    assert_equal(4, equal_by_trans?(Range1d[5,2],Range1d[1,2]))
    assert_equal(5, equal_by_trans?(Range1d[6,2],Range1d[1,2]))
    assert_equal(true, equal_by_trans?(Range2d[Range1d[0,2], Range1d[1,2]],Range2d[Range1d[0,2], Range1d[1,2]]))
    assert_equal(false, equal_by_trans?(Range2d[Range1d[1,2], Range1d[1,2]],Range2d[Range1d[0,2], Range1d[1,2]]))
  end
  
  def test_equal_by_tree
    assert_equal(true, equal_by_tree?(Range1d[1,2], Range1d[1,2]))
    assert_equal(false,equal_by_tree?(Range1d[1,2], Range1d[2,2]))
    assert_equal(true, equal_by_tree?(Range1d[3,3], Range1d[3,3]))
    assert_equal(false,equal_by_tree?(Range1d[5,6], Range1d[1,2]))
    assert_equal(true, equal_by_tree?(Range1d[1,2], Range1d[1,2]))
    assert_equal(true, equal_by_tree?(Range1d[1,2], Range1d[1,2]))
    assert_equal(true, equal_by_tree?(Range2d[Range1d[1, 2], Range1d[1, 2]],Range2d[Range1d[1, 2], Range1d[1, 2]]))
    assert_equal(false,equal_by_tree?(Range2d[Range1d[1, 2], Range1d[6, 2]],Range2d[Range1d[1, 2], Range1d[1, 2]]))
    assert_equal(false,equal_by_tree?(Range2d[Range1d[0, 2], Range1d[1, 2]],Range2d[Range1d[1, 2], Range1d[1, 2]]))
    assert_equal(true, equal_by_tree?(Range2d[Range1d[0, 2], Range1d[1, 2]],Range2d[Range1d[0, 2], Range1d[1, 2]]))
    assert_raise(RuntimeError) {equal_by_tree?(Range2d[Range1d['0', '2'], Range1d['1', '2']],Range2d[Range1d['0', '2'], Range1d['1', '2']])}
  end
  
  def test_bounds
    assert_equal(Range1d[1,2], bounds(Range1d[1,2]))
    assert_equal(Range1d[1,5], bounds(Union1d[Range1d[1,5], Range1d[2,3]]))
    assert_equal(Range1d[1,4], bounds(Union1d[Range1d[1,2], Range1d[2,4]]))
    assert_equal(Range1d[1,8], bounds(Union1d[Range1d[1,2], Range1d[2,8]]))
    assert_equal(Range1d[2,5], bounds(Union1d[Range1d[2,3], Range1d[4,5]]))
    assert_equal(Range1d[2,6], bounds(Union1d[Range1d[5,6], Range1d[2,3]]))
    assert_equal(Range1d[2,2], bounds(Union1d[Range1d[2,2], Range1d[2,2]]))
    assert_equal(Range1d[1,1], bounds(Union1d[Range1d[1,1], Range1d[1,1]]))
    assert_equal(Range1d[2,4], bounds(Union1d[Range1d[2,3], Range1d[3,4]]))
    assert_equal(Range1d[0,0], bounds(Union1d[Range1d[0,0], Range1d[0,0]]))
    assert_equal(Range1d[1,3], bounds(Union1d[Range1d[1,2], Range1d[2,3]]))
    assert_equal(Range1d[1,3], bounds(Union1d[Range1d[1,2], Range1d[2,3]]))
    assert_equal(Range1d[1,3], bounds(Union1d[Range1d[1,2], Range1d[2,3]]))
    assert_equal(Range1d[1,3], bounds(Union1d[Range1d[1,2], Range1d[2,3]]))
    assert_raise(RuntimeError){bounds(Range1d['a','5'])}
  end
  
  def test_include
    assert_equal(true, include?(Range1d[1,3],2))
    assert_equal(true, include?(Range1d[1,3],1))
    assert_equal(false,include?(Range1d[1,3],4))
    assert_equal(false,include?(Range1d[1,4],5))
    assert_equal(true, include?(Range1d[1,4],3))
    assert_equal(true, include?(Range1d[1,4],1))
    assert_equal(true, include?(Range1d[1,4],2))
    assert_equal(true, include?(Range1d[1,6],5))
    assert_equal(true, include?(Range1d[1,6],3))
    assert_equal(true, include?(Range1d[1,6],1))
    assert_equal(false,include?(Range1d[1,6],7))
    assert_equal(false,include?(Range1d[1,8],9))
    assert_equal(false,include?(Range1d[2,8],1))
    assert_equal(false,include?(Range1d[10,11],9))
    assert_equal(false,include?(Union1d[Range1d[1,2],Range1d[1,2]],3))
    assert_equal(true,include?(Union1d[Range1d[1,2],Range1d[1,2]],1))
    assert_raise(RuntimeError){include?(Range1d['a','5'],-1)}
  end
  
  def test_bounding_range
    assert_equal(Range1d[1,3], bounding_range(Range1d[1,2],Range1d[2,3]))
    assert_equal(Range1d[1,3], bounding_range(Range1d[1,3],Range1d[2,3]))
    assert_equal(Range1d[5,3], bounding_range(Range1d[5,2],Range1d[6,3]))
    assert_equal(Range1d[1,3], bounding_range(Range1d[1,3],Range1d[2,3]))
    assert_raise(RuntimeError){bounding_range(Range1d['a','5'],Range1d['a','5'])}
  end
  
  def test_dim
    assert_equal(1, dim?(1))
    assert_equal(true, dim?(Range1d[1,2]))
    assert_raise(RuntimeError){dim?(Range1d['a','5'])}
  end
end
