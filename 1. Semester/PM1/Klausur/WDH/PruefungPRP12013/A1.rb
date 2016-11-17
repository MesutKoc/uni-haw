# Die Aufgabe 1 der Klausur SS13
# Author:: Mesut Koc

# Die Funktion f_1_tel der vorgebenen Formel iterativ gelöst
# f_1_tel(n) ::= Fixnum -> Fixnum
def f_1_tel(n)
  return false if !n.integer? || n < 1
  sum = 0
  for i in 1 .. n
    sum += 1.0 / (i * (i+1.0)*(i+2.0))
  end
  return sum
end

# Die Funktion f_1_tel der vorgebenen Formel iterativ (anders) gelöst
# f_1_tel(n) ::= Fixnum -> Fixnum
def f_1_tel_2(n)
  return false if !n.integer? || n < 1
  (1..n).inject(0) {|sum, i| sum+ (1.0 / (i * (i+1.0)*(i+2.0))) }
end

# Die Funktion f_1_tel der vorgebenen Formel REKURSIV gelöst
# f_1_tel(n) ::= Fixnum -> Fixnum
def f_1_tel_3(n)
  f_1_tel_(n,0)
end

def f_1_tel_(n,accu)
  return false if !n.integer?
  (n <= 0) ? accu : f_1_tel_(n-1,(1.0 / (n * (n+1.0)*(n+2.0))) + accu)
end

# Aufgabe 2
class Array
  
  # Die Funktion überprüft zwei Elemente eines Arrays
  # array_min_2_elems ::= Array -> Integer
  def array_min_2_elems
    counter = 0
    counter += 1 if self.length > 1
    self.each {|elem| 
      counter += elem.array_min_2_elems if elem.is_a?(Array) 
    }
    return counter
  end
end

# Aufgabe Hash
require "Set"
h1 = {:a1 => Set.new([:p1,:p2,:p3,:p4]), 
      :a2 => Set.new([:p1,:p4,:p5,:p6]), 
      :a3 => Set.new([:p1,:p2,:p3]) }
        
def organisiere_nach_wert(other)
  new_hash = Hash.new
  other.values.each {|set| set.each {|elem| new_hash[elem] = Set.new if !new_hash.key?(elem) } }
  other.each { |pair| pair[1].each { |val| new_hash[val].add(pair[0])}}
  return new_hash
end

def schluessel_pro_wert(a_hash)
  new_hash = Hash.new
  organisiere_nach_wert(a_hash).each { |pair| new_hash[pair[0]] = pair[1].length }
  return new_hash
end