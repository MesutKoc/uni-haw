class Stack
  include Enumerable
  
  attr_reader :stack
  # Erzeugt unsere gewünschte Datenstruktur, hier in dem Fall ein Stack
  # mittels Array
  # Stack#initialize ::= ø -> Stack
  def initialize
    @stack = []
  end
  
  # Fügt ein Element auf den Stapel hinzu
  # Stack#push(elem) ::= elem -> Stack
  # Example:
  def push(elem)
    raise ArgumentError, 'Fehler: Sie dürfen keine Nil-Elemente hinzufügen' if elem.nil?
    @stack << elem
  end
  
  # Gibt das Element aus dem letzten Stapel zurück und löscht es
  # Stack#pop ::= ø -> Stack
  # Example: [1,2,3].pop =>  [1,2]
  #          [ ].pop     => nil
  def pop
    check_precondition()
    @stack.pop
  end
  
  # Gibt das letzte Element ohne löschen aus
  # Stack#peek ::= ø -> Element
  # Example: [1,2,3].peek => 3
  #          [ ].peek     => nil
  def peek
    check_precondition()
    @stack.last
  end
  
  # Wir gehen hier ein Vertrag mit Enumerable ein
  # und implementieren den Basisiterator für unsere Klasse
  # Example: newstack.each {|elem| puts elem}
  def each
    ((block_given?) ? @stack.each {|var| yield(var) } : @stack.each)
  end
  
  # Konventiert die Inhalte zu einem Array
  def to_a
    [self.stack]
  end
  
  # Überprüfung auf Gleichheit
  # Stack#==(other) ::== Any -> Boolean
  # Example: [1,2].==([1,2]) => true
  #          [1,2].==([1,3]) => false
  def ==(other)
    self.equal?(other) or (other.is_a?(self.class) and (self.to_a == other.to_a))
  end
  
  # Ordnungsrelations
  # Stack#<=>(other) ::= Any -> {1,-1,0}
  def <=>(other)
    return false if other.nil? || !other.is_a?(self.class)
    return true if self.equal?(other)
    return self.to_a <=> other.to_a
  end
  
  # Die Methode überprüft, ob der Staack leer ist oder nicht
  # Stack#empty? ::= Stack -> Boolean
  # Example:  [ ].empty?  => true
  #          [1,2].empty? => false
  def empty?
    @stack.empty?
  end
  
  # Single-Point of Control
  # Da wir beispielsweise bei pop und peek immer dasselbe Abfragen
  # und duplizierter Sourcecode nicht gewünscht ist, habe ich diese zur SPC-Methode umgeändert
  # Stack#check_precondition ::= Stack -> Boolean
  # Example: [1,2].check_precondition => true
  #          [ ].check_precondition   => nil
  def check_precondition
    return nil if @stack.empty?
  end
end

def max_key(a_hash)
  return a_hash.max()[0]
end

def max_value(a_hash)
  return a_hash.values.max
end

def count_max_values(a_hash)
  return a_hash.values.count(max_value(a_hash))
end

def key_value_sum_even(a_hash)
  return a_hash.select { |key, value| (key + value) % 2 == 0 }
end

h = {:a=> 7, :b=> 11, :c=> 6, :d=> 11, :e=> 11, :f => 9}
puts count_max_values({:a=> 7, :b=> 11, :c=> 6, :d=> 11, :e=> 11, :f => 9})

puts "max key " + max_key(h)
puts max_value(h)

h = {3=> 7, 4=> 11, 2=> 6, 5=> 11, 6=> 11, 8 => 9}

puts key_value_sum_even({3=> 7, 4=> 11, 2=> 6, 5=> 11, 6=> 11, 8 => 9})


def truth_tables
  for zeile in 0 .. 3
    for spalte in 0 .. 3
      if zeile < 2 && spalte < 2
        # erster Quadrant = AND
        #print 'a'
        if zeile % 2 == 1 && spalte % 2 == 1
          print '1'
        else
          print '0'
        end
      elsif zeile < 2 && spalte > 1
        # zweiter Quadrant = OR
        if zeile % 2 == 1 || spalte % 2 == 1
          print '1'
        else
          print '0'
        end
      elsif zeile > 1 && spalte < 2
        # dritter Quadrant = NAND
        if !(zeile % 2 == 1 && spalte % 2 == 1)
          print '1'
        else
          print '0'
        end
      else
        # vierter Quadrant = NOR
        if !(zeile % 2 == 1 || spalte % 2 == 1)
          print '1'
        else
          print '0'
        end
      end
    end
    puts ''
  end
end