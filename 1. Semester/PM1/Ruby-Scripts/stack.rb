# Ein simpler Stack
# Author:: Uwe Krause

require_relative 'empty_stack_error'

class Stack
  def initialize
    @stack = []
  end

  def push(arg)
    raise ArgumentError, "Nicht NIL!" if arg.nil?
    # Wenn der Methodenaufruf durch eine Exception abgebrochen wird, hat sie keinen Rückgabewert

    @stack.push(arg)

    # Rueckgabewert ist nicht das interne Array, sondern das Objekt der Stack Klasse!
    self
  end

  def pop
    raise EmptyStackError if self.empty?
    @stack.pop
  end

  def empty?
    @stack.empty?
  end

  def to_s
    @stack.to_s
  end

end

#s = Stack.new
#s.push "a"
#s.push "b"
#p s.pop
#s.push "c"
#p s.pop
#p s.pop
#p s.pop
#puts s
#