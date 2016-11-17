## Script zur Aufgabe Aufgabe 3. Strings aaaa bis zzzz
# Author::Uwe Krause
# Author::Constanze Maaß

require_relative "palindrom"
pal = Palindrom.new

file = File.new("palindrom.txt", "w")
# w write only, truncates file or creates new one

("aaaa".."zzzz").each { |string|
  if pal.palindrom?(string)
    file.puts(string)
  end
}

file.close()
