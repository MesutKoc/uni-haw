# Aufgabe 1
# sum(x,n) ::= Fixnum x Integer -> Fixnum
def sum(x,n)
  raise ArgumentError, '' if x.integer? && x < 0.5
  sum = 0
  for i in 1 .. n
    sum += ((x-1.0)**i) / (i*(x**i))
  end
  return sum
end

# Aufgabe 1.2
class Array
  def rek_count_type(type)
    count = 0
    self.each {|elem| 
      count +=1 if elem.is_a?(type)
      count += elem.rek_count_type(type) if elem.is_a?(type) 
    } 
    return count
  end
  
  def array_min_2_elems
      counter = 0
      counter += 1 if self.length > 1
      self.each {|elem| 
        counter += elem.array_min_2_elems if elem.is_a?(Array) 
      }
      return counter
    end
    
    
  
end

# Aufgae 1.3
@bestell_hash = {"knr1" => [12,12,12.78,12],
                 "knr2" => [24,56,788.9, 30],
                 "knr3"=>[1,1,1,1,4] }
                 
@h1 = {"a" => 100, "b" => 200, "c" => 300}
@h2 = {"a" => 100, "b" => 100, "d" => 300}

def max_bestellwert(ein_hash)
  return ein_hash.key(ein_hash.values.max)
end

class Hash
def equal_entries?(other)
  retn = {}
  self.each {|key,elem| retn[key] = elem if other.has_key?(key) and other.has_value?(elem) }
  retn
end
end

def test
  puts "Geben sie was ein; "
while c = gets.downcase.chomp and c != 'q'
    puts "Looser"
  end
end

puts max_bestellwert(@bestell_hash)
puts @h1.equal_entries?(@h2)