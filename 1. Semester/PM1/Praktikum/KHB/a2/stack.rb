class Stack
  def initialize
    @new_stack = [1,2,3,4]
  end

  def push(element)
    @new_stack << element
    @new_stack
  end

  def pop
    last_element = @new_stack.last(1)
    @new_stack.reverse!()
    @new_stack.delete_at(0)
    @new_stack.reverse!()
    last_element
  end

  def peek
    last_element = @new_stack.last(1)
    last_element
  end

  def to_s
    @new_stack.to_s
  end

end

test = Stack.new
test.push(gets.chomp)
