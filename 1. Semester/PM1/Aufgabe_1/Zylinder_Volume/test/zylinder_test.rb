$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'zylinder_volume'

# Purpose:    Eine Funktion, die das Volumen eines Zylinders berechnet zu
#             schreiben.
#             Die Argumente und das Resultat sollen Gleitkommazahlen sein.
# Contrackt:  value_zylinder::=(radius,hoehe)
#             :: Float x Float -> Float where((radius, hoehe) >=0)
# Examples:   Test{
#             (3.0,2.0) => 56.54867
#             (4.0,2.0) => 100.53096
#             (2.0,1.0) => 12.56637
#              }

class ZylinderTest < Test::Unit::TestCase

  # tests

  DELTA=0.00001

  def test_value_zylinder

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
