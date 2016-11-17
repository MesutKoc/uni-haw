$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Aufgabe_3/lib')

#===========INCLUDES:=============
require 'ext_pr1_v4'
require 'graphics_main'
#=================================

def min(val1, val2)
  check_pre((val1.numeric? and val2.numeric?))
  (val1 < val2) ? val1 : val2
end

def max(val1, val2)
  check_pre((val1.numeric? and val2.numeric?))
  (val1 > val2) ? val1 : val2
end

def within?(val, lower, upper)
  check_pre((val.numeric? and lower.numeric? and upper.numeric?))
  (val >= min(lower, upper)) ? (val <= max(lower, upper)) : false
end

# bounding_range gets the smallets range between both ranges
# bounding_range ::= (in_range, in_range2) :: (Range1d x Range1d -> Range1d) | (Range2d x Range2d -> Range2d) 
# Test: {...}
def bounding_range(range1, range2)
  if    range1.range1d?  then Range1d[min(range1.first,range2.first), max(range1.last,range2.last)]
  elsif range1.range2d?  then Range2d[Range1d[min(range1.x_range,range2.x_range), max(range1.x_range,ra4gne2.x_range)],
                                      Range1d[min(range1.y_range,range2.y_range), 
                                              max(range1.y_range,range2.y_range)]]
  else check_pre(false)
  end
end