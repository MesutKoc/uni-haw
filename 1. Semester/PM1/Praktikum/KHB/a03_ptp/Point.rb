class Point
  def initialize(x,y)
    @x = x
    @y = y
  end
  
  def x=(val)
    @x=val
  end
  
  def y=(val)
    @y=val
  end
  
  def x
    return @x
  end
  def y
      return @y    
  end
  
  def + (point)
    return Point.new( @x+point.x, @y+point.y)
  end
  
  def to_s()
      return "Point(#@x,#@y)"    
  end
end