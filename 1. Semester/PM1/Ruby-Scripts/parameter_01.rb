# Diese Skript zeigt einige Möglichkeiten der Parameterübergabe in Ruby.
# Author:: Bernd Kahlbrandt
# Diese Methode tut nichts. Ihr Rückgabewert ist nil.
def dummy
end
def no_default_parms(a, b)
  if(a.is_a?(Numeric) && b.is_a?(Numeric))
    a + b
  else
    raise ArgumentError, "Argument nicht Numerisch!"
  end
end

def first_parm_default(a = 11, b)##Ich vermute, dieser Fehler kommt wegen der Einstellung -K, die ich noch nicht entfernen konnte.
  if(a.is_a?(Numeric) && b.is_a?(Numeric))
    a + b
  end
end

def second_parm_default(a, b = 42)
  if(a.is_a?(Numeric) && b.is_a?(Numeric))
    a + b
  end
end
def two_parms_default(a = 11, b = 42)
  if(a.is_a?(Numeric) && b.is_a?(Numeric))
    a + b
  end
end
def one_variable_parm(*a)
  a.each{|n| raise ArgumentError unless n.is_a? Numeric}
  a.reduce(0, :+)
end
def last_parm_variable(a, *b)
  raise ArgumentError, "Parameter nicht numerisch!" unless a.is_a? Numeric 
  b.each{|n| raise ArgumentError unless n.is_a? Numeric}
    a + b.reduce(0, :+)  
end
def middle_parm_variable(a, *b, c)
  raise ArgumentError, "Parameter nicht numerisch!" unless(a.is_a?(Numeric) && c.is_a?(Numeric)) 
  b.each{|n| raise ArgumentError unless n.is_a? Numeric}
    a + b.reduce(0, :+) + c 
end
def mixed_up_parms_01(a, *b, c)
  raise ArgumentError, "Parameter nicht numerisch!" unless(a.is_a?(Numeric) && c.is_a?(Numeric)) 
  b.each{|n| raise ArgumentError unless n.is_a? Numeric}
    a + b.reduce(0, :+) + c 
end
def priority_of_parms_01(a, *b, c)
  raise ArgumentError, "Parameter nicht numerisch!" unless(a.is_a?(Numeric) && c.is_a?(Numeric)) 
  puts "Aktuelle Parameter"
  p a, b, c
  a + b.reduce(0, :+) + c 
end
