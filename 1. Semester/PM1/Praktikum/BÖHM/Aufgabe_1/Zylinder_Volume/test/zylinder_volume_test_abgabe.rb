
$:.unshift File.join(File.dirname(__FILE__),'..','lib')
# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.

require 'test/unit';
require 'zylinder_volume_abgabe';

class ZylinderVolumeTest < Test::Unit::TestCase;
  DELTA=0.00001;
  def test_zylinder_volume
 
    # positive tests

    assert_in_delta(100.53096,zylinder_volume(4.0,2.0),DELTA)
    assert_in_delta(56.54867,zylinder_volume(3.0,2.0),DELTA)
    assert_in_delta(12.56637,zylinder_volume(2.0,1.0),DELTA)

    # test illegal types

    assert_raise(RuntimeError){zylinder_volume("a","b")}
    assert_raise(RuntimeError){zylinder_volume(2,4)}

    # test illegal values of legal types

    assert_raise(RuntimeError){zylinder_volume(-2,1)}
    assert_raise(RuntimeError){zylinder_volume(2,-1)}
  end
end
