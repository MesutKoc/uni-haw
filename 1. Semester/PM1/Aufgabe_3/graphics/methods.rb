$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Extension/lib')
$LOAD_PATH.unshift File.join(File.dirname(__FILE__),'../..','Point/lib')
#===========INCLUDES:=============
require 'ext_pr1_v4'

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

  min = min(lower, upper)
  max = max(lower, upper)

  (val >= min) ? (val <= max) : false
end


def bounding_range(r1, r2)
  x_range_min = min(r1.first.x, r2.first.x)
  x_range_max = max(r1.last.x, r2.last.x)
  min = min(x_range_min, x_range_max)
  
  y_range_min = min(r1.first.x, r2.first.x)
  y_range_max = max(r1.last.x, r2.last.x)
  max = max(y_range_min, y_range_max)
  
  if    (r1.range1d?)    then Range1d[Point1d[min], Point1d[max]]
  elsif (r1.range2d?)    then new_x_range = Range1d[r1.x_range, r2.x_range]
                              new_y_range = Range1d[r1.y_range, r2.y_range]
                              Range2d[new_x_range, new_y_range]
  else check_pre(false)
  end
end
