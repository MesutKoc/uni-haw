class Queue
  # Default-Konstrutor
  # initialize ::= ø -> Queue
  def initialize
    @items = Array.new
  end
  # Zum einfügen in die Queue
  # enqueue(item) ::= item -> Queue  
  def enqueue(item)
    @items.push(item)
    self
  end
  
  # Zum löschen von einer Queue  
  # dequeue ::= ø -> Queue
  # pre: Queue darf nicht leer sein
  def dequeue
    return nil if @items.empty?
    @items.shift
  end
  
  def peek
    return nil if @items.empty?
    return @items.first
  end
     
  def to_s
    @items.each {|elem| puts elem}
  end   
end

class Queue1
  def initialize
    @instack = []
    @outstack = []
  end
  
  def enqueue(item)
    @instack.push(item)
    return item
  end
  
  def dequeue
    shift if @outstack.empty?
    @outstack.pop
  end
end