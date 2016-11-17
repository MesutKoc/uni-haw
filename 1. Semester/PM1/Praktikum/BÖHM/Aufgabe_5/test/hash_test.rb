# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'hash'

class HashTest < Test::Unit::TestCase
  def test_foo
    hash_1 = {:a=>1,"b"=>2}; 
    hash_2 = {"b"=>2,:c=>2}; 
    
    assert_equal( {:a=>1,"b"=>4,:c=>2}, hash_1.add_counts(hash_2))
    assert_equal({:a=>1,"b"=>4,:c=>2}, hash_2.add_counts(hash_1))
    assert_equal({:a=>1,"b"=>4,:c=>2}, hash_2.add_counts(hash_1))
  end
end
