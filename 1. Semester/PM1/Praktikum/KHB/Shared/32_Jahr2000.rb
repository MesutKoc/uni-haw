## Script zur Aufgabe 2. Jahre 2000 bis 2100
# Author::Uwe Krause
# Author::Constanze Maaß

require_relative "palindrom"
pal = Palindrom.new

jahre = 2000..2100

jahre.each { |jahr|

  if pal.palindrom?(jahr.to_s(2))
    puts "#{jahr}: #{jahr.to_s(2)}"
  end
}
